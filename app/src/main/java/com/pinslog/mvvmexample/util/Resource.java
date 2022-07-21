package com.pinslog.mvvmexample.util;

public class Resource<T> {

    private Status status;
    private T data;
    private String message;

    public Resource() {

    }

    public Resource(Status status, T data) {
        this.status = status;
        this.data = data;
    }

    public Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data);
    }

    public Resource<T> fail(T data, String message) {
        return new Resource<>(Status.ERROR, data, message);
    }
}
