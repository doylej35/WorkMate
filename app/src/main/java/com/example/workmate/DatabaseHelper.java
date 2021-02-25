package com.example.workmate;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String CLIENT_TABLE = "CLIENT_TABLE";
    public static final String COLUMN_CLIENT_FNAME = "CLIENT_FNAME";
    public static final String COLUMN_CLIENT_LNAME = "CLIENT_LNAME";
    public static final String COLUMN_CLIENT_PHONE = "CLIENT_PHONE";
    public static final String COLUMN_CLIENT_EMAIL = "CLIENT_EMAIL";
    public static final String COLUMN_CLIENT_ADDR = "CLIENT_ADDR";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "client.db", null, 1);
    }

    //this is called the first time a database is acessed. Should be code to create a anew database
    //also called when new data added
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CLIENT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CLIENT_FNAME + " TEXT, " + COLUMN_CLIENT_LNAME + " TEXT, " +
                COLUMN_CLIENT_PHONE + " TEXT, " + COLUMN_CLIENT_EMAIL + " TEXT, " + COLUMN_CLIENT_ADDR + " TEXT)";

        db.execSQL(createTableStatement);
    }

    //this is called if the database version number changes. Prevents users from breaking database if making changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ClientModel clientModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CLIENT_FNAME, clientModel.getClientFname());
        cv.put(COLUMN_CLIENT_LNAME, clientModel.getClientLname());
        cv.put(COLUMN_CLIENT_EMAIL, clientModel.getClientEmail());
        cv.put(COLUMN_CLIENT_PHONE, clientModel.getClientPhone());
        cv.put(COLUMN_CLIENT_ADDR, clientModel.getClientAddr());

        long insert = db.insert(CLIENT_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }else {
            return true;
        }
    }
}
