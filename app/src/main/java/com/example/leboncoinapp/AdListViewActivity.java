package com.example.leboncoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;
import android.view.MenuItem;
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
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.IMAGE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.EMAIL_ADDRESS)), // Ajout de l'e-mail
                        cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PHONE_NUMBER)) // Ajout du numéro de téléphone
                );

                Intent intent = new Intent(AdListViewActivity.this, AdViewActivity.class);
                intent.putExtra("adTitle", clickedAd.getTitle());
                intent.putExtra("adAddress", clickedAd.getAddress());
                intent.putExtra("adImage", clickedAd.getImage());
                intent.putExtra("adEmail", clickedAd.getEmailAddress()); // Passer l'e-mail à l'activité AdViewActivity
                intent.putExtra("adPhoneNumber", clickedAd.getPhoneNumber()); // Passer le numéro de téléphone à l'activité AdViewActivity
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
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}
