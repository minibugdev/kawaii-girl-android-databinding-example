package com.example.kawaii.service;

import com.example.kawaii.model.Kawaii;
import com.example.kawaii.service.base.Result;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface KawaiiService {

    @GET("{id}")
    Call<Result<List<Kawaii>>> getList(@Path("id") String id);
}
