package com.example.da_traloicauhoi.UI_Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.APIAsyncTask;
import com.example.da_traloicauhoi.Ultils.CallAPI;
import com.example.da_traloicauhoi.Ultils.SharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QuanLyTKActivity extends AppCompatActivity {
    private EditText mTaiKhoan, mEmail, mMatKhau, mXacNhanMatKhau;
    private JSONObject mJsonObject;
    private ImageView mHinhDaiDien;
    private String mEncodeAnhDaiDien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tk);

        //khởi tạo các view
        InitiView();
    }

    public void InitiView() {
        mTaiKhoan = findViewById(R.id.edtTenTK_QLTK);
        mEmail = findViewById(R.id.edtEmail_QLTK);
        mMatKhau = findViewById(R.id.edtMatKhauMoi_QLTK);
        mXacNhanMatKhau = findViewById(R.id.edtXacNhanMatKhau_QLTK);
        mHinhDaiDien = findViewById(R.id.imgHinhDaiDien_QLTK);

        //get intent
        Intent intent = getIntent();
        try {
            mJsonObject = new JSONObject(intent.getStringExtra("json"));
            mTaiKhoan.setText(mJsonObject.getString("ten_dang_nhap"));
            mEmail.setText(mJsonObject.getString("email"));

            //lấy ảnh từ Shared
            mEncodeAnhDaiDien = SharedPreference.readFile(this,mJsonObject.getString("ten_dang_nhap"));

            byte[] decodeString = Base64.decode(mEncodeAnhDaiDien,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
            mHinhDaiDien.setImageBitmap(bitmap);
            mEncodeAnhDaiDien = mJsonObject.getString("hinh_dai_dien");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void HandleCapNhat(View view) {
        String matKhauMoi  = mMatKhau.getText().toString();
        String xacNhanMatKhau = mXacNhanMatKhau.getText().toString();

            Map<String, String> paramet = new HashMap<>();

            paramet.put("ten_dang_nhap",mTaiKhoan.getText().toString());
            paramet.put("mat_khau",matKhauMoi);
            paramet.put("hinh_dai_dien",mEncodeAnhDaiDien);
            SharedPreference.writeFile(this,mTaiKhoan.getText().toString(),mEncodeAnhDaiDien);

            //Gọi api bởi Asynctask
            new APIAsyncTask(this, CallAPI.POST,paramet){
                @Override
                public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
                    super.XuLy(jsonObject, context);
                    if (jsonObject.getBoolean("success") == true) {
                        Toast.makeText(context, "Cập nhật thành công.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Cập nhật thất bại.", Toast.LENGTH_SHORT).show();
                    }
                }
            }.execute("http://10.0.2.2:8000/api/nguoi-choi/cap-nhat");


    }

    public void HandleChooseImg(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select Chooser"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            Bitmap bitmap;

            try {
              bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                mHinhDaiDien.setImageBitmap(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                byte[] b =baos.toByteArray();
                mEncodeAnhDaiDien = Base64.encodeToString(b,Base64.DEFAULT);
                Log.d("Lenght_Base64",mEncodeAnhDaiDien.length()+"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
