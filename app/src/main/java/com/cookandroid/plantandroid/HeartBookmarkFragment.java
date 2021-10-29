package com.cookandroid.plantandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HeartBookmarkFragment extends Fragment {
    private RecyclerView recyclerView;
    public HeartBookmarkAdapter heartBookmarkAdapter;
    View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.heart_bookmark, container, false);

        String TAG="hoooo"; //임의로 작성
        final FirebaseFirestore db = FirebaseFirestore.getInstance();


        ArrayList<String> list = new ArrayList<>();

        db.collection("HeartBookmark")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()){ //하트 눌렀을때 목록들 보여줌
                                // 현재 s= {plantName=섬잣나무}
//                                String s= document.getData().toString().split("=")[1];
//                                s=s.substring(0,s.length()-1);

                                list.add(document.get("plantName").toString());
                                // 리사이클러뷰에 LinearLayoutManager 객체 지정.
                                recyclerView =v.findViewById(R.id.heart_Recycler) ;
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext())) ;

                                // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
                                heartBookmarkAdapter = new HeartBookmarkAdapter(list) ;
                                recyclerView.setAdapter(heartBookmarkAdapter) ;


//                                String s= (String) document.get("plantName"); //이름만 가져오기
//                                plant_mark1.setText(s); //하트 누른 개수만큼 텍스트뷰 생성+ 그만큼 보여줘야됨
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        return v;
    }
}