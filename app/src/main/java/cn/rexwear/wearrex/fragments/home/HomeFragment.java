package cn.rexwear.wearrex.fragments.home;

import static cn.rexwear.wearrex.Application.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.rexwear.wearrex.adapters.HomePageForumListAdapter;
import cn.rexwear.wearrex.beans.NodesBean;
import cn.rexwear.wearrex.databinding.FragmentHomeBinding;
import cn.rexwear.wearrex.managers.ForumManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    final static int nodeDepth = 0;
    final ExecutorService mThreadPool = Executors.newCachedThreadPool();
    RecyclerView.LayoutManager recyclerViewManager;
    HomePageForumListAdapter recyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewManager = new LinearLayoutManager(requireContext());
        recyclerViewAdapter = new HomePageForumListAdapter();
        binding.recyclerView.setAdapter(recyclerViewAdapter);
        binding.recyclerView.setLayoutManager(recyclerViewManager);
        binding.swipeRefreshLayout.setOnRefreshListener(this::GetAllForums);
        binding.swipeRefreshLayout.setRefreshing(true);
        GetAllForums();
    }

    void GetAllForums() {
        Log.d(TAG, "GetAllForums: ");
        ForumManager.getAllNodes(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> {
                    Toast.makeText(requireActivity(), "获取论坛列表失败！", Toast.LENGTH_SHORT).show();
                    binding.swipeRefreshLayout.setRefreshing(false);
                }));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> {
                    binding.swipeRefreshLayout.setRefreshing(false);
                    try {
                        NodesBean allNodes = (new Gson()).fromJson(Objects.requireNonNull(response.body()).string(), NodesBean.class);
                        List<NodesBean.NodesFlatDTO.NodeDTO> nodeList = ForumManager.getAllNodesByDepth(allNodes, nodeDepth);
                        Log.d(TAG, "onResponse: " + nodeList);
                        recyclerViewAdapter.submitList(nodeList);
                    } catch (IOException e) {
                        e.printStackTrace();
                        mThreadPool.execute(() -> requireActivity().runOnUiThread(() -> {
                            Toast.makeText(requireActivity(), "获取论坛列表失败！", Toast.LENGTH_SHORT).show();
                            binding.swipeRefreshLayout.setRefreshing(false);
                        }));
                    }
                }));
            }
        });
    }
}