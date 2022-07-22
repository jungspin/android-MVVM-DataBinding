package com.pinslog.mvvmexample.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pinslog.mvvmexample.base.BaseViewModel;
import com.pinslog.mvvmexample.data.api.ApiServiceImpl;
import com.pinslog.mvvmexample.data.model.PostResponse;
import com.pinslog.mvvmexample.util.CommonValue;
import com.pinslog.mvvmexample.util.Resource;
import com.pinslog.mvvmexample.util.RetrofitInstance;
import com.pinslog.mvvmexample.util.Status;

import java.util.List;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainViewModel extends BaseViewModel<CommonValue<List<PostResponse>>> {
    private static final String TAG = "MainViewModel";

    private final ApiServiceImpl apiService = new ApiServiceImpl(RetrofitInstance.getInstance());

    public void loadPosts() {
        disposable = apiService.getPosts().subscribe(
                responses -> mutableData.postValue(new CommonValue<>(Status.SUCCESS, responses)),
                throwable -> mutableData.setValue(new CommonValue<>(Status.ERROR, throwable))
        );
    }
}
