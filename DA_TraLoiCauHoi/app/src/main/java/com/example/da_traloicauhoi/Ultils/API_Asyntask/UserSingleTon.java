package com.example.da_traloicauhoi.Ultils.API_Asyntask;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.da_traloicauhoi.Object_Model.User;

import org.json.JSONObject;

public class UserSingleTon {
    private static UserSingleTon mInstance;
    private User user;

    private UserSingleTon(JSONObject jsonObject){
        user = new User(jsonObject);
    }

    public static synchronized UserSingleTon getInstance(JSONObject jsonObject){
        if (mInstance == null) {
            mInstance = new UserSingleTon(jsonObject);
        }
        return mInstance;
    }

    public  User getUser() {
        return this.user;
    }
}
