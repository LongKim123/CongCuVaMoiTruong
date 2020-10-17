package com.example.appbanhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbanhang.R;
import com.example.appbanhang.adapter.LoaispAdapter;
import com.example.appbanhang.adapter.SanphamAdapter;
import com.example.appbanhang.model.Giohang;
import com.example.appbanhang.model.Loaisp;
import com.example.appbanhang.model.Sanpham;
import com.example.appbanhang.ultil.CheckConnection;
import com.example.appbanhang.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // toolbar=(Toolbar) findViewById(R.id.toolbarmanhinhchinh);

    Toolbar tool ;
    Toolbar toolbar;
    Button btnSearch;
    ImageButton btnLau, btnDAN, btnPho, btnNuoc;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    ImageButton btnsearch;
    EditText edtsearch;

    int id=0;
    String tensanpham="";
    String tenloaiSP="";
    String hinhanhloaisp="";
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    int ID=0;
    String Tensanpham="";
    Integer Giasanpham=0;
    String Hinhanhsanpham="";
    String Motasanpham="";
    int IDsanpham=0;
    public static ArrayList<Giohang> manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addViews();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaisp();
            GetDuLieuSPMoiNhat();
            addEvents();


        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
            finish();
        }
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        getMenuInflater().inflate(R.menu.thongtincanhan,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent= new Intent(getApplicationContext(), com.example.appbanhang.activity.Giohang.class);
                startActivity(intent);

        }
        if(item.getItemId()==R.id.thongtincanhan){
            thongtin();
        }

        return super.onOptionsItemSelected(item);
    }
    private void thongtin(){
        Intent intent= new Intent(getApplicationContext(), com.example.appbanhang.activity.Session.class);
        startActivity(intent);
       // Dialog dialogthongtin=new Dialog(this);
       // dialogthongtin.setContentView(R.layout.thongtin);
        //dialogthongtin.show();
    }

    private void addEvents() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this, MonLauActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(i).getId());
                            startActivity(intent);
                        }
                        else{
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this, DoAnNhanhActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(2).getId());
                            startActivity(intent);
                        }
                        else{
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 7:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this,Order.class);
                            startActivity(intent);
                        }
                        else{
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        Intent intent= new Intent(MainActivity.this,Register.class);

                        startActivity(intent);

                }
            }
        });

        btnLau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                    Intent intent= new Intent(MainActivity.this, MonLauActivity.class);
                    intent.putExtra("idloaisanpham",1);
                    startActivity(intent);
                }
                else{
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn kiểm tra lại kết nối");
                }
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        btnDAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                    Intent intent= new Intent(MainActivity.this, DoAnNhanhActivity.class);
                    intent.putExtra("idloaisanpham",2);
                    startActivity(intent);
                }
                else{
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn kiểm tra lại kết nối");
                }
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Server.Duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response !=null){

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID= jsonObject.getInt("id_sp");
                            Tensanpham = jsonObject.getString("tensanpham");
                            Hinhanhsanpham = jsonObject.getString("hinhanh");
                            Giasanpham=jsonObject.getInt("giasanpham");
                            IDsanpham=jsonObject.getInt("idsanpham");
                            Motasanpham=jsonObject.getString("motasanpham");

                            mangsanpham.add(new Sanpham(ID,Tensanpham,Hinhanhsanpham, Giasanpham,IDsanpham,Motasanpham));
                            sanphamAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                          // mangloaisp.add(3,new Loaisp(0, "Thông tin", "http://kinhtevadubao.vn/uploads/images/news/1515687283_news_10383.jpg"));

                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaisp() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Server.Duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response !=null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenloaiSP = jsonObject.getString("tenloaisp");
                            hinhanhloaisp = jsonObject.getString("hinhanhloaisp");
                            mangloaisp.add(new Loaisp(id, tenloaiSP, hinhanhloaisp));
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add(8,new Loaisp(0, "Thông tin", "http://kinhtevadubao.vn/uploads/images/news/1515687283_news_10383.jpg"));
                    mangloaisp.add(9,new Loaisp(0,"Liên Hệ ","https://i.pinimg.com/originals/57/cf/21/57cf2127a1b9c8fdb334e5860fc22f61.png"));

                }
            }
        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao=new ArrayList<>();
        mangquangcao.add("https://png.pngtree.com/thumb_back/fw800/back_our/20190621/ourmid/pngtree-tmall-taobao-gourmet-food-festival-banner-template-image_192133.jpg");
        mangquangcao.add("https://png.pngtree.com/thumb_back/fh260/back_our/20190621/ourmid/pngtree-winter-spicy-hot-pot-texture-black-banner-image_181035.jpg");
        mangquangcao.add("https://ihawa.com.vn/uploads/dichvu/1493333652_Banner_Web_01_Nuoc%20Ngot.jpg");

        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView= new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
    viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }



    private void addViews(){
        //toolbar = (Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        toolbar=(Toolbar) findViewById(R.id.toolbarmanhinhchinh) ;
        viewFlipper =(ViewFlipper) findViewById(R.id.viewlipper);
        recyclerViewmanhinhchinh=(RecyclerView) findViewById(R.id.recyclerview);
        navigationView=(NavigationView) findViewById(R.id.navigation);
        listViewmanhinhchinh=(ListView) findViewById(R.id.listviewmanhinhchinh);
        btnLau = findViewById(R.id.btnLau);
        btnDAN = findViewById(R.id.btnDAN);
        btnNuoc = findViewById(R.id.btnNuoc);
        btnPho = findViewById(R.id.btnPho);
        btnsearch=findViewById(R.id.btnSearch);
        edtsearch=findViewById(R.id.editsearch);
        drawerLayout=findViewById(R.id.drawerlayout);
        mangloaisp= new ArrayList<>();
        loaispAdapter= new LoaispAdapter(mangloaisp,getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaispAdapter);
        mangloaisp.add(0,new Loaisp(0,"Trang Chính ","https://laptopgiasi.vn/wp-content/uploads/2017/09/icon-trang-chu-laptopgiasi.vn_.png"));

     //   mangloaisp.add(2,new Loaisp(0, "Thông Tin", "http://kinhtevadubao.vn/uploads/images/news/1515687283_news_10383.jpg"));
        mangsanpham= new ArrayList<>();
        sanphamAdapter= new SanphamAdapter(mangsanpham,getApplicationContext());
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        recyclerViewmanhinhchinh.setAdapter(sanphamAdapter);
        if(manggiohang!=null){

        }
        else{
            manggiohang= new ArrayList<>();
        }
    }
}