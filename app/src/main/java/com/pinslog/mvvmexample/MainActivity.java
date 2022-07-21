package com.pinslog.mvvmexample;

import android.util.Log;
import android.view.LayoutInflater;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pinslog.mvvmexample.adapter.MainRecyclerAdapter;
import com.pinslog.mvvmexample.base.BaseActivity;
import com.pinslog.mvvmexample.data.model.PostResponse;
import com.pinslog.mvvmexample.databinding.ActivityMainBinding;
import com.pinslog.mvvmexample.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private static final String TAG = "MainActivity2";

    private MainRecyclerAdapter adapter;
    private MainViewModel mainViewModel;

    @Override
    protected ActivityMainBinding getViewBinding(LayoutInflater inflater) {
        binding = ActivityMainBinding.inflate(inflater);
        return binding;
    }

    @Override
    protected void initSetting() {
        super.initSetting();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        binding.mainRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MainRecyclerAdapter();
        binding.mainRecyclerView.setAdapter(adapter);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    protected void initData() {
        super.initData();
        mainViewModel.loadPosts();
        mainViewModel.getPosts().observe(this, postResponses -> {
            Log.d(TAG, "initData: " + postResponses.get(0).getTitle());
            adapter.setItems(postResponses);
        });
    }
}