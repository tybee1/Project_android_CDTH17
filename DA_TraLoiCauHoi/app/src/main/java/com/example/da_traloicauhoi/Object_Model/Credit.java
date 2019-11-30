package com.example.da_traloicauhoi.Object_Model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Credit {
    private int id;
    private String ten_goi;
    private int credit;
    private int so_tien;

    public Credit(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt("id");
            this.ten_goi = jsonObject.getString("ten_goi");
            this.credit = jsonObject.getInt("credit");
            this.so_tien = jsonObject.getInt("so_tien");
        } catch (JSONException e) {
            Log.d("error","Lỗi khởi tạo credit");
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_goi() {
        return ten_goi;
    }

    public void setTen_goi(String ten_goi) {
        this.ten_goi = ten_goi;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getSo_tien() {
        return so_tien;
    }

    public void setSo_tien(int so_tien) {
        this.so_tien = so_tien;
    }
}
