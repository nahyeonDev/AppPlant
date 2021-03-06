package com.cookandroid.plantandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class PlantDetail extends AppCompatActivity {

    private Intent intent;
    private TextView plantTitle;
    private String title;
    private int number;

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    String data_title = null;

    TextView pSize;
    TextView pLocation;
    TextView pSpecial;
    TextView pContent;
    ImageView pImg;

    ImageButton likeBtn;
    boolean i = true;

    private String userId;
    private String uid;
    private String HeartBookmark;

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

        pImg = findViewById(R.id.imageView2);

        String imgName = data_title + ".png";
        //파이어베이스 스토리지 연결
        firebaseStorage = FirebaseStorage.getInstance();
        //파이어베이스 스토리지에서 사진 경로 설정
        storageReference = firebaseStorage.getReference().child("plantdetail").child(imgName);
        //StorageReference에서 파일 다운로드 URL 가져옴
        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    // Glide 이용하여 이미지뷰에 로딩
                    Glide.with(PlantDetail.this)
                            .load(task.getResult())
                            .into(pImg);
                } else {
                    // URL을 가져오지 못함.
                }
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
//        db.collection("HeartBookmark")
//                .add(plant)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) { //디테일 들어가면 문서와 필드가 추가됨
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });

        //사용자 이메일로 uid 만들어서 각 사용자마다의 찜을 저장.
        userId = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String target = "@";
        int target_num = userId.indexOf(target);
        uid = userId.substring(0, target_num);
        HeartBookmark = uid + "HeartBookmark";

        likeBtn = (ImageButton)findViewById(R.id.like_btn);
        likeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (i == true){ //문제- 처음에 버튼이 안먹혀서 하트 해제할때마다 필드 업뎃일어남. 반대로 구현되게해야됨. 밑에 주석풀면 이해될것
                    likeBtn.setBackgroundResource(R.drawable.heart);
                    i = false;

                    //문서 이름을 식물 이름(data_title)으로 저장
                    db.collection(HeartBookmark).document(data_title).set(plant);


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
                    likeBtn.setBackgroundResource(R.drawable.line_heart);
                    i = true;

                    //하트북마크 해제할시 저장한것도 삭제함
                    db.collection(HeartBookmark).document(data_title).delete();
                }

            }
        });


    }
}