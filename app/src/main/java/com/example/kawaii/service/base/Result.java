package com.example.kawaii.service.base;

public class Result<T> {
    private boolean error;
    private T results;

    public boolean isSuccess() {
        return !error;
    }

    public T getResults() {
        return results;
    }
}
