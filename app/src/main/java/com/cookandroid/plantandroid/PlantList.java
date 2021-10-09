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

            RecyclerView_main = findViewById(R.id.recycler_view);
            pList = new ArrayList<>();

            //Unable to start activity ComponentInfo/ java.lang.NullPointerException: Attempt to invoke virtual method 'void androidx.recyclerview.widget.RecyclerView.setAdapter(androidx.recyclerview.widget.RecyclerView$Adapter)' on a null object reference
            pAdapter = new PlantAdapter(pList);
            RecyclerView_main.setAdapter(pAdapter);
            RecyclerView_main.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

            addItem("식물1");
            addItem("2");
            addItem("3");


            pAdapter.notifyDataSetChanged();

        }

        private void addItem (String text){
            ListItemObj item = new ListItemObj(text);

            pList.add(item);
        }

    }