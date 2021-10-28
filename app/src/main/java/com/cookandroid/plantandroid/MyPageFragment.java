package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class MyPageFragment extends Fragment {
    View v;
    private Button logoutBtn;
    private Button userBtn;
    private TextView userEmail;
    private ImageButton likeBtn;

    MyPageUserFragment myPageUserFragment;
    HeartBookmarkFragment heartBookmarkFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.mypage_main, container, false);

        logoutBtn=v.findViewById(R.id.logoutBtn);
        userBtn=v.findViewById(R.id.userBtn);
        userEmail=v.findViewById(R.id.userEmail);
        likeBtn=v.findViewById(R.id.likeBtn);

        myPageUserFragment = new MyPageUserFragment();
        heartBookmarkFragment = new HeartBookmarkFragment();

        //사용자 이메일 보여짐
        userEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        //회원정보 버튼
        userBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //회원정보 프래그먼트로 연결
                getChildFragmentManager().beginTransaction().replace(R.id.frame_container, myPageUserFragment).commit();
            }
        });

        //하트 북마크 버튼
        likeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //하트북마크 프래그먼트로 연결
                getChildFragmentManager().beginTransaction().replace(R.id.frame_container, heartBookmarkFragment).commit();
            }
        });


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
