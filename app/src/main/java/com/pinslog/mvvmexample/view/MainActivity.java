package com.pinslog.mvvmexample.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pinslog.mvvmexample.adapter.MainRecyclerAdapter;
import com.pinslog.mvvmexample.base.BaseActivity;
import com.pinslog.mvvmexample.databinding.ActivityMainBinding;
import com.pinslog.mvvmexample.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
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
        mainViewModel.getMutableData().observe(this, response -> {
            switch (response.getStatus()){
                case SUCCESS:
                    adapter.setItems(response.getData());
                    break;
                case FAIL:
                    Toast.makeText(mContext, "데이터를 받아올 수 없음", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    response.getThrowable().printStackTrace();
                    break;
            }

        });
    }

    @Override
    protected void initListener() {
        super.initListener();
        binding.mainMoveBtn.setOnClickListener(v->{
            Intent intent = getPackageManager().getLaunchIntentForPackage("kr.jeios.motioncoremobile");
            startActivity(intent);
        });
    }
}