package com.cookandroid.plantandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PlantList extends AppCompatActivity{
    private ImageButton backBtn_plant_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_list);

        //10.5추가
        backBtn_plant_list= findViewById(R.id.backBtn_plant_list);

        //식물 상세페이지 연결
//        listBtn1.setOnClickListener(new Button.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), PlantDetail.class);
//                startActivity(intent);
//            }
//        });



        //뒤로가기 버튼
        backBtn_plant_list.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {finish();}
        });


    }
}