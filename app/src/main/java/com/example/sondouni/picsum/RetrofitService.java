package com.example.sondouni.picsum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/v2/list")
    Call<List<PicsumVO>> getList();//서버한테 보내는데 노출되지만 빠르다, <-> post 보안,대용량

}
