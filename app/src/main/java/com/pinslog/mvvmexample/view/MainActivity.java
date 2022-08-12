package com.pinslog.mvvmexample.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pinslog.mvvmexample.R;
import com.pinslog.mvvmexample.adapter.MainRecyclerAdapter;
import com.pinslog.mvvmexample.base.BaseActivity;
import com.pinslog.mvvmexample.databinding.ActivityMainBinding;
import com.pinslog.mvvmexample.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private static final String TAG = "MainActivity2";


    @Override
    protected ActivityMainBinding getViewBinding(LayoutInflater inflater) {
        binding = ActivityMainBinding.inflate(inflater);
        return binding;
    }

}