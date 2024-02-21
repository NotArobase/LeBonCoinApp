package com.example.leboncoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;
import android.view.MenuItem;
import androidx.core.content.ContextCompat;
import android.net.Uri;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;




public class AdListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_list_view);


        Drawable drawable1 = ContextCompat.getDrawable(this, R.drawable.buche);
        AdModel admodel1 = new AdModel("Bois", "Bourseul, Douai", drawable1);

        Drawable drawable2 = ContextCompat.getDrawable(this, R.drawable.tuyaux);
        AdModel admodel2 = new AdModel("Tuyaux", "Bourseul, Douai", drawable2);

        Drawable drawable3 = ContextCompat.getDrawable(this, R.drawable.tuile);
        AdModel admodel3 = new AdModel("Tuile", "Bourseul, Douai", drawable3);

        Drawable drawable4 = ContextCompat.getDrawable(this, R.drawable.feutrine);
        AdModel admodel4 = new AdModel("Feutrine", "Bourseul, Douai", drawable4);

        Drawable drawable5 = ContextCompat.getDrawable(this, R.drawable.plot);
        AdModel admodel5 = new AdModel("Plot", "Bourseul, Douai", drawable5);


        ArrayList<AdModel> listAdModel = new ArrayList<AdModel>();

        Intent intent = getIntent();
        String newTitle = intent.getStringExtra("title");
        String newAddress = intent.getStringExtra("address");
        String img_path = intent.getStringExtra("image_path");

        // Si des informations sont reçues, ajoutez une nouvelle annonce à votre liste
        if (newTitle != null && newAddress != null && img_path != null) {
            Uri selectedImageUri = Uri.parse(img_path);
            Drawable drawable = Drawable.createFromPath(selectedImageUri.getPath());
            AdModel newAd = new AdModel(newTitle, newAddress, drawable);

            listAdModel.add(newAd);
        }

        listAdModel.add(admodel1);
        listAdModel.add(admodel2);
        listAdModel.add(admodel3);
        listAdModel.add(admodel4);
        listAdModel.add(admodel5);

        AdAdapter adapter = new AdAdapter(this, listAdModel);

        ListView listView = findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer l'annonce cliquée
                AdModel clickedAd = listAdModel.get(position);

                // Passer les informations de l'annonce à l'activité AdViewActivity
                Intent intent = new Intent(AdListViewActivity.this, AdViewActivity.class);
                intent.putExtra("adTitle", clickedAd.getTitle());
                intent.putExtra("adAddress", clickedAd.getAddress());
                // Convert the Drawable to a Bitmap
                BitmapDrawable bitmapDrawable = (BitmapDrawable) clickedAd.getImage();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("adImage", byteArray);
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
        finish();
        // Retour sur MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}