package com.example.leboncoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;
import android.view.MenuItem;
import androidx.core.content.ContextCompat;
import android.net.Uri;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import android.graphics.drawable.BitmapDrawable;
import android.widget.CursorAdapter;
import android.database.Cursor;

public class AdListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_list_view);
        ListView listView = findViewById(R.id.listView);

        DBManager dbManager = DBManager.getDBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        CursorAdapter adapter = new DBAdAdapter(this, cursor, R.layout.item_listview_ad_db);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                DBAdModel clickedAd = new DBAdModel(
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ADDRESS)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.IMAGE))
                );

                // Passer les informations de l'annonce à l'activité AdViewActivity
                Intent intent = new Intent(AdListViewActivity.this, AdViewActivity.class);
                intent.putExtra("adTitle", clickedAd.getTitle());
                intent.putExtra("adAddress", clickedAd.getAddress());
                intent.putExtra("adImage", clickedAd.getImage());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Retour sur MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
