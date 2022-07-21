package com.pinslog.mvvmexample.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.pinslog.mvvmexample.data.model.PostResponse;
import com.pinslog.mvvmexample.databinding.ItemPostBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostResponse> postList = new ArrayList<>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPostBinding binding = ItemPostBinding.inflate(inflater);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){
            ((ItemViewHolder) holder).onBind(postList.get(holder.getAdapterPosition()));
        }
    }


    @Override
    public int getItemCount() {
        return postList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<PostResponse> postList){
        this.postList = postList;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemPostBinding binding;

        public ItemViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void onBind(PostResponse post){
            binding.setPost(post);
        }
    }
}
