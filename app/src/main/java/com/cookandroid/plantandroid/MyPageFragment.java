package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class MyPageFragment extends Fragment {
    View v;
    private Button logoutBtn;
    private Button userBtn;
    private TextView userEmail;
    private TextView googleLog;
    private TextView email;
    private TextView name;
    private TextView memberText;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.mypage_main, container, false);

        logoutBtn=v.findViewById(R.id.logoutBtn);
        userBtn=v.findViewById(R.id.userBtn);
        userEmail=v.findViewById(R.id.userEmail);
        googleLog=v.findViewById(R.id.googleLog);
        email=v.findViewById(R.id.email);
        name=v.findViewById(R.id.name);
        memberText=v.findViewById(R.id.memberText);

        //사용자 이메일 보여짐
        userEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        //회원정보 버튼
        userBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //회원정보 텍스트 보임
                memberText.setVisibility(View.VISIBLE);
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
