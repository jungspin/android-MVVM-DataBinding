package com.pinslog.mvvmexample.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pinslog.mvvmexample.R;
import com.pinslog.mvvmexample.adapter.MainRecyclerAdapter;
import com.pinslog.mvvmexample.base.BaseFragment;
import com.pinslog.mvvmexample.data.api.ApiServiceImpl;
import com.pinslog.mvvmexample.databinding.FragmentMainBinding;
import com.pinslog.mvvmexample.util.CommonValue;
import com.pinslog.mvvmexample.util.RetrofitInstance;
import com.pinslog.mvvmexample.util.Status;
import com.pinslog.mvvmexample.viewmodel.MainViewModel;

import io.reactivex.disposables.Disposable;


public class MainFragment extends BaseFragment<FragmentMainBinding> {

    private MainRecyclerAdapter adapter;
    private MainViewModel mainViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    @Override
    protected FragmentMainBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
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
    protected void initViewModel() {
        super.initViewModel();
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
    protected void initData() {
        super.initData();
        mainViewModel.loadPosts();
    }

    // enabled – The default enabled state for this callback.
    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setMessage("앱을 종료하시겠습니까?")
                    .setPositiveButton("앱 종료", (dialogInterface, i) -> requireActivity().finish())
                    .setNegativeButton("취소", (dialogInterface, i) -> dialogInterface.dismiss())
                    .create()
                    .show();
        }
    };
}