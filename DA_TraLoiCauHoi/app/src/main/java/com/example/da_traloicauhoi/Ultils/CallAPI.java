package com.example.da_traloicauhoi.Ultils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class CallAPI {

    public static final int GET=0;
    public static final int POST=1;

    public static void RequestGET(String url, final Map<String, String>[] paramets, Context context, final JSONObject[] jsonObject, final boolean[] flag){

        final RequestQueue requestQueue =  Volley.newRequestQueue(context);

        StringRequest   stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //sau khi nhận được phản hồi sẽ bật cờ hiệu để thông báo
                        flag[0] = true;
                        //lưu JSONObject
                        try {
                            jsonObject[0] = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("Respone_Get","ok");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return paramets[0];
            }
        };
        requestQueue.add(stringRequest);
    }
    public static void RequestPost(String url, final Map<String, String>[] paramets, Context context,final JSONObject[] jsonObject, final boolean[] flag){

        final RequestQueue requestQueue =  Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //sau khi nhận được phản hồi sẽ bật cò để thông báo
                        flag[0] = true;
                        try {
                            jsonObject[0] = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("Respone_Post","ok");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return paramets[0];
            }
        };

        requestQueue.add(stringRequest);

    }

}
