package com.example.appbanhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;
import com.example.appbanhang.adapter.Order_detailAdapter;
import com.example.appbanhang.model.Order;
import com.example.appbanhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order_detail extends AppCompatActivity {
    int id;
    ListView lvorder_detail;
    Order_detailAdapter order_detailAdapter;
    ArrayList<com.example.appbanhang.model.Order_detail> mangdetail;
    TextView id_ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Intent intent= getIntent();
        Order order1=(Order) intent.getSerializableExtra("id");
        AnhXa();
        id=order1.getOrderId();
//        id_ct.setText(String.valueOf(order1.getOrderId()));
        Load_Detail();
    }

    private void Load_Detail() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= Server.order_detail;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String TenSP="";
                String Gia="";
                String Soluong="";
                String Hinhanh="";
                if(response !=null && response.length() !=2){
                    try {
                        JSONArray jsonArray= new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            TenSP = jsonObject.getString("tensanpham");
                            Gia = jsonObject.getString("dongia");
                            Soluong = jsonObject.getString("soluong");
                            Hinhanh = jsonObject.getString("hinhanh");



                            mangdetail.add(new com.example.appbanhang.model.Order_detail(TenSP,Gia,Soluong,Hinhanh));
                            order_detailAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // mangloaisp.add(3,new Loaisp(0, "Thông tin", "http://kinhtevadubao.vn/uploads/images/news/1515687283_news_10383.jpg"));

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<>();
                param.put("id_donhang",String.valueOf(id));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void AnhXa() {
        //id_ct=findViewById(R.id.id_ct);
        lvorder_detail=(ListView) findViewById(R.id.lvOrder_detail);
        mangdetail= new ArrayList<>();
        order_detailAdapter=new Order_detailAdapter(getApplicationContext(),mangdetail);
        lvorder_detail.setAdapter(order_detailAdapter);

    }
}