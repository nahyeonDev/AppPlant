package com.cookandroid.plantandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlantList extends AppCompatActivity{
    private ImageButton backBtn_plant_list;
    private RecyclerView RecyclerView_main = null;
    private PlantAdapter pAdapter = null;
    private ArrayList<ListItemObj> pList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.plant_list);

        //10.5추가
        backBtn_plant_list = findViewById(R.id.backBtn_plant_list);

        //식물 상세페이지 연결
//        식물 상세페이지 연결
//        listBtn1.setOnClickListener(new Button.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//            protected void onCreate(Bundle savedInstanceState) {
//                public void onClick(View v) {finish();}
//            });


        pList = new ArrayList<>();

        RecyclerView_main = findViewById(R.id.recycler_view);
        RecyclerView_main.setLayoutManager(new LinearLayoutManager(this));

        pAdapter = new PlantAdapter(pList);
        RecyclerView_main.setAdapter(pAdapter);

        //데이터 양이 많아지면 화면에서 어떻게 보일지 테스트로 더 넣음.
        addItem("식물1");
        addItem("식물2");
        addItem("식물3");
        addItem("식물4");
        addItem("식물5");


        pAdapter.notifyDataSetChanged();
    }

    private void addItem (String name){
        ListItemObj item = new ListItemObj();
        item.setPlantName(name);

        pList.add(item);
    }

}