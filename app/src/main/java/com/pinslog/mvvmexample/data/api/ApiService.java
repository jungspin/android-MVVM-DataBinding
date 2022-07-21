package com.pinslog.mvvmexample.data.api;

import com.pinslog.mvvmexample.data.model.PostResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("posts/{id}")
    Single<PostResponse> getPost(@Path("id")int id);

    @GET("/posts")
    Single<List<PostResponse>> getPosts();

    @GET("/posts")
    Call<List<PostResponse>> getPostList();
}
