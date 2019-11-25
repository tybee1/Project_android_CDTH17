package com.example.da_traloicauhoi.UI_Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.SharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

public class ManHinhChinhActivity extends AppCompatActivity {
    private ImageView imgDaiDien;
    private TextView txtTen, txtCredit;
    private JSONObject jsonObject;
    private  Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);

        //khởi tạo các view
        InitiView();
    }

    public void InitiView(){
        imgDaiDien = findViewById(R.id.imgAnhDaiDien_Chinh);
        txtTen = findViewById(R.id.txtHoVaTen_ManHinhChinh);
        txtCredit = findViewById(R.id.txtCredit_ManHinhChinh);

        //lấy dữ liệu từ intent
        Intent intent = getIntent();
        try {
            //nhận json từ đăng nhập
            jsonObject  =  new JSONObject(intent.getStringExtra("json"));

            txtTen.setText(jsonObject.getString("ten_dang_nhap"));
            txtCredit.setText(jsonObject.getString("credit"));
            String anhDaiDien = SharedPreference.readFile(this,jsonObject.getString("ten_dang_nhap"));

            byte[] decodeString = Base64.decode(anhDaiDien,Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
            imgDaiDien.setImageBitmap(bitmap);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void HandleQuanLyTK(View view) {
        Intent intent = new Intent(this, QuanLyTKActivity.class);
        intent.putExtra("json",jsonObject.toString());
        startActivity(intent);
    }

    public void HandleTroChoiMoi(View view) {
        Intent intent = new Intent(this, LinhVucActivity.class);
        intent.putExtra("json",jsonObject.toString());
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

    @Override
    protected void onStart() {
        super.onStart();
        String anhDaiDien = null;
        try {
            anhDaiDien = SharedPreference.readFile(this,jsonObject.getString("ten_dang_nhap"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        byte[] decodeString = Base64.decode(anhDaiDien,Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        imgDaiDien.setImageBitmap(bitmap);
    }
}
