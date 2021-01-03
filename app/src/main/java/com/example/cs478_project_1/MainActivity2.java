package com.example.cs478_project_1;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.view.KeyEvent;
import java.lang.String;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.TextView.OnEditorActionListener;
import android.util.Log;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements OnEditorActionListener {
    EditText text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text1 =  findViewById(R.id.Number);
        text1.setOnEditorActionListener(this);
    }


    // Checks for the right format of the number and then returns the results
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEND){
            String msg = text1.getText().toString();
            Intent send_result = new Intent();
            send_result.putExtra("Number", msg);
            int count = 0;
            for(int i = 0; i<msg.length(); i++){
                if((msg.charAt(i))- 48 <= 9 && (msg.charAt(i))- 48 >= 0 ){
                    count++;
                }
            }
            if(count == 10){
                setResult(RESULT_OK, send_result );
            }
            else {
                setResult(RESULT_CANCELED, send_result);
            }
            finish();
            return true;
        }
        return false;
    }
}