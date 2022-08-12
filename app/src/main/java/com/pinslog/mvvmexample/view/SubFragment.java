package com.pinslog.mvvmexample.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.transition.TransitionInflater;

import com.pinslog.mvvmexample.R;
import com.pinslog.mvvmexample.base.BaseFragment;
import com.pinslog.mvvmexample.data.model.Post;
import com.pinslog.mvvmexample.databinding.FragmentSubBinding;
import com.pinslog.mvvmexample.viewmodel.SubViewModel;


public class SubFragment extends BaseFragment<FragmentSubBinding> {

    private SubViewModel subViewModel;
    private int userId;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    protected FragmentSubBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentSubBinding.inflate(inflater, container, false);
        return binding;
    }
    @Override
    protected void initSetting() {
        super.initSetting();
        String transitionName = SubFragmentArgs.fromBundle(getArguments()).getTransitionName();
        binding.subPostImg.setTransitionName(transitionName);
        setSharedElementEnterTransition(TransitionInflater.from(mContext).inflateTransition(R.transition.change_bounds));
        setSharedElementReturnTransition(TransitionInflater.from(mContext).inflateTransition(R.transition.change_bounds));
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
                    Post post = postResponse.getData();
                    //binding.setPost(postResponse.getData());
                    binding.subId.setText(String.valueOf(post.getId()));
                    binding.subUserId.setText(String.valueOf(post.getUserId()));
                    binding.subTitle.setText(post.getTitle());
                    binding.subBody.setText(post.getBody());
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