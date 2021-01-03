package com.example.cs478_project_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button call;
    private TextView display;
    public static final int call_num = -999;
    String receive;
    int result_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        display = findViewById(R.id.textView);
        call = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( result_num == 1 ) {
                    Uri num = Uri.parse("tel:" + receive);
                    Intent dialer = new Intent(Intent.ACTION_DIAL, num);
                    startActivity(dialer);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Number Invalid.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    // opens new activity
    public void openActivity2(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivityForResult(intent, 1);
    }

    // Acts upto receiving the results from Activity 2
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            result_num = 1;
        }
        else if(resultCode == RESULT_CANCELED){
            result_num = 0;
        }
        receive = data.getStringExtra("Number");
        display.setText(receive);
    }
}