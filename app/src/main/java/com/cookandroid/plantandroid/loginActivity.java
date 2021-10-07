package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    Button logBtn;
    TextView goJoin;

    FirebaseAuth firebaseAuth;
    private EditText emailInput;
    private EditText passwordIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        logBtn = findViewById(R.id.loginBtn);
        goJoin = findViewById(R.id.goJoin);

        emailInput= findViewById(R.id.emailInput);
        passwordIn= findViewById(R.id.passwordIn);

        firebaseAuth = firebaseAuth.getInstance();//firebaseAuth의 인스턴스를 가져옴


        //로그인 비번입력시 ****로 나오는것도 생각해볼것
        //로그인 후 메인액티비티에서 뒤로가기 누를시 로그인 화면 뜨는거 고칠것

        logBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //로그인
                String email = emailInput.getText().toString().trim();
                String pwd = passwordIn.getText().toString().trim();
                //String형 변수 email.pwd(edittext에서 받오는 값)으로 로그인하는것
                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {//성공했을때
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {//실패했을때
                                    Toast.makeText(loginActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        goJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinMemberActivity.class);
                startActivity(intent);
            }
        });

    }
}
