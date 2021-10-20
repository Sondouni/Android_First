package ddazua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sondouni.R;

public class DIntentActivity extends AppCompatActivity {
    Button next_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IntentSubActivity로 화면전환
                Intent i = new Intent(DIntentActivity.this,DIntentSubActivity.class);//2개 파라미터 1. 현재 위치 액티비티 2. 전환할 액티비티
                startActivity(i);//지정해 놓은 페이지로 화면을 전환
            }
        });
    }
}