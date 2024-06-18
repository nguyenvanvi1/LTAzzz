package com.phantanthinh.cau3;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.phantanthinh.Adapter.SinhVienAdapter;
import com.phantanthinh.Model.Databases;
import com.phantanthinh.Model.SinhVien;
import com.phantanthinh.cau3.databinding.ActivityCau2Binding;

import java.util.ArrayList;
import java.util.List;

public class Cau2 extends AppCompatActivity {
    ActivityCau2Binding binding;
    Databases db;
    SinhVienAdapter adapter;
    ArrayList<SinhVien> sinhViens;

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
        binding.lvSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(Cau2.this);
                dialog.setContentView(R.layout.dialog_add);
                EditText edtMa = dialog.findViewById(R.id.edtMa);
                EditText edtTen = dialog.findViewById(R.id.edtTen);
                EditText edtLop = dialog.findViewById(R.id.edtLop);
                Button btnSave = dialog.findViewById(R.id.btnSave);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<Integer> maList = new ArrayList<>();
                        String lop = edtLop.getText().toString();
                        String ten = edtTen.getText().toString();
                        int ma = Integer.parseInt(edtMa.getText().toString());
                        Cursor cursor = db.QueryData("SELECT SVCode FROM " + db.TBL_NAME);
                        while (cursor.moveToNext()){
                            maList.add(cursor.getInt(0));
                        }
                        if(!maList.contains(ma)){
                            db.execSQL("INSERT INTO " + db.TBL_NAME + " VALUES(" +ma + ",'"+ten +"','"+ lop +"')");
                            loadData();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(Cau2.this, "Mã sinh viên đã tồn tại", Toast.LENGTH_SHORT).show();
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

    private void loadData() {
        adapter = new SinhVienAdapter(Cau2.this,R.layout.item_list,getDaFromDb());
        binding.lvSinhVien.setAdapter(adapter);
    }

    private List<SinhVien> getDaFromDb() {
        sinhViens = new ArrayList<>();
        Cursor cursor = db.QueryData("SELECT * FROM " + db.TBL_NAME);
        while (cursor.moveToNext()){
            sinhViens.add(new SinhVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
        }
        cursor.close();
        return sinhViens;
    }

    private void prepareDb() {
        db = new Databases(this);
        db.createSampleData();
    }

}