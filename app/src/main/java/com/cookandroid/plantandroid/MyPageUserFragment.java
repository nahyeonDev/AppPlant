package com.cookandroid.plantandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

//회원정보 프래그먼트 화면
public class MyPageUserFragment extends Fragment {
    private TextView googleLog;
    private TextView email;
    private TextView name;
    View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.mypage_user, container, false);

        googleLog = v.findViewById(R.id.googleLog);
        email = v.findViewById(R.id.email);
        name = v.findViewById(R.id.name);

        if(FirebaseAuth.getInstance().getCurrentUser().getEmail().contains("gmail")){
            googleLog.setText("구글 로그인 여부: O");
        }else{
            googleLog.setText("구글 로그인 여부: X");
        }
        email.setText("이메일: "+FirebaseAuth.getInstance().getCurrentUser().getEmail());
        //이름은 구글처럼 따로 연동되는 계정만 가져올 수 있음-> null이 아닐때만 보여지도록 설정
        if(!FirebaseAuth.getInstance().getCurrentUser().getDisplayName().equals("")){
            name.setText("이름: "+FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        }

        return v;
    }
}

