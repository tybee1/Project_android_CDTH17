package com.example.da_traloicauhoi.UI_Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.APIAsyncTask;
import com.example.da_traloicauhoi.Ultils.test;
import com.example.da_traloicauhoi.Ultils.CallAPI;

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
        new test(this).execute();

    }

    public void InitView(){
        edtTenDangNhap  = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
    }



    public void handleLogin(View view) {
        String ten_dang_nhap = edtTenDangNhap.getText().toString();
        String mat_khau = edtMatKhau.getText().toString();
        String result = "";
         Map<String, String> map = new HashMap<>();
        map.put("ten_dang_nhap",ten_dang_nhap);
        map.put("mat_khau", mat_khau);


        new APIAsyncTask(this, CallAPI.POST,map){
            @Override
            public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
                super.XuLy(jsonObject, context);
                Log.d("Response", jsonObject.toString());
                if(jsonObject.getBoolean("success") == true)
                {
                    Intent intent = new Intent(context,ManHinhChinhActivity.class);
                    intent.putExtra("json",jsonObject.getJSONArray("data").get(0).toString());
                    startActivity(intent);
                }
            }

        }.execute("http://10.0.2.2:8000/api/nguoi-choi/xac-thuc");



//        openActivity(ManHinhChinhActivity.class);
    }

    public void handleForgetPassword(View view) {
        Intent intent = new Intent(this,QuenMayKhauActivity.class);
        startActivity(intent);

    }

    public void handleRegister(View view) {
        Intent intent = new Intent(this,DangKyActivity.class);
        startActivity(intent);
    }
}
