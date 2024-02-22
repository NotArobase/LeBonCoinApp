package com.example.leboncoinapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    public static DBManager DBManager;

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    private DBManager(Context c) {
        context = c;
        resetDatabase();
        init(); // Useful for adding ads for the first time.
    }

    public static DBManager getDBManager(Context context) {
        if (DBManager == null){
            return new DBManager(context);
        }
        return DBManager;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Add ads manually.
    public void init() {
        open();
        insert(new DBAdModel("Wood", "Douai", "https://media.istockphoto.com/id/134253640/photo/construction-of-a-wooden-roof-frame-underway.jpg?s=612x612&w=0&k=20&c=e5gUkic9LGQWahIdHozOsEzHKy_HtsmvmtOHmYsejSU=", "john.doe@example.com", "123456789"));
        insert(new DBAdModel("Steel", "Lille", "https://as2.ftcdn.net/v2/jpg/03/91/83/87/1000_F_391838708_4HFADW5beay2VVlnoual6Qi5fWeIaD9V.jpg", "jane.doe@example.com", "987654321"));
        insert(new DBAdModel("Clay", "Douai", "https://constrofacilitator.com/wp-content/uploads/2020/02/clay-in-construction.jpg", "bob.smith@example.com", "456123789"));
        insert(new DBAdModel("Metal", "Lyon", "https://www.meto-constructions.fr/wp-content/uploads/2018/12/IMG_6067.jpg", "alice.smith@example.com", "321987654"));
        insert(new DBAdModel("Glass", "Valenciennes", "https://i0.wp.com/www.tipsnepal.com/wp-content/uploads/2020/09/simple-float-glass-1505049573-3306125.jpeg?resize=500%2C317&quality=100&strip=all&ssl=1", "tom.jones@example.com", "654987321"));
        insert(new DBAdModel("Wood", "Orchies", "https://yieldpro.com/wp-content/uploads/2020/08/lumber1.jpg", "susan.jones@example.com", "789456123"));
        close();

    }

    private boolean dataExists() {
        // Check if data already exists in the database
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " + DBHelper.TABLE_NAME, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    public void resetDatabase() {
        // Open the database
        open();

        // Delete all rows from the table
        database.delete(DBHelper.TABLE_NAME, null, null);

        // Close the database connection
        //close();
    }

    public void insert(DBAdModel ad) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.TITLE, ad.getTitle());
        contentValue.put(DBHelper.ADDRESS, ad.getAddress());
        contentValue.put(DBHelper.IMAGE, ad.getImage());
        contentValue.put(DBHelper.EMAIL_ADDRESS, ad.getEmailAddress()); // Ajout de l'adresse e-mail
        contentValue.put(DBHelper.PHONE_NUMBER, ad.getPhoneNumber()); // Ajout du numéro de téléphone
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBHelper._ID, DBHelper.TITLE, DBHelper.ADDRESS, DBHelper.IMAGE};
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, DBAdModel ad) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TITLE, ad.getTitle());
        contentValues.put(DBHelper.ADDRESS, ad.getAddress());
        contentValues.put(DBHelper.IMAGE, ad.getImage());
        contentValues.put(DBHelper.EMAIL_ADDRESS, ad.getEmailAddress()); // Mettre à jour l'adresse e-mail
        contentValues.put(DBHelper.PHONE_NUMBER, ad.getPhoneNumber()); // Mettre à jour le numéro de téléphone
        int i = database.update(DBHelper.TABLE_NAME, contentValues, DBHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DBHelper.TABLE_NAME, DBHelper._ID + "=" + _id, null);
    }

    public DBAdModel getById(int id){
        return dbHelper.getById(id);
    }
}
