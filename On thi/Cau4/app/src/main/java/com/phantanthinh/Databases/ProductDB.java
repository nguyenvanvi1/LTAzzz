package com.phantanthinh.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ProductDB extends SQLiteOpenHelper {
    public static final String DB_NAME = "product.db";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Products";
    public static final String COL_CODE = "productCode";
    public static final String COL_NAME = "productName";
    public static final String COL_PRICE = "productPrice";

    public ProductDB(@Nullable Context context) {
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
