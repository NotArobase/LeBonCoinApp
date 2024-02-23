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
        //resetDatabase();
        //init();
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

    public void init() {
        open();
        insert(new DBAdModel("Bois", "Douai", "https://media.istockphoto.com/id/134253640/photo/construction-of-a-wooden-roof-frame-underway.jpg?s=612x612&w=0&k=20&c=e5gUkic9LGQWahIdHozOsEzHKy_HtsmvmtOHmYsejSU=", "john.doe@example.com", "123456789"));
        insert(new DBAdModel("Fer", "Lille", "https://as2.ftcdn.net/v2/jpg/03/91/83/87/1000_F_391838708_4HFADW5beay2VVlnoual6Qi5fWeIaD9V.jpg", "jane.doe@example.com", "987654321"));
        insert(new DBAdModel("Argile", "Douai", "https://constrofacilitator.com/wp-content/uploads/2020/02/clay-in-construction.jpg", "bob.smith@example.com", "456123789"));
        insert(new DBAdModel("Metal", "Lyon", "https://www.meto-constructions.fr/wp-content/uploads/2018/12/IMG_6067.jpg", "alice.smith@example.com", "321987654"));
        insert(new DBAdModel("Vitre", "Valenciennes", "https://www.fin-de-chantier.fr/8226-large_default/chassis-3-vitres-fixes.jpg", "tom.jones@example.com", "654987321"));
        insert(new DBAdModel("Bois", "Orchies", "https://yieldpro.com/wp-content/uploads/2020/08/lumber1.jpg", "susan.jones@example.com", "789456123"));
        close();

    }

    private boolean dataExists() {
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " + DBHelper.TABLE_NAME, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    public void resetDatabase() {
        open();

        database.delete(DBHelper.TABLE_NAME, null, null);

        //close();
    }

    public void insert(DBAdModel ad) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.TITLE, ad.getTitle());
        contentValue.put(DBHelper.ADDRESS, ad.getAddress());
        contentValue.put(DBHelper.IMAGE, ad.getImage()); // Ajout de l'adresse e-mail
        contentValue.put(DBHelper.PHONE_NUMBER, ad.getPhoneNumber());
        contentValue.put(DBHelper.EMAIL_ADDRESS, ad.getEmailAddress()); // Ajout du numéro de téléphone
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBHelper._ID, DBHelper.TITLE, DBHelper.ADDRESS, DBHelper.IMAGE, DBHelper.PHONE_NUMBER, DBHelper.EMAIL_ADDRESS};
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
        contentValues.put(DBHelper.PHONE_NUMBER, ad.getPhoneNumber());
        contentValues.put(DBHelper.EMAIL_ADDRESS, ad.getEmailAddress());
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
