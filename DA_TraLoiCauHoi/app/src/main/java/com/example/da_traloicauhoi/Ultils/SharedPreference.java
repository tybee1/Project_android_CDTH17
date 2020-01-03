package com.example.da_traloicauhoi.Ultils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private static String SHARE_PREFERENCE = "storage_img";
    public static boolean writeFile(Context context, String fileName, String content){
            SharedPreferences.Editor editor = context.getSharedPreferences(SHARE_PREFERENCE,context.MODE_PRIVATE).edit();
            editor.putString(fileName,content);
        return editor.commit();
    }
    public static String readFile(Context context, String fileName){
        SharedPreferences  sharedPreferences =  context.getSharedPreferences(SHARE_PREFERENCE,context.MODE_PRIVATE);

        return sharedPreferences.getString(fileName,"null");
    }
}
