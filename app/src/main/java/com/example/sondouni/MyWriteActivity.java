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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class MyWriteActivity extends AppCompatActivity {
    private EditText etMsg;
    private Button btnSend;
    private RecyclerView rvList;
    private List<String> msgList;
    SimpleTextAdapter sta = new SimpleTextAdapter(msgList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        etMsg = findViewById(R.id.etMsg);//부모의 메소드는 객체생성필요없이 메소드 실행가능
        btnSend = findViewById(R.id.btnSend);
        rvList = findViewById(R.id.rvList);

        msgList = new LinkedList<>();//위에서 아래로 vertical

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvList.setLayoutManager(llm); // 화면에 어떻게 나올지 세팅해주는 부분
        sta = new SimpleTextAdapter(msgList);
        rvList.setAdapter(sta);//adapter를 연결하는데 쓰이는 메소드

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = etMsg.getText().toString();
                Log.i("myLog",msg);
                etMsg.setText("");
                msgList.add(msg);
                sta.notifyDataSetChanged();
            }
        });
    }
    public void refresh(View v){ sta.notifyDataSetChanged(); }
}

class SimpleTextAdapter1 extends RecyclerView.Adapter<SimpleTextAdapter1.MyViewHolder1>{
    public List<String> list;
    SimpleTextAdapter1(List<String> list){ this.list = list;}

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_textview,parent,false);
        return new SimpleTextAdapter1.MyViewHolder1(layoutView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder1, int position) {
        Log.i("myLog","Position : "+position);
        String str = list.get(position);
        holder1.tvMsg.setText(str);
    }

    @Override
    public int getItemCount() {
        Log.i("myLog","getItemCount : "+list.size());
        return list.size();
    }
    class MyViewHolder1 extends RecyclerView.ViewHolder{
        TextView tvMsg;

        public MyViewHolder1(View v){
            super(v);
            tvMsg = itemView.findViewById(R.id.tvMsg);
        }
    }
}

