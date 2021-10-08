package com.cookandroid.plantandroid;

public class ListItemObj {
    private String plantName;

//    public ListItemObj() {
//
//    }

    /*리사이클러뷰 리스트 레이아웃에 뿌려줄 데이터 담아놓은 클래스*/
    public String getName() {
        return plantName;
    }

    public void setName(String plantName) {
        this.plantName = plantName;
    }

    public ListItemObj(String plantName) {
        this.plantName = plantName;
    }

}
