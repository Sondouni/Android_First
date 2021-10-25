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
import com.example.sondouni.Utils;
import com.example.sondouni.ch10.boxofficemodel.WeeklyBoxOfficeVO;
import com.example.sondouni.ch10.searchmoviemodel.MovieListResultBodyVO;
import com.example.sondouni.ch10.searchmoviemodel.MovieListResultVO;
import com.example.sondouni.ch10.searchmoviemodel.MovieVO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListActivity extends AppCompatActivity {
    private MovieListAdapter adapter;
    private RecyclerView rvList;
    private final String ITEM_PER_PAGE = "20";
    private int curPage = 1;

    Retrofit rf = new Retrofit.Builder()
            .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_list);
        adapter = new MovieListAdapter();
        rvList = findViewById(R.id.rvList);
        rvList.setAdapter(adapter);
        network();

        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView rView, int newState) {
                if((rView.canScrollVertically(1))&&(newState==RecyclerView.SCROLL_STATE_IDLE)){
                    Log.i("myLog","스크롤끝");
                    network();
                }
            }

//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
        });

    }
    public void network(){
        KobisService sv= rf.create(KobisService.class);
        Log.i("myLog","네트워크 메서드 실행");
        Call<MovieListResultBodyVO> call = sv.movieSearchList(ITEM_PER_PAGE,(curPage++)+"");
        call.enqueue(new Callback<MovieListResultBodyVO>() {
            @Override
            public void onResponse(Call<MovieListResultBodyVO> call, Response<MovieListResultBodyVO> res) {
                Log.i("myLog","연결성공");
                if(res.isSuccessful()){
                    Log.i("myLog","연결성공1");
                    MovieListResultBodyVO vo = res.body();
                    Log.i("myLog","연결성공2");
                    MovieListResultVO resultVo = vo.getMovieListResult();
                    Log.i("myLog","연결성공3");
                    List<MovieVO> list = resultVo.getMovieList();
                    Log.i("myLog", list.size() + "개");
                    for (MovieVO item : vo.getMovieListResult().getMovieList()) {
                        Log.i("myLog", item.getMovieNm());
                    }
                    for(MovieVO voo : list){ // -> 리스트의 길이만큼 안의 MovieVO의 객체를 addlist로 하나하나씩 넣어줌
                        adapter.addList(voo);
                    }
//                    adapter.setList(list); -> 리스트를 그대로 준다
                    adapter.notifyDataSetChanged();
                }else {
                    Log.i("myLog","데이터실패");
                }
            }

            @Override
            public void onFailure(Call<MovieListResultBodyVO> call, Throwable t) {
                Log.i("myLog","연결실패");
            }
        });
    }
}


class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder>{
    private List<MovieVO> list = new ArrayList<>();
    public void addList(MovieVO vo){ list.add(vo);}
    public void setList(List<MovieVO> list){this.list = list;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("myLog","뷰홀더생성");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_movie,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("myLog","홀더에 값넣기");
        final MovieVO obj = list.get(position);
        final int pos = position;//원래는 이벤트를 이벤트로 연결
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = getAdapterPosition();//getaDapterPostion은 클릭한 view의 위치를 알려줌
                // (myViewHolder((viewholer를 상속받은 곳에서만)
                String movieCd = obj.getMovieCd();
                Log.i("myLog","movieCd = "+movieCd);
                Intent intent = new Intent(v.getContext(),MovieDetailActivity.class);
                //v.getContext()로 해야 클래스가 떨어진 MovieListActivity를 가져옴
                intent.putExtra("movieCd",movieCd);
                v.getContext().startActivity(intent);
            }
        });
        holder.setItem(obj);
    }

    @Override
    public int getItemCount() {
        return list == null? 0: list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMovienm, tvRepNationNm, tvMovienmEn, tvRepGenreNm;

        public MyViewHolder(View v) {
            super(v);
            Log.i("myLog","이너클래스 생성");
            tvMovienm = v.findViewById(R.id.tvMovienm);
            tvRepNationNm = v.findViewById(R.id.tvRepNationNm);
            tvMovienmEn = v.findViewById(R.id.tvMovienmEn);
            tvRepGenreNm = v.findViewById(R.id.tvRepGenreNm);
        }

        public void setItem(MovieVO vo) {
            Log.i("myLog", "데이터가져오기");
            tvMovienm.setText("영화명 "+vo.getMovieNm());
            tvRepNationNm.setText("나라 " + vo.getRepNationNm());
            tvMovienmEn.setText("영어명: " + vo.getMovieNmEn());
            tvRepGenreNm.setText("장르 "+vo.getRepGenreNm());
        }
    }
}