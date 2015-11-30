package com.example.word.service;

import com.example.word.model.Kawaii;
import com.example.word.service.base.Result;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface KawaiiService {

    @GET("{id}")
    Call<Result<List<Kawaii>>> getList(@Path("id") String id);
}
