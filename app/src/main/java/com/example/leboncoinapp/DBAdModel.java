package com.example.leboncoinapp;

public class DBAdModel {
    private String title;
    private String address;
    private String image;

    // Constructor
    public DBAdModel(String title, String address, String image) {
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

    public String getImage() {
        return image;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}


