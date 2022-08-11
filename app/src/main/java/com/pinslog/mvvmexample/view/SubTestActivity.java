package com.pinslog.mvvmexample.view;

import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.pinslog.mvvmexample.base.BaseActivity;
import com.pinslog.mvvmexample.data.api.ApiService;
import com.pinslog.mvvmexample.data.api.ApiServiceImpl;
import com.pinslog.mvvmexample.databinding.ActivitySubBinding;
import com.pinslog.mvvmexample.util.RetrofitInstance;
import com.pinslog.mvvmexample.viewmodel.SubViewModel;

import java.util.Locale;

import io.reactivex.disposables.Disposable;

public class SubTestActivity extends BaseActivity<ActivitySubBinding> {

    @Override
    protected ActivitySubBinding getViewBinding(LayoutInflater inflater) {
        binding = ActivitySubBinding.inflate(inflater);
        return binding;
    }

    @Override
    protected void initSetting() {
        super.initSetting();
    }

    @Override
    protected void initData() {
        super.initData();
        int id = getIntent().getIntExtra("id", 0);
        ApiServiceImpl service = new ApiServiceImpl(RetrofitInstance.getInstance());
        Disposable disposable = service.getPost(id).subscribe(response ->{
            int titleLength = response.getTitle().length();
            if (titleLength >= 20){
                response.setTitle(String.format(Locale.KOREA, "제목이 너무 깁니다! (%d자)", titleLength));
            }
            binding.setPost(response);
        }, throwable -> {
            throwable.printStackTrace();
        });
    }
}