package cn.rexwear.wearrex.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.beans.ThreadsBean;

/**
 * Created by XC-Qan on 2022/5/31.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

public class ForumDetailListAdapter extends ListAdapter<ThreadsBean.ThreadsDTO, ForumDetailListAdapter.ThreadViewHolder> {

    public ForumDetailListAdapter() {
        super(new DiffUtil.ItemCallback<ThreadsBean.ThreadsDTO>() {
            @Override
            public boolean areItemsTheSame(@NonNull ThreadsBean.ThreadsDTO oldItem, @NonNull ThreadsBean.ThreadsDTO newItem) {
                return Objects.equals(oldItem.threadId, newItem.threadId);
            }

            @Override
            public boolean areContentsTheSame(@NonNull ThreadsBean.ThreadsDTO oldItem, @NonNull ThreadsBean.ThreadsDTO newItem) {
                return oldItem.title.equals(newItem.title);
            }
        });
    }

    @NonNull
    @Override
    public ThreadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ForumDetailListAdapter.ThreadViewHolder(inflater.inflate(R.layout.node_post_cell, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ThreadViewHolder holder, int position) {
        ThreadsBean.ThreadsDTO thread = getItem(position);
        holder.threadName.setText(thread.title);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String updateTime = format.format(new Date(thread.lastPostDate * 1000));
        holder.postUserAndTime.setText(thread.lastPostUsername + " · " + updateTime);
    }

    public static class ThreadViewHolder extends RecyclerView.ViewHolder {
        TextView threadName, postUserAndTime;

        public ThreadViewHolder(@NonNull View itemView) {
            super(itemView);
            threadName = itemView.findViewById(R.id.threadTitle);
            postUserAndTime = itemView.findViewById(R.id.postUser);
        }
    }
}
