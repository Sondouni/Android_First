package ddazua;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sondouni.R;

public class DDialogActivity extends AppCompatActivity {
    Dialog dialog;
    Button btn_ok,btn_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddialog);
    }
    //뒤로가기 버튼 클릭 감지

    @Override
    public void onBackPressed() {
        //super.onBackPressed(); -> 뒤로가기클릭시 무조건 앱을 종료시킴
        //다이얼로그 생성
        dialog = new Dialog(DDialogActivity.this);
        dialog.setContentView(R.layout.ddazua_dialog);//dialog에 ddazua_dialog를 넣음

        btn_ok = dialog.findViewById(R.id.btn_ok);//dialog를 통해 id값을 가져옴
        btn_no = dialog.findViewById(R.id.btn_no);

        btn_ok.setOnClickListener(click);
        btn_no.setOnClickListener(click);

        dialog.setTitle("app name");

        //show()메서드를 호출하지 않으면 다이얼로그가 화면에 노출되지 않는다
        dialog.show();
    }
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_ok:
                    finish();
                    break;
                case R.id.btn_no:
                    //다이얼로그 취소
                    dialog.dismiss();

            }
        }
    };
}