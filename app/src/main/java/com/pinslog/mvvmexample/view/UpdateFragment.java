package com.pinslog.mvvmexample.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import com.pinslog.mvvmexample.R;
import com.pinslog.mvvmexample.base.BaseFragment;
import com.pinslog.mvvmexample.data.model.Post;
import com.pinslog.mvvmexample.databinding.FragmentUpdateBinding;
import com.pinslog.mvvmexample.viewmodel.UpdateViewModel;


public class UpdateFragment extends BaseFragment<FragmentUpdateBinding> {

    private int userId;
    private UpdateViewModel viewModel;
    private Post post;

    @Override
    protected FragmentUpdateBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        return binding;
    }

    @Override
    protected void initSetting() {
        super.initSetting();

        userId = UpdateFragmentArgs.fromBundle(getArguments()).getUserId();
    }

    @Override
    protected void initListener() {
        super.initListener();
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    protected void initViewModel() {
        super.initViewModel();
        viewModel = new ViewModelProvider(requireActivity()).get(UpdateViewModel.class);
        viewModel.getMutableData().observe(getViewLifecycleOwner(), data -> {
            switch (data.getStatus()) {
                case SUCCESS:
                    post = data.getData();
                    binding.writeEt.setText(post.getBody());
                    break;
                case ERROR:
                    data.getThrowable().printStackTrace();
                    break;
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        viewModel.loadPost(userId);
    }

    private final OnBackPressedCallback callback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            post.setBody(binding.writeEt.getText().toString());
            viewModel.updatePost(post);
            requireActivity().onBackPressed();
        }
    };
}