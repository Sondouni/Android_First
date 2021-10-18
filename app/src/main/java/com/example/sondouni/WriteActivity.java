package com.example.sondouni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class WriteActivity extends AppCompatActivity {
    private EditText etMsg;
    private Button btnSend;
    private RecyclerView rvList; // view 영역
    private List<String> msgList;// date
    SimpleTextAdapter sta = new SimpleTextAdapter(msgList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write); // activity_write을 화면에 보여라

        etMsg = findViewById(R.id.etMsg);
        btnSend = findViewById(R.id.btnSend);
        rvList = findViewById(R.id.rvList); // 각 객체에 writeactivity의 각id값을 가져와서 findViewById로 주소값을 줌

        msgList = new LinkedList<>();


        LinearLayoutManager llm = new LinearLayoutManager(this); // recyclerView를 어떻게 보여줄지 보여줌
        rvList.setLayoutManager(llm); //
        sta = new SimpleTextAdapter(msgList); // adapter를 연결
        rvList.setAdapter(sta);

        btnSend.setOnClickListener(new View.OnClickListener() { //클래스 이름없이 interface를 implement했다(interface의 객체화??)
            @Override
            public void onClick(View v) {
                String msg = etMsg.getText().toString();
                Log.i("myLog",msg);
                etMsg.setText("");
                msgList.add(msg);
//                sta.notifyDataSetChanged();
            }
        });
    }
    public void refresh(View v){
        sta.notifyDataSetChanged();
    }
}

class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.MyViewHolder>{
    public  List<String> list;
    SimpleTextAdapter(List<String> list){
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_textview,parent,false);//layout파일을 자바객체주소값으로 만들어 쓰겠다
        return new SimpleTextAdapter.MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("myLog","Position : "+position);
        String str = list.get(position);
        holder.tvMsg.setText(str);
    }

    @Override
    public int getItemCount() {
        Log.i("myLog","getItemCount : "+list.size());
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{//private 같이 클래스 안에서만 쓰겟다
        TextView tvMsg;

        public MyViewHolder(View v){
            super(v);
            tvMsg = itemView.findViewById(R.id.tvMsg);
        }
    }
}