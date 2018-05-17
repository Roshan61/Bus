package com.bus.bus.android;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BusDBHandler extends SQLiteOpenHelper {
    String TAG = "err";

    String create_DB_Query = "CREATE TABLE bus (_id INTEGER PRIMARY KEY ,url TEXT ,cluster TEXT, stationId TEXT)";

    public BusDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_DB_Query);
    }


    public String[] getAllUrl() {
        String selectQuery = "SELECT url FROM bus";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String url[] = new String[cursor.getCount()];

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            url[i] = (cursor.getString(0));
            cursor.moveToNext();
        }
        return url;
    }


    public String[] getAllCluster() {
        String selectQuery = "SELECT cluster FROM bus";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String cluster[] = new String[cursor.getCount()];

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cluster[i] = (cursor.getString(0));
            cursor.moveToNext();
        }
        return cluster;
    }

    public void deleteRecord(String url, String cluster) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "DELETE FROM bus Where url = '" + url + "'AND cluster ='" + cluster + "'";
        db.execSQL(insertQuery);
        db.close();
    }

    public boolean checkForTables() {
        boolean hasTables = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM bus", null);

        if (cursor != null && cursor.getCount() > 0) {
            hasTables = true;
            cursor.close();
        }

        return hasTables;
    }

    public String getUrlByCluster(String cluster) {
        String url = null;
        String selectQuery = "SELECT url FROM bus WHERE cluster = '" + cluster.trim() + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            url = cursor.getString(0);
        }
        return url;
    }

    public String getStationIdByCluster(String cluster) {
        String stationId = null;
        String selectQuery = "SELECT stationId FROM bus WHERE cluster = '" + cluster.trim() + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            stationId = cursor.getString(0);
        }
        return stationId;
    }

    public void insertRecord(String url, String cluster, String stationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO bus(url , cluster, stationId) VALUES ('" + url + "','" + cluster + "','" + stationId + "')";
        db.execSQL(insertQuery);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        if (newVersion > oldVersion) {
            sqLiteDatabase.execSQL("ALTER TABLE bus ADD COLUMN stationId TEXT DEFAULT null");
        }
    }
}
