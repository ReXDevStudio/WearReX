package cn.rexwear.wearrex;


import static cn.rexwear.wearrex.MainActivity.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "WEAREXSHP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (getUsername() == -1) {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        }
        TimeThread timeThread = new TimeThread(findViewById(R.id.hometime));   //新建一个获取时间的进程
        timeThread.start();     //开始获取时间
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getUsername() == -1) {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        }
    }

    /**
     * 读取用户信息
     */
    private int getUsername() {
        SharedPreferences userInfo = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int username = userInfo.getInt("userID", -1);//读取username
        Log.i(TAG, "读取用户信息");
        Log.i(TAG, "username:" + username);
        return username;
    }
}