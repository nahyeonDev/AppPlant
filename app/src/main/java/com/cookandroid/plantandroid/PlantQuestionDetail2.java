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

public class PlantQuestionDetail2 extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private String main = null;
    private String sub = null;
    private String iNum = null;

    public String Q_title="";

    TextView Qtitle;
    TextView Qtxt;
    TextView Qcontent;
    ImageButton backBtn_Q;
    ImageView QImage;

    ImageButton starBtn;
    boolean i = true;

    private String userId;
    private String uid;
    private String StarBookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_question_detail_img);

        Qtitle = findViewById(R.id.QTitle);
        Qtxt = findViewById(R.id.plantTitle);
        Qcontent = findViewById(R.id.plant_data);
        QImage = findViewById(R.id.pImg);

        //intent로 데이터베이스 관련 정보 받아 옴.
        Intent qIntent = getIntent();
        String mainQ = qIntent.getStringExtra("메인");
        String subQ = qIntent.getStringExtra("상세");
        String imgQ = qIntent.getStringExtra("사진");

        main = new String(mainQ);
        sub = new String(subQ);
        iNum = new String(imgQ);

        if(iNum != null){
            String imgName = "img" + iNum + ".png";
            //파이어베이스 스토리지 연결
            firebaseStorage = FirebaseStorage.getInstance();
            //파이어베이스 스토리지에서 사진 경로 설정
            storageReference = firebaseStorage.getReference().child("qPhoto").child(imgName);
            //StorageReference에서 파일 다운로드 URL 가져옴
            storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        // Glide 이용하여 이미지뷰에 로딩
                        Glide.with(PlantQuestionDetail2.this)
                                .load(task.getResult())
                                .into(QImage);
                    } else {
                        // URL을 가져오지 못함.
                    }
                }
            });
        }

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(main).child(sub);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    QList txt = snapshot.getValue(QList.class);

                    Qtitle.setText(txt.gettitle());
                    Q_title = txt.getquestion();
                    Qtxt.setText(Q_title);
                    Qcontent.setText(txt.getcontent());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String TAG="hoooo";//임의로 작성
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        // 질문을 Map으로 구현해 추가할 필드 넣기
        Map<String, Object> Question = new HashMap<>();

        //사용자 이메일로 uid 만들어서 각 사용자마다의 별을 저장.
        userId = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String target = "@";
        int target_num = userId.indexOf(target);
        uid = userId.substring(0, target_num);
        StarBookmark = uid + "StarBookmark";

        starBtn = (ImageButton)findViewById(R.id.star_btn);
        starBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == true){
                    starBtn.setBackgroundResource(R.drawable.star);
                    i = false;

                    //문서 이름을 질문제목(Q_title)으로 저장
                    Question.put("title", Qtitle.getText());
                    Question.put("content", Qcontent.getText());
                    Question.put("question", Q_title);

                    Question.put("mainQ",mainQ);
                    Question.put("subQ",subQ);

                    db.collection(StarBookmark).document(Q_title).set(Question);

                }else {
                    starBtn.setBackgroundResource(R.drawable.line_star);
                    i = true;
                    //별북마크 해제할시 저장한것도 삭제함
                    db.collection(StarBookmark).document(Q_title).delete();
                }
            }
        });

        //back 버튼 액션
        backBtn_Q = findViewById(R.id.backBtn_Q);
        backBtn_Q.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {finish();}
        });
    }
}
