package ddazua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sondouni.R;

public class DdIntentSubSActivity extends AppCompatActivity {
    TextView txt_name,txt_age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd_intent_sub_sactivity);

        txt_name = findViewById(R.id.txt_name);
        txt_age = findViewById(R.id.txt_age);

        //BundleActivity에서 준 intent정보를 받으려면 이렇게 해야함함
       Intent i = getIntent();

        String name = i.getStringExtra("m_name");
        String age = i.getStringExtra("m_name");

        Bundle b = i.getExtras();
        String nname = b.getString("m_name");
        String aage = b.getString("m_nage");

        txt_name.setText(name);
        txt_age.setText(age);
    }
}