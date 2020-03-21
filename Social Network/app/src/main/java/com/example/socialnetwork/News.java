package com.example.socialnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class News extends AppCompatActivity {
    private int icnUser;
    private String username;
    private String date;
    private String description;
    private int imgPost;
    private boolean isLike;
    private int cntLike;
    private int icnLike;

    public News(int icnUser, String username, String date, String description, int imgPost) {
        this.icnUser = icnUser;
        this.username = username;
        this.date = date;
        this.description = description;
        this.imgPost = imgPost;
    }

    public int getIcnUser() {
        return icnUser;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getImgPost() {
        return imgPost;
    }

    public boolean isLike() {
        return isLike;
    }

    public int getCntLike() {
        return cntLike;
    }

    public int getIcnLike(){
        return icnLike;
    }
    public void setIcnUser(int icnUser) {
        this.icnUser = icnUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgPost(int imgPost) {
        this.imgPost = imgPost;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public void setCntLike(int cntLike) {
        this.cntLike = cntLike;
    }
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }*/
}
