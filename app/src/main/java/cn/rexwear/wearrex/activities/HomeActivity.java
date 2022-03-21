package cn.rexwear.wearrex.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.databinding.ActivityHomeBinding;
import cn.rexwear.wearrex.utils.TimeThread;

public class HomeActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "WEAREXSHP";
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(HomeActivity.this, R.layout.activity_home);
        TimeThread timeThread = new TimeThread(binding.hometime);   //新建一个获取时间的进程
        timeThread.start();     //开始获取时间

        binding.logout.setOnClickListener(view -> {
            DeleteUserInfo();
            startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));
            finish();
        });
    }

    void DeleteUserInfo() {
        SharedPreferences userInfo = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.remove("userID");
        editor.remove("isExperiment");
        editor.commit();//提交修改
    }
}