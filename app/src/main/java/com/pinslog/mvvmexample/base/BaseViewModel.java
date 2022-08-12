package com.pinslog.mvvmexample.base;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pinslog.mvvmexample.util.SingleLiveEvent;

import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel<T> extends ViewModel {
    private static final String TAG = "BaseViewModel";

    protected SingleLiveEvent<T> mutableData;
    protected Disposable disposable;

    public LiveData<T> getMutableData() {
        if(mutableData == null){
            mutableData = new SingleLiveEvent<T>();
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
