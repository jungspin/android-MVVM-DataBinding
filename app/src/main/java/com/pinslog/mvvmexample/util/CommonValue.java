package com.pinslog.mvvmexample.util;


public class CommonValue<T> {

    private Status status;
    private T data;
    private String message;
    private Throwable throwable;

    public CommonValue(){}

    public CommonValue(Status status, T data) {
        this.status = status;
        this.data = data;
    }

    public CommonValue(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public CommonValue(Status status, Throwable throwable) {
        this.status = status;
        this.throwable = throwable;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
