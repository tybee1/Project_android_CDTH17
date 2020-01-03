package com.example.da_traloicauhoi.UI_Controller;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.da_traloicauhoi.Object_Model.CauHinhCauHoi;
import com.example.da_traloicauhoi.Object_Model.CauHoi;
import com.example.da_traloicauhoi.Object_Model.ChiTietLuotChoi;
import com.example.da_traloicauhoi.Object_Model.User;
import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.API_AsyncTask;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.NetworkUtils;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.UserSingleTon;
import com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter.CustomDialog;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.RingProgressbarAsyntask;
import com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter.Dialog_Chart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class TroChoiActivity extends AppCompatActivity implements View.OnClickListener, RingProgressBar.OnProgressListener {

    private ImageView[] mListCoHoiChoi;
    private LinearLayout[]  mListDapAn_View; // các view cho mỗi Đáp án ( xử lý chọn đáp án)
    private TextView[] mListDapAn; // danh sách các view hiển thị nội đáp án
    private ArrayList<CauHoi> mListCauHoi; //danh sách các câu hỏi theo lĩnh vực đã chọn
    private ArrayList<ChiTietLuotChoi> mListChiTietLuotChoi;
    private ArrayList<CauHinhCauHoi> mListCauHinhDiemCauHoi;
    private int mLinhVuc_ID;
    private boolean flagLoai2Cau;
    private final int VISIBLE = 0x00000000;
    private final int INVISIBLE = 0x00000004;
    private int[] viewVisible;
    private  Boolean flagThread; // dừng proressbar
    private TextView mSecond, mUserName, mCredit, mDiem, mSoCau, mNoiDungCauHoi;
    private RingProgressBar mRingProgressBar;
    private int progress = 1, mMaxProgress;
    private RingProgressbarAsyntask mThreadCountdown;

    private Button mBtnQuaCau, mBtnLoai2DapAn, mBtnKhanGia, mBtnGoiNguoiThan;
    private LinearLayout mLnMuaDapAn;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tro_choi);
        InitiView();

        LoadData(mLinhVuc_ID);
    }

    public void InitiView() {

        viewVisible = new int[2];
        //Ánh xạ
        mListCauHoi = new ArrayList<>();
        mListChiTietLuotChoi = new ArrayList<>();
        mListCauHinhDiemCauHoi = new ArrayList<>();
        mUserName = findViewById(R.id.txtName_TroChoi);
        mDiem = findViewById(R.id.txtDiem_TroChoi);
        mCredit = findViewById(R.id.txtCredit_TroChoi);
        mSecond = findViewById(R.id.txtDemGiay);
        mSoCau = findViewById(R.id.txtSoCau);
        mNoiDungCauHoi = findViewById(R.id.txtNoiDungCauHoi);

        //button tro giup
        mBtnQuaCau = findViewById(R.id.btnQuaCau);
        mBtnLoai2DapAn = findViewById(R.id.btnLoai2);
        mBtnKhanGia = findViewById(R.id.btnKhanGia);
        mBtnGoiNguoiThan = findViewById(R.id.btnGoiDien);
        mLnMuaDapAn = findViewById(R.id.lnMuaDapAn);

        mBtnQuaCau.setOnClickListener(this);
        mBtnLoai2DapAn.setOnClickListener(this);
        mBtnKhanGia.setOnClickListener(this);
        mBtnGoiNguoiThan.setOnClickListener(this);
        mLnMuaDapAn.setOnClickListener(this);



        //RingProgress
        mRingProgressBar = findViewById(R.id.ringProgress);
        mRingProgressBar.setOnProgressListener(this);
        mRingProgressBar.setMax(10);
        mMaxProgress = mRingProgressBar.getMax(); // chỉ số max của Ring
        mThreadCountdown = new RingProgressbarAsyntask(this, mRingProgressBar,progress,mMaxProgress, mSecond);//countdown

        //ánh xạ các view nội dung đáp án
        mListDapAn = new TextView[4];
        mListDapAn[0] = findViewById(R.id.txtDapAnA);
        mListDapAn[1] = findViewById(R.id.txtDapAnB);
        mListDapAn[2] = findViewById(R.id.txtDapAnC);
        mListDapAn[3] = findViewById(R.id.txtDapAnD);

        //ánh xạ image cơ ội chơi (Trái tim)
        mListCoHoiChoi = new ImageView[5];
        mListCoHoiChoi[0] = findViewById(R.id.imgTraiTim_1);
        mListCoHoiChoi[1] = findViewById(R.id.imgTraiTim_2);
        mListCoHoiChoi[2] = findViewById(R.id.imgTraiTim_3);
        mListCoHoiChoi[3] = findViewById(R.id.imgTraiTim_4);
        mListCoHoiChoi[4] = findViewById(R.id.imgTraiTim_5);

        //ánh xạ và setonclick cho các view để xử lý chọn đáp án
        mListDapAn_View = new LinearLayout[4];
        mListDapAn_View[0] = findViewById(R.id.lnDapAnA);
        mListDapAn_View[1] = findViewById(R.id.lnDapAnB);
        mListDapAn_View[2] = findViewById(R.id.lnDapAnC);
        mListDapAn_View[3] = findViewById(R.id.lnDapAnD);

        mListDapAn_View[0].setOnClickListener(this);
        mListDapAn_View[1].setOnClickListener(this);
        mListDapAn_View[2].setOnClickListener(this);
        mListDapAn_View[3].setOnClickListener(this);


        user = UserSingleTon.getInstance(null).getUser();
        mUserName.setText(user.getTen_dang_nhap());
        mCredit.setText(user.getCredit()+"");
        mLinhVuc_ID = getIntent().getIntExtra("linh_vuc_id",-1);
        mSoCau.setText(ChiTietLuotChoi.mIndex_CauHoiHienTai + "");
        mDiem.setText("Điểm: " + ChiTietLuotChoi.mTongTiem);

        Log.d("mMax",mMaxProgress+"");

    }

    public void LoadData(int linh_vuc_id) {
        Map<String, String> params = new HashMap<>();
        params.put("linh_vuc_id",linh_vuc_id+"");

        //load danh sách câu hỏi
        new API_AsyncTask(this, NetworkUtils.POST,params,"Get DaTa", "Loading....."){
            @Override
            public void XuLy(JSONObject jsonObject, Context context) throws JSONException {

                //gán nội dung các lĩnh vực
                if (jsonObject.getBoolean("success") == true) {
                    JSONArray dataCauHoi = jsonObject.getJSONArray("data");
                    for (int i = 0; i < dataCauHoi.length(); i++) {
                       mListCauHoi.add(new CauHoi(dataCauHoi.getJSONObject(i)));
                    }
                    SetCauHoi(mListCauHoi.get(ChiTietLuotChoi.mIndex_CauHoiHienTai));
                    countDown(true);
                }
            }
        }.execute("cau-hoi");

        //load cấu hình câu hỏi
        new API_AsyncTask(this, NetworkUtils.GET,null){
            @Override
            public void XuLy(JSONObject jsonObject, Context context) throws JSONException {

                //gán nội dung các caasu hinh
                if (jsonObject.getBoolean("success") == true) {
                    JSONArray dataCauHinh = jsonObject.getJSONArray("data");
                    for (int i = 0; i < dataCauHinh.length(); i++) {
                        mListCauHinhDiemCauHoi.add(new CauHinhCauHoi(dataCauHinh.getJSONObject(i)));
                    }
                }
            }
        }.execute("cau-hoi/cau-hinh-diem-cau-hoi");

    }

    //gán nội dung câu hỏi và đáp án
    public void SetCauHoi (CauHoi cauHoi) {
        mNoiDungCauHoi.setText(cauHoi.getNoi_dung());
        mListDapAn[0].setText(cauHoi.getPhuong_an_a());
        mListDapAn[1].setText(cauHoi.getPhuong_an_b());
        mListDapAn[2].setText(cauHoi.getPhuong_an_c());
        mListDapAn[3].setText(cauHoi.getPhuong_an_d());
        mSoCau.setText(ChiTietLuotChoi.mIndex_CauHoiHienTai + 1+ "");
        mDiem.setText("Điểm: " + ChiTietLuotChoi.mTongTiem);
        if(flagLoai2Cau){
            InvisibleButton(viewVisible,this.VISIBLE);
            flagLoai2Cau = false;
        }
    }

    //start/stop progress
    public void countDown(boolean flag) {
        if (flag) {
                mThreadCountdown.execute();
        } else {
            mThreadCountdown.cancel(true);
            mThreadCountdown = new RingProgressbarAsyntask(this, mRingProgressBar, progress,mMaxProgress, mSecond);
        }
    }


    //xử lý chọn đáp án
    public void XuLyDieuKienDapAn(final String stringDapAn) {
        //dừng đếm giây
        countDown(false);

        //đáp án đúng
        if (mListCauHoi.get(ChiTietLuotChoi.mIndex_CauHoiHienTai).getDap_an().equals(stringDapAn)) {

            //Thêm chi tiết sau khi xử lý đúng sai
            ThemChiTietLuotChoi(mListCauHoi.get(ChiTietLuotChoi.mIndex_CauHoiHienTai),stringDapAn);

            //tăng điểm
            ChiTietLuotChoi.mTongTiem = ChiTietLuotChoi.mTongTiem + mListCauHinhDiemCauHoi.get(ChiTietLuotChoi.mThuTuDiemCauHoi++).getDiem();

            //Thay đổi câu hỏi mới
            SetCauHoi(mListCauHoi.get(++ChiTietLuotChoi.mIndex_CauHoiHienTai));
            countDown(true);
        }//đáp án sai
        else{

            //xoá mạng
            XuLySetMang(false);

            //Thêm chi tiết sau khi xử lý đúng sai
            ThemChiTietLuotChoi(mListCauHoi.get(ChiTietLuotChoi.mIndex_CauHoiHienTai),stringDapAn);

            //create customDialog
            new CustomDialog(this) {
                @Override
                protected void onCreate(Bundle savedInstanceState) {

                    requestWindowFeature(Window.FEATURE_NO_TITLE);
                    setContentView(R.layout.dialog_thong_bao_tro_choi);

                    setCancelable(false);

                    //anh xa view
                    TextView mTitle = findViewById(R.id.txtTitle);
                    TextView mContent = findViewById(R.id.txtContent);
                    Button mButtonOk = findViewById(R.id.btnOk);

                    mTitle.setText("Thông Báo");
                    mContent.setText("Dáp án sai !");
                    mButtonOk.setText("Tiếp tục");


                    //set onclick cho notification
                    mButtonOk.setOnClickListener(this);
                }
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.btnOk) {
                        this.dismiss();
                        //update câu hỏi mới
                        SetCauHoi(mListCauHoi.get(++ChiTietLuotChoi.mIndex_CauHoiHienTai));
                        //xử lý trường hợp đáp án sai và mạng = 0
                        if (ChiTietLuotChoi.mSoLuotChoi >= 0) {
                            countDown(true);
                        }
                    }
                }
            }.show();
        }

    }

    public void XuLyQuaCau(){
        countDown(false);
        SetCauHoi(mListCauHoi.get(++ChiTietLuotChoi.mIndex_CauHoiHienTai));
        countDown(true);

    }
    public void InvisibleButton(int[] Id_Button, int mode) {
        for (int i = 0; i < Id_Button.length; i++) {
            mListDapAn_View[i].setVisibility(mode);
        }

    }

    public int Random(int a, int b){
        if(b == 0)
            return 0;
        int tmp = (int)(Math.random()*1000);
        return tmp % (b - (a - 1)) + a;
    }

    public void XuLyLoai2DapAn(){

        String dapan = mListCauHoi.get(ChiTietLuotChoi.mIndex_CauHoiHienTai).getDap_an();

        if (dapan.compareTo("B") > 0) {
            this.viewVisible[0] = 0;
            this.viewVisible[1] = 1;
            InvisibleButton(viewVisible,this.INVISIBLE);
        } else {
            this.viewVisible[0] = 2;
            this.viewVisible[1] = 3;
            InvisibleButton(viewVisible,this.INVISIBLE);
        }
        flagLoai2Cau = true;

    }

    public void XuLyKhaoSatKhanGia() {
        progress = mRingProgressBar.getProgress() + 1;
        countDown(false);
        String dapAn = mListCauHoi.get(ChiTietLuotChoi.mIndex_CauHoiHienTai).getDap_an();
        float [] percent = new float[4];
        int idDapAn = dapAn.equals("A")?0:(dapAn.equals("B")?1:(dapAn.equals("C")?2:3));

        percent[idDapAn] = Random(60, 100);
        int index[] = new int[3];
        int progressPercent = 100 - (int)percent[idDapAn];
        for(int i = 0 , j = 0; j < 4 ; j++){
            if (j != idDapAn) {

                index[i] = j;
                if(i != 2){
                    percent[index[i]] = Random(0,progressPercent);
                    progressPercent  -= (int)percent[index[i]];
                }
                i++;
            }
        }
        percent[index[2]] = progressPercent;


        new Dialog_Chart(this,percent){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                progress = 0;
                countDown(true);
            }
        }.show();

    }

    public void XuLyHoiNguoiThan() {
        progress = mRingProgressBar.getProgress() + 1;
        countDown(false);
        String dapAn = mListCauHoi.get(ChiTietLuotChoi.mIndex_CauHoiHienTai).getDap_an();

        new CustomDialog(this,"Đáp Án Từ Người Thân",dapAn, "Xin Cám Ơn", CustomDialog.SIZE_XL){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                progress = 0;
                countDown(true);
            }
        }.show();
    }

    public void XuLyMuaDapAn(){
        progress = mRingProgressBar.getProgress() + 1;
        countDown(false);
        String dapAn = mListCauHoi.get(ChiTietLuotChoi.mIndex_CauHoiHienTai).getDap_an();

        new CustomDialog(this,"Đáp án chương trình",dapAn, "Xin Cám Ơn", CustomDialog.SIZE_XL){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                progress = 0;
                user.setCredit(user.getCredit() - 100);
                mCredit.setText(user.getCredit()+"");
                new API_AsyncTask(this.Context(), NetworkUtils.POST, user.ConvertUserToMap()).execute("nguoi-choi/cap-nhat");
                countDown(true);
            }
        }.show();
    }

    public void XuLySetMang( boolean trangThai) {
        //true: full trái tim
        //false: trái tim rỗng

        if (trangThai) {
            mListCoHoiChoi[++ChiTietLuotChoi.mSoLuotChoi].setImageResource(R.drawable.traitimfull);
        } else {
            mListCoHoiChoi[ChiTietLuotChoi.mSoLuotChoi--].setImageResource(R.drawable.vientraitim);
        }
    }


    //xử lý button click
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.lnDapAnA:
                XuLyDieuKienDapAn("A");
                break;
            case R.id.lnDapAnB:
                XuLyDieuKienDapAn("B");
                break;
            case R.id.lnDapAnC:
                XuLyDieuKienDapAn("C");
                break;
            case R.id.lnDapAnD:
                XuLyDieuKienDapAn("D");
                break;
            case R.id.btnQuaCau:
                XuLyQuaCau();
                mBtnQuaCau.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnLoai2:
                XuLyLoai2DapAn();
                mBtnLoai2DapAn.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnKhanGia:
                XuLyKhaoSatKhanGia();
                mBtnKhanGia.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnGoiDien:
                XuLyHoiNguoiThan();
                mBtnGoiNguoiThan.setVisibility(View.INVISIBLE);
                break;
            case R.id.lnMuaDapAn:
                XuLyMuaDapAn();
                mLnMuaDapAn.setVisibility(View.INVISIBLE);
                break;

        }
    }


    //them chi tiết để lưu thông tin
    public void ThemChiTietLuotChoi(CauHoi cauHoi, String phuong_an) {
        //add chitiet
        mListChiTietLuotChoi.add(new ChiTietLuotChoi(cauHoi.getid(),phuong_an));


        if (ChiTietLuotChoi.mSoLuotChoi < 0) {

            // xử lý hết lượt chơi
            new CustomDialog(this){
                @Override
                protected void onCreate(Bundle savedInstanceState) {

                    requestWindowFeature(Window.FEATURE_NO_TITLE);
                    setContentView(R.layout.dialog_tro_choi_ket_thuc);
                    setCancelable(true);

                    //anh xa view
                    TextView mDiem = findViewById(R.id.txtDiem_dialog);
                    LinearLayout mMuaLuotChoi = findViewById(R.id.lnMuaLuotChoi);
                    LinearLayout mKetThuc = findViewById(R.id.lnKetThuc);


                    mDiem.setText(ChiTietLuotChoi.mTongTiem + "");

                    //set onclick
                    mMuaLuotChoi.setOnClickListener(this);
                    mKetThuc.setOnClickListener(this);
                }
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.lnMuaLuotChoi) {

                    } else if (v.getId() == R.id.lnKetThuc) {
                        this.dismiss();
                        finish();
                    }
                }
            }.show();
        }
    }


    //hết giờ
    @Override
    public void progressToComplete() {

        //dừng đếm thời gian
        countDown(false);
        //xoá mạng
        XuLySetMang(false);

        //show dialog
        new CustomDialog(this) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {

                requestWindowFeature(Window.FEATURE_NO_TITLE);
                setContentView(R.layout.dialog_thong_bao_tro_choi);
                setCancelable(true);

                //anh xa view
                TextView mTitle = findViewById(R.id.txtTitle);
                TextView mContent = findViewById(R.id.txtContent);
                Button mButtonOk = findViewById(R.id.btnOk);

                mTitle.setText("Thông Báo");
                mContent.setText("Hết Giờ !");
                mButtonOk.setText("Ok");

                //set onclick
                mButtonOk.setOnClickListener(this);
            }

            //xử lý onclick cho Dialog
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnOk) {
                    this.dismiss();
                    progress = 0;

                    //add chi tiết cho trường hợp hết giờ
                    ThemChiTietLuotChoi(mListCauHoi.get(ChiTietLuotChoi.mIndex_CauHoiHienTai), "Bỏ Trống");
                    //update câu hỏi mới
                    SetCauHoi(mListCauHoi.get(++ChiTietLuotChoi.mIndex_CauHoiHienTai));

                    if (ChiTietLuotChoi.mSoLuotChoi >= 0) {
                        countDown(true);
                    }
                }
            }
        }.show();

    }

    @Override
    public void onBackPressed() {

        countDown(false);
        super.onBackPressed();
    }

    @Override
    public void finish() {

        //reset lại các giá trị
        ChiTietLuotChoi.mTongTiem = 0;
        ChiTietLuotChoi.mSoLuotChoi = 4;
        ChiTietLuotChoi.mIndex_CauHoiHienTai = 0;
        ChiTietLuotChoi.mThuTuDiemCauHoi = 0;
        ChiTietLuotChoi.mTongSoCauDung = 0;
        super.finish();
    }
}
