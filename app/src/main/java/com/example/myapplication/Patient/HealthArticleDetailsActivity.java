package com.example.myapplication.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class HealthArticleDetailsActivity extends AppCompatActivity {
    TextView tv1;
    ImageView img;
    Button btnBack;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article_details);
        tv1 = findViewById(R.id.textViewHADTitle);
        img = findViewById(R.id.imageViewHAD);
        btnBack = findViewById(R.id.buttonHADBack);
        Intent intent=getIntent();
        tv1.setText(intent.getStringExtra("text1"));
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            int resId=bundle.getInt("text2");
            img.setImageResource(resId);
        }
        btnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(HealthArticleDetailsActivity.this,HealthArticleActivity.class));
            }
        });
    }
}