package com.pinslog.mvvmexample.viewmodel;

import android.util.Log;

import com.pinslog.mvvmexample.base.BaseViewModel;
import com.pinslog.mvvmexample.data.api.ApiServiceImpl;
import com.pinslog.mvvmexample.data.model.Post;
import com.pinslog.mvvmexample.util.CommonValue;
import com.pinslog.mvvmexample.util.RetrofitInstance;
import com.pinslog.mvvmexample.util.Status;

import java.util.Locale;

public class UpdateViewModel extends BaseViewModel<CommonValue<Post>> {
    public static final String TAG = "UpdateViewModel";
    @Override
    protected void getData() {

    }
    private final ApiServiceImpl service = new ApiServiceImpl(RetrofitInstance.getInstance());

    public void updatePost(Post post){
        Log.d(TAG, "updatePost: " + post);
        disposable = service.writePost(post).subscribe(response -> {
            Log.d(TAG, "updatePost: " + response);
            mutableData.setValue(new CommonValue<>(Status.SUCCESS, response));
        }, throwable -> {

        });

    }

    public void loadPost(int userId) {
        ApiServiceImpl apiService = new ApiServiceImpl(RetrofitInstance.getInstance());
        disposable = apiService.getPost(userId).subscribe(
                response -> {
                    int titleLength = response.getTitle().length();
                    if (titleLength >= 20){
                        response.setTitle(String.format(Locale.KOREA, "제목이 너무 깁니다! (%d자)", titleLength));
                    }
                    mutableData.setValue(new CommonValue<>(Status.SUCCESS, response));
                },
                throwable -> mutableData.setValue(new CommonValue<>(Status.ERROR, throwable))
        );
    }


}
