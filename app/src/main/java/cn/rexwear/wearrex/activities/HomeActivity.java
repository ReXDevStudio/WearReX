package cn.rexwear.wearrex.activities;

import static cn.rexwear.wearrex.activities.WelcomeActivity.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.beans.UserBean;
import cn.rexwear.wearrex.databinding.ActivityHomeBinding;
import cn.rexwear.wearrex.utils.NetworkUtils;
import cn.rexwear.wearrex.utils.SharedPreferencesUtils;
import cn.rexwear.wearrex.utils.TimeThread;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    final ExecutorService mThreadPool = Executors.newCachedThreadPool();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(HomeActivity.this, R.layout.activity_home);
        TimeThread timeThread = new TimeThread(binding.hometime);   //新建一个获取时间的进程
        timeThread.start();     //开始获取时间

        if (SharedPreferencesUtils.getUserID() == -1) {       //获取登录状态
            if (SharedPreferencesUtils.getUserIsExperiment()) {       //获取是否为游客
                String str = this.getString(R.string.currentUserTextWithColons) + this.getString(R.string.touristText);
                binding.currentUser.setText(str);
                binding.logout.setText(R.string.loginText);
            } else {
                startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));        //打开登录activity
                finish();
            }
        } else {
            NetworkUtils.getUrl("/users/" + SharedPreferencesUtils.getUserID(), new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.d(TAG, "onFailure: " + e.getMessage());
                    mThreadPool.execute(() -> HomeActivity.this.runOnUiThread(() -> Toast.makeText(HomeActivity.this, HomeActivity.this.getString(R.string.failedToFetchUsernameText), Toast.LENGTH_SHORT).show()));
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    mThreadPool.execute(() -> HomeActivity.this.runOnUiThread(() -> {
                        if (response.code() == 200) {
                            try {
                                UserBean userBean = (new Gson()).fromJson(Objects.requireNonNull(response.body()).string(), UserBean.class);
                                String currentUserName = userBean.user.username;
                                String str = HomeActivity.this.getString(R.string.currentUserTextWithColons) + currentUserName;
                                binding.currentUser.setText(str);
                                binding.logout.setText(R.string.logout);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }));
                }
            });
        }

        binding.logout.setOnClickListener(view -> {
            SharedPreferencesUtils.DeleteUserInfo();      //删除userID
            startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));        //打开登录activity
            finish();
        });
    }
}