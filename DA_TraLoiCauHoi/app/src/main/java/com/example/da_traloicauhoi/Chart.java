package com.example.da_traloicauhoi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.da_traloicauhoi.Ultils.PercentFormatter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Chart extends AppCompatActivity {

    private PieChart pieChart;
    private float[] ydata = {30, 10, 50, 10};
    private String[] xdata = {"Đáp Án A", "Đáp Án B", "Đáp Án C", "Đáp Án D"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        pieChart = (PieChart) findViewById(R.id.pieChart);
        addData((pieChart));

    }

    public void addData(PieChart pieChart) {
        ArrayList<PieEntry> yEntry = new ArrayList<>();

        for (int i = 0; i < ydata.length; i++) {
            yEntry.add(new PieEntry(ydata[i],xdata[i]));
        }


        PieDataSet pieDataSet = new PieDataSet(yEntry, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(20);
        pieDataSet.setValueFormatter(new PercentFormatter(pieChart));



        PieData piedata = new PieData(pieDataSet);
        pieChart.setDrawEntryLabels(false);
        pieChart.animateY(1000);
        pieChart.setCenterText("ý Kiến Khá Giả");
        pieChart.setData(piedata);
        pieChart.invalidate();
    }
}
