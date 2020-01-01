package com.example.da_traloicauhoi.Ultils.API_Asyntask;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Window;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.Intergface.XuLyJSON;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class API_AsyncTask extends AsyncTask<String,Void,Void> implements XuLyJSON {


    private Context context;
    private Dialog dialog;
    private String METHOD;
    private boolean flagRunProgressBar;
    private JSONObject jsonObjects;
    private Map<String, String> paramets;
    private String TitleProgress, MessageProgress;



    //khởi tạo không có progressbarDialog
    public API_AsyncTask(Context context,String METHOD, Map<String, String> paramets) {
        this.context = context;
        this.paramets = paramets;
        this.METHOD = METHOD;
        this.flagRunProgressBar = false;
    }

    //khởi tạo có progressbarDialog
    public API_AsyncTask(Context context,String METHOD, Map<String, String> paramets,String TitleProgress, String MessageProgress) {
        this.context = context;
        this.paramets = paramets;
        this.METHOD = METHOD;
        this.TitleProgress = TitleProgress;
        this.MessageProgress = MessageProgress;
        this.flagRunProgressBar = true;



    }


    //thực hiện trên trên giao diện chính khi doIn đang thực thi
    @Override
    protected void onPreExecute() {

        //kiểm tra xem có cần progressbar Hay Khong
        if(flagRunProgressBar)
            createProgressBar();



    }

    //tạo progressbarDialog
    public void createProgressBar(){

        this.dialog = new Dialog(this.context);
        dialog.setContentView(R.layout.loading);
        dialog.setCancelable(true);
        dialog.show();
    }


    //làm tác vụ trong nền
    @Override
    protected Void doInBackground(String... urls) {
        for (int i = 0; i < 2; i++) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //gọi api và nhận về chuỗi json
        String jsonString = NetworkUtils.getJSONData(urls[0],this.METHOD, this.paramets);
        try {
            //tạo json bằng thuộc tính của đối tượng đã khai báo bằng chuôi json trả về
            this.jsonObjects = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


    //phương thức này sẽ được gọi khi quá gọi api xong
    @Override
    protected void onPostExecute(Void aVoid) {

        //khởi chạy một interface tự định nghĩa
        try {
            Log.d("result",jsonObjects.toString());
            XuLy(jsonObjects, context);// đây là interface (xu ly o day)

        } catch (JSONException e) {
            //Log.d("xuly",e.toString());
            e.printStackTrace();

        }

        //stop progressBarDialog
        if(flagRunProgressBar)
        {
            this.dialog.dismiss();
        }


    }

    @Override
    public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
        //interface xu ly response
    }



}
