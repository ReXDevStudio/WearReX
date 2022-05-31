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
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.adapters.ForumDetailListAdapter;
import cn.rexwear.wearrex.beans.ThreadsBean;
import cn.rexwear.wearrex.databinding.ActivityForumDetailBinding;
import cn.rexwear.wearrex.managers.ForumManager;
import cn.rexwear.wearrex.utils.TimeUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForumDetailActivity extends AppCompatActivity {

    final ExecutorService mThreadPool = Executors.newCachedThreadPool();
    ActivityForumDetailBinding binding;
    RecyclerView.LayoutManager recyclerViewManager;
    ForumDetailListAdapter recyclerViewAdapter;
    private int forumID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ForumDetailActivity.this, R.layout.activity_forum_detail);
        TimeUtils timeThread = new TimeUtils(binding.nodeDetailTime);   //新建一个获取时间的进程
        timeThread.start();     //开始获取时间
        binding.forumName.setText(getIntent().getStringExtra("forumName"));
        forumID = getIntent().getIntExtra("forumID", 0);
        recyclerViewManager = new LinearLayoutManager(ForumDetailActivity.this);
        recyclerViewAdapter = new ForumDetailListAdapter();
        binding.recyclerView.setAdapter(recyclerViewAdapter);
        binding.recyclerView.setLayoutManager(recyclerViewManager);
        binding.swipeRefreshLayout.setOnRefreshListener(this::GetAllThreadByForum);
        binding.swipeRefreshLayout.setRefreshing(true);
        GetAllThreadByForum();
    }

    void GetAllThreadByForum() {
        ForumManager.getAllThreadByForum(forumID, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mThreadPool.execute(() -> ForumDetailActivity.this.runOnUiThread(() -> {
                    Toast.makeText(ForumDetailActivity.this, "获取帖子列表失败！", Toast.LENGTH_SHORT).show();
                    binding.swipeRefreshLayout.setRefreshing(false);
                }));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                mThreadPool.execute(() -> ForumDetailActivity.this.runOnUiThread(() -> {
                    binding.swipeRefreshLayout.setRefreshing(false);
                    try {
                        ThreadsBean allThreads = (new Gson()).fromJson(Objects.requireNonNull(response.body()).string(), ThreadsBean.class);
                        Log.d(TAG, "onResponse: " + allThreads);
                        recyclerViewAdapter.submitList(allThreads.threads);
                    } catch (IOException e) {
                        e.printStackTrace();
                        mThreadPool.execute(() -> ForumDetailActivity.this.runOnUiThread(() -> {
                            Toast.makeText(ForumDetailActivity.this, "获取帖子列表失败！", Toast.LENGTH_SHORT).show();
                            binding.swipeRefreshLayout.setRefreshing(false);
                        }));
                    }
                }));
            }
        });
    }
}