package cn.rexwear.wearrex.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.databinding.ActivityHomeBinding;
import cn.rexwear.wearrex.utils.GetSharedPreferences;
import cn.rexwear.wearrex.utils.TimeThread;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(HomeActivity.this, R.layout.activity_home);
        TimeThread timeThread = new TimeThread(binding.hometime);   //新建一个获取时间的进程
        timeThread.start();     //开始获取时间

        if (GetSharedPreferences.getUserID() == -1) {       //获取登录状态
            if (GetSharedPreferences.getUserIsExperiment()) {       //获取是否为游客
                binding.logout.setText("登录");
            } else {
                startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));        //打开登录activity
                finish();
            }
        }

        binding.logout.setOnClickListener(view -> {
            GetSharedPreferences.DeleteUserInfo();      //删除userID
            startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));        //打开登录activity
            finish();
        });
    }
}