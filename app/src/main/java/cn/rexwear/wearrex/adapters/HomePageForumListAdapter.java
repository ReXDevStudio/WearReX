package cn.rexwear.wearrex.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import cn.rexwear.wearrex.Application;
import cn.rexwear.wearrex.R;
import cn.rexwear.wearrex.activities.ForumDetailActivity;
import cn.rexwear.wearrex.activities.NodeDetailActivity;
import cn.rexwear.wearrex.beans.NodesBean;

/**
 * Created by XC-Qan on 2022/5/30.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

public class HomePageForumListAdapter extends ListAdapter {

    public HomePageForumListAdapter() {
        super(new DiffUtil.ItemCallback<NodesBean.NodesFlatDTO.NodeDTO>() {
            @Override
            public boolean areItemsTheSame(@NonNull NodesBean.NodesFlatDTO.NodeDTO oldItem, @NonNull NodesBean.NodesFlatDTO.NodeDTO newItem) {
                return Objects.equals(oldItem.nodeId, newItem.nodeId);
            }

            @Override
            public boolean areContentsTheSame(@NonNull NodesBean.NodesFlatDTO.NodeDTO oldItem, @NonNull NodesBean.NodesFlatDTO.NodeDTO newItem) {
                return oldItem.title.equals(newItem.title);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        NodesBean.NodesFlatDTO.NodeDTO node = (NodesBean.NodesFlatDTO.NodeDTO) getItem(position);
        if (node.nodeTypeId.equals("Category")) return 0;
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 0)
            return new CategoryViewHolder(inflater.inflate(R.layout.node_category_cell, parent, false));
        return new ForumViewHolder(inflater.inflate(R.layout.node_forum_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NodesBean.NodesFlatDTO.NodeDTO node = (NodesBean.NodesFlatDTO.NodeDTO) getItem(position);

        if (holder instanceof CategoryViewHolder) {
            ((CategoryViewHolder) holder).categoryName.setText(node.title);
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(Application.getContext(), NodeDetailActivity.class);
                intent.putExtra("parentNodeID", node.nodeId);
                intent.putExtra("parentNodeName", node.title);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Application.getContext().startActivity(intent);
            });
        }
        if (holder instanceof ForumViewHolder) {
            ((ForumViewHolder) holder).forumName.setText(node.title);
            ((ForumViewHolder) holder).forumDescription.setText(node.description);
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(Application.getContext(), ForumDetailActivity.class);
                intent.putExtra("forumID", node.nodeId);
                intent.putExtra("forumName", node.title);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Application.getContext().startActivity(intent);
            });
        }
    }

    static class ForumViewHolder extends RecyclerView.ViewHolder {
        TextView forumName, forumDescription;

        public ForumViewHolder(@NonNull View itemView) {
            super(itemView);
            forumName = itemView.findViewById(R.id.threadTitle);
            forumDescription = itemView.findViewById(R.id.postUser);
        }
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.threadTitle);
        }
    }
}
