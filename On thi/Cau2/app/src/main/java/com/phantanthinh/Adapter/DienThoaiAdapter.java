package com.phantanthinh.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.phantanthinh.Model.Databases;
import com.phantanthinh.Model.DienThoai;
import com.phantanthinh.cau2.Cau2;
import com.phantanthinh.cau2.R;

import java.util.List;

public class DienThoaiAdapter extends BaseAdapter {
    Cau2 context;
    int item_layout;
    List<DienThoai> dienThoais;

    public DienThoaiAdapter(Cau2 context, int item_layout, List<DienThoai> dienThoais) {
        this.context = context;
        this.item_layout = item_layout;
        this.dienThoais = dienThoais;
    }

    @Override
    public int getCount() {
        return dienThoais.size();
    }

    @Override
    public Object getItem(int position) {
        return dienThoais.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout,null);
            holder.txtTen = convertView.findViewById(R.id.txtTen);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        DienThoai p = dienThoais.get(position);
        holder.txtTen.setText(p.getName());
        holder.txtTen.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_detail);

                EditText edtMa = dialog.findViewById(R.id.edtMa);
                EditText edtTen = dialog.findViewById(R.id.edtTen);
                EditText edtGia = dialog.findViewById(R.id.edtGia);
                Button btnDelete = dialog.findViewById(R.id.btnDelete);
                Button btnCancel = dialog.findViewById(R.id.btnCancel);

                edtMa.setText(p.getId());
                edtTen.setText(p.getName());
                edtGia.setText(String.valueOf(p.getPrice()));

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.db.execSQL("DELETE FROM " + context.db.TBL_NAME + " WHERE ID = '" + p.getId() + "'");
                        context.loadData();
                        dialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.show();
                return true;
            }
        });
        return convertView;
    }

    public static class ViewHolder{
        TextView txtTen;
    }
}
