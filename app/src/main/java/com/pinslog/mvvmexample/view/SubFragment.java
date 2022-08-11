package com.pinslog.mvvmexample.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pinslog.mvvmexample.R;
import com.pinslog.mvvmexample.base.BaseFragment;
import com.pinslog.mvvmexample.databinding.FragmentSubBinding;
import com.pinslog.mvvmexample.viewmodel.SubViewModel;


public class SubFragment extends BaseFragment<FragmentSubBinding> {

    private SubViewModel subViewModel;
    private int userId;

    @Override
    protected FragmentSubBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentSubBinding.inflate(inflater, container, false);
        return binding;
    }
    @Override
    protected void initSetting() {
        super.initSetting();
        userId = SubFragmentArgs.fromBundle(getArguments()).getPostId();
        subViewModel = new ViewModelProvider(this).get(SubViewModel.class);
    }

    @Override
    protected void initListener() {
        super.initListener();
        binding.subModifyBtn.setOnClickListener(v->{
            SubFragmentDirections.ActionSubFragmentToWriteFragment action
                    = SubFragmentDirections.actionSubFragmentToWriteFragment(userId);
            Navigation.findNavController(binding.getRoot()).navigate(action);
        });
    }

    @Override
    protected void initData() {
        super.initData();

        subViewModel.loadPost(userId);
        subViewModel.getMutableData().observe(this, postResponse -> {
            switch (postResponse.getStatus()) {
                case SUCCESS:
                    binding.setPost(postResponse.getData());
                    break;
                case FAIL:
                    Toast.makeText(mContext, "데이터를 받아올 수 없음", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    postResponse.getThrowable().printStackTrace();
                    break;

            }
        });

    }
}