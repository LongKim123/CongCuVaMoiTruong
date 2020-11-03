package com.example.appbanhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;
import com.example.appbanhang.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class Profile_user extends AppCompatActivity {
    static TextView mail_r,name_r,phone,address ;
    static EditText edtname,edt_address;

    ImageView sua_pro;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        sessionManager = new SessionManager(this);
        Anhxa();
        HashMap<String,String> user=sessionManager.getUserDetail();

        String mname=user.get(sessionManager.NAME);
        String memail=user.get(sessionManager.EMAIL);
        String meaddress=user.get(SessionManager.ADDRESS);
        String mephone=user.get(SessionManager.PHONE);
        mail_r.setText(memail);
        name_r.setText(mname);
        address.setText(meaddress);
        phone.setText(mephone);
        edtname.setText(mname);
        edt_address.setText(meaddress);
        sua_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CapnhatProfile();
            }
        });



    }
    private void CapnhatProfile(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.suasanpham, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("1")) {
                    Toast.makeText(Profile_user.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Profile_user.this, Register.class));
                } else {
                    Toast.makeText(Profile_user.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",mail_r.getText().toString().trim());
                params.put("name",edtname.getText().toString().trim());
                params.put("diachi",edt_address.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
        private void Anhxa() {
        name_r=(TextView) findViewById(R.id.tv_namePro);
        mail_r=(TextView) findViewById(R.id.mail_pro);
        phone =(TextView) findViewById(R.id.phone_pro);
        address=(TextView) findViewById(R.id.address_pro);
        sua_pro=(ImageView) findViewById(R.id.sua_pro);
        edtname=(EditText) findViewById(R.id.nickname_pro);
        edt_address=(EditText) findViewById(R.id.edt_pro);
    }
}