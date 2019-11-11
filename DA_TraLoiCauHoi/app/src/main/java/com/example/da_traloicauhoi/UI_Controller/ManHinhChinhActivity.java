package com.example.da_traloicauhoi.UI_Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.da_traloicauhoi.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ManHinhChinhActivity extends AppCompatActivity {
    private ImageView imgDaiDien;
    private TextView txtTen, txtCredit;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        InitiView();
    }

    public void InitiView(){
        imgDaiDien = findViewById(R.id.imgAnhDaiDien_Chinh);
        txtTen = findViewById(R.id.txtHoVaTen_ManHinhChinh);
        txtCredit = findViewById(R.id.txtCredit_ManHinhChinh);

        //set data
        Intent intent = getIntent();
        try {
            //nhận json từ đăng nhập
            jsonObject  =  new JSONObject(intent.getStringExtra("json"));

            txtTen.setText(jsonObject.getString("ten_dang_nhap"));
            txtCredit.setText(jsonObject.getString("credit"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void HandleQuanLyTK(View view) {
        Intent intent = new Intent(this, QuanLyTKActivity.class);
        startActivity(intent);
    }

    public void HandleTroChoiMoi(View view) {
        Intent intent = new Intent(this, TroChoiActivity.class);
        startActivity(intent);
    }

    public void HandleLichSuChoi(View view) {

    }

    public void HandleBangXepHang(View view) {
    }

    public void HandleMuaCredit(View view) {
        Intent  intent = new Intent(this, MuaCreditActivity.class);
        startActivity(intent);
    }
}
