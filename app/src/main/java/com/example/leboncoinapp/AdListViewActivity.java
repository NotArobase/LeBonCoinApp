package com.example.leboncoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;

public class AdListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_list_view);

        AdModel admodel1 = new AdModel("Bois", "Bourseul, Douai", R.drawable.buche);
        AdModel admodel2 = new AdModel("Tuyaux", "Bourseul, Douai", R.drawable.tuyaux);
        AdModel admodel3 = new AdModel("Tuile", "Bourseul, Douai", R.drawable.tuile);
        AdModel admodel4 = new AdModel("Feutrine", "Bourseul, Douai", R.drawable.feutrine);
        AdModel admodel5 = new AdModel("plot", "Bourseul, Douai", R.drawable.plot);

        ArrayList<AdModel> listAdModel = new ArrayList<AdModel>();

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
                intent.putExtra("adImage", clickedAd.getImage());
                startActivity(intent);
            }
        });
    }



}