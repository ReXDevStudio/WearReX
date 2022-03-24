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

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
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
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton go = getView().findViewById(R.id.yes);
        go.setOnClickListener(view1 -> {
            NavController controller = Navigation.findNavController(view1);
            controller.navigate(R.id.action_registerFragment_to_login);
        });
        ImageButton back = getView().findViewById(R.id.no);
        back.setOnClickListener(view1 -> {
            NavController controller = Navigation.findNavController(view1);
            controller.navigateUp();
        });
    }
}