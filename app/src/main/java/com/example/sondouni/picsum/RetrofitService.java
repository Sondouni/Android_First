package com.example.sondouni.picsum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/v2/list")
    Call<List<PicsumVO>> getList();
}
