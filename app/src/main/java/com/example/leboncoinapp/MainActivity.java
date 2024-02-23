package com.example.leboncoinapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        Button btnAdListView = findViewById(R.id.btnAdListView);
        Button btnAdAdd = findViewById(R.id.btnAdAdd);


        btnAdListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdListViewActivity.class));
            }
        });

        btnAdAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdAddActivity.class));

            }
        });




    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            resetDatabase();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetDatabase() {
        DBManager dbManager = DBManager.getDBManager(this);
        dbManager.open();
        dbManager.resetDatabase();
    }
}