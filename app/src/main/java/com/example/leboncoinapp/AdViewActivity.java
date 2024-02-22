package com.example.leboncoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class AdViewActivity extends AppCompatActivity {
 // Replace with the actual email address

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_view);

        Intent intent = getIntent();
        String adTitle = intent.getStringExtra("adTitle");
        String adAddress = intent.getStringExtra("adAddress");
        String adImage = intent.getStringExtra("adImage");
        String adPhoneNumber = intent.getStringExtra("adAddress");
        String adEmail = intent.getStringExtra("adImage");

        TextView titleTextView = findViewById(R.id.textView2);
        TextView addressTextView = findViewById(R.id.textView3);
        ImageView imageView = findViewById(R.id.imageView);

        titleTextView.setText(adTitle);
        addressTextView.setText(adAddress);

        // Load the image using Glide
        Glide.with(this).load(adImage).into(imageView);

        // Button for making a phone call
        findViewById(R.id.buttonCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + adPhoneNumber));
                startActivity(callIntent);
            }
        });

        // Button for sending an email
        findViewById(R.id.buttonEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + adEmail));
                startActivity(emailIntent);
            }
        });
    }
}
