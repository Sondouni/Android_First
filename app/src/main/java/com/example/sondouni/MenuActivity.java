package com.example.sondouni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sondouni.ch07.BookPersonActivity;
import com.example.sondouni.ch10.DailyBoxOfficeActivity;
import com.example.sondouni.ch10.MovieListActivity;
import com.example.sondouni.ch10.TestWDActivity;
import com.example.sondouni.ch10.WeeklyBoxOfficeActivity;
import com.example.sondouni.picsum.PicsumActivity;
import com.google.android.material.snackbar.Snackbar;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void moveToActivity(View v){
        int id = v.getId();
        Class c = null;
        switch (id){
            case R.id.menuBtn1:
                c=MainActivity.class;
                break;
            case R.id.menuBtn2:
                c=LinearActivity.class;
                break;
            case R.id.menuBtn3:
                c=ConstraintActivity.class;
                break;
            case R.id.menuBtn4:
                c=WriteActivity.class;
                break;
            case R.id.menuBtn5:
                c= BookPersonActivity.class;
                break;
            case R.id.menuBtn6:
                c=ImageViewActivity.class;
                break;
            case R.id.menuBtn7:
                c= PicsumActivity.class;
                break;
            case R.id.menuBtn8:
                c= DailyBoxOfficeActivity.class;
                break;
            case R.id.menuBtn9:
                c= WeeklyBoxOfficeActivity.class;
                break;
            case R.id.menuBtn10:
                c= MovieListActivity.class;
                break;
        }
        if(c==null){
            Toast.makeText(this,"갈곳이없는데?",Toast.LENGTH_SHORT).show();
            Snackbar.make(v,"준비중",Snackbar.LENGTH_SHORT);
        }
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }
    public void call(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1111-2222"));
        startActivity(intent);
    }
    public void moveToActivityText(View v){
        TextView tv = (TextView)v;
        String text = (String) tv.getText();
        Log.i("myLog",text);

        Class c = null;
//        if("Main".equals(text))(
//            c=MainActivity.class;
//        )else if
        switch (text){
                case "Main":
                   c=MainActivity.class;
                    break;
               case "Linear":
                   c=LinearActivity.class;
                    break;
                case "Constraint":
                    c=ConstraintActivity.class;
                   break;
            }



        Intent intent = new Intent(this,c);
        startActivity(intent);

//        Intent intent = new Intent(this,c);
//        startActivity(intent);
    }
//    public void moveToMain(View v){
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
//    }
//    public void moveToLinear(View v){
//        Intent intent = new Intent(this,LinearLayout.class);
//        startActivity(intent);
//    }
//    public void moveToConstraint(View v){
//        Intent intent = new Intent(this, ConstraintActivity.class);
//        startActivity(intent);
//    }
}