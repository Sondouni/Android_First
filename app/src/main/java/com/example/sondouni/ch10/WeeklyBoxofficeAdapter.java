package com.example.sondouni.ch10;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sondouni.R;
import com.example.sondouni.Utils;

import java.util.List;

public class WeeklyBoxofficeAdapter extends RecyclerView.Adapter<WeeklyBoxofficeAdapter.MyViewHolder> {
    private List<WeeklyBoxOfficeVO> list;
    public void setList(List<WeeklyBoxOfficeVO> list){this.list = list;}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("myLog","뷰홀더생성");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_w_b_o,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("myLog","홀더에 값넣기");
        holder.setItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list==null? 0:list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle,tvAudienceCnt,tvRank,tvDate;
        public MyViewHolder(View v){
            super(v);
            Log.i("myLog","이너클래스 생성");
            tvTitle = v.findViewById(R.id.tvTitle);
            tvAudienceCnt = v.findViewById(R.id.tvAudienceCnt);
            tvRank = v.findViewById(R.id.tvRank);
            tvDate = v.findViewById(R.id.tvDate);
        }
        public void setItem(WeeklyBoxOfficeVO vo){
            Log.i("myLog","데이터가져오기");
            tvTitle.setText(vo.getMovieNm());
            tvRank.setText(vo.getRank());
            tvDate.setText(vo.getOpenDt());
            tvAudienceCnt.setText(Utils.getNumberComma(vo.getAudiCnt())+"명");

        }
    }
}
