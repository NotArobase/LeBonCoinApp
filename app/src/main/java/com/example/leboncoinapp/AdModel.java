package com.example.leboncoinapp;

public class AdModel {
    private String title;
    private String address;
    private int image;
    // Constructor
    public AdModel(String title, String address, int image) {
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

    public int getImage() {
        return image;
    }
    public void setTitle(String title) {
        this.title = title;
    }
// ...
}

