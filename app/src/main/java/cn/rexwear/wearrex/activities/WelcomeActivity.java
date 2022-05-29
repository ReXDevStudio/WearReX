package cn.rexwear.wearrex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.managers.UserManager;
import cn.rexwear.wearrex.utils.TimeUtils;


public class WelcomeActivity extends AppCompatActivity {
    TextView textViewTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTime = findViewById(R.id.time);
        TimeUtils timeThread = new TimeUtils(textViewTime);   //新建一个获取时间的进程
        timeThread.start();     //开始获取时间
        if (UserManager.getUserIsExperiment()) {
            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            finish();
        }
        if (UserManager.getUserID() != -1) {
            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            finish();
        }
    }


    //https://github.com/XC-Qan/WearReX/issues/6


}