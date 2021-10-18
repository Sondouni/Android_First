package com.example.sondouni.ch07;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sondouni.R;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder>{
    List<Person> items = new ArrayList<>();
    public void addItem(Person item){
        items.add(item);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Person item = items.get(position);
        holder.setItem(item);

        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvAge;

        public MyViewHolder(View v){
            super(v);

            tvName = v.findViewById(R.id.tvName);//레이아웃파일 만들어주면 에러없어짐
            tvAge = v.findViewById(R.id.tvAge);
        }
        public void setItem(Person item){
            tvName.setText(item.getName());
            //tvAge.setText(item.getAge());//정수값은 R에서 관리하고있는 정수값만 들어갈수 있다
            //tvAge.setText(R.string.tv_01);//이렇게 String.xml에서 관리하고 있는 문자열을 입력할때 정수값을 사용
//            tvAge.setText(item.getAge()+"살");
            tvAge.setText(String.format("%d살",item.getAge()));
//            String.format("%d살",item.getAge());
        }
    }
}
