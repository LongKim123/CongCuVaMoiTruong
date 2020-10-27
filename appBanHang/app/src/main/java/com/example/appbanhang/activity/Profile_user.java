package com.example.appbanhang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhang.R;

import java.util.HashMap;

public class Profile_user extends AppCompatActivity {
    static TextView mail_r,name_r,phone ,address ;

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


        sua_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }


    private void Anhxa() {
        name_r=(TextView) findViewById(R.id.tv_namePro);
        mail_r=(TextView) findViewById(R.id.mail_pro);
        phone =(TextView) findViewById(R.id.phone_pro);
        address=(TextView) findViewById(R.id.address_pro);
        sua_pro=(ImageView) findViewById(R.id.sua_pro);





    }
}