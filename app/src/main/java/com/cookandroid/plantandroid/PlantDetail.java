package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
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

    ImageButton likeBtn;
    boolean i = true;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        intent = getIntent();
        number = intent.getIntExtra("number", -1); //이걸로 데이터 구분할수있을듯
        title = intent.getStringExtra("title");

        plantTitle = findViewById(R.id.plantTitle);      plantTitle.setText(title);

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

        //파이어베이스에 하트 누르면 현재 사용자의 이메일과 그 게시물의 식물 이름 같이 저장할 예정
        //마이페이지 화면에서 현재 사용자 이메일로 찜 내역 불러올 것임.
        userId = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        database.getReference("Like").setValue(userId);

        likeBtn = (ImageButton)findViewById(R.id.like_btn);
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == true){
                    likeBtn.setBackgroundResource(R.drawable.line_heart);
                    i = false;
                }else {
                    likeBtn.setBackgroundResource(R.drawable.heart);
                    i = true;
                }
            }
        });
    }
}