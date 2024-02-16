package com.example.leboncoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class AdViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_view);

        Intent intent = getIntent();
        String adTitle = intent.getStringExtra("adTitle");
        String adAddress = intent.getStringExtra("adAddress");
        int adImage = intent.getIntExtra("adImage", 0);

        TextView titleTextView = findViewById(R.id.textView2);
        TextView addressTextView = findViewById(R.id.textView3);
        ImageView imageView = findViewById(R.id.imageView);

        titleTextView.setText(adTitle);
        addressTextView.setText(adAddress);
        imageView.setImageResource(adImage);
    }
}