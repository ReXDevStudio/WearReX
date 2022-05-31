package cn.rexwear.wearrex.activities;

import static cn.rexwear.wearrex.Application.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.adapters.HomePageForumListAdapter;
import cn.rexwear.wearrex.beans.NodesBean;
import cn.rexwear.wearrex.databinding.ActivityNodeDetailBinding;
import cn.rexwear.wearrex.managers.ForumManager;
import cn.rexwear.wearrex.utils.TimeUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NodeDetailActivity extends AppCompatActivity {

    final ExecutorService mThreadPool = Executors.newCachedThreadPool();
    ActivityNodeDetailBinding binding;
    RecyclerView.LayoutManager recyclerViewManager;
    HomePageForumListAdapter recyclerViewAdapter;
    private int parentNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_node_detail);  //DataBinding加载视图
        TimeUtils timeThread = new TimeUtils(binding.nodeDetailTime);   //新建一个获取时间的进程
        timeThread.start();     //开始获取时间
        binding.parentNodeName.setText(getIntent().getStringExtra("parentNodeName"));
        parentNode = getIntent().getIntExtra("parentNodeID", 0);
        recyclerViewManager = new LinearLayoutManager(NodeDetailActivity.this);
        recyclerViewAdapter = new HomePageForumListAdapter();
        binding.recyclerView.setAdapter(recyclerViewAdapter);
        binding.recyclerView.setLayoutManager(recyclerViewManager);
        binding.swipeRefreshLayout.setOnRefreshListener(this::GetAllNodeByParent);
        binding.swipeRefreshLayout.setRefreshing(true);
        GetAllNodeByParent();
    }

    void GetAllNodeByParent() {
        ForumManager.getAllNodes(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mThreadPool.execute(() -> NodeDetailActivity.this.runOnUiThread(() -> {
                    Toast.makeText(NodeDetailActivity.this, "获取列表失败！", Toast.LENGTH_SHORT).show();
                    binding.swipeRefreshLayout.setRefreshing(false);
                }));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                mThreadPool.execute(() -> NodeDetailActivity.this.runOnUiThread(() -> {
                    binding.swipeRefreshLayout.setRefreshing(false);
                    try {
                        NodesBean allNodes = (new Gson()).fromJson(Objects.requireNonNull(response.body()).string(), NodesBean.class);
                        List<NodesBean.NodesFlatDTO.NodeDTO> nodeList = ForumManager.getAllNodesByParentNode(allNodes, parentNode);
                        Log.d(TAG, "onResponse: " + nodeList);
                        recyclerViewAdapter.submitList(nodeList);
                    } catch (IOException e) {
                        e.printStackTrace();
                        mThreadPool.execute(() -> NodeDetailActivity.this.runOnUiThread(() -> {
                            Toast.makeText(NodeDetailActivity.this, "获取列表失败！", Toast.LENGTH_SHORT).show();
                            binding.swipeRefreshLayout.setRefreshing(false);
                        }));
                    }
                }));
            }
        });
    }
}