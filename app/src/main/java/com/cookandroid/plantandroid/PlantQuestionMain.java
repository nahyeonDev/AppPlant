package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PlantQuestionMain extends Fragment {
    View v;
    Button content1, content2, content3, content4, content5,
    content6, content7, content8;

    TextView category1, category2, bookTitle1, bookTitle2, bookTitle3;

    ImageView img1, img2, img3;

    private String txt = null;
    private String txt2 = null;
    private String txt3 = null;

    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    private FirebaseDatabase database;
//    private FirebaseStorage firebaseStorage;
//    private StorageReference storageReference;

    LinearLayout bookContent1, bookContent2, bookContent3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.plant_question_main, container, false);

        content1 = v.findViewById(R.id.QContent1);
        content2 = v.findViewById(R.id.QContent2);
        content3 = v.findViewById(R.id.QContent3);
        content4 = v.findViewById(R.id.QContent4);
        content5 = v.findViewById(R.id.QContent5);
        content6 = v.findViewById(R.id.QContent6);
        content7 = v.findViewById(R.id.QContent7);
        content8 = v.findViewById(R.id.QContent8);

        category1 = v.findViewById(R.id.cat_main1);
        category2 = v.findViewById(R.id.cat_main2);

        bookTitle1 = v.findViewById(R.id.book_title1);
        bookTitle2 = v.findViewById(R.id.book_title2);
        bookTitle3 = v.findViewById(R.id.book_title3);

        img1 = v.findViewById(R.id.book_img1);
        img2 = v.findViewById(R.id.book_img2);
        img3 = v.findViewById(R.id.book_img3);

        database = FirebaseDatabase.getInstance();
        //파이어베이스 읽어오기
        for(int i=1; i<=4; i++) {
            txt = "Q" + Integer.toString(i);
            txt2 = "QQ" + Integer.toString(i);
            //첫번째 카테고리(PlantStory - Q1 - question을 접근)
            databaseReference = database.getReference("PlantStory").child(txt);
            //두번째 카테고리(PlantStory2 - QQ1 - question을 접근)
            databaseReference2 = database.getReference("PlantStory2").child(txt2);

            int num = i;

            //첫번째 카테고리
            //question 값을 가져와서 버튼에 setText
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    QList title = snapshot.getValue(QList.class);
                    //카테고리 타이틀
                    category1.setText(title.gettitle());

                    if (num == 1)
                        //질문/제목 배치
                        content1.setText(title.getquestion());
                    else if (num == 2)
                        content2.setText(title.getquestion());
                    else if (num == 3)
                        content3.setText(title.getquestion());
                    else if (num == 4)
                        content4.setText(title.getquestion());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            //두번째 카테고리
            //question 값을 가져와서 버튼에 setText
            databaseReference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    QList title = snapshot.getValue(QList.class);
                    //카테고리 타이틀
                    category2.setText(title.gettitle());
                    if (num == 1)
                        content5.setText(title.getquestion());
                    else if (num == 2)
                        content6.setText(title.getquestion());
                    else if (num == 3)
                        content7.setText(title.getquestion());
                    else if (num == 4)
                        content8.setText(title.getquestion());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        //버튼 누르면 세부화면으로 넘어감.
        content1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("메인", "PlantStory");
                intent.putExtra("상세", "Q1");
                startActivity(intent);
            }
        });

        content2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("메인", "PlantStory");
                intent.putExtra("상세", "Q2");
                startActivity(intent);
            }
        });

        content3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("메인", "PlantStory");
                intent.putExtra("상세", "Q3");
                startActivity(intent);
            }
        });

        content4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("메인", "PlantStory");
                intent.putExtra("상세", "Q4");
                startActivity(intent);
            }
        });

        content5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("메인", "PlantStory2");
                intent.putExtra("상세", "QQ1");
                startActivity(intent);
            }
        });

        content6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("메인", "PlantStory2");
                intent.putExtra("상세", "QQ2");
                startActivity(intent);
            }
        });

        content7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("메인", "PlantStory2");
                intent.putExtra("상세", "QQ3");
                startActivity(intent);
            }
        });

        content8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("메인", "PlantStory2");
                intent.putExtra("상세", "QQ4");
                startActivity(intent);
            }
        });

        for(int i =0; i<3; i++){
            txt3 = "B" + Integer.toString(i);

            int num =1;

            //사진도 uri 상태로 파이어베이스에 저장할 예정

            //세번째 카테고리(book)
            databaseReference = database.getReference("PlantStoryBook").child(txt3);
            //question 값을 가져와서 버튼에 setText
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    QbookList title = snapshot.getValue(QbookList.class);

                    if (num == 1) {
                        //질문/제목 배치
                        bookTitle1.setText(title.gettitle());
                    }
                    else if (num == 2) {
                        bookTitle2.setText(title.gettitle());
                    }
                    else if (num == 3) {
                        bookTitle3.setText(title.gettitle());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        bookContent1 = v.findViewById(R.id.book1);
        bookContent2 = v.findViewById(R.id.book2);
        bookContent3 = v.findViewById(R.id.book3);

        bookContent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //클릭하면 책 구매 사이트로 이동하도록 할 예정.
            }
        });

        return v;
    }

}
