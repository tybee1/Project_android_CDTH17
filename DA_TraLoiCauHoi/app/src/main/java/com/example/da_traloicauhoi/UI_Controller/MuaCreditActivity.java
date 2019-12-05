package com.example.da_traloicauhoi.UI_Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.da_traloicauhoi.Object_Model.Credit;
import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.API_AsyncTask;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.NetworkUtils;
import com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter.CreditAdapter;
import com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter.CustomDialog;
import com.example.da_traloicauhoi.Ultils.SharedPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MuaCreditActivity extends AppCompatActivity {
    private RecyclerView recyclerView_credit;
    private TextView mUserName, mCreditUser;
    private CreditAdapter mApdaterCredit;
    private ArrayList<Credit> listCredit;
    private JSONObject jsonObject;
    private int mID_NguoiChoi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua_credit);
        InitiView();
    }

    public void InitiView() {
        recyclerView_credit = findViewById(R.id.recyclerview_muacredit);
        mUserName = findViewById(R.id.txtUsername_MuaCredit);
        mCreditUser = findViewById(R.id.txtCredit_Muacredit);
        listCredit = new ArrayList<>();

        recyclerView_credit.setLayoutManager(new GridLayoutManager(this,2));

        Intent intent  = getIntent();
        try {
            jsonObject = new JSONObject(intent.getStringExtra("json"));
            mID_NguoiChoi = jsonObject.getInt("id");
            mUserName.setText(jsonObject.getString("ten_dang_nhap"));
            mCreditUser.setText(jsonObject.getString("credit"));
            //tạo asyntask đẻ gọi api
            new API_AsyncTask(this, NetworkUtils.GET,null,"Load data", "Waiting...."){
                @Override
                public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
                    if (jsonObject.getBoolean("success") == true) {
                        //tạo đối tượng JsonNguoiChoi
                        JSONArray jsonCredit = jsonObject.getJSONArray("data");
                        for (int i = 0 ; i < jsonCredit.length() ; i++) {
                            JSONObject objCredit = jsonCredit.getJSONObject(i);
                            listCredit.add(new Credit(objCredit));
                        }
                        mApdaterCredit = new CreditAdapter(context, listCredit,mID_NguoiChoi);
                        recyclerView_credit.setAdapter(mApdaterCredit);

                    } else {
                        new CustomDialog(context, "Thông báo", "Không load được dữ liệu", "Ok", CustomDialog.SIZE_M).show();
                    }
                }

            }.execute("credit");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
