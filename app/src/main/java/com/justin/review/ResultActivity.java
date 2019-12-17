package com.justin.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent =getIntent();
        double outcome= intent.getDoubleExtra("fee:",-1);
        TextView textView=findViewById(R.id.textView3);
        int n =(int) (outcome+0.5);
        textView.setText("fee: "+n);
    }
}
