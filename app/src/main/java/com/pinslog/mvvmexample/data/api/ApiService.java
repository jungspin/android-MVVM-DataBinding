package com.pinslog.mvvmexample.data.api;

import com.pinslog.mvvmexample.data.model.Post;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("posts/{id}")
    Single<Post> getPost(@Path("id")int id);

    @GET("posts")
    Single<List<Post>> getPosts();

    @GET("/posts")
    Call<List<Post>> getPostList();

    @PUT("posts")
    Single<Post> writePost(@Body Post post);
}
