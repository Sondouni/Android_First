package ddazua;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sondouni.R;

public class DAlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dalert);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        //AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(DAlertActivity.this);

        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DAlertActivity.this,"no click",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNeutralButton("neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DAlertActivity.this,"neutral click",Toast.LENGTH_SHORT).show();
            }
        });

        dialog.setTitle("app name");
        dialog.setMessage("Are you sure?");
        dialog.show();
    }
}