package cn.rexwear.wearrex.utils;

import static cn.rexwear.wearrex.Application.TAG;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import cn.rexwear.wearrex.Application;
import cn.rexwear.wearrex.R;

public class TimeUtils extends Thread {
    public TextView tvDate;

    public TimeUtils(TextView tvDate) {
        this.tvDate = tvDate;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
                Message msg = new Message();
                msg.what = 22;
                mHandler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 22) {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String date = sdf.format(new Date());

                tvDate.setText(date);
            }
        }
    };

    public static String getGreeting() {
        int currentHour;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime localDateTime = LocalDateTime.now();
            currentHour = localDateTime.getHour();
        } else {
            Calendar calendar = Calendar.getInstance();
            currentHour = calendar.get(Calendar.HOUR);
        }
        Log.d(TAG, "getGreeting: " + currentHour);
        if (currentHour > 5 && currentHour < 12) {
            return Application.getContext().getString(R.string.goodMorningText);
        } else if (currentHour > 12 && currentHour < 18) {
            return Application.getContext().getString(R.string.goodAfternoonText);
        } else if (currentHour > 18 && currentHour < 23) {
            return Application.getContext().getString(R.string.goodEveningText);
        }
        return Application.getContext().getString(R.string.goodNightText);
    }
}