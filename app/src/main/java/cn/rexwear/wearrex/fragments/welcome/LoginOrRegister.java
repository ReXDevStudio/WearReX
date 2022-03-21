package cn.rexwear.wearrex.fragments.welcome;

import android.os.Bundle;
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


public class LoginOrRegister extends Fragment {
    ImageButton login;
    ImageButton register;

    public LoginOrRegister() {
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
        return inflater.inflate(R.layout.fragment_login_or_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login = getView().findViewById(R.id.yes);
        register = getView().findViewById(R.id.no);

        login.setOnClickListener(view1 -> {
            NavController controller = Navigation.findNavController(view1);
            controller.navigate(R.id.action_loginOrUp_to_login);
        });

        register.setOnClickListener(view1 -> {
            NavController controller = Navigation.findNavController(view1);
            controller.navigate(R.id.action_loginOrUp_to_registerFragment);
        });
    }
}