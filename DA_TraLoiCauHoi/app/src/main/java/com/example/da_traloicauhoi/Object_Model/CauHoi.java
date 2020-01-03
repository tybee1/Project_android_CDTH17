package com.example.da_traloicauhoi.Object_Model;

import org.json.JSONException;
import org.json.JSONObject;

public class CauHoi {
    private int id;
    private int linh_vuc_id;
    private String noi_dung;
    private String phuong_an_a;
    private String phuong_an_b;
    private String phuong_an_c;
    private String phuong_an_d;
    private String dap_an;

    public CauHoi(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt("id");
            this.linh_vuc_id = jsonObject.getInt("linh_vuc_id");
            this.noi_dung = jsonObject.getString("noi_dung");
            this.phuong_an_a = jsonObject.getString("phuong_an_a");
            this.phuong_an_b = jsonObject.getString("phuong_an_b");
            this.phuong_an_c = jsonObject.getString("phuong_an_c");
            this.phuong_an_d = jsonObject.getString("phuong_an_d");
            this.dap_an = jsonObject.getString("dap_an");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getLinh_vuc_id() {
        return linh_vuc_id;
    }

    public void setLinh_vuc_id(int linh_vuc_id) {
        this.linh_vuc_id = linh_vuc_id;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public String getPhuong_an_a() {
        return phuong_an_a;
    }

    public void setPhuong_an_a(String phuong_an_a) {
        this.phuong_an_a = phuong_an_a;
    }

    public String getPhuong_an_b() {
        return phuong_an_b;
    }

    public void setPhuong_an_b(String phuong_an_b) {
        this.phuong_an_b = phuong_an_b;
    }

    public String getPhuong_an_c() {
        return phuong_an_c;
    }

    public void setPhuong_an_c(String phuong_an_c) {
        this.phuong_an_c = phuong_an_c;
    }

    public String getPhuong_an_d() {
        return phuong_an_d;
    }

    public void setPhuong_an_d(String phuong_an_d) {
        this.phuong_an_d = phuong_an_d;
    }

    public int getDap_an_Number() {
        switch (dap_an) {
            case "A": return 0;
            case "B": return 1;
            case "C": return 2;
        }
        return 3;
    }
    public String getDap_an() {
        return dap_an;
    }

    public void setDap_an(String dap_an) {
        this.dap_an = dap_an;
    }
}
