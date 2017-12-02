package com.example.rama.smarthealth;

/**
 * Created by Rama on 21-11-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by priya on 12-11-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "health.db";
    public static final String TABLE_NAME = "health_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AGE";
    public static final String COL_4 = "BLOODGROUP";
    public static final String COL_5 = "SLEEP";
    public static final String COL_6 = "CARDIO";
    public static final String COL_7 = "ALCOHOL";
    public static final String COL_8 = "TOBACCO";
    public static final String COL_9 = "DISEASE";
    public static final String COL_10 = "SCORE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AGE INTEGER,BLOODGROUP TEXT, SLEEP INTEGER, CARDIO INTEGER, ALCOHOL INTEGER, TOBACCO INTEGER, DISEASE INTEGER, SCORE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String age, String bloodgroup, String sleep, String cardio, String alcohol, String tobacco, String disease, int score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,age);
        contentValues.put(COL_4,bloodgroup);
        contentValues.put(COL_5,sleep);
        contentValues.put(COL_6,cardio);
        contentValues.put(COL_7,alcohol);
        contentValues.put(COL_8,tobacco);
        contentValues.put(COL_9,disease);
        contentValues.put(COL_10,score);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(String emailid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE "+COL_2+" = '"+emailid.trim()+"'", null);
        return res;
    }

}
