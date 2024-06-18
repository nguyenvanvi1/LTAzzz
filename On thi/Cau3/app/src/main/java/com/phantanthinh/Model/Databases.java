package com.phantanthinh.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Databases extends SQLiteOpenHelper {
    public static final String DB_NAME = "SinhVien.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "SINHVIEN";
    public static final String COL_CODE = "SVCode";
    public static final String COL_NAME = "SVName";
    public static final String COL_CLASS = "SVClass";
    public Databases(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" + COL_CODE + " INTEGER PRIMARY KEY , " + COL_NAME + " NVARCHAR(50), " + COL_CLASS + " VARCHAR(50))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TBL_NAME);
        onCreate(db);
    }
    // SELECT ....
    public Cursor QueryData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    //Truy vấn không trả kết quả (CREATE, INSERT, UPDATE, DELETE, ...)
    public void execSQL(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public int getNumbOfRows(){
        Cursor cursor = QueryData("SELECT * FROM "+ TBL_NAME);
        int numbOfRows = cursor.getCount();
        cursor.close();
        return numbOfRows;
    }
    public void createSampleData(){
        if(getNumbOfRows() == 0 ){
            try{
                execSQL("INSERT INTO "+ TBL_NAME + " VALUES(625107,'Van A','CNTT')");
                execSQL("INSERT INTO "+ TBL_NAME + " VALUES(625108,'Van B','CNTT')");
                execSQL("INSERT INTO "+ TBL_NAME + " VALUES(625109,'Van C','CNTT')");
                execSQL("INSERT INTO "+ TBL_NAME + " VALUES(625110,'Van D','CNTT')");
            }catch (Exception e){
                Log.e("Error:", e.getMessage().toString());
            }
        }
    }
}
