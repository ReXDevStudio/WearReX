package cn.rexwear.wearrex.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.adapters.HomeViewPagerAdapter;
import cn.rexwear.wearrex.databinding.ActivityHomeBinding;
import cn.rexwear.wearrex.utils.TimeUtils;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(HomeActivity.this, R.layout.activity_home);
        TimeUtils timeThread = new TimeUtils(binding.hometime);   //新建一个获取时间的进程
        timeThread.start();     //开始获取时间
        binding.viewPager.setAdapter(new HomeViewPagerAdapter(HomeActivity.this));


    }


}