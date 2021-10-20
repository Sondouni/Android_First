package ddazua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sondouni.R;

public class DHandlerActivity extends AppCompatActivity {
    TextView txt_count;
    Button dstart,dstop;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhandler);

        txt_count = findViewById(R.id.txt_count);
        dstart = findViewById(R.id.dstart);
        dstop = findViewById(R.id.dstop);

        dstart.setOnClickListener(click);
        dstop.setOnClickListener(click);
    }
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.dstart:
                    handler.sendEmptyMessage(0);
                    dstart.setEnabled(false);
                    break;
                case R.id.dstop:
                    handler.removeMessages(0);
                    dstart.setEnabled(true);
                    break;
            }
        }
    };
    //카운트를 증가시키는 핸들러
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
            //일정간격으로 핸들러 자신을 반복호출
            handler.sendEmptyMessageDelayed(0,1000);
            txt_count.setText(String.valueOf(++count));//리터럴값(정수)를 집어넣으면 오류
        }
    };
}