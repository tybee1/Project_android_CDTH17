package com.example.da_traloicauhoi.UI_Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.da_traloicauhoi.Object_Model.NguoiChoi;
import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.API_AsyncTask;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.NetworkUtils;
import com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter.BXHAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BangXepHangActivity extends AppCompatActivity {
    private BXHAdapter mAdapterBXH;
    private ArrayList<NguoiChoi> mListNguoiChoi;
    private RecyclerView mRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bang_xep_hang);

        InitiView();

    }

    public void InitiView() {
        this.mRecyclerview = findViewById(R.id.recyclerview_BXH);
        this.mListNguoiChoi = new ArrayList<>();
        this.mRecyclerview.setLayoutManager( new LinearLayoutManager(this));

        new API_AsyncTask(this, NetworkUtils.GET,null, "Đang load danh sách người chơi", "Chờ tui chút" ) {
            @Override
            public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
                if (jsonObject.getBoolean("success") == true) {
                    JSONArray arrayUser = jsonObject.getJSONArray("data");

                    for (int i = 0; i < arrayUser.length(); i++) {
                        mListNguoiChoi.add(new NguoiChoi(arrayUser.getJSONObject(i)));
                    }

                    mAdapterBXH = new BXHAdapter(context, mListNguoiChoi);
                    mRecyclerview.setAdapter(mAdapterBXH);

                }
            }
        }.execute("nguoi-choi");
    }

}
