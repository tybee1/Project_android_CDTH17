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

public class APIAsyncTask extends AsyncTask<String,Void,String> implements XuLyJSON {

    private Context context;
    private ProgressDialog progressDialog;
    private int METHOD;
    private boolean flagRunProgressBar;
    private final JSONObject[] jsonObjects;
    private  final Map<String, String>[] paramets;
    private String TitleProgress, MessageProgress;



    public APIAsyncTask(Context context,int METHOD, Map<String, String> paramets) {
        this.context = context;
        this.jsonObjects = new JSONObject[1];
        this.paramets = new HashMap[1];
        this.paramets[0] = paramets;
        this.METHOD = METHOD;
        this.flagRunProgressBar = false;
    }

    public APIAsyncTask(Context context,int METHOD, Map<String, String> paramets,String TitleProgress, String MessageProgress) {
        this.context = context;
        this.jsonObjects = new JSONObject[1];
        this.paramets = new HashMap[1];
        this.paramets[0] = paramets;
        this.METHOD = METHOD;
        this.TitleProgress = TitleProgress;
        this.MessageProgress = MessageProgress;
        this.flagRunProgressBar = true;


    }

    //thực hiện trên UI Thread khi doIn đang thực thi
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

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
    protected String doInBackground(String... urls) {

        //đặt cờ hiệu chờ phản hồi
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
            Log.d("result",jsonObjects[0].toString());
            XuLy(jsonObjects[0], context);

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
