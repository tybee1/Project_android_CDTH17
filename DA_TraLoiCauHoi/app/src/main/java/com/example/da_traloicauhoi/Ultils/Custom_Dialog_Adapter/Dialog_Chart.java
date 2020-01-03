package com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.PercentFormatter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Dialog_Chart extends Dialog implements android.view.View.OnClickListener {

    private Context context;
    private PieChart pieChart;
    private float[] ydata;
    private String[] xdata = {"Đáp Án A", "Đáp Án B", "Đáp Án C", "Đáp Án D"};

    public Dialog_Chart(@NonNull Context context, float [] percent) {
        super(context);
        this.context = context;
        this.ydata = percent;


        for(int i = 0 ; i < 4 ; i++){
            xdata[i] += " (" + (int)ydata[i] + "%)";
        }

    }

    public void addData(PieChart pieChart) {
        ArrayList<PieEntry> yEntry = new ArrayList<>();

        for (int i = 0; i < ydata.length; i++) {
            yEntry.add(new PieEntry(ydata[i],xdata[i]));
        }


        PieDataSet pieDataSet = new PieDataSet(yEntry, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueFormatter(new PercentFormatter(pieChart));



        PieData piedata = new PieData(pieDataSet);
        pieChart.setDrawEntryLabels(false);
        pieChart.animateY(1000);
        pieChart.setCenterText("ý Kiến Khán Giả");
        pieChart.setData(piedata);
        pieChart.invalidate();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chart);

        setCancelable(true);

        this.pieChart = (PieChart) findViewById(R.id.pieChart);
        addData((pieChart));

        Button mButtonOk = findViewById(R.id.btnOkChart);

        //set onclick
        mButtonOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnOkChart) {
            this.dismiss();
        }
    }
}
