package com.cookandroid.plantandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PlantDetail extends AppCompatActivity {

    private Intent intent;
    private TextView plantTitle;
    private String title;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        intent= getIntent();
        number=intent.getIntExtra("number",-1); //이걸로 데이터 구분할수있을듯
        title= intent.getStringExtra("title");

        plantTitle=findViewById(R.id.plantTitle);

        plantTitle.setText(title);




    }
}