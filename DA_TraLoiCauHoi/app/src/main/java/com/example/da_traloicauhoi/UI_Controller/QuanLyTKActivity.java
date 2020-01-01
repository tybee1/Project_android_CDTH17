package com.example.da_traloicauhoi.UI_Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.da_traloicauhoi.Object_Model.User;
import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.API_AsyncTask;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.NetworkUtils;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.UserSingleTon;
import com.example.da_traloicauhoi.Ultils.SharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QuanLyTKActivity extends AppCompatActivity {
    private EditText mTaiKhoan, mEmail, mMatKhau, mXacNhanMatKhau;
    private User user;
    private ImageView mHinhDaiDien;
    private String mEncodeImgTemp;

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

        user = UserSingleTon.getInstance(null).getUser();
        mTaiKhoan.setText(user.getTen_dang_nhap());
        mEmail.setText(user.getEmail());


        byte[] decodeString = Base64.decode(user.getHinh_dai_dien(),Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        mHinhDaiDien.setImageBitmap(bitmap);

        

    }

    public void HandleCapNhat(View view) {
        String matKhauMoi  = mMatKhau.getText().toString();
        String xacNhanMatKhau = mXacNhanMatKhau.getText().toString();

            Map<String, String> paramet = new HashMap<>();

            paramet.put("ten_dang_nhap",user.getTen_dang_nhap());
            paramet.put("mat_khau",matKhauMoi);
            paramet.put("hinh_dai_dien",mEncodeImgTemp);
            paramet.put("diem_cao_nhat",user.getDiem_cao_nhat() +"");
            paramet.put("credit",user.getCredit() + "");
            paramet.put("email",user.getEmail());



            //Gọi api bởi Asynctask
            new API_AsyncTask(this, NetworkUtils.POST,paramet){
                @Override
                public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
                    if (jsonObject.getBoolean("success") == true) {
                        user.setHinh_dai_dien(mEncodeImgTemp);

                        Toast.makeText(context, "Cập nhật thành công.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Cập nhật thất bại.", Toast.LENGTH_SHORT).show();
                    }
                }
            }.execute("nguoi-choi/cap-nhat");


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
                mEncodeImgTemp = Base64.encodeToString(b,Base64.DEFAULT);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
