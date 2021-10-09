package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class loginActivity extends AppCompatActivity {
    Button logBtn;
    TextView goJoin;
    ImageButton googleLogin;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private EditText emailInput;
    private EditText passwordIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        logBtn = findViewById(R.id.loginBtn);
        goJoin = findViewById(R.id.goJoin);
        googleLogin=findViewById(R.id.googleLogin);

        emailInput= findViewById(R.id.emailInput);
        passwordIn= findViewById(R.id.passwordIn);

        firebaseAuth = firebaseAuth.getInstance();//firebaseAuth의 인스턴스를 가져옴

        //로그인 유지
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null){ //로그인한적이 있다면
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            //메인액티비티에서 뒤로가기 누를시 로그인 화면뜨지 않도록 종료시킴
            finish();
        }
        //이력이 없다면 로그인하기
        else{
            logBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //String형 변수 email.pwd(edittext에서 받오는 값)으로 로그인하는것
                    String email = emailInput.getText().toString().trim();
                    String pwd = passwordIn.getText().toString().trim();

                    //이메일, 비밀번호가 채워지지 않았을때
                    if(email.equals("") ){
                        Toast.makeText(loginActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(pwd.equals("")){
                        Toast.makeText(loginActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //채워졌을때
                    else{
                        firebaseAuth.signInWithEmailAndPassword(email, pwd)
                                .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {//성공했을때
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {//실패했을때
                                            Toast.makeText(loginActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }

                }
            });

        }

        //회원가입
        goJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinMemberActivity.class);
                startActivity(intent);
            }
        });

        //구글 로그인
        googleLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {//안들어와
                        if (task.isSuccessful()) { //로그인 성공
                            user = firebaseAuth.getCurrentUser();

                            if (user!=null){
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        } else { //로그인 실패
                            Toast.makeText(loginActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
