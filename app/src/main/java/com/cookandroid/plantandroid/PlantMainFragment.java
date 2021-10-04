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

        plant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                startActivity(intent);
            }
        });
        plant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                startActivity(intent);
            }
        });
        plant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                startActivity(intent);
            }
        });
        plant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                startActivity(intent);
            }
        });
        plant5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                startActivity(intent);
            }
        });
        plant6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                startActivity(intent);
            }
        });

        return v;

    }

}
