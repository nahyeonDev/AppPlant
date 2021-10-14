package com.cookandroid.plantandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//유해식물을 제외한 나머지 식물리스트
public class PlantList extends AppCompatActivity {
    private ImageButton backBtn_plant_list;
    private RecyclerView RecyclerView_main;
    private PlantAdapter pAdapter;
    private ArrayList<ListItemObj> pList;

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    //firebase PlantList의 식물 이름 수 만큼 MAX_SIZE를 미리 설정해 줘야 함.
    final int MAX_SIZE = 4;
    String title;
    String[] name = new String[MAX_SIZE];

    private String data_name;
    String dName = null;

    private String title_name;
    TextView sortTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_list);

        pList = new ArrayList<>();

        RecyclerView_main = findViewById(R.id.recycler_view);
        RecyclerView_main.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();

        RecyclerView_main.setHasFixedSize(true);
        pAdapter = new PlantAdapter(pList);
        RecyclerView_main.setAdapter(pAdapter);

        //메인 화면에서 데이터제목 받기
        Intent pIntent = getIntent();
        data_name = pIntent.getStringExtra("dataName");
        dName = new String(data_name);

        //메인 화면에서 제목 받기
        title_name = pIntent.getStringExtra("title");
        sortTitle = findViewById(R.id.sortTitle);
        sortTitle.setText(title_name);

        //데이터베이스에 저장한 식물을 받아옴.
        //MAX_SIZE만큼 받아오기 때문에 데이터베이스를 늘리려면 MAX_SIZE를 그 수로 변경한 후 실행
        for (int i = 0; i < MAX_SIZE; i++) {

            //파이어베이스의 P1,P2....이름을 title에 저장함
            title = "P" + Integer.toString(i + 1);
            //plant 밑 dName(메인에서 받아온 PlantList(1,2..)) 그 밑 title(P1,P2...)에 접근
            databaseReference = database.getReference("Plant").child(dName).child(title);

            int num = i;
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ListItemObj list = snapshot.getValue(ListItemObj.class);
                        //plantName name 배열에 저장 후 addItem으로 화면에 보이게 함.
                        name[num] = list.getplantName();
                        addItem(name[num]);

                    }
                    pAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    private void addItem(String text) {
        ListItemObj item = new ListItemObj(text);

        pList.add(item);
    }
}