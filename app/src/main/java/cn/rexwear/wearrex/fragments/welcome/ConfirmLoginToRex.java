package cn.rexwear.wearrex.fragments.welcome;

import static cn.rexwear.wearrex.activities.WelcomeActivity.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.activities.HomeActivity;

public class ConfirmLoginToRex extends Fragment {

    ImageButton ok;
    ImageButton no;
    private static final String PREFS_NAME = "WEAREXSHP";


    public ConfirmLoginToRex() {
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
        return inflater.inflate(R.layout.fragment_confirm_login_to_rex, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        no = getView().findViewById(R.id.no);
        ok = getView().findViewById(R.id.yes);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserIsExperiment(true);
                Intent intent = new Intent(getContext(), HomeActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserIsExperiment(false);
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_confirmLoginToRex_to_loginOrUp);
            }
        });
    }

    private void saveUserIsExperiment(boolean bool) {
        SharedPreferences userInfo = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.putBoolean("isExperiment", bool);
        editor.commit();//提交修改
        Log.i(TAG, "保存用户信息成功");
    }
}