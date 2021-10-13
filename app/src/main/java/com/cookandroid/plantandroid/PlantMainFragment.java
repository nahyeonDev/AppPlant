package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class PlantMainFragment extends Fragment {

    ImageButton plant1, plant2, plant3, plant4, plant5, plant6;
    View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.plant_main, container, false);

        plant1 = v.findViewById(R.id.plant1);
        plant2 = v.findViewById(R.id.plant2);
        plant3 = v.findViewById(R.id.plant3);
        plant4 = v.findViewById(R.id.plant4);
        plant5 = v.findViewById(R.id.plant5);
        plant6 = v.findViewById(R.id.plant6);

        //go to 쌍떡잎 식물 리스트 뷰
        plant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                intent.putExtra("title", "쌍떡잎 식물");
                intent.putExtra("dataName","PlantList1");
                startActivity(intent);
            }
        });
        //go to 외떡잎 식물 리스트 뷰
        plant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                intent.putExtra("title", "외떡잎 식물");
                intent.putExtra("dataName","PlantList2");
                startActivity(intent);
            }
        });
        //go to 민꽃 식물 리스트 뷰
        plant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                intent.putExtra("title", "민꽃 식물");
                intent.putExtra("dataName","PlantList3");
                startActivity(intent);
            }
        });
        //go to 겉씨 식물 리스트 뷰
        plant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                intent.putExtra("title", "겉씨 식물");
                intent.putExtra("dataName","PlantList4");
                startActivity(intent);
            }
        });
        //go to 경북 식물 리스트 뷰
        plant5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                intent.putExtra("title", "경북 식물");
                intent.putExtra("dataName","PlantList5");
                startActivity(intent);
            }
        });
        //go to 외래유해 식물 리스트 뷰
        plant6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList2.class);
                intent.putExtra("title", "외래위험 식물");
                intent.putExtra("dataName","PlantList6");
                startActivity(intent);
            }
        });

        return v;

    }

}
