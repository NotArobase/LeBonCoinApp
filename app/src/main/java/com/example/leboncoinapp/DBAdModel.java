package com.example.leboncoinapp;

public class DBAdModel {
    private String title;
    private String address;
    private String image;

    private String phoneNumber;
    private String emailAddress;


    public DBAdModel(String title, String address, String image, String phoneNumber, String emailAddress) {
        this.title = title;
        this.address = address;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}


