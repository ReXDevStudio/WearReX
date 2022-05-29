package cn.rexwear.wearrex.fragments.home;

import static cn.rexwear.wearrex.Application.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.activities.WelcomeActivity;
import cn.rexwear.wearrex.beans.UserBean;
import cn.rexwear.wearrex.databinding.FragmentProfileBinding;
import cn.rexwear.wearrex.managers.UserManager;
import cn.rexwear.wearrex.utils.NetworkUtils;
import cn.rexwear.wearrex.utils.TimeUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProfileFragment extends Fragment {
    final ExecutorService mThreadPool = Executors.newCachedThreadPool();
    FragmentProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.hitokoto.setOnClickListener(view1 -> {
            binding.hitokoto.setText(R.string.loadingText);
            GetHitokoto();
        });
        if (UserManager.getUserID() == -1) {       //获取登录状态
            if (UserManager.getUserIsExperiment()) {       //获取是否为游客
                String str = this.getString(R.string.currentUserTextWithColons) + this.getString(R.string.touristText); //拼接字符串
                binding.currentUser.setText(str);
                binding.logout.setText(R.string.loginText);
                binding.logout.setEnabled(true);
            } else {
                startActivity(new Intent(requireActivity(), WelcomeActivity.class));        //打开登录activity
                requireActivity().finish();
            }
        } else {
            NetworkUtils.getUrl("/me", new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.d(TAG, "onFailure: " + e.getMessage());
                    mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> Toast.makeText(requireActivity(), requireActivity().getString(R.string.failedToFetchUsernameText), Toast.LENGTH_SHORT).show()));    //失败报错Toast
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> {
                        if (response.code() == 200) {
                            try {
                                UserBean user = (new Gson()).fromJson(Objects.requireNonNull(response.body()).string(), UserBean.class);    //获取User实体类
                                String currentUserName = user.me.username;
                                String str = TimeUtils.getGreeting() + getString(R.string.commaText) + currentUserName;     //拼接字符串
                                Glide.with(view).load(Uri.parse(user.me.avatarUrls.getH())).circleCrop().placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.avatarImageView);       //加载头像
                                binding.currentUser.setText(str);
                                binding.logout.setText(R.string.logout);
                                binding.logout.setEnabled(true);
                                GetHitokoto();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(requireActivity(), R.string.failedToFetchUsernameText, Toast.LENGTH_SHORT).show();       //失败
                        }
                    }));
                }
            });
        }

        binding.logout.setOnClickListener(view1 -> {
            UserManager.deleteAllUserInfo();
            startActivity(new Intent(requireActivity(), WelcomeActivity.class));        //打开登录activity
            requireActivity().finish();
        });
    }

    void GetHitokoto() {
        NetworkUtils.getUrlWithCustomDomain("https://v1.hitokoto.cn?encode=text&c=d&c=i&c=k", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(requireActivity(), R.string.failedToFetchHitokotoText, Toast.LENGTH_SHORT).show();       //失败
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String str = Objects.requireNonNull(response.body()).string();
                mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> binding.hitokoto.setText(str)));
            }
        });
    }
}