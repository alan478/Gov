package com.example.VyAppar;

public class Class_Search_Categories {
    private String Title,Desc,Misc,Rating;
    private int iwDisp;

    public Class_Search_Categories(String title, String desc, String misc, String rating, int iwDisp) {
        Title = title;
        Desc = desc;
        Misc = misc;
        Rating = rating;
        this.iwDisp = iwDisp;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getMisc() {
        return Misc;
    }

    public void setMisc(String misc) {
        Misc = misc;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public int getIwDisp() {
        return iwDisp;
    }

    public void setIwDisp(int iwDisp) {
        this.iwDisp = iwDisp;
    }
}
