package com.example.da_traloicauhoi.UI_Controller;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.API_AsyncTask;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.NetworkUtils;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.UserSingleTon;
import com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter.CustomDialog;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText edtTenDangNhap, edtMatKhau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize views
        InitView();


    }

    public void InitView(){
        edtTenDangNhap  = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
    }



    public void handleLogin(View view) {
        String ten_dang_nhap = edtTenDangNhap.getText().toString();
        String mat_khau = edtMatKhau.getText().toString();

        if (ten_dang_nhap.equals("") || mat_khau.equals("")) {
            new CustomDialog(this, "Thông báo", "Tài khoản và mật khẩu không được trống.", "Ok", CustomDialog.SIZE_M).show();
        } else {

            //tham số request
            Map<String, String> map = new HashMap<>();

            map.put("ten_dang_nhap",ten_dang_nhap);
            map.put("mat_khau", mat_khau);

            //tạo asyntask đẻ gọi api
            new API_AsyncTask(this, NetworkUtils.POST,map,"Login", "Waiting...."){
                @Override
                public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
                    if (jsonObject.getBoolean("success") == true) {
                        //tạo đối tượng JsonNguoiChoi
                        JSONObject jsonNguoiChoi = (JSONObject) jsonObject.getJSONArray("data").get(0);

                        //khoi tao thong tin nguoi choi
                        UserSingleTon.getInstance(jsonNguoiChoi);

                       //start activity
                        Intent intent = new Intent(context, ManHinhChinhActivity.class);
                        startActivity(intent);
                    }
                    else {
                        new CustomDialog(context, "Thông báo", "Sai tài khoản hoặc mật khẩu.", "Ok", CustomDialog.SIZE_M).show();
                    }

                }
            }.execute("nguoi-choi/xac-thuc");

        }




    }

    public void handleForgetPassword(View view) {
        Intent intent = new Intent(this,QuenMayKhauActivity.class);
        startActivity(intent);

    }

    public void handleRegister(View view) {
        Intent intent = new Intent(this,DangKyActivity.class);
        startActivity(intent);
    }

    public void HandleAPIGmail(View view){

    }
}
