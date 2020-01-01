package com.example.da_traloicauhoi.Object_Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;
    private String ten_dang_nhap;
    private String mat_khau;
    private String email;
    private String hinh_dai_dien;
    private int diem_cao_nhat;
    private int credit;

    public User() {}

    public User(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt("id");
            this.ten_dang_nhap = jsonObject.getString("ten_dang_nhap");
            this.mat_khau = jsonObject.getString("mat_khau");
            this.email = jsonObject.getString("email");
            this.hinh_dai_dien = jsonObject.getString("hinh_dai_dien");
            this.diem_cao_nhat = jsonObject.getInt("diem_cao_nhat");
            this.credit = jsonObject.getInt("credit");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public Map<String, String> ConvertUserToMap(){
        Map<String, String> paramet = new HashMap<>();
        paramet.put("ten_dang_nhap", this.ten_dang_nhap);
        paramet.put("mat_khau",this.mat_khau);
        paramet.put("hinh_dai_dien",this.hinh_dai_dien);
        paramet.put("diem_cao_nhat",this.diem_cao_nhat +"");
        paramet.put("credit",this.credit + "");
        paramet.put("email", this.email);

        return paramet;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_dang_nhap() {
        return ten_dang_nhap;
    }

    public void setTen_dang_nhap(String ten_dang_nhap) {
        this.ten_dang_nhap = ten_dang_nhap;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinh_dai_dien() {
        return hinh_dai_dien;
    }

    public void setHinh_dai_dien(String hinh_dai_dien) {
        this.hinh_dai_dien = hinh_dai_dien;
    }

    public int getDiem_cao_nhat() {
        return diem_cao_nhat;
    }

    public void setDiem_cao_nhat(int diem_cao_nhat) {
        this.diem_cao_nhat = diem_cao_nhat;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }


}
