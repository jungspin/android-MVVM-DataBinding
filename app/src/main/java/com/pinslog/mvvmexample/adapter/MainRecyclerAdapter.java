package com.pinslog.mvvmexample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.pinslog.mvvmexample.data.model.Post;
import com.pinslog.mvvmexample.databinding.ItemPostBinding;
import com.pinslog.mvvmexample.view.MainFragment;
import com.pinslog.mvvmexample.view.MainFragmentDirections;
import com.pinslog.mvvmexample.view.SubActivity;
import com.pinslog.mvvmexample.view.SubTestActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> postList = new ArrayList<>();


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
    public void setItems(List<Post> postList){
        this.postList = postList;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemPostBinding binding;

        public ItemViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v->{
                Post post = postList.get(getAdapterPosition());
                MainFragmentDirections.ActionMainFragmentToSubFragment action = MainFragmentDirections.actionMainFragmentToSubFragment();
                action.setPostId(post.getId());
                Navigation.findNavController(binding.getRoot()).navigate(action);
            });
        }

        void onBind(Post post){
            binding.setPost(post);
        }
    }
}
