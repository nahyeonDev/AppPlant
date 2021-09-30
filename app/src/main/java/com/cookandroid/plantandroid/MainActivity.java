package com.cookandroid.plantandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    PlantMainFragment PMainFragment;
    SiteMainFragment SMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_view);

        //프래그먼트 생성
        PMainFragment = new PlantMainFragment();
        SMainFragment = new SiteMainFragment();

        //처음 화면에 고정할 화면 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout,PMainFragment).commitAllowingStateLoss();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.plantMain:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, PMainFragment).commit();
                    return true;
                case R.id.siteMap:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, SMainFragment).commit();
                    return true;
            }

            return true;
        });
    }
}