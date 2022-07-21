package com.pinslog.mvvmexample.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pinslog.mvvmexample.data.api.ApiServiceImpl;
import com.pinslog.mvvmexample.data.model.PostResponse;
import com.pinslog.mvvmexample.util.Resource;
import com.pinslog.mvvmexample.util.RetrofitInstance;
import com.pinslog.mvvmexample.util.Status;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    private final MutableLiveData<List<PostResponse>> posts = new MutableLiveData<>();
    private final ApiServiceImpl apiService = new ApiServiceImpl(RetrofitInstance.getInstance());
    private Disposable disposable;

    public MutableLiveData<List<PostResponse>> getPosts() {
        return posts;
    }

    public void loadPost(int userId) {
        disposable = apiService.getPost(userId).subscribe((postResponse, throwable) -> {

        });
    }

    public void loadPosts() {
        disposable = apiService.getPosts().subscribe(new Consumer<List<PostResponse>>() {
            @Override
            public void accept(List<PostResponse> postResponses) throws Exception {
                posts.postValue(postResponses);
            }
        }, Throwable::printStackTrace);
    }
}
