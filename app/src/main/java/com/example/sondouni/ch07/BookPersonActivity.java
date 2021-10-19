package com.example.sondouni.ch07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sondouni.R;
import com.example.sondouni.Utils;

import java.util.List;

public class BookPersonActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private PersonAdapter adapter;
    private EditText etName,etAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_person);
//        rvList = findViewById(R.id.rvList);//view를 상송받는 appCompatActivity라서 v를 import안해도됨
//        adapter = new PersonAdapter();
//        rvList.setLayoutManager(new LinearLayoutManager(this));
//        rvList.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        etName = findViewById(R.id.etName);
//        etAge = findViewById(R.id.etAge);
        rvList = findViewById(R.id.rvList);//view를 상송받는 appCompatActivity라서 v를 import안해도됨
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        adapter = new PersonAdapter();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);
    }
    public void clkReg(View v){
//이노트북은 이제 제껍니다.
        String name = (String)etName.getText().toString();
        String age =  (String)etAge.getText().toString(); //getText().toString() toString으로 쉽게 String캐스팅
        int intAge = Utils.parseStringToInt(age);
//        (Integer.parseInt((String)etAge.getText().toString()))
        if(intAge == 0){
            error();
        }else {
            Person p = new Person(((String) etName.getText().toString()), intAge);
            adapter.addItem(p);
            adapter.notifyDataSetChanged();
            etName.setText("");
            etAge.setText("");
        }
    }
    public void error(){
        Toast.makeText(this, "문제가 발생하였습니다"
                , Toast.LENGTH_LONG).show();
        adapter.notifyDataSetChanged();
//        etName.setText("");
//        etAge.setText("");
    }
}
