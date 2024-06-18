package com.phantanthinh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.phantanthinh.Model.Sach;
import com.phantanthinh.cau5.Cau2;
import com.phantanthinh.cau5.R;

import java.util.List;

public class SachAdapter extends BaseAdapter {
    Cau2 context;
    int item_layout;
    List<Sach> Sachs;

    public SachAdapter(Cau2 context, int item_layout, List<Sach> sachs) {
        this.context = context;
        this.item_layout = item_layout;
        Sachs = sachs;
    }

    @Override
    public int getCount() {
        return Sachs.size();
    }

    @Override
    public Object getItem(int position) {
        return Sachs.get(position);
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
            holder.txtGia = convertView.findViewById(R.id.txtGia);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Sach p = Sachs.get(position);
        holder.txtMa.setText(p.getSCode());
        holder.txtTen.setText(p.getSName());
        holder.txtGia.setText(String.valueOf(p.getSPrice()));
        return convertView;
    }
    public static class ViewHolder{
        TextView txtMa,txtTen,txtGia;
    }
}
