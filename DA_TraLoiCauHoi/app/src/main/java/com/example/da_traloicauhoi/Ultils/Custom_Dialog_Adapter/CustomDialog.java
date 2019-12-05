package com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.da_traloicauhoi.R;

import java.util.List;

public class CustomDialog extends Dialog implements android.view.View.OnClickListener  {

    private Context context;
    private String title;
    private String content;
    private String textButton;
    private int SIZE;
    public static int SIZE_M = 15, SIZE_L = 20;

    public CustomDialog(@NonNull Context context, String title, String content, String textButton, int SIZE) {
        super(context);
        this.context = context;
        this.title = title;
        this.content  = content;
        this.textButton = textButton;
        this.SIZE = SIZE;
    }

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_thong_bao_tro_choi);

        setCancelable(true);

        //anh xa view
        TextView mTitle = findViewById(R.id.txtTitle);
        TextView mContent = findViewById(R.id.txtContent);
        Button mButtonOk = findViewById(R.id.btnOk);

        mTitle.setText(this.title);
        mContent.setText(this.content);
        mButtonOk.setText(this.textButton);
        mContent.setTextSize(this.SIZE);

        //set onclick
        mButtonOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }
}

