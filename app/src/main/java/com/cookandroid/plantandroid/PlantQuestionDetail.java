package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlantQuestionDetail extends AppCompatActivity {

//    private DatabaseReference databaseReference;
//    private FirebaseDatabase database;

    TextView Qtitle;
    TextView Qtxt;
    TextView Qcontent;
    private ImageButton backBtn;

    private String main = null;
    private String sub = null;

    ImageButton starBtn;
    boolean i = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_question_detail);

        Qtitle = findViewById(R.id.QTitle);
        Qtxt = findViewById(R.id.QTxt);
        Qcontent = findViewById(R.id.plant_data);

//        //intent로 데이터베이스 관련 정보 받아 옴.
//        Intent qIntent = getIntent();
//        String mainQ = qIntent.getStringExtra("메인");
//        String subQ = qIntent.getStringExtra("상세");
//
//        main = new String(mainQ);
//        sub = new String(subQ);
//
//        database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference(main).child(sub);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                QList txt = snapshot.getValue(QList.class);
//
//                Qtitle.setText(txt.gettitle());
//                Qtxt.setText(txt.getquestion());
//                Qcontent.setText(txt.getcontent());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        starBtn = (ImageButton)findViewById(R.id.star_btn);
        starBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == true){
                    starBtn.setBackgroundResource(R.drawable.line_star);
                    i = false;
                }else {
                    starBtn.setBackgroundResource(R.drawable.star);
                    i = true;
                }
            }
        });

        //back 버튼 액션
        backBtn = findViewById(R.id.backBtn_Q);
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {finish();}
        });
    }
}
