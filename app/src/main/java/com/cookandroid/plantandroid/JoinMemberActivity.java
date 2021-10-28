package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinMemberActivity extends AppCompatActivity {
    Button joinBtn;

    private EditText emailEdit;
    private EditText passwordEdit;
    private EditText rePasswordEdit;

    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_member);

        joinBtn = findViewById(R.id.joinBtn);

        emailEdit = findViewById(R.id.emailEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        rePasswordEdit = findViewById(R.id.rePasswordEdit);

        firebaseAuth = FirebaseAuth.getInstance();

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //공백인 부분을 제거하고 보여주는 trim();
                final String email = emailEdit.getText().toString().trim();
                final String pwd = passwordEdit.getText().toString().trim();
                final String repwd = rePasswordEdit.getText().toString().trim();

                //이메일, 비밀번호가 채워지지 않았을때
                if(email.equals("") ){
                    Toast.makeText(JoinMemberActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else if(pwd.equals("")||repwd.equals("")){
                    Toast.makeText(JoinMemberActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else{//칸이 다 채워졌을 때
                    if(pwd.length()<6){
                        Toast.makeText(JoinMemberActivity.this, "비밀번호를 6자리 이상 입력해주세요", Toast.LENGTH_SHORT).show();
                    }

                    if(pwd.equals(repwd)){//비밀번호가 동일하다면
                        firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                                .addOnCompleteListener(JoinMemberActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                                            startActivity(intent);
                                            finish();

                                        } else {
                                            Toast.makeText(JoinMemberActivity.this, "이메일을 올바르게 입력해주세요", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }
                                });
                    }
                    else{//비밀번호가 다르면
                        Toast.makeText(JoinMemberActivity.this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
