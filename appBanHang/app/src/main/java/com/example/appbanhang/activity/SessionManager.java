package com.example.appbanhang.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE=0;
    private static final String PREF_NAME="LOGIN";
    private static final String LOGIN="IS_LOGIN";
    public static final String NAME="NAME";
    public static final  String EMAIL="EMAIL";
    public static final  String ADDRESS="ADDRESS";
    public static final  String PHONE="PHONE";



    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences= context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor= sharedPreferences.edit();

    }
    public void createsesstion(String name,String email,String address,String phone){
        editor.putBoolean(LOGIN,true);
        editor.putString(NAME,name);
        editor.putString(EMAIL,email);
        editor.putString(ADDRESS,address);
        editor.putString(PHONE,phone);
        editor.apply();
    }
    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }
    public void checkLogin(){
        if(!this.isLoggin());
        Intent i= new Intent(context,MainActivity.class);
        context.startActivity(i);
        ((Session)context).finish();

    }
    public HashMap<String,String >getUserDetail(){
        HashMap<String,String>user= new HashMap<>();
        user.put(NAME,sharedPreferences.getString(NAME,null));
        user.put(EMAIL,sharedPreferences.getString(EMAIL,null));
        user.put(ADDRESS,sharedPreferences.getString(ADDRESS,null));
        user.put(PHONE,sharedPreferences.getString(PHONE,null));
        return user;

    }
    public void logout(){
        editor.clear();
        editor.commit();
        MainActivity.manggiohang.clear();
        Intent i= new Intent(context,Register.class);
        context.startActivity(i);
        ((Session)context).finish();
    }
}
