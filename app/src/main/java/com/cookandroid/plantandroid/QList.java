package com.cookandroid.plantandroid;

//SiteMainFragment, SiteDetailActivity 데이터베이스 읽어올 때 쓰는 함수
public class QList {
    //변수 선언
    private String title;
    private String content;
    private String question;

    public QList(){}

    //여기서부터 get 함수는 읽어 올때, set 함수는 쓴 내용을 데이터에 저장할 때 사용
    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getcontent() {
        return content;
    }

    public void setcontent(String content) {
        this.content = content;
    }

    public String getquestion() {
        return question;
    }

    public void setquestion(String question) {
        this.question = question;
    }

    //이거는 그룹을 생성할때 사용하는 부분
    public QList(String title, String content, String question) {
        this.title = title;
        this.content = content;
        this.question = question;
    }
}
