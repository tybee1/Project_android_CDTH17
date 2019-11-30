package com.example.da_traloicauhoi.Ultils.API_Asyntask;

import android.content.Context;
import android.os.AsyncTask;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class RingProgressbarAsyntask extends AsyncTask<Void,Integer, Void> {

    private RingProgressBar ringProgressBar;
    private Context context;
    private int start;
    private int end;

    public RingProgressbarAsyntask( Context context,RingProgressBar ringProgressBar, int start, int end) {
        this.ringProgressBar = ringProgressBar;
        this.context = context;
        this.start = start;
        this.end = end;
    }

    public RingProgressbarAsyntask(Context context, RingProgressBar ringProgressBar, int end) {
        this.ringProgressBar = ringProgressBar;
        this.context = context;
        this.start = 0;
        this.end = end;
    }

    @Override
    protected Void doInBackground(Void... Void) {
        for (int i = start; i <= end; i++) {
            if (!this.isCancelled()) {
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
//        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        ringProgressBar.setProgress(values[0]);
    }
}