package com.example.appbanhang.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanhang.R;

import java.util.HashMap;

public class Thongtin_acc extends AppCompatActivity {
    EditText edtusername,edtemail,edtsdt,edtpassword,edtdiachi,edtconfimpass;
    Button btnxacnhan,btntrove;
    SessionManager sessionManager;
    static TextView mail_r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_acc);
        sessionManager = new SessionManager(this);
        Anhxa();

        HashMap<String,String> user=sessionManager.getUserDetail();

        String mname=user.get(sessionManager.NAME);
        String memail=user.get(sessionManager.EMAIL);
        mail_r.setText(memail);
        
    }

    private void Anhxa() {
        edtusername= (EditText) findViewById(R.id.acc_username);
        edtemail= (EditText) findViewById(R.id.acc_email);
        edtsdt= (EditText) findViewById(R.id.acc_sdt);
        edtdiachi= (EditText) findViewById(R.id.acc_diachi);
        edtpassword= (EditText) findViewById(R.id.acc_password);
        btntrove= (Button) findViewById(R.id.btn_acc);
        btnxacnhan= (Button) findViewById(R.id.buttonxacnhan);
        edtconfimpass=(EditText) findViewById(R.id.acc_c_password);

    }
}