package com.example.sondouni.ch10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sondouni.R;
import com.example.sondouni.ch10.searchmoviemodel.MovieInfoResultBodyVO;
import com.example.sondouni.ch10.searchmoviemodel.MovieInfoResultVO;
import com.example.sondouni.ch10.searchmoviemodel.MovieInfoVO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity {
    private String movieCd;
    private TextView tvMovieNm,tvMovieNmEn,tvShowTm;
    Retrofit rf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        movieCd = intent.getStringExtra("movieCd");
        Log.i("myLog","detail - movieCd : "+movieCd);
        rf = new Retrofit.Builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tvMovieNm = findViewById(R.id.tvMovieNm);
        tvMovieNmEn = findViewById(R.id.tvMovieNmEn);
        tvShowTm = findViewById(R.id.tvShowTm);

        netconnect(movieCd);
    }
    public void netconnect(String movieCd){

        Log.i("myLog","네트워크 메서드 실행");
        KobisService sv = rf.create(KobisService.class);
        Call<MovieInfoResultBodyVO> call  = sv.searchMovieInfo(movieCd);
        call.enqueue(new Callback<MovieInfoResultBodyVO>() {
            @Override
            public void onResponse(Call<MovieInfoResultBodyVO> call, Response<MovieInfoResultBodyVO> response) {
                if(response.isSuccessful()){ // status : 200 -> 통신성공
                    Log.i("myLog","연결성공");
                    MovieInfoResultBodyVO vo = response.body();
                    MovieInfoResultVO rvo = vo.getMovieInfoResult();
                    MovieInfoVO mvo = rvo.getMovieInfo();
                    Log.i("myLog",mvo.getMovieNm());
                    tvMovieNm.setText(mvo.getMovieNm());
                    tvMovieNmEn.setText(mvo.getMovieNmEn());
                    tvShowTm.setText(mvo.getShowTm());
                }
            }

            @Override
            public void onFailure(Call<MovieInfoResultBodyVO> call, Throwable t) {

            }
        });
    }
}