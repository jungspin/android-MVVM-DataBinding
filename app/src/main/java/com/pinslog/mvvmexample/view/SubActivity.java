package com.pinslog.mvvmexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.pinslog.mvvmexample.R;
import com.pinslog.mvvmexample.base.BaseActivity;
import com.pinslog.mvvmexample.databinding.ActivitySubBinding;
import com.pinslog.mvvmexample.viewmodel.SubViewModel;
import com.pinslog.mvvmexample.viewmodel.TestViewModel;

public class SubActivity extends BaseActivity<ActivitySubBinding> {

    private SubViewModel subViewModel;

    @Override
    protected ActivitySubBinding getViewBinding(LayoutInflater inflater) {
        binding = ActivitySubBinding.inflate(inflater);
        return binding;
    }

    @Override
    protected void initSetting() {
        super.initSetting();
        subViewModel = new ViewModelProvider(this).get(SubViewModel.class);
    }

    @Override
    protected void initData() {
        super.initData();
        int userId = getIntent().getIntExtra("id", 0);
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