package com.example.da_traloicauhoi.UI_Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.APIAsyncTask;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.API_AsyncTask;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.NetworkUtils;
import com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter.CustomDialog;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.CallAPI;

import org.json.JSONException;
import org.json.JSONObject;

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
        //


        final Map<String, String> paramet = new HashMap<>();
        paramet.put("ten_dang_nhap",mTenDangNhap);
        paramet.put("email", mEmail);

        new API_AsyncTask(this,NetworkUtils.POST,paramet){
            @Override
            public void XuLy(JSONObject jsonObject, final Context context) throws JSONException {
                if (jsonObject.getBoolean("success") == true) {
                    new CustomDialog(context) {
                        @Override
                        protected void onCreate(Bundle savedInstanceState) {
                            requestWindowFeature(Window.FEATURE_NO_TITLE);
                            setContentView(R.layout.dialog_quen_mat_khau);
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
                            switch (v.getId()) {
                                case R.id.btnDongY:
                                    //Get mật khẩu mới
                                    String mMatKhauMoi = edtMatKhauMoi.getText().toString();
                                    String mXacNhanMK = edtXacNhanMK.getText().toString();
                                    if (mMatKhauMoi.equals(mXacNhanMK)) {
                                        paramet.put("mat_khau", mMatKhauMoi);

                                        new API_AsyncTask(context, NetworkUtils.POST, paramet) {
                                            @Override
                                            public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
                                                if (jsonObject.getBoolean("success") == true) {
                                                    dismiss();
                                                    Toast.makeText(context, "Lấy mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    dismiss();
                                                    Toast.makeText(context, "Lấy mật thất bại", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        }.execute("nguoi-choi/cap-nhat/mat-khau");
                                    } else {
                                        Toast.makeText(context, "Sai mật khẩu.", Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                case R.id.btnHuyMK:
                                    this.dismiss();
                                    break;
                            }
                        }
                    }.show();
                } else {
                    Toast.makeText(context,"Tài khoản hoặc email không tồn tại.",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute("nguoi-choi/quen-mat-khau/xac-thuc");

    }
}
