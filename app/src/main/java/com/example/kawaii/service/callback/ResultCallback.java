package com.example.kawaii.service.callback;

import android.util.Log;

import java.io.IOException;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public abstract class ResultCallback<T> implements Callback<T> {

    private static final String TAG = "ResultCallback";

    @Override
    public void onResponse(Response<T> response, Retrofit retrofit) {
        if (response.isSuccess()) {
            if (response.body() != null) {
                onResult(response.body(), retrofit);
            }
            else {
                onFailure(-1, "Can not parse to Object.");
            }
        }
        else {
            try {
                onFailure(response.code(), response.errorBody().string());
            }
            catch (IOException e) {
                onFailure(response.code(), e.getMessage());
            }
        }
    }

    @Override
    public void onFailure(Throwable t) {
        onFailure(500, t.getMessage());
    }

    /***
     * Callback on Failure
     *
     * @param errorCode : HTTP status code.
     * @param message   : error message
     */
    public void onFailure(int errorCode, String message) {
        Log.e(TAG, String.format("%d : %s", errorCode, message));
    }

    /***
     * Callback on Success
     *
     * @param response : Result<T> object.
     * @param retrofit : Retrofit object.
     */
    public abstract void onResult(T response, Retrofit retrofit);
}
