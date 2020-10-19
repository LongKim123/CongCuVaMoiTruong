package com.example.appbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.R;
import com.example.appbanhang.activity.Order_detail;
import com.example.appbanhang.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    public ArrayList<Order> arrayorders;

    public OrderAdapter(Context context, ArrayList<Order> arrayorders) {
        this.context = context;
        this.arrayorders = arrayorders;
    }

    @Override
    public int getCount() {
        return arrayorders.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayorders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder= new  OrderAdapter.ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_order,null);
            viewHolder.orderIdTv= view.findViewById(R.id.orderIdTv);
            viewHolder.amountTv=view.findViewById(R.id.amountTv);
            viewHolder.emailTv=view.findViewById(R.id.emailTv);
            viewHolder.statusTv=view.findViewById(R.id.statusTv);
            viewHolder.orderDateTv=view.findViewById(R.id.orderDateTv);
            viewHolder.btn_detail=view.findViewById(R.id.btn_detail);
            view.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Order order=(Order) getItem(i);
        viewHolder.orderIdTv.setText("Mã Đơn Hàng:"+order.getOrderId()+ "");
        viewHolder.orderDateTv.setText("Ngày Giờ Đặt "+order.getDate());
        viewHolder.statusTv.setText(order.getStatus());
        viewHolder.emailTv.setText("Địa Chỉ:"+order.getOrdermail());
        viewHolder.amountTv.setText("Tổng tiền:"+order.getAmount());
        if(order.getStatus().equals("Hoàn Thành"))
        {
            viewHolder.statusTv.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        else
        {
            viewHolder.statusTv.setTextColor(context.getResources().getColor(R.color.colorRed));
        }
        viewHolder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Toast.makeText(context,String.valueOf("Chi tiết đơn hàng "+order.getOrderId()),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Order_detail.class);
                intent.putExtra("id",order);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent );
            }
        });

        return view;
    }
    public class ViewHolder{
        public TextView orderIdTv,orderDateTv,emailTv,amountTv,statusTv;
        public Button btn_detail;


    }

//    class HolderOrder extends RecyclerView.ViewHolder{
//        //public TextView orderIdTv,orderDateTv,emailTv,amountTv,statusTv;
//
//        public HolderOrder(@NonNull View itemView) {
//            super(itemView);
//            orderIdTv=itemView.findViewById(R.id.orderIdTv);
//            orderDateTv=itemView.findViewById(R.id.orderDateTv);
//            emailTv=itemView.findViewById(R.id.emailTv);
//            amountTv=itemView.findViewById(R.id.amountTv);
//            statusTv=itemView.findViewById(R.id.statusTv);
//        }
    // }
}