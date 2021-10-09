package com.cookandroid.plantandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.List;

public class PlantList extends AppCompatActivity{
    private ImageButton backBtn_plant_list;
    private RecyclerView RecyclerView_main = null;
    private PlantAdapter pAdapter = null;
    private ArrayList<ListItemObj> pList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_list);

        //10.5추가
        backBtn_plant_list= findViewById(R.id.backBtn_plant_list);

//        식물 상세페이지 연결
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

        RecyclerView_main = findViewById(R.id.recycler_view);
        pList = new ArrayList<>();

        pAdapter = new PlantAdapter(pList);
        RecyclerView_main.setAdapter(pAdapter);
        RecyclerView_main.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));

        addItem("식물1");
        addItem("2");
        addItem("3");


        pAdapter.notifyDataSetChanged();

    }

    private void addItem(String text) {
        ListItemObj item = new ListItemObj(text);

        pList.add(item);
    }
}