package com.example.da_traloicauhoi.Object_Model;

import org.json.JSONException;
import org.json.JSONObject;

public class LinhVuc {
    private int ID;
    private String ten_linh_vuc;

    public LinhVuc(JSONObject jsonObject) {
        try {
            this.ID = jsonObject.getInt("id");
            this.ten_linh_vuc = jsonObject.getString("ten_linh_vuc");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen_linh_vuc() {
        return ten_linh_vuc;
    }

    public void setTen_linh_vuc(String ten_linh_vuc) {
        this.ten_linh_vuc = ten_linh_vuc;
    }
}
