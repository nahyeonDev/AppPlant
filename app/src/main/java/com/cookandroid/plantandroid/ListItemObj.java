package com.cookandroid.plantandroid;

public class ListItemObj {

    private String plantName;

    public ListItemObj() { }

    public ListItemObj(String plantName) {
        this.plantName = plantName;
    }

//    /*리사이클러뷰 리스트 레이아웃에 뿌려줄 데이터 담아놓은 클래스*/
    public void setplantName(String plantName){
        this.plantName=plantName;
    }

    public String getplantName(){
        return this.plantName;
    }
}
