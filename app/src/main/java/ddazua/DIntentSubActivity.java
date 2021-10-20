package ddazua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sondouni.R;

public class DIntentSubActivity extends AppCompatActivity {
    Button pre_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dintent_sub);
        pre_btn = findViewById(R.id.pre_btn);
        pre_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(DIntentSubActivity.this,DIntentActivity.class);
                //startActivity(i); // -> 엑티비티가 쌓임
                finish(); // ->액티비티 종료
            }
        });
    }
}