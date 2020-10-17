package com.example.appbanhang.activity;

import android.os.Bundle;
import android.widget.ImageButton;
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
import com.example.appbanhang.adapter.OrderAdapter;
import com.example.appbanhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order extends AppCompatActivity {
    ImageButton filterOrderBtn;
    static TextView mail_r,name_r;
    ArrayList<com.example.appbanhang.model.Order> mangdonhang;
     OrderAdapter orderAdapter;

    ListView lvdonhang;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        sessionManager = new SessionManager(this);
        Anhxa();
        HashMap<String,String> user=sessionManager.getUserDetail();

        String mname=user.get(sessionManager.NAME);
        String memail=user.get(sessionManager.EMAIL);
        mail_r.setText(memail);
        name_r.setText(mname);



        loadAllOrder();
    }
    private void loadAllOrder(){
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= Server.order;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int ID=0;
                String ngayDH="";
                String Tongtien="";
                String Trangthai="";
                String email="";
                if(response !=null && response.length() !=2){
                    try {
                        JSONArray jsonArray= new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            ID = jsonObject.getInt("id");
                            Tongtien = jsonObject.getString("total_price");
                            Trangthai = jsonObject.getString("status");
                            email = jsonObject.getString("address");
                            ngayDH=jsonObject.getString("NgayDH");


                            mangdonhang.add(new com.example.appbanhang.model.Order(ID,Trangthai,ngayDH,Tongtien,email));
                            orderAdapter.notifyDataSetChanged();
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
                param.put("email_r",mail_r.getText().toString().trim());
                return param;
            }
        };
        requestQueue.add(stringRequest);

    }
    private void Anhxa() {
        mail_r=findViewById(R.id.mail_r);
        name_r=findViewById(R.id.order_name);
        lvdonhang=(ListView)findViewById(R.id.lvDonHang);
        mangdonhang= new ArrayList<>();
        orderAdapter= new OrderAdapter(getApplicationContext(),mangdonhang);
        lvdonhang.setAdapter(orderAdapter);
    }
}