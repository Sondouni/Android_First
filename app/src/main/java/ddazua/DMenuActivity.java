package ddazua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.sondouni.R;

public class DMenuActivity extends AppCompatActivity {
    Button popMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dmenu);

        popMenu = findViewById(R.id.popMenu);
        popMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//View v에 이 인터페이스를 부른 popMenu가 호출됨
                PopupMenu popup = new PopupMenu(DMenuActivity.this,v);//context에 DMenuActivity.this 로 해야 같은 메서드 안이라도 지시가능
                //팝업메뉴에 뿌려줄 메뉴 리소스 파일 참조
                getMenuInflater().inflate(R.menu.my_menu, popup.getMenu());
                //팝업메뉴에 이벤트 감지자 등록
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.add:
                                Toast.makeText(DMenuActivity.this,"add button click",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.edit:
                                Toast.makeText(DMenuActivity.this,"edit button click",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.email:
                                Toast.makeText(DMenuActivity.this,"email button click",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        //팝업메뉴는 리턴값에 관계없이 동작
                        return false;
                    }
                });
                //팝업메뉴를 화면에 표시
                popup.show();
            }
        });
    }
    public void menuClick(View v){
        int id = v.getId();
        Class c = null;
        if(id == R.id.mBtn1){
            c = DAlertActivity.class;
        }else if(id == R.id.mBtn2){
            c = DIntentActivity.class;
        }else if(id == R.id.mBtn3){
            c = DDialogActivity.class;
        }
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }
    //엑티비티 타이틀바에 메뉴호출 기능을 추가
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
        //super.onCreateOptionsMenu(menu)
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Toast.makeText(DMenuActivity.this,"add button click",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit:
                Toast.makeText(DMenuActivity.this,"edit button click",Toast.LENGTH_SHORT).show();
                break;
            case R.id.email:
                Toast.makeText(DMenuActivity.this,"email button click",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}