package com.example.appbanhang.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.R;

public class Order extends AppCompatActivity {
    private ImageButton filterOrderBtn;
    private TextView filteredOrdersTv;
    RecyclerView ordersRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Anhxa();
    }

    private void Anhxa() {
        filteredOrdersTv=(TextView) findViewById(R.id.filteredOrdersTv);
        filterOrderBtn =(ImageButton) findViewById(R.id.filterOrderBtn);
        ordersRv=findViewById(R.id.ordersRv);
    }
}