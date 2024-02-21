package com.example.leboncoinapp;
import android.graphics.drawable.Drawable;

public class AdModel {
    private String title;
    private String address;
    private Drawable image;

    // Constructor
    public AdModel(String title, String address, Drawable image) {
        this.title = title;
        this.address = address;
        this.image = image;
    }
    // Getter and Setter
    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public Drawable getImage() {
        return image;
    }
    public void setTitle(String title) {
        this.title = title;
    }
// ...
}

