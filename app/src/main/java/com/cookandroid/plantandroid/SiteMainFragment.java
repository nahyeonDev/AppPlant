package com.cookandroid.plantandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;

public class SiteMainFragment extends Fragment implements OnMapReadyCallback {
    ImageButton site1, site2, site3, site4;

    //네이버 지도
    NaverMap naverMap;

    //지도 마커
    Marker marker1 = new Marker();
    Marker marker2 = new Marker();
    Marker marker3 = new Marker();
    Marker marker4 = new Marker();

    Button zoomOutBtn;

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
                intent.putExtra("제목", "경상북도식물원");
                intent.putExtra("링크", "https://www.gb.go.kr/Main/open_contents/section/arboretum/index.html");
                startActivity(intent);
            }
        });
        site2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SiteDetailActivity.class);
                intent.putExtra("제목", "기청산식물원");
                intent.putExtra("링크", "http://key-chungsan.co.kr/");
                startActivity(intent);
            }
        });
        site3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SiteDetailActivity.class);
                intent.putExtra("제목", "사유원");
                intent.putExtra("링크", "http://sayuwon.com/");
                startActivity(intent);
            }
        });
        site4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SiteDetailActivity.class);
                intent.putExtra("제목", "세아조각수목원");
                intent.putExtra("링크", "https://xn--4y2bzqm1nvza89ijna55edy9c.com/");
                startActivity(intent);
            }
        });

        //mapfragment 연결
        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        zoomOutBtn = v.findViewById(R.id.zOut);
        
        return v;

    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        //배경 지도 선택
        naverMap.setMapType(NaverMap.MapType.Basic);

        //건물 표시
        naverMap.setLayerGroupEnabled(naverMap.LAYER_GROUP_BUILDING, true);

        //위치 및 각도 조정
        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(36.126626, 128.924173),   // 위치 지정
                7,                                     // 줌 레벨
                0,                                       // 기울임 각도
                0                                     // 방향
        );
        naverMap.setCameraPosition(cameraPosition);

        //마커 세팅
        setMarker(marker1,36.21912857025516, 129.25919078255424,R.drawable.map_marker,
                0, "경상북도수목원");
        setMarker(marker2,36.034284816789565, 128.43396541991825,R.drawable.map_marker,
                0, "세아조각수목원");
        setMarker(marker3,36.09254343329724, 128.6819306555703,R.drawable.map_marker,
                0, "사유원");
        setMarker(marker4,36.195966889393226, 129.33333596229406,R.drawable.map_marker,
                0, "기청산수목원");

        //마커 클릭시 줌 인.(+토스트 메세지)
        marker1.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                Toast.makeText(getActivity(), "경상북도수목원 클릭", Toast.LENGTH_SHORT).show();
                CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(
                        new LatLng(36.21912857025516, 129.25919078255424),16)
                        .animate(CameraAnimation.Fly, 3000);

                naverMap.moveCamera(cameraUpdate);

                //줌 인 되면 버튼 보이게 설정. 버튼 누르면 줌 아웃
                zoomOutBtn.setVisibility(View.VISIBLE);
                zoomOutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(
                                new LatLng(36.126626, 128.924173),7)
                                .animate(CameraAnimation.Fly, 3000);

                        naverMap.moveCamera(cameraUpdate);
                        zoomOutBtn.setVisibility(View.INVISIBLE);
                    }
                });
                return false;
            }
        });

        marker2.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                Toast.makeText(getActivity(), "세아조각수목원 클릭", Toast.LENGTH_SHORT).show();
                CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(
                        new LatLng(36.034284816789565, 128.43396541991825),16)
                        .animate(CameraAnimation.Fly, 3000);

                naverMap.moveCamera(cameraUpdate);

                //줌 인 되면 버튼 보이게 설정. 버튼 누르면 줌 아웃
                zoomOutBtn.setVisibility(View.VISIBLE);
                zoomOutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(
                                new LatLng(36.126626, 128.924173),7)
                                .animate(CameraAnimation.Fly, 3000);

                        naverMap.moveCamera(cameraUpdate);
                        zoomOutBtn.setVisibility(View.INVISIBLE);
                    }
                });
                return false;
            }
        });

        marker3.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                Toast.makeText(getActivity(), "사유원 클릭", Toast.LENGTH_SHORT).show();
                CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(
                        new LatLng(36.09254343329724, 128.6819306555703),16)
                        .animate(CameraAnimation.Fly, 3000);

                naverMap.moveCamera(cameraUpdate);

                //줌 인 되면 버튼 보이게 설정. 버튼 누르면 줌 아웃
                zoomOutBtn.setVisibility(View.VISIBLE);
                zoomOutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(
                                new LatLng(36.126626, 128.924173),7)
                                .animate(CameraAnimation.Fly, 3000);

                        naverMap.moveCamera(cameraUpdate);
                        zoomOutBtn.setVisibility(View.INVISIBLE);
                    }
                });
                return false;
            }
        });

        marker4.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                Toast.makeText(getActivity(), "기청산수목원 클릭", Toast.LENGTH_SHORT).show();
                CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(
                        new LatLng(36.195966889393226, 129.33333596229406),16)
                        .animate(CameraAnimation.Fly, 3000);

                naverMap.moveCamera(cameraUpdate);

                //줌 인 되면 버튼 보이게 설정. 버튼 누르면 줌 아웃
                zoomOutBtn.setVisibility(View.VISIBLE);
                zoomOutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CameraUpdate cameraUpdate = CameraUpdate.scrollAndZoomTo(
                                new LatLng(36.126626, 128.924173),7)
                                .animate(CameraAnimation.Fly, 3000);

                        naverMap.moveCamera(cameraUpdate);
                        zoomOutBtn.setVisibility(View.INVISIBLE);
                    }
                });
                return false;
            }
        });

    }

    private void setMarker(Marker marker,  double lat, double lng, int resourceID, int zIndex, String text)
    {
        //원근감 표시
        marker.setIconPerspectiveEnabled(true);
        //아이콘 지정
        marker.setIcon(OverlayImage.fromResource(resourceID));
        //마커의 투명도
        marker.setAlpha(0.8f);
        //마커 위치
        marker.setPosition(new LatLng(lat, lng));
        //마커 우선순위
        marker.setZIndex(zIndex);
        //마커 캡션, 색상
        marker.setCaptionText(text);
        marker.setCaptionColor(Color.BLUE);
        //마커 사이즈
        marker.setWidth(130);
        marker.setHeight(130);
        //마커 표시
        marker.setMap(naverMap);
    }


}
