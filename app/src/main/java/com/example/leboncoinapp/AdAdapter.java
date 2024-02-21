package com.example.leboncoinapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class AdAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<AdModel> adModelArrayList;
    private final LayoutInflater inflater;
    // Constructor
    public AdAdapter(Context context, ArrayList<AdModel> adModelArrayList) {
        this.context = context;
        this.adModelArrayList = adModelArrayList;
        inflater = (LayoutInflater.from(context));
    }
    @Override
    public int getCount() { return adModelArrayList.size(); } // Return ad number
    @Override
    public Object getItem(int i) { return adModelArrayList.get(i); } // Return ad number i
    @Override
    public long getItemId(int i) { return i; } // Return ad id i
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AdModel ad = (AdModel) this.getItem(i);
        view = inflater.inflate(R.layout.item_listview_ad, null);

        ImageView imageIV = view.findViewById(R.id.imageIV);
        TextView titleTV = view.findViewById(R.id.titleTV);
        TextView addressTV = view.findViewById(R.id.adressTV);

        imageIV.setImageDrawable(ad.getImage());
        titleTV.setText(ad.getTitle());
        addressTV.setText(ad.getAddress());
        return view;
    }
}
