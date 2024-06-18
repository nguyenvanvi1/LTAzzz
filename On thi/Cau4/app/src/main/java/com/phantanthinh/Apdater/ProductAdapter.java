package com.phantanthinh.Apdater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phantanthinh.Model.Product;
import com.phantanthinh.cau4.Cau2;
import com.phantanthinh.cau4.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    Cau2 context;
    int item_layout;
    List<Product> products;

    public ProductAdapter(Cau2 context, int item_layout, List<Product> products) {
        this.context = context;
        this.item_layout = item_layout;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
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
        Product p = products.get(position);
        holder.txtMa.setText(p.getProductCode());
        holder.txtTen.setText(p.getProductName());
        holder.txtGia.setText(String.valueOf(p.getProductPrice()));
        return convertView;
    }
    public static class ViewHolder{
        TextView txtMa,txtTen,txtGia;
    }
}
