package com.example.da_traloicauhoi.Ultils;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIAsyncTask extends AsyncTask<String,Void,String> implements XuLyJSON {

    private Context context;
    private ProgressDialog progressDialog;
    private int METHOD;
    private boolean flagRunProgressBar;
    private final JSONObject[] jsonObjects;
    private  final Map<String, String>[] paramets;



    public APIAsyncTask(Context context,int METHOD, Map<String, String> paramets) {
        this.context = context;
        this.jsonObjects = new JSONObject[1];
        this.paramets = new HashMap[1];
        this.paramets[0] = paramets;
        this.METHOD = METHOD;
        this.flagRunProgressBar = true;


    }

    //thực hiện trên UI Thread khi doIn đang thực thi
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

       createProgressBar();


    }

    public void createProgressBar(){
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }


    //làm tác vụ trong nền
    @Override
    protected String doInBackground(String... urls) {
        final boolean [] flag = new boolean[1];

        //call API
        if(METHOD == CallAPI.GET)
            CallAPI.RequestGET(urls[0],paramets, context, jsonObjects, flag);
        else
            CallAPI.RequestPost(urls[0],paramets , context,jsonObjects, flag);


        //Chờ API Load API.
        while(flag[0] == false) {}

        return null;
    }

    //sau khi doIn thực thi xong và trả về kết quả cho tham số của hàm này
    @Override
    protected void onPostExecute(@Nullable String string) {

        //khởi chạy một interface tự định nghĩa
        try {
            XuLy(jsonObjects[0], context);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(flagRunProgressBar)
            progressDialog.dismiss();
        Log.d("onPost", "ok");

    }

    @Override
    public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
        //interface xu ly response
    }



}
