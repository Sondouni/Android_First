package ddazua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sondouni.R;

public class BundleActivity extends AppCompatActivity {
    EditText edit_name,edit_age;
    Button dbtn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle);

        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        dbtn_send = findViewById(R.id.dbtn_send);

        dbtn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이름과 나이를 sub로 전달
                String name = edit_name.getText().toString();
                String age = edit_age.getText().toString();
                Intent intent = new Intent(BundleActivity.this,DdIntentSubSActivity.class);
                //intent에 값을 저장해서 sub로 보냄
                //intent.putExtra("M_name",name);
                //intent.putExtra("M_age",age);

                Bundle bundle = new Bundle();
                bundle.putString("m_name",name);
                bundle.putString("m_age",age);

                //intent에 bundle 객체를 저장
                intent.putExtras(bundle);


                startActivity(intent);
           }
        });
    }
}