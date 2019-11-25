package com.example.da_traloicauhoi.UI_Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.APIAsyncTask;
import com.example.da_traloicauhoi.Ultils.CallAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LinhVucActivity extends AppCompatActivity implements View.OnClickListener {
    private JSONObject jsonObject;
    private LinearLayout[] mListLinhVuc_View;
    private TextView mUserName, mCredit;
    private TextView[] mListLinhVuc_NoiDung;
    private int[] mListLinhVuc_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc);


        InitiView();
    }

    public void InitiView() {

        mListLinhVuc_ID = new int[4];

        //view linh vuc
        mListLinhVuc_View = new LinearLayout[4];
        mListLinhVuc_View[0] = findViewById(R.id.lnLinhVuc_1);
        mListLinhVuc_View[1] = findViewById(R.id.lnLinhVuc_2);
        mListLinhVuc_View[2] = findViewById(R.id.lnLinhVuc_3);
        mListLinhVuc_View[3] = findViewById(R.id.lnLinhVuc_4);

        mUserName = findViewById(R.id.txtName_LinhVuc);
        mCredit = findViewById(R.id.credit_LinhVuc);

        //view nội dung lĩnh vực
        mListLinhVuc_NoiDung = new TextView[4];
        mListLinhVuc_NoiDung[0] = findViewById(R.id.txtLinhVuc_1);
        mListLinhVuc_NoiDung[1] = findViewById(R.id.txtLinhVuc_2);
        mListLinhVuc_NoiDung[2] = findViewById(R.id.txtLinhVuc_3);
        mListLinhVuc_NoiDung[3] = findViewById(R.id.txtLinhVuc_4);

        //set onclick cho view lĩnh vực
        mListLinhVuc_View[0].setOnClickListener(this);
        mListLinhVuc_View[1].setOnClickListener(this);
        mListLinhVuc_View[2].setOnClickListener(this);
        mListLinhVuc_View[3].setOnClickListener(this);
        Intent intent  = getIntent();
        try {
            jsonObject = new JSONObject(intent.getStringExtra("json"));
            mUserName.setText(jsonObject.getString("ten_dang_nhap"));
            mCredit.setText(jsonObject.getString("credit"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new APIAsyncTask(this, CallAPI.GET,null,"Get Data", "Loading..."){
            @Override
            public void XuLy(JSONObject jsonObject, Context context) throws JSONException {

                //gán nội dung các lĩnh vực
                if (jsonObject.getBoolean("success") == true) {
                    JSONArray dataCauHoi = jsonObject.getJSONArray("data");
                    for (int i = 0; i < dataCauHoi.length(); i++) {
                        mListLinhVuc_ID[i] =  dataCauHoi.getJSONObject(i).getInt("id");
                        mListLinhVuc_NoiDung[i].setText(dataCauHoi.getJSONObject(i).getString("ten_linh_vuc"));
                    }
                }
            }
        }.execute("http://10.0.2.2:8000/api/linh-vuc");
    }

    public void StartActivity(int id_LinhVuc) {
        Intent intent = new Intent(this,TroChoiActivity.class);
        intent.putExtra("linh_vuc_id",id_LinhVuc);
        intent.putExtra("json",jsonObject.toString());
        startActivity(intent);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lnLinhVuc_1:
                StartActivity(mListLinhVuc_ID[0]);
                break;
            case R.id.lnLinhVuc_2:
                StartActivity(mListLinhVuc_ID[1]);
                break;
            case R.id.lnLinhVuc_3:
                StartActivity(mListLinhVuc_ID[2]);
                break;
            case R.id.lnLinhVuc_4:
                StartActivity(mListLinhVuc_ID[3]);
                break;
        }
    }
}
