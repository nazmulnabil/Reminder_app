package com.example.zzzzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.zzzzz.AlarmContract;

import static com.example.zzzzz.AlarmContract.AlarmEntry.COL_ACTIVITY;
import static com.example.zzzzz.AlarmContract.AlarmEntry.COL_TIME;
import static com.example.zzzzz.AlarmContract.AlarmEntry.TABLE_NAME;
import static com.example.zzzzz.AlarmContract.AlarmEntry.COL_ACTIVITY;
import static com.example.zzzzz.AlarmContract.AlarmEntry.COL_TIME;
import static com.example.zzzzz.AlarmContract.AlarmEntry.TABLE_NAME;

public class AlarmDBHelper extends SQLiteOpenHelper {


    public static  final String DATABASE_NAME="AlarmDB.db";
    public static  final int DATABASE_VERSION=1;
    private String activity;
    private String time;


    public AlarmDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table="CREATE TABLE "+TABLE_NAME+" ( "+ AlarmContract.AlarmEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+COL_ACTIVITY+" TEXT NOT NULL , "+COL_TIME+" TEXT NOT NULL)";

        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }



    public void insertdata(String activity,String time){


        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues values =new ContentValues();

        values.put(COL_ACTIVITY,activity);
        values.put(COL_TIME,time);


        long rowID=sqLiteDatabase.insert(TABLE_NAME,null,values);



    }

   /*
    public Cursor displaydata(){

        Cursor cursor=null;

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        cursor=sqLiteDatabase.rawQuery ("SELECT *  FROM "+TABLE_NAME,null);
        return cursor;


    }
*/







}
