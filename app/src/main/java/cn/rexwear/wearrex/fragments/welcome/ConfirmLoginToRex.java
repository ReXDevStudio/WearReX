package cn.rexwear.wearrex.fragments.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.activities.HomeActivity;
import cn.rexwear.wearrex.databinding.FragmentConfirmLoginToRexBinding;
import cn.rexwear.wearrex.managers.UserManager;

public class ConfirmLoginToRex extends Fragment {
    FragmentConfirmLoginToRexBinding binding;


    public ConfirmLoginToRex() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConfirmLoginToRexBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController controller = Navigation.findNavController(requireView());
        binding.login.setOnClickListener(view1 -> controller.navigate(R.id.action_confirmLoginToRex_to_login));
        binding.register.setOnClickListener(view1 -> controller.navigate(R.id.action_confirmLoginToRex_to_registerFragment));
        binding.tourist.setOnClickListener(view1 -> {
            UserManager.saveUserIsExperiment(true);
            startActivity(new Intent(getActivity(), HomeActivity.class));
            requireActivity().finish();
        });

    }


}