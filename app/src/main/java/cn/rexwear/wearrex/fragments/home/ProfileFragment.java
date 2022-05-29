package cn.rexwear.wearrex.fragments.home;

import static cn.rexwear.wearrex.activities.WelcomeActivity.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        if (UserManager.getUserID() == -1) {       //获取登录状态
            if (UserManager.getUserIsExperiment()) {       //获取是否为游客
                String str = this.getString(R.string.currentUserTextWithColons) + this.getString(R.string.touristText);
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
                    mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> Toast.makeText(requireActivity(), requireActivity().getString(R.string.failedToFetchUsernameText), Toast.LENGTH_SHORT).show()));
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> {
                        if (response.code() == 200) {
                            try {
                                UserBean user = (new Gson()).fromJson(Objects.requireNonNull(response.body()).string(), UserBean.class);
                                String currentUserName = user.me.username;
                                String str = requireActivity().getString(R.string.currentUserTextWithColons) + currentUserName;
                                binding.currentUser.setText(str);
                                binding.logout.setText(R.string.logout);
                                binding.logout.setEnabled(true);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(requireActivity(), R.string.failedToFetchUsernameText, Toast.LENGTH_SHORT).show();
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
}