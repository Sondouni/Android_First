package com.example.sondouni.ch10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sondouni.R;
import com.example.sondouni.ch10.searchmoviemodel.ActorVO;
import com.example.sondouni.ch10.searchmoviemodel.MovieInfoResultBodyVO;
import com.example.sondouni.ch10.searchmoviemodel.MovieInfoResultVO;
import com.example.sondouni.ch10.searchmoviemodel.MovieInfoVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity {
    private String movieCd;
    private TextView tvMovieNm,tvMovieNmEn,tvShowTm;
    Retrofit rf;
    RecyclerView rvActor;
    ActorAdapter acad;
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
        acad = new ActorAdapter();
        tvMovieNm = findViewById(R.id.tvMovieNm);
        tvMovieNmEn = findViewById(R.id.tvMovieNmEn);
        tvShowTm = findViewById(R.id.tvShowTm);
        rvActor = findViewById(R.id.rvActor);
        netconnect(movieCd);
        rvActor.setAdapter(acad);
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
                    List<ActorVO> list = mvo.getActors();
                    Log.i("myLog",mvo.getMovieNm());
                    tvMovieNm.setText(mvo.getMovieNm());
                    tvMovieNmEn.setText(mvo.getMovieNmEn());
                    tvShowTm.setText(mvo.getShowTm());
                    acad.setList(list);
                    acad.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieInfoResultBodyVO> call, Throwable t) {

            }
        });
    }
}
class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.MyViewHolder>{
    private List<ActorVO> list;
    public void setList(List<ActorVO> list){this.list = list;}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_actor,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list==null?0: list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvPeopleNm,tvPeopleNmEn,tvCast,tvCastEn;
        public MyViewHolder(View v){
            super(v);
            tvPeopleNm = v.findViewById(R.id.tvPeopleNm);
            tvPeopleNmEn = v.findViewById(R.id.tvPeopleNmEn);
            tvCast = v.findViewById(R.id.tvCast);
            tvCastEn = v.findViewById(R.id.tvCastEn);
        }
        public void setItem(ActorVO vo){
            tvPeopleNm.setText(vo.getPeopleNm());
            tvPeopleNmEn.setText(vo.getPeopleNmEn());
            tvCast.setText(vo.getCast());
            tvCastEn.setText(vo.getCastEn());
        }

    }
}