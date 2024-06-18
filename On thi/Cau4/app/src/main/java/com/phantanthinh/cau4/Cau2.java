package com.phantanthinh.cau4;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.phantanthinh.Apdater.ProductAdapter;
import com.phantanthinh.Databases.ProductDB;
import com.phantanthinh.Model.Product;
import com.phantanthinh.cau4.databinding.ActivityCau2Binding;

import java.util.ArrayList;
import java.util.List;

public class Cau2 extends AppCompatActivity {
    ActivityCau2Binding binding;
    public static ProductDB db;
    ProductAdapter adapter;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCau2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prepareDb();
        loadData();
        addEvent();
    }

    private void addEvent() {
        binding.btnCau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cau2.this,Cau1.class);
                startActivity(intent);
            }
        });
        binding.lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product p = products.get(position);
                String sql = "SELECT productName FROM " + db.TBL_NAME + " WHERE productCode LIKE '" + p.getProductCode() + "%'";
                Cursor cursor = db.queryData(sql);
                if (cursor.moveToFirst()) {
                    String productName = cursor.getString(0);
                    Toast.makeText(Cau2.this, "Product Name: " + productName, Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });
        binding.lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(Cau2.this);
                dialog.setContentView(R.layout.dialog_add);
                EditText edtMa = dialog.findViewById(R.id.edtMa);
                EditText edtTen = dialog.findViewById(R.id.edtTen);
                EditText edtGia = dialog.findViewById(R.id.edtGia);
                Button btnSave = dialog.findViewById(R.id.btnSave);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<String> maList = new ArrayList<>();
                        String ma = edtMa.getText().toString();
                        String ten = edtTen.getText().toString();
                        double gia = Double.parseDouble(edtGia.getText().toString());
                        Cursor cursor = db.queryData("SELECT productCode FROM " + db.TBL_NAME);
                        while (cursor.moveToNext()){
                            maList.add(cursor.getString(0));
                        }
                        if(!maList.contains(ma)){
                            db.execSQL("INSERT INTO " + db.TBL_NAME + " VALUES('" +ma + "','"+ten +"',"+ gia +")");
                            loadData();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(Cau2.this, "Mã sản phẩm đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                        cursor.close();
                    }
                });
                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.show();
                return true;
            }
        });
    }

    private void prepareDb() {
        db = new ProductDB(Cau2.this);
        createSampleData();
    }

    private void createSampleData() {
        if(db.getNumberOfRows() == 0 ){
            try{
                db.execSQL("INSERT INTO "+ db.TBL_NAME + " VALUES('MH01','Heineken',19000)");
                db.execSQL("INSERT INTO "+ db.TBL_NAME + " VALUES('MH02','Tiger',18000)");
                db.execSQL("INSERT INTO "+ db.TBL_NAME + " VALUES('MH03','Sapporo',22000)");
                db.execSQL("INSERT INTO "+ db.TBL_NAME + " VALUES('MH04','Blanc 1664',24000)");
            }catch (Exception e){
                Log.e("Error:", e.getMessage().toString());
            }
        }
    }

    private void loadData() {
        adapter = new ProductAdapter(Cau2.this,R.layout.item_list,getDaFromDb());
        binding.lvProduct.setAdapter(adapter);
    }

    private List<Product> getDaFromDb() {
        products = new ArrayList<>();
        Cursor cursor = db.queryData("SELECT * FROM " + db.TBL_NAME);
        while (cursor.moveToNext()){
            products.add(new Product(cursor.getString(0),cursor.getString(1),cursor.getDouble(2)));
        }
        cursor.close();
        return products;
    }
}