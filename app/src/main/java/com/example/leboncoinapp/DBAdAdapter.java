package com.example.leboncoinapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;



public class DBAdAdapter extends CursorAdapter {

    private final int item_layout;

    public DBAdAdapter(Context context, Cursor c, int layout) {
        super(context, c);
        item_layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(item_layout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView idTextView = view.findViewById(R.id.id);
        TextView titleTextView = view.findViewById(R.id.title);
        TextView addressTextView = view.findViewById(R.id.adress);
        ImageView imageView = view.findViewById(R.id.image);

        String id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper._ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.TITLE));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ADDRESS));
        String image = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.IMAGE));

        idTextView.setText(id);
        titleTextView.setText(title);
        addressTextView.setText(address);
        Glide.with(context).load(image).into(imageView);
    }
}
