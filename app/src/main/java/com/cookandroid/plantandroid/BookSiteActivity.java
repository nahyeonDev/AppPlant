package com.cookandroid.plantandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class BookSiteActivity extends AppCompatActivity {
    private WebView mWebView; // 웹뷰 선언
    private WebSettings mWebSettings; //웹뷰세팅
    private ImageButton backBtn;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    String web;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_book_site);

        mWebView = findViewById(R.id.book_web);

        Intent siteIntent = getIntent();
        String web = siteIntent.getStringExtra("순서");
        int num = Integer.parseInt(web);

        mWebView.setWebViewClient(new WebViewClient());
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setDomStorageEnabled(true);

        //intent로 받아온 순서번호로 각 책에 맞는 사이트 url을 배치
        if(num == 1){
            mWebView.loadUrl("http://www.kyobobook.co.kr/product/detailViewKor" +
                    ".laf?mallGb=KOR&ejkGb=KOR&barcode=9788957366547");
        }
        else if(num == 2){
            mWebView.loadUrl("http://www.kyobobook.co.kr/product/detailViewKor." +
                    "laf?ejkGb=KOR&mallGb=KOR&barcode=9788934991335&orderClick=LAG&Kc=");
        }
        else {
            mWebView.loadUrl("http://www.kyobobook.co.kr/product/detailViewKor" +
                    ".laf?ejkGb=KOR&mallGb=KOR&barcode=9788958284932&orderClick=LBY&Kc=");
        }

        //back 버튼 액션
        backBtn = findViewById(R.id.backBtn_book);
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {finish();}
        });

    }

}
