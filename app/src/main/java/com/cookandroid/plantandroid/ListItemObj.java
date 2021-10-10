package com.cookandroid.plantandroid;

import android.graphics.drawable.Drawable;

public class ListItemObj {
//    private String plantName;
//
////    public ListItemObj() {
////
////    }
//
//    /*리사이클러뷰 리스트 레이아웃에 뿌려줄 데이터 담아놓은 클래스*/
//    public String getName() {
//        return plantName;
//    }
//
//    public void setName(String plantName) {
//        this.plantName = plantName;
//    }
//
//    public ListItemObj(String plantName) {
//        this.plantName = plantName;
//    }

    private String plantName;
    private Drawable plantImg ;

    public void setPlantImg(Drawable icon) {
        plantImg = icon ;
    }
    public void setPlantName(String name){
        plantName=name;
    }

    public Drawable getPlantImg() {
        return this.plantImg ;
    }
    public String getPlantName(){
        return this.plantName;
    }


}
