package cn.rexwear.wearrex;

import static cn.rexwear.wearrex.MainActivity.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import cn.rexwear.wearrex.beans.UserBean;

public class WelcomeLoginFragment extends Fragment {
    private static final String PREFS_NAME = "WEAREXSHP";

    UserBean userBean;
    public WelcomeLoginFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = (UserBean) getArguments().getSerializable("userBean");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = getView().findViewById(R.id.textView2);
        ImageButton go = view.findViewById(R.id.go);
        if (!userBean.user.username.equals("")) {
            textView.setText("欢迎!\n" + userBean.user.username);
        }
        ImageView imageView = getView().findViewById(R.id.imageView);
        if (userBean.user.avatarUrls.getM() != null) {
            Glide.with(this).load(Uri.parse(userBean.user.avatarUrls.getH())).circleCrop().placeholder(R.drawable.ic_baseline_account_circle_24).into(imageView);
        }

        go.setOnClickListener(view1 -> {
            saveUserInfo(userBean.user.userId);
            startActivity(new Intent(getContext(), HomeActivity.class));
            getActivity().finish();
        });
    }

    private void saveUserInfo(int userID) {
        SharedPreferences userInfo = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.putInt("userID", userID);
        editor.commit();//提交修改
        Log.i(TAG, "保存用户信息成功");
    }
}