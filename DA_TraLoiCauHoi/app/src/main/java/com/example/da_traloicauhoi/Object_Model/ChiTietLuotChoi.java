package com.example.da_traloicauhoi.Object_Model;

public class ChiTietLuotChoi {
    private int luot_choi_id;
    private int cau_hoi_id;
    private String phuong_an;
    private int diem;
    public static int mIndex_CauHoiHienTai = 1;
    public static int mTongSoCauDung = 0;
    public static int mTongTiem = 0;
    public static int mSoLuotChoi = 4;
    public static int mThuTuDiemCauHoi = 0;

    public ChiTietLuotChoi( int cau_hoi_id, String phuong_an) {
        this.cau_hoi_id = cau_hoi_id;
        this.phuong_an = phuong_an;
        this.diem = 0;
    }



    public int getLuot_choi_id() {
        return luot_choi_id;
    }

    public void setLuot_choi_id(int luot_choi_id) {
        this.luot_choi_id = luot_choi_id;
    }

    public int getCau_hoi_id() {
        return cau_hoi_id;
    }

    public void setCau_hoi_id(int cau_hoi_id) {
        this.cau_hoi_id = cau_hoi_id;
    }

    public String getPhuong_an() {
        return phuong_an;
    }

    public void setPhuong_an(String phuong_an) {
        this.phuong_an = phuong_an;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
}
