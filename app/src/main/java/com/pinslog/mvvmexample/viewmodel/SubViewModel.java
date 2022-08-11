package com.pinslog.mvvmexample.viewmodel;

import com.pinslog.mvvmexample.base.BaseViewModel;
import com.pinslog.mvvmexample.data.api.ApiServiceImpl;
import com.pinslog.mvvmexample.data.model.Post;
import com.pinslog.mvvmexample.util.CommonValue;
import com.pinslog.mvvmexample.util.RetrofitInstance;
import com.pinslog.mvvmexample.util.Status;

import java.util.Locale;

public class SubViewModel extends BaseViewModel<CommonValue<Post>> {

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

    @Override
    protected void getData() {

    }
}
