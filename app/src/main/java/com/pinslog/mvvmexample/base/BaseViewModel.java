package com.pinslog.mvvmexample.base;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel<T> extends ViewModel {
    private static final String TAG = "BaseViewModel";

    protected MutableLiveData<T> mutableData = new MutableLiveData<T>();
    protected Disposable disposable;

    public MutableLiveData<T> getMutableData() {
        return mutableData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (!disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
