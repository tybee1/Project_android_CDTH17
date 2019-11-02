package com.example.da_traloicauhoi.UI_Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.da_traloicauhoi.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void handleLogin(View view) {
        Intent intent = new Intent(MainActivity.this,ManHinhChinhActivity.class);
        startActivity(intent);
    }
}
