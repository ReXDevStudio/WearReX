package cn.rexwear.wearrex.fragments.welcome;

import static cn.rexwear.wearrex.activities.WelcomeActivity.TAG;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.beans.UserBean;
import cn.rexwear.wearrex.utils.NetworkUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends Fragment {
    ImageButton ok;
    ImageButton buOK;

    EditText user;
    EditText password;

    TextView textViewTitle;
    TextView textViewTitle2;

    boolean isEnteringPassword = false;

    final ExecutorService mThreadPool = Executors.newCachedThreadPool();


    public Login() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ok = requireView().findViewById(R.id.yes);
        buOK = requireView().findViewById(R.id.no);


        user = requireView().findViewById(R.id.userName);
        password = requireView().findViewById(R.id.password);

        textViewTitle = requireView().findViewById(R.id.loginTitle);
        textViewTitle2 = requireView().findViewById(R.id.loginTitle2);

        ok.setOnClickListener(view1 -> {
            if (!isEnteringPassword) {
                isEnteringPassword = true;
                user.setVisibility(View.INVISIBLE);
                password.setVisibility(View.VISIBLE);
                textViewTitle.setTextColor(Color.parseColor("#90CAF9"));
                textViewTitle.setText("输入密码");
                textViewTitle2.setText("区分大小写");
                ok.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_check_24, null));
                buOK.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_arrow_back_24, null));
            }else {
                if (!TextUtils.isEmpty(user.getText()) | !TextUtils.isEmpty(password.getText())) {
                    LoginWithOkHttp(user.getText().toString(), password.getText().toString());
                } else {
                    Toast.makeText(getContext(), "请正确填写ID和密码", Toast.LENGTH_LONG).show();
                }
            }
        });

        buOK.setOnClickListener(view12 -> {
            if(!isEnteringPassword) {

                NavController controller = Navigation.findNavController(view12);
                controller.navigateUp();
                //controller.navigate(R.id.action_login_to_loginOrUp);
            } else{
                isEnteringPassword = false;
                password.setVisibility(View.INVISIBLE);
                user.setVisibility(View.VISIBLE);
                textViewTitle.setTextColor(Color.parseColor("#90CAF9"));
                ok.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.btn_round_light_blue, null));
                textViewTitle.setText("输入用户名/邮箱");
                textViewTitle2.setText("不支持ID登陆");
                ok.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_arrow_forward_ios_24, null));
                buOK.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_close_24, null));
            }

        });
    }


    void LoginWithOkHttp(String userName, String passWord) {
        Log.d(TAG, "Login: userName: " + userName + ", password: " + passWord);

        ok.setEnabled(false);
        buOK.setEnabled(false);
        password.setEnabled(false);
        textViewTitle.setTextColor(Color.parseColor("#90CAF9"));
        ok.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.btn_round_light_blue, null));
        textViewTitle.setText("校验中...");

        RequestBody body = new FormBody.Builder()
                .add("login", userName)
                .add("password", passWord)
                .build();

        NetworkUtils.postUrl("/auth", body, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
                mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> {
                    textViewTitle.setText("校验失败，请重新登陆");
                    textViewTitle.setTextColor(Color.parseColor("#F2B8B5"));
                    ok.setEnabled(true);
                    buOK.setEnabled(true);
                    password.setEnabled(true);
                }));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.code());

                //https://github.com/XC-Qan/WearReX/issues/4
                if(response.code() == 200){
                    //Log.d(TAG, "onResponse: " + response.body().string());
                    UserBean user = UserBean.objectFromData(Objects.requireNonNull(response.body()).string());

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userBean", user);
                    Log.d(TAG, "onResponse: 登录成功，欢迎" + user.user.username);

                    mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> {
                        textViewTitle.setTextColor(Color.parseColor("#90CAF9"));
                        ok.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.btn_round_light_blue, null));
                        textViewTitle.setText("登录成功");
                        NavController controller = Navigation.findNavController(requireView());
                        controller.navigate(R.id.action_login_to_welcomeLoginFragment, bundle);
                    }));

                }
                else{
                    Log.d(TAG, "onResponse: 登录失败  " + Objects.requireNonNull(response.body()).string());
                    if(response.code() == 400){
                        mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> {
                            textViewTitle.setText("输入信息有误");
                            textViewTitle.setTextColor(Color.parseColor("#F2B8B5"));
                            //https://github.com/XC-Qan/WearReX/issues/5
                            ok.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.btn_round_light_pink, null));
                            ok.setEnabled(true);
                            buOK.setEnabled(true);
                            password.setEnabled(true);
                        }));
                    }
                    else{
                        mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> {
                            textViewTitle.setText("校验失败，请重新登陆");
                            textViewTitle.setTextColor(Color.parseColor("#F2B8B5"));
                            //https://github.com/XC-Qan/WearReX/issues/5
                            ok.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.btn_round_light_pink, null));
                            ok.setEnabled(true);
                            buOK.setEnabled(true);
                            password.setEnabled(true);
                        }));
                    }
                }
            }
        });
    }
}