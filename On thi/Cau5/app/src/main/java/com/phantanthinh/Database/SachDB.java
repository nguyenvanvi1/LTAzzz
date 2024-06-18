package com.phantanthinh.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SachDB extends SQLiteOpenHelper {
    public static final String DB_NAME = "Sach.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Sachs";
    public static final String COL_CODE = "SCode";
    public static final String COL_NAME = "SName";
    public static final String COL_PRICE = "SPrice";

    public SachDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " ("
                + COL_CODE + " VARCHAR(50) PRIMARY KEY, "
                + COL_NAME + " NVARCHAR(50), "
                + COL_PRICE + " REAL)";
        db.execSQL(sql);
        Log.d("Database", "Table " + TBL_NAME + " created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }

    // SELECT query
    public Cursor queryData(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    // Query without returning result (CREATE, INSERT, UPDATE, DELETE, ...)
    public void execSQL(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public int getNumberOfRows() {
        Cursor cursor = queryData("SELECT * FROM " + TBL_NAME);
        int numberOfRows = cursor.getCount();
        cursor.close();
        return numberOfRows;
    }
}
