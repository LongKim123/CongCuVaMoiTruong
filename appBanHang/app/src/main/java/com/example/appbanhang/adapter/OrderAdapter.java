package com.example.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.HolderOrder> {
    private Context context;
    public ArrayList<Order> orders;

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public HolderOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.dong_order,parent,false);
        return new HolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderOrder holder, int position) {
        Order modelOrder=orders.get(position);


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class HolderOrder extends RecyclerView.ViewHolder{
        private TextView orderIdTv,orderDateTv,emailTv,amountTv,statusTv;

        public HolderOrder(@NonNull View itemView) {
            super(itemView);
            orderIdTv=itemView.findViewById(R.id.orderIdTv);
            orderDateTv=itemView.findViewById(R.id.orderDateTv);
            emailTv=itemView.findViewById(R.id.emailTv);
            amountTv=itemView.findViewById(R.id.emailTv);
            statusTv=itemView.findViewById(R.id.statusTv);
        }
    }
}
