package com.example.da_traloicauhoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class TroChoiActivity extends AppCompatActivity {

    RingProgressBar ringProgressBar;
    int progress = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == 0){
                if(progress < 100){
                    progress++;
                    ringProgressBar.setProgress(progress);
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tro_choi);
        ringProgressBar = findViewById(R.id.ringProgress);
        ringProgressBar.setOnProgressListener(new RingProgressBar.OnProgressListener() {
            @Override
            public void progressToComplete() {

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 30 ; i++){
                    try {
                        Thread.sleep(1000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}