package com.example.sondouni.ch07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sondouni.R;

import java.util.List;

public class BookPersonActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_person);

        rvList = findViewById(R.id.rvList);
        adapter = new PersonAdapter();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);

        adapter.addItem(new Person("홍길동",12));
        adapter.addItem(new Person("김룰러",32));
        adapter.addItem(new Person("바보",41));
        adapter.addItem(new Person("멍청이",53));
        adapter.addItem(new Person("센척하는",76));
        adapter.addItem(new Person("머저리",33));

        adapter.notifyDataSetChanged();
    }
}