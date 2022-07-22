package com.pinslog.mvvmexample.viewmodel;

import com.pinslog.mvvmexample.base.BaseViewModel;
import com.pinslog.mvvmexample.data.api.ApiServiceImpl;
import com.pinslog.mvvmexample.data.model.PostResponse;
import com.pinslog.mvvmexample.util.CommonValue;
import com.pinslog.mvvmexample.util.RetrofitInstance;
import com.pinslog.mvvmexample.util.Status;

public class SubViewModel extends BaseViewModel<CommonValue<PostResponse>> {

    private final ApiServiceImpl apiService = new ApiServiceImpl(RetrofitInstance.getInstance());

    public void loadPost(int userId) {
        disposable = apiService.getPost(userId).subscribe(
                postResponse -> mutableData.setValue(new CommonValue<>(Status.SUCCESS, postResponse)),
                throwable -> mutableData.setValue(new CommonValue<>(Status.ERROR, throwable))
        );
    }

}
