package com.pinslog.mvvmexample.base;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel<T> extends ViewModel {
    private static final String TAG = "BaseViewModel";

    protected MutableLiveData<T> mutableData;
    protected Disposable disposable;

    public LiveData<T> getMutableData() {
        if(mutableData == null){
            mutableData = new MutableLiveData<T>();
            getData();
        }
        return mutableData;
    }

    protected abstract void getData();

    @Override
    protected void onCleared() {
        super.onCleared();
        if (!disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
