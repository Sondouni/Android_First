package com.example.sondouni.picsum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sondouni.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PicsumActivity extends AppCompatActivity {
    private RecyclerView rvlist;
    private PicsumAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picsum);

        rvlist = findViewById(R.id.rvList);
        adapter = new PicsumAdapter();

        rvlist.setAdapter(adapter);

        network();

    }
    private void network(){

        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://picsum.photos/")
                .addConverterFactory(GsonConverterFactory.create())//gson형태로 되어있는것을 java에서 읽을수있게끔 만들어줌
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
                    List<PicsumVO> list = response.body();//데이터가 들어있는곳,제네릭을 안줫으면 오브젝트가 리턴됨

                    adapter.setList(list);
                    adapter.notifyDataSetChanged();

//                    Log.i("myLog","----response success----");
//                    for(PicsumVO vo : list){
//                        Log.i("myLog",vo.getAuthor());
//                    }->> 통신이 다 잘 됏는지 확인용
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
class PicsumAdapter extends RecyclerView.Adapter<PicsumAdapter.PicsumViewHolder> {
    private List<PicsumVO> list;
    public void setList(List<PicsumVO> list){
        this.list = list;
    }

    @NonNull
    @Override
    public PicsumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_picsum,parent,false);
        return new PicsumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PicsumViewHolder holder, int position) {
        PicsumVO vo = list.get(position);
        holder.setItem(vo);
    }

    @Override
    public int getItemCount() {
        return list == null? 0:list.size();
    }

    static class PicsumViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivImg;
        private TextView tvAuthor;
        private View v;
        public PicsumViewHolder(View v){
            super(v);
            this.v = v;
            ivImg = v.findViewById(R.id.ivImg);// onCreate는 레이아웃의 위치를 알고있지만, 여기서는 어디 레이아웃인지를 몰라 들어오는 데이터인 View v의 레이아웃속에서 찾겠다는 의미
            tvAuthor = v.findViewById(R.id.tvAuthor);
        }
        public void setItem(PicsumVO vo){
            Glide.with(v).load(vo.getDownload_url()).into(ivImg);
            tvAuthor.setText(vo.getAuthor());
        }
    }
}