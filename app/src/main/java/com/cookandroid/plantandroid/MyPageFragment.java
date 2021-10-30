package com.cookandroid.plantandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.Activity.RESULT_OK;

public class MyPageFragment extends Fragment {
    View v;
    private Button logoutBtn;
    private Button userBtn;
    private TextView userEmail;
    private ImageButton likeBtn;
    private ImageButton starBtn;

    MyPageUserFragment myPageUserFragment;
    HeartBookmarkFragment heartBookmarkFragment;
    StarBookmarkFragment starBookmarkFragment;

    private final String TAG = this.getClass().getSimpleName();
    private ImageView userImg;

    private FirebaseDatabase database;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.mypage_main, container, false);

        logoutBtn=v.findViewById(R.id.logoutBtn);
        userBtn=v.findViewById(R.id.userBtn);
        userEmail=v.findViewById(R.id.userEmail);
        likeBtn=v.findViewById(R.id.likeBtn);
        starBtn=v.findViewById(R.id.starBtn);

        myPageUserFragment = new MyPageUserFragment();
        heartBookmarkFragment = new HeartBookmarkFragment();
        starBookmarkFragment = new StarBookmarkFragment();

        //프로필 사진 누르면 avd 갤러리와 연결.
        userImg=v.findViewById(R.id.userImg);
        userImg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_PICK);
                //기기 기본 갤러리 접근
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                //구글 갤러리 접근
                intent.setType("image/*");
                launcher.launch(intent);
            }
        });

        database = FirebaseDatabase.getInstance();

        //사용자 이메일 보여짐
        userEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        //하트 북마크 버튼
        likeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //하트북마크 프래그먼트로 연결
                getChildFragmentManager().beginTransaction().replace(R.id.frame_container, heartBookmarkFragment).commit();
            }
        });

        //별 북마크 버튼
        starBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //별북마크 프래그먼트로 연결
                getChildFragmentManager().beginTransaction().replace(R.id.frame_container, starBookmarkFragment).commit();
            }
        });

        //회원정보 버튼
        userBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //회원정보 프래그먼트로 연결
                getChildFragmentManager().beginTransaction().replace(R.id.frame_container, myPageUserFragment).commit();
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

    //갤러리 사진을 선택하면 마이페이지에 세팅.
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result.getResultCode() == RESULT_OK)
                    {
                        Log.e(TAG, "result : " + result);
                        Intent intent = result.getData();
                        Log.e(TAG, "intent : " + intent);
                        Uri uri = intent.getData();
                        Log.e(TAG, "uri : " + uri);
//                        userImg.setImageURI(uri);

                        Glide.with(MyPageFragment.this) //getContext()로 대체해볼것
                                .load(uri)
                                .into(userImg);

                        //해당 사진을 프로필에 올릴 때 uri 정보를 파이어베이스에 저장해서,
                        //다음에 로그인할 때도 프로필 사진이 유지 되도록 할 예정. 현재는 앱을 끄고 들어가면 사라짐.
                    }
                }
            });

}
