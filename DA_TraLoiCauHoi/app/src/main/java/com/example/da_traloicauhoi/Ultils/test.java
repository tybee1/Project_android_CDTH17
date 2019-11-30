package com.example.da_traloicauhoi.Ultils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.da_traloicauhoi.Ultils.API_Asyntask.CallAPI;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class test extends AsyncTask<Void, Void, Void> {

    private Context context;

    public test(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        final boolean [] flag = new boolean[1];
        final JSONObject[] jsonObjects = new JSONObject[1];
        jsonObjects[0] = new JSONObject();

        Map<String, String>[] map1 = new Map[1];
        map1[0] = new HashMap<>();
        map1[0].put("ten_dang_nhap","ss");
        map1[0].put("mat_khau", "");

        //call API

        CallAPI.RequestGET("http://10.0.2.2:8000/api/nguoi-choi/xac-thuc",map1, context, jsonObjects, flag);



        //Chờ API Load API.
        while(flag[0] == false) {}
        return null;
    }
}
