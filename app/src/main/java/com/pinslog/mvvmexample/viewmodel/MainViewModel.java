package com.pinslog.mvvmexample.viewmodel;

import com.pinslog.mvvmexample.base.BaseViewModel;
import com.pinslog.mvvmexample.data.api.ApiServiceImpl;
import com.pinslog.mvvmexample.data.model.Post;
import com.pinslog.mvvmexample.util.CommonValue;
import com.pinslog.mvvmexample.util.RetrofitInstance;
import com.pinslog.mvvmexample.util.Status;

import java.util.List;

public class MainViewModel extends BaseViewModel<CommonValue<List<Post>>> {

    private final ApiServiceImpl apiService = new ApiServiceImpl(RetrofitInstance.getInstance());

    public void loadPosts() {
        disposable = apiService.getPosts().subscribe(
                responses -> mutableData.setValue(new CommonValue<>(Status.SUCCESS, responses)),
                throwable -> mutableData.setValue(new CommonValue<>(Status.ERROR, throwable))
        );
    }

    @Override
    protected void getData() {
        disposable = apiService.getPosts().subscribe(
                responses -> mutableData.setValue(new CommonValue<>(Status.SUCCESS, responses)),
                throwable -> mutableData.setValue(new CommonValue<>(Status.ERROR, throwable))
        );
    }
}
