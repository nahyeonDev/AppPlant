package com.cookandroid.plantandroid;

//식물 세부 화면 데이터
public class PConItem {

    private String content;
    private String size;
    private String location;
    private String special;
    private String Img;

    public PConItem(){}

    public PConItem(String content, String size, String location, String special){
        this.content = content;
        this.size = size;
        this.location = location;
        this.special = special;
    }

    public String getcontent(){
        return this.content;
    }
    public void setcontent(String content){
        this.content = content;
    }
    public String getsize(){
        return this.size;
    }
    public void setsize(String size){
        this.size = size;
    }
    public String getlocation(){
        return this.location;
    }
    public void setlocation(String location){
        this.location = location;
    }
    public String getspecial(){
        return this.special;
    }
    public void setspecial(String special){
        this.special = special;
    }

}
