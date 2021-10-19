package com.example.sondouni.picsum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.sondouni.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PicsumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picsum);
        network();

    }
    private void network(){

        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://picsum.photos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();//객체생성할때 private한 맴버에 넣는방법중 constructor를 이용해 넣는데,
        // constructor가 오버로딩돼서 다양한 값을 받을때 builder를 이용해 원하는값만(맴버필드,파라미터등?) 넣음

        //RetrofitService 인터페이스를 구현한 객체를 리턴
        RetrofitService service = rf.create(RetrofitService.class);

        Call<List<PicsumVO>> call = service.getList();

        //비동기처리
        call.enqueue(new Callback<List<PicsumVO>>() {

            //통신이 성공한 경우(서버의 응답이 성공한 경우), 실행될 내용작성
            @Override
            public void onResponse(Call<List<PicsumVO>> call, Response<List<PicsumVO>> response) {
                //통신에서 데이터가 제대로 왓는지 확인
                if(response.isSuccessful()){
                    List<PicsumVO> list = response.body();
                    Log.i("myLog","----response success----");
                    for(PicsumVO vo : list){
                        Log.i("myLog",vo.getAuthor());
                    }
                }else{
                    Log.d("myLog","response(Date) Fail");
                }
            }
            //통신이 실패한 경우(서버의 응답이 실패한 경우), 실행될 내용
            @Override
            public void onFailure(Call<List<PicsumVO>> call, Throwable t) {
                Log.d("myLog","response Fail");
            }
        });


    }
}