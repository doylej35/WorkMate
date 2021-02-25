package com.example.workmate;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "workmate_database";
    public static final String CLIENT_TABLE = "CLIENT_TABLE";
    public static final String COLUMN_CLIENT_FNAME = "CLIENT_FNAME";
    public static final String COLUMN_CLIENT_LNAME = "CLIENT_LNAME";
    public static final String COLUMN_CLIENT_PHONE = "CLIENT_PHONE";
    public static final String COLUMN_CLIENT_EMAIL = "CLIENT_EMAIL";
    public static final String COLUMN_CLIENT_ADDR = "CLIENT_ADDR";
    public static final String COLUMN_CLIENT_ID = "ID";

    public static final String COLUMN_SUPPLIER_FNAME = "SUPPLIER_FNAME";
    public static final String SUPPLIER_TABLE = "SUPPLIER_TABLE";
    public static final String COLUMN_SUPPLIER_LNAME = "SUPPLIER_LNAME";
    public static final String COLUMN_SUPPLIER_PHONE = "SUPPLIER_PHONE";
    public static final String COLUMN_SUPPLIER_EMAIL = "SUPPLIER_EMAIL";
    public static final String COLUMN_SUPPLIER_ADDR = "SUPPLIER_ADDR";
    public static final String COLUMN_SUPPLIER_SERVICE = "SUPPLIER_SERVICE";
    public static final String COLUMN_SUPPLIER_ID = "ID";



    public DatabaseHelper(@Nullable Context context) {
        super(context, "client.db", null, 1);
    }
    private static final String CREATE_TABLE_CLIENT = "CREATE TABLE " + CLIENT_TABLE + " (" + COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CLIENT_FNAME + " TEXT, " + COLUMN_CLIENT_LNAME + " TEXT, " +
            COLUMN_CLIENT_PHONE + " TEXT, " + COLUMN_CLIENT_EMAIL + " TEXT, " + COLUMN_CLIENT_ADDR + " TEXT)";
    private static final String CREATE_TABLE_SUPPLIER = "CREATE TABLE " + SUPPLIER_TABLE + " (" + COLUMN_SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SUPPLIER_FNAME + " TEXT, " + COLUMN_SUPPLIER_LNAME + " TEXT, " +
            COLUMN_SUPPLIER_PHONE + " TEXT, " + COLUMN_SUPPLIER_EMAIL + " TEXT, " + COLUMN_SUPPLIER_SERVICE + " TEXT, " + COLUMN_SUPPLIER_ADDR + " TEXT)";


    //this is called the first time a database is acessed. Should be code to create a anew database
    //also called when new data added
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SUPPLIER);
        db.execSQL(CREATE_TABLE_CLIENT);
    }

    //this is called if the database version number changes. Prevents users from breaking database if making changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //need code here to remove stuff thats already there/hasnt been updated
        //onCreate(db);
    }

    public boolean addClient(ClientModel clientModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        //adding clients
        ContentValues cvClient = new ContentValues();
        cvClient.put(COLUMN_CLIENT_FNAME, clientModel.getClientFname());
        cvClient.put(COLUMN_CLIENT_LNAME, clientModel.getClientLname());
        cvClient.put(COLUMN_CLIENT_EMAIL, clientModel.getClientEmail());
        cvClient.put(COLUMN_CLIENT_PHONE, clientModel.getClientPhone());
        cvClient.put(COLUMN_CLIENT_ADDR, clientModel.getClientAddr());

        long insert = db.insert(CLIENT_TABLE, null, cvClient);
        if(insert == -1) {
            return false;
        }else {
            return true;
        }
    }
    public boolean addSupplier(SupplierModel supplierModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        //adding clients
        ContentValues cvSupplier = new ContentValues();
        cvSupplier.put(COLUMN_SUPPLIER_FNAME, supplierModel.getSupplierFname());
        cvSupplier.put(COLUMN_SUPPLIER_LNAME, supplierModel.getSupplierLname());
        cvSupplier.put(COLUMN_SUPPLIER_EMAIL, supplierModel.getSupplierEmail());
        cvSupplier.put(COLUMN_SUPPLIER_PHONE, supplierModel.getSupplierPhone());
        cvSupplier.put(COLUMN_SUPPLIER_ADDR, supplierModel.getSupplierAddr());
        cvSupplier.put(COLUMN_SUPPLIER_SERVICE, supplierModel.getSupplierService());

        long insert = db.insert(SUPPLIER_TABLE, null, cvSupplier);
        if(insert == -1) {
            return false;
        }else {
            return true;
        }
    }
}
