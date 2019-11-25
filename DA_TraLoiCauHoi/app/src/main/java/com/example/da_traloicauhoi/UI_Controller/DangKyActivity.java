package com.example.da_traloicauhoi.UI_Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.APIAsyncTask;
import com.example.da_traloicauhoi.Ultils.CallAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {
    private EditText edtTenDangNhap, edtEmail, edtMatKhau, edtXacNhanMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        InitView();
    }

    public void InitView(){
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap_dk);
        edtMatKhau = findViewById(R.id.edtMatKhau_dk);
        edtEmail = findViewById(R.id.edtEmail_dk);
        edtXacNhanMatKhau = findViewById(R.id.edtXacNhan_dk);

    }

    public void HandleDangKy(View view) {


        String mTenDangNhap = edtTenDangNhap.getText().toString();
        String mMatKhau = edtMatKhau.getText().toString();
        String mEmail = edtEmail.getText().toString();
        String mXacNhanMatKhau = edtXacNhanMatKhau.getText().toString();


        //kiểm tra tài khản
        //kiểm tra email
        //kiểm tra mật khẩu
        //xác nhận mật khẩu

        //kiểm trả khớp mật khẩu và != null
        if (mMatKhau.compareTo(mXacNhanMatKhau) == 0 && mMatKhau != null) {
            //---Start call api
                Map<String, String> param = new HashMap<>();
                param.put("ten_dang_nhap", mTenDangNhap);
                param.put("mat_khau", mMatKhau);
                param.put("email", mEmail);

                //gọi API đăng ký
                new APIAsyncTask(this, CallAPI.POST, param) {
                    @Override
                    public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
                        super.XuLy(jsonObject, context);
                        if (jsonObject.getBoolean("success") == true) {
                            Toast.makeText(context, "Đăng ký thành công.", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(context, "Đăng ký thất bại.", Toast.LENGTH_SHORT).show();
                    }
                }.execute("http://10.0.2.2:8000/api/nguoi-choi/dang-ky");
            //---end call api
        } else {
            Toast.makeText(this,"Sai định dạng.",Toast.LENGTH_SHORT).show();
        }

    }
}
