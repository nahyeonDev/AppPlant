package com.cookandroid.plantandroid;

public class QbookList {
    //변수 선언
    private String title;

    public QbookList(){}

    //이거는 그룹을 생성할때 사용하는 부분
    public QbookList(String title) {
        this.title = title;
    }

    //여기서부터 get 함수는 읽어 올때, set 함수는 쓴 내용을 데이터에 저장할 때 사용
    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

}
