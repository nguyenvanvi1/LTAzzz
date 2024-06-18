package com.phantanthinh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phantanthinh.Model.SinhVien;
import com.phantanthinh.cau3.Cau2;
import com.phantanthinh.cau3.R;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    Cau2 context;
    int item_layout;
    List<SinhVien> sinhViens;

    public SinhVienAdapter(Cau2 context, int item_layout, List<SinhVien> sinhViens) {
        this.context = context;
        this.item_layout = item_layout;
        this.sinhViens = sinhViens;
    }

    @Override
    public int getCount() {
        return sinhViens.size();
    }

    @Override
    public Object getItem(int position) {
        return sinhViens.get(position);
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
            holder.txtMa = convertView.findViewById(R.id.txtMa);
            holder.txtTen = convertView.findViewById(R.id.txtTen);
            holder.txtLop = convertView.findViewById(R.id.txtLop);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        SinhVien p = sinhViens.get(position);
        holder.txtLop.setText(p.getSVClass());
        holder.txtTen.setText(p.getSVName());
        holder.txtMa.setText(String.valueOf(p.getSVCode()));
        return convertView;
    }
    public static class ViewHolder{
        TextView txtMa,txtTen,txtLop;
    }
}
