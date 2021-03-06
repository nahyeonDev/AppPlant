package com.cookandroid.plantandroid;

import android.content.Intent;
import android.net.Uri;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class PlantQuestionMain extends Fragment {
    View v;
    Button content1, content2, content3, content4, content5,
    content6, content7, content8;

    TextView category1, category2, bookTitle1, bookTitle2, bookTitle3;

    ImageView img1, img2, img3;

    private String txt = null;
    private String txt2 = null;
    private String txt3 = null;
    private String img = null;

    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

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
        //?????????????????? ????????????
        for(int i=1; i<=4; i++) {
            txt = "Q" + Integer.toString(i);
            txt2 = "QQ" + Integer.toString(i);
            //????????? ????????????(PlantStory - Q1 - question??? ??????)
            databaseReference = database.getReference("PlantStory").child(txt);
            //????????? ????????????(PlantStory2 - QQ1 - question??? ??????)
            databaseReference2 = database.getReference("PlantStory2").child(txt2);

            int num = i;

            //????????? ????????????
            //question ?????? ???????????? ????????? setText
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    QList title = snapshot.getValue(QList.class);
                    //???????????? ?????????
                    category1.setText(title.gettitle());

                    if (num == 1)
                        //??????/?????? ??????
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
            //????????? ????????????
            //question ?????? ???????????? ????????? setText
            databaseReference2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    QList title = snapshot.getValue(QList.class);
                    //???????????? ?????????
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

        //?????? ????????? ?????????????????? ?????????.
        content1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("??????", "PlantStory");
                intent.putExtra("??????", "Q1");
                startActivity(intent);
            }
        });

        content2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("??????", "PlantStory");
                intent.putExtra("??????", "Q2");
                startActivity(intent);
            }
        });

        content3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("??????", "PlantStory");
                intent.putExtra("??????", "Q3");
                startActivity(intent);
            }
        });

        content4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail.class);
                intent.putExtra("??????", "PlantStory");
                intent.putExtra("??????", "Q4");
                startActivity(intent);
            }
        });

        content5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail2.class);
                intent.putExtra("??????", "PlantStory2");
                intent.putExtra("??????", "QQ1");
                intent.putExtra("??????","1");
                startActivity(intent);
            }
        });

        content6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail2.class);
                intent.putExtra("??????", "PlantStory2");
                intent.putExtra("??????", "QQ2");
                intent.putExtra("??????","2");
                startActivity(intent);
            }
        });

        content7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail2.class);
                intent.putExtra("??????", "PlantStory2");
                intent.putExtra("??????", "QQ3");
                intent.putExtra("??????","3");
                startActivity(intent);
            }
        });

        content8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantQuestionDetail2.class);
                intent.putExtra("??????", "PlantStory2");
                intent.putExtra("??????", "QQ4");
                intent.putExtra("??????","4");
                startActivity(intent);
            }
        });

        //?????????????????? ???????????? ??????
        firebaseStorage = FirebaseStorage.getInstance();

        for(int i =1; i<=3; i++){
            txt3 = "B" + Integer.toString(i);
            //????????????????????? ????????? ?????? ?????? ??????
            img = "book" + Integer.toString(i) +".jpg";

            int num = i;

            //????????? ????????????(book)
            databaseReference = database.getReference("PlantStoryBook").child(txt3);
            //title ?????? ???????????? ????????? setText
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    QbookList t = snapshot.getValue(QbookList.class);
                    if (num == 1)
                        //?????? ??????
                        bookTitle1.setText(t.gettitle());
                    else if (num == 2)
                        bookTitle2.setText(t.gettitle());
                    else if (num == 3)
                        bookTitle3.setText(t.gettitle());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            //?????????????????? ?????????????????? ?????? ?????? ??????
            storageReference = firebaseStorage.getReference().child("bookphoto").child(img);
            //StorageReference?????? ?????? ???????????? URL ?????????
            storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        if(num == 1){
                            // Glide ???????????? ??????????????? ??????
                            Glide.with(PlantQuestionMain.this)
                                    .load(task.getResult())
                                    .into(img1);
                        }
                        else if(num == 2){
                            // Glide ???????????? ??????????????? ??????
                            Glide.with(PlantQuestionMain.this)
                                    .load(task.getResult())
                                    .into(img2);
                        }
                        else if(num == 3){
                            // Glide ???????????? ??????????????? ??????
                            Glide.with(PlantQuestionMain.this)
                                    .load(task.getResult())
                                    .into(img3);
                        }
                    } else {
                        // URL??? ???????????? ??????.
                    }
                }
            });
        }

        bookContent1 = v.findViewById(R.id.book1);
        bookContent2 = v.findViewById(R.id.book2);
        bookContent3 = v.findViewById(R.id.book3);

        bookContent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //???????????? ??? ?????? ???????????? ??????????????? ??? ??????.
                Intent intent = new Intent(getActivity(), BookSiteActivity.class);
                intent.putExtra("??????", "1");
                startActivity(intent);
            }
        });

        bookContent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //???????????? ??? ?????? ???????????? ??????????????? ??? ??????.
                Intent intent = new Intent(getActivity(), BookSiteActivity.class);
                intent.putExtra("??????", "2");
                startActivity(intent);
            }
        });

        bookContent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //???????????? ??? ?????? ???????????? ??????????????? ??? ??????.
                Intent intent = new Intent(getActivity(), BookSiteActivity.class);
                intent.putExtra("??????", "3");
                startActivity(intent);
            }
        });

        return v;
    }

}
