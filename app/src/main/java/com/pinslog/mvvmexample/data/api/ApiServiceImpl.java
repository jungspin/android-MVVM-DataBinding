package com.pinslog.mvvmexample.data.api;

import com.pinslog.mvvmexample.data.model.Post;
import com.pinslog.mvvmexample.util.RetrofitInstance;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class ApiServiceImpl implements ApiService {

    private final ApiService apiService;

    public ApiServiceImpl(RetrofitInstance retrofitInstance) {
        apiService = retrofitInstance.getRetrofit().create(ApiService.class);
    }

    @Override
    public Single<Post> getPost(int id) {
        return apiService.getPost(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Post>> getPosts() {
        return apiService.getPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Call<List<Post>> getPostList() {
        return apiService.getPostList();
    }

    @Override
    public Single<Post> writePost(Post post) {
        return apiService.writePost(post).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
