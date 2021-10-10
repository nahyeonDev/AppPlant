package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;

public class SiteMainFragment extends Fragment implements OnMapReadyCallback {
    ImageButton site1, site2, site3, site4;


    //네이버 지도
    private MapView mapView;
    private static NaverMap naverMap;

    View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.site_main, container, false);

        site1 = v.findViewById(R.id.site1);
        site2 = v.findViewById(R.id.site2);
        site3 = v.findViewById(R.id.site3);
        site4 = v.findViewById(R.id.site4);

        site1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SiteDetailActivity.class);
                intent.putExtra("제목","경상북도식물원");
                intent.putExtra("링크", "https://www.gb.go.kr/Main/open_contents/section/arboretum/index.html");
                startActivity(intent);
            }
        });
        site2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SiteDetailActivity.class);
                intent.putExtra("제목","기청산식물원");
                intent.putExtra("링크", "http://key-chungsan.co.kr/");
                startActivity(intent);
            }
        });
        site3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SiteDetailActivity.class);
                intent.putExtra("제목","사유원");
                intent.putExtra("링크", "http://sayuwon.com/");
                startActivity(intent);
            }
        });
        site4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SiteDetailActivity.class);
                intent.putExtra("제목","세아조각수목원");
                intent.putExtra("링크", "https://xn--4y2bzqm1nvza89ijna55edy9c.com/");
                startActivity(intent);
            }
        });

        //네이버 지도 연결
        mapView = v.findViewById(R.id.naver_map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return v;

    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

    }
}
