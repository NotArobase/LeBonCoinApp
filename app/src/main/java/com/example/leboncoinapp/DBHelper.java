package com.example.leboncoinapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "ads";

    // Table columns
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String ADDRESS = "address";
    public static final String PHONE_NUMBER = "phoneNumber"; // Nouvelle colonne pour le numéro de téléphone
    public static final String EMAIL_ADDRESS = "email"; // Nouvelle colonne pour l'adresse e-mail
    public static final String IMAGE = "image";

    // Database Information
    static final String DB_NAME = "LEBONCOINAPP.DB";

    // database version
    static final int DB_VERSION = 2;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT NOT NULL, " + ADDRESS + " TEXT, " + IMAGE + " TEXT, " +
            PHONE_NUMBER + " TEXT, " + EMAIL_ADDRESS + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Util if you want to add a clicklistener on specific ad in listview.
    public DBAdModel getById(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + _ID + "=?";
        Cursor data = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (data != null) {
            data.moveToFirst();
        } else {
            return null;
        }

        String title = data.getString(data.getColumnIndexOrThrow(TITLE));
        String address = data.getString(data.getColumnIndexOrThrow(ADDRESS));
        String image = data.getString(data.getColumnIndexOrThrow(IMAGE));
        String phoneNumber = data.getString(data.getColumnIndexOrThrow(PHONE_NUMBER));
        String email = data.getString(data.getColumnIndexOrThrow(EMAIL_ADDRESS));

        return new DBAdModel(title, address, image, phoneNumber, email);
    }
}
