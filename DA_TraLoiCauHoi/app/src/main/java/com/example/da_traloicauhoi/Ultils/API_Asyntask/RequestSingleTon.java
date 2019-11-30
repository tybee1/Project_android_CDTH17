package com.example.da_traloicauhoi.Ultils.API_Asyntask;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestSingleTon {
    private static RequestSingleTon mInstance;
    private RequestQueue requestQueue;

    private RequestSingleTon(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    public static synchronized RequestSingleTon getInstance(Context context){
        if (mInstance == null) {
            mInstance = new RequestSingleTon(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
