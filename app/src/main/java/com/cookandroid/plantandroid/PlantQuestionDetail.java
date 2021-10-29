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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PlantQuestionDetail extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    TextView Qtitle;
    TextView Qtxt;
    TextView Qcontent;
    ImageButton backBtn_Q;

    private String main = null;
    private String sub = null;
    public String Q_title="";

    ImageButton starBtn;
    boolean i = true;

    private String userId;
    private String uid;
    private String StarBookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_question_detail);

        Qtitle = findViewById(R.id.QTitle);
        Qtxt = findViewById(R.id.QTxt);
        Qcontent = findViewById(R.id.plant_data);

        //intent로 데이터베이스 관련 정보 받아 옴.
        Intent qIntent = getIntent();
        String mainQ = qIntent.getStringExtra("메인");
        String subQ = qIntent.getStringExtra("상세");

        main = new String(mainQ);
        sub = new String(subQ);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(main).child(sub);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                QList txt = snapshot.getValue(QList.class);

                Qtitle.setText(txt.gettitle());
                Q_title=txt.getquestion();
                Qtxt.setText(Q_title);
                Qcontent.setText(txt.getcontent());
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
                    Question.put("Q_title", Q_title);
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
