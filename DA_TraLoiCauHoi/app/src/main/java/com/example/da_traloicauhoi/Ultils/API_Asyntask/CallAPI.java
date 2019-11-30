package com.example.da_traloicauhoi.Ultils.API_Asyntask;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class CallAPI {

    public static final int GET=0;
    public static final int POST=1;

    public static void RequestGET(String url, final Map<String, String>[] paramets, Context context, final JSONObject[] jsonObject, final boolean[] flag){

        final RequestQueue requestQueue =  RequestSingleTon.getInstance(context).getRequestQueue();

        //biến request
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
                    }
                },//lỗi kết nối
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        flag[0] = true;
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

        final RequestQueue requestQueue =  RequestSingleTon.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //sau khi nhận được phản hồi sẽ bật cò để thông báo
                        flag[0] = true;
                        try {
                            jsonObject[0] = new JSONObject(response);
                        } catch (JSONException e) {
                            Log.d("onResponse_Exception",e.toString());;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String jsonString = "{'success'=>false}";
                        try {
                            jsonObject[0] = new JSONObject(jsonString);
                        } catch (JSONException e) {
                            Log.d("ErrorResponse_Exception",e.toString());;
                        }
                        flag[0]=true;
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
