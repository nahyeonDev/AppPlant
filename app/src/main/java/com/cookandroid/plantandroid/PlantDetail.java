package com.cookandroid.plantandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlantDetail extends AppCompatActivity {

    private Intent intent;
    private TextView plantTitle;
    private String title;
    private int number;

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    String data_title = null;

    TextView pSize;
    TextView pLocation;
    TextView pSpecial;
    TextView pContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        intent = getIntent();
        number = intent.getIntExtra("number", -1); //이걸로 데이터 구분할수있을듯
        title = intent.getStringExtra("title");

        plantTitle = findViewById(R.id.plantTitle);
        plantTitle.setText(title);

        pSize = findViewById(R.id.plant_size);
        pLocation = findViewById(R.id.plant_location);
        pSpecial = findViewById(R.id.plant_detail);
        pContent = findViewById(R.id.plant_data);

        //받아온 식물 이름을 string 형태로 변환.
        data_title = new String(title);
        database = FirebaseDatabase.getInstance();

        //받아온 식물 이름으로 파이어베이스에서 데이터 읽어 옴.
        databaseReference = database.getReference("Plant").child("AllPlantContent").child(data_title);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PConItem list = snapshot.getValue(PConItem.class);
                    pSize.setText(list.getsize());
                    pLocation.setText(list.getlocation());
                    pSpecial.setText(list.getspecial());
                    pContent.setText(list.getcontent());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}