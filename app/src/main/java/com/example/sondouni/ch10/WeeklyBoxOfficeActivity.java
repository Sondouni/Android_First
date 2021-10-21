package com.example.sondouni.ch10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.sondouni.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeeklyBoxOfficeActivity extends AppCompatActivity {
    private DatePicker dpTargetDt;
    private WeeklyBoxofficeAdapter adapter;
    private RecyclerView rvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_box_office);
        adapter = new WeeklyBoxofficeAdapter();

        dpTargetDt = findViewById(R.id.dpTargetDt);
        rvList = findViewById(R.id.rvList);
        rvList.setAdapter(adapter);

    }
    private void network(String targetDt,String weekGb){
        Log.i("myLog","어뎁터성공");
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KobisService sv = rf.create(KobisService.class);
        final String KEY = "71ebaabe99c41b10686133dae485862a";
        //코비스 서비스에서 콜이 지금 오버로딩이 되어있는건가?
        Call<BoxOfficeResultBodyVO> call = sv.boxofficeSerchWeeklyBoxOfficeList(KEY, targetDt,weekGb);

        call.enqueue(new Callback<BoxOfficeResultBodyVO>() {
            @Override
            public void onResponse(Call<BoxOfficeResultBodyVO> call, Response<BoxOfficeResultBodyVO> res) {
                Log.i("myLog","연결성공");
                if(res.isSuccessful()){
                    Log.i("myLog","연결성공2");
                    BoxOfficeResultBodyVO vo = res.body();
                    Log.i("myLog","연결성공3");
                    BoxOfficeResultVO resultVO = vo.getBoxOfficeResultVO();
                    Log.i("myLog","연결성공4");
                    List<WeeklyBoxOfficeVO> list = resultVO.getWeeklyBoxOfficelist();
                    Log.i("myLog", list.size() + "개");
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                    Log.i("myLog","통신성공");
                }else {
                    Log.i("myLog","데이터실패");
                }
            }

            @Override
            public void onFailure(Call<BoxOfficeResultBodyVO> call, Throwable t) {
                Log.i("myLog","통신실패");
            }
        });
    }
    public void clkSearch(View v){
        int day = dpTargetDt.getDayOfMonth();
        int mon = dpTargetDt.getMonth()+1;
        int year = dpTargetDt.getYear();

        String date = String.format("%s%02d%02d",year,mon,day);
        String weekGb = "0";
        Log.i("myLog","클릭성공");
        network(date,weekGb);
    }
}