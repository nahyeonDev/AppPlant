package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

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
//        userId = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//        database.getReference("Like").setValue(userId);

        String TAG="hoooo";//임의로 작성
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        // plant를 Map으로 구현해 추가할 필드 넣기
        Map<String, Object> plant = new HashMap<>();
        plant.put("plantName", data_title);
//        plant.put("last", "Lovelace");

        // 문서와 ID 넣기
        db.collection("HeartBookmark")
                .add(plant)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) { //디테일 들어가면 문서와 필드가 추가됨
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        likeBtn = (ImageButton)findViewById(R.id.like_btn);
        likeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (i == true){ //문제- 처음에 버튼이 안먹혀서 하트 해제할때마다 필드 업뎃일어남. 반대로 구현되게해야됨. 밑에 주석풀면 이해될것
                    likeBtn.setBackgroundResource(R.drawable.line_heart);
                    i = false;

//                    db.collection("HeartBookmark")
//                            .get()
//                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                    if (task.isSuccessful()) {
//                                        for (QueryDocumentSnapshot document : task.getResult()){ //하트 눌렀을때 목록들 보여줌
//                                            Log.d(TAG, document.getId() + " => " + document.getData());
//                                        }
//                                    } else {
//                                        Log.w(TAG, "Error getting documents.", task.getException());
//                                    }
//                                }
//                            });

                }else {
                    likeBtn.setBackgroundResource(R.drawable.heart);
                    i = true;
                }

            }
        });


    }
}