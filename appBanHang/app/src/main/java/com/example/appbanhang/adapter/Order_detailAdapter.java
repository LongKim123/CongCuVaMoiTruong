package com.example.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Order_detail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Order_detailAdapter extends BaseAdapter {
    Context context;

    public Order_detailAdapter(Context context, ArrayList<Order_detail> array_detail) {
        this.context = context;
        this.array_detail = array_detail;
    }

    ArrayList<Order_detail> array_detail;
    @Override
    public int getCount() {
        return array_detail.size();
    }

    @Override
    public Object getItem(int i) {
        return array_detail.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        ImageView imgdetail;
        TextView tensanpham_detail ,soluong_detail, gia_detail;


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(viewHolder==null){
            viewHolder= new ViewHolder();
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.dong_order_detail,null);
            viewHolder.tensanpham_detail=(TextView) view.findViewById(R.id.tv_order_sanpham);
            viewHolder.gia_detail=(TextView) view.findViewById(R.id.tv_order_gia);

            viewHolder.soluong_detail=(TextView) view.findViewById(R.id.tv_order_soluong);
            viewHolder.imgdetail=(ImageView) view.findViewById(R.id.img_order);
            view.setTag(viewHolder);


        }
        else {
            viewHolder=(ViewHolder) view.getTag();
        }
        Order_detail order_detail=(Order_detail) getItem(i);
        viewHolder.tensanpham_detail.setText(order_detail.getDetail_ten());
        viewHolder.gia_detail.setText("Giá:"+order_detail.getDetail_gia());
        viewHolder.soluong_detail.setText("Số lượng :"+order_detail.getDetail_soluong());
        Picasso.with(context).load(order_detail.getDetail_hinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgdetail);

        return view;
    }
}
