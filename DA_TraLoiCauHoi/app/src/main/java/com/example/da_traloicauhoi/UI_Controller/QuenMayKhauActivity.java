package com.example.da_traloicauhoi.UI_Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.EventLog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.APIAsyncTask;
import com.example.da_traloicauhoi.Ultils.CallAPI;
import com.example.da_traloicauhoi.Ultils.CustomDialog;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuenMayKhauActivity extends AppCompatActivity {
    private EditText edtTenDangNhap, edtEmail;
    private Button btnDongY, btnHuyMK;
    private EditText edtMatKhauMoi, edtXacNhanMK;
    private String mMatKhau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_may_khau);

        InitiView();
    }

    public void InitiView(){
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap_qmk);
        edtEmail  = findViewById(R.id.edtEmail_qmk);
    }


    public void HandleLayMatKhau(View view) {
        String mTenDangNhap = edtTenDangNhap.getText().toString();
        String mEmail = edtEmail.getText().toString();

        new CustomDialog(this){
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);



                requestWindowFeature(Window.FEATURE_NO_TITLE);
                setContentView(R.layout.dialog);
                setTitle("Đổi Mật Khẩu");
                setCancelable(true);


                //anh xa view
                edtMatKhauMoi = findViewById(R.id.edtMatKhauMoi);
                edtXacNhanMK = findViewById(R.id.edtXacNhanMatKhau);
                btnDongY = findViewById(R.id.btnDongY);
                btnHuyMK = findViewById(R.id.btnHuyMK);

                //set onclick
                btnDongY.setOnClickListener(this);
                btnHuyMK.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.btnDongY:
                        //Get mật khẩu mới
                        String mMatKhauMoi = edtMatKhauMoi.getText().toString();
                        String mXacNhanMK = edtXacNhanMK.getText().toString();
                        if(mMatKhauMoi.equals(mXacNhanMK))
                            mMatKhau = mMatKhauMoi;
                        this.dismiss();
                        Toast.makeText(QuenMayKhauActivity.this,"Giá trị mật khẩu không giong nhau", Toast.LENGTH_SHORT).show();
                        ; break;
                    case R.id.btnHuyMK: this.dismiss(); break;
                }
            }
        }.show();



    }
}
