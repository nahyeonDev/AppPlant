package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class MyPageFragment extends Fragment {
    View v;
    private Button logoutBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.mypage_main, container, false);

        logoutBtn=v.findViewById(R.id.logoutBtn);

        //로그아웃
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                //메인 액티비티를 종료해서 이전에 실행된 모든 프래그먼트를 종료시킴
                getActivity().finish();

                //로그인화면으로 이동
                Intent intent = new Intent(getActivity(), loginActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }


}
