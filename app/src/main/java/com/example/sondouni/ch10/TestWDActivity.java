package com.example.sondouni.ch10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.sondouni.R;
import com.example.sondouni.Utils;
import com.example.sondouni.ch10.boxofficemodel.BoxOfficeResultBodyVO;
import com.example.sondouni.ch10.boxofficemodel.BoxOfficeResultVO;
import com.example.sondouni.ch10.boxofficemodel.WeeklyBoxOfficeVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestWDActivity extends AppCompatActivity {
    private WTBoxofficeAdapter adapter;
    private DatePicker dpTargetDt;

    private RecyclerView rvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_box_office);
        adapter = new WTBoxofficeAdapter();

        dpTargetDt = findViewById(R.id.dpTargetDt);
        rvList = findViewById(R.id.rvList);
        rvList.setAdapter(adapter);
    }
    private void network(String targetDt) {

        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //gson형태로 되어있는것을 java에서 읽을수있게끔 만들어줌

        KobisService service = rf.create(KobisService.class);
        final String KEY = "71ebaabe99c41b10686133dae485862a";
        final String weekGb = "1";
        Call<BoxOfficeResultBodyVO> call = service.boxofficeSerchWeeklyBoxOfficeList1(KEY, targetDt);

        call.enqueue(new Callback<BoxOfficeResultBodyVO>() {
            @Override
            public void onResponse(Call<BoxOfficeResultBodyVO> call, Response<BoxOfficeResultBodyVO> res) {
                if (res.isSuccessful()) {
                    //BoxOfficeResultBodyVO vo = res.body();
                    //Log.i("myLog", vo.getBoxOfficeResultVO().getDailyBoxOfficeVOList().size() + "개");

                    BoxOfficeResultBodyVO vo1 = res.body();
                    BoxOfficeResultVO resultVo = vo1.getBoxOfficeResultVO();
                    List<WeeklyBoxOfficeVO> list = resultVo.getWeeklyBoxOfficelist();

//                    Log.i("myLog", list.size() + "개"); //-> 위에것을 풀어쓰기
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
//                    for (DailyBoxOfficeVO item : vo.getBoxOfficeResultVO().getDailyBoxOfficeVOList()) {
//                        Log.i("myLog", item.getMovieNm());
//                    }
                } else {
                    Log.d("myLog", "response(Date) Fail");
                }
            }

            @Override
            public void onFailure(Call<BoxOfficeResultBodyVO> call, Throwable t) {

            }
        });

    }
    public void clkSearch(View v) {
        int day = dpTargetDt.getDayOfMonth();
        int mon = dpTargetDt.getMonth() + 1;
        int year = dpTargetDt.getYear();

        String date = String.format("%s%02d%02d", year, mon, day);
        /*
        Calendar c = Calendar.getInstance();
        c.set(year,mon,day);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(c.getTime());
        캘린더클래스 + dateFormat
        */

        network(date);
        Log.i("myLog", date);
        //        Calendar c = Calendar.getInstance();
//        c.set(year,mon,day);
//        Date date = c.getTime();
//        Log.i("myLog",date.toString()); -> java 캘린더 클래스
    }
}
class WTBoxofficeAdapter extends RecyclerView.Adapter<WTBoxofficeAdapter.MyViewHolder>{
    private List<WeeklyBoxOfficeVO> list;
    public void setList(List<WeeklyBoxOfficeVO> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_daily_boxoffice,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        DailyBoxOfficeVO vo = list.get(position);
//        holder.setItem(vo);
        holder.setItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list==null? 0:list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvAudienceCnt;
        public MyViewHolder(View v){
            super(v);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvAudienceCnt = v.findViewById(R.id.tvAudienceCnt);
        }
        public void setItem(WeeklyBoxOfficeVO vo){
            tvTitle.setText(vo.getMovieNm());
            tvAudienceCnt.setText(Utils.getNumberComma(vo.getAudiCnt())+"명");
            Log.i("myLog","11111");
        }
    }
}