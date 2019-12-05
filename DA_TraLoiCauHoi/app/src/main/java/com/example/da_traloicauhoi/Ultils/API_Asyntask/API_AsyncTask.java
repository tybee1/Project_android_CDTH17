package com.example.da_traloicauhoi.Ultils.API_Asyntask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.da_traloicauhoi.Ultils.Intergface.XuLyJSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class API_AsyncTask extends AsyncTask<String,Void,Void> implements XuLyJSON {


    private Context context;
    private ProgressDialog progressDialog;
    private String METHOD;
    private boolean flagRunProgressBar;
    private JSONObject jsonObjects;
    private Map<String, String> paramets;
    private String TitleProgress, MessageProgress;



    public API_AsyncTask(Context context,String METHOD, Map<String, String> paramets) {
        this.context = context;
        this.paramets = paramets;
        this.METHOD = METHOD;
        this.flagRunProgressBar = false;
    }

    public API_AsyncTask(Context context,String METHOD, Map<String, String> paramets,String TitleProgress, String MessageProgress) {
        this.context = context;
        this.paramets = paramets;
        this.METHOD = METHOD;
        this.TitleProgress = TitleProgress;
        this.MessageProgress = MessageProgress;
        this.flagRunProgressBar = true;


    }

    //thực hiện trên UI Thread khi doIn đang thực thi
    @Override
    protected void onPreExecute() {
        if(flagRunProgressBar)
            createProgressBar();


    }

    public void createProgressBar(){
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(this.TitleProgress);
        progressDialog.setMessage(this.MessageProgress);
        progressDialog.show();
    }


    //làm tác vụ trong nền
    @Override
    protected Void doInBackground(String... urls) {
        String jsonString = NetworkUtils.getJSONData(urls[0],this.METHOD, this.paramets);
        try {
            this.jsonObjects = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //khởi chạy một interface tự định nghĩa
        try {
            Log.d("result",jsonObjects.toString());
            XuLy(jsonObjects, context);

        } catch (JSONException e) {
            //Log.d("xuly",e.toString());
            e.printStackTrace();

        }

        if(flagRunProgressBar)
            progressDialog.dismiss();
    }

    @Override
    public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
        //interface xu ly response
    }



}
