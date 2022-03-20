package cn.rexwear.wearrex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "Wear ReX TAG";
    private static final String PREFS_NAME = "WEAREXSHP";
    TextView textViewTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTime = findViewById(R.id.time);
        if (getUsername() != -1) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }
        TimeThread timeThread = new TimeThread(textViewTime);   //新建一个获取时间的进程
        timeThread.start();     //开始获取时间
    }


    //https://github.com/XC-Qan/WearReX/issues/6
/*    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK ) {
            //do something.
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }*/


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