package com.example.workmate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.Nullable;

import java.util.ArrayList;

//this class implements the sqlite openhelper functionality to create our data base functions that will be used
//WARNINGS ARE ABOUT SIMPLIFYING IF STATEMENTS
public class DatabaseHelper extends SQLiteOpenHelper {

    //name of the database is defined here
    public static String DATABASE_NAME = "workmatedb.db";
    //declarations of the client table columns(ie data points)
    public static final String TABLE_CLIENT = "CLIENT_TABLE";
    public static final String COLUMN_CLIENT_FNAME = "CLIENT_FNAME";
    public static final String COLUMN_CLIENT_LNAME = "CLIENT_LNAME";
    public static final String COLUMN_CLIENT_EMAIL = "CLIENT_EMAIL";
    public static final String COLUMN_CLIENT_PHONE = "CLIENT_PHONE";
    public static final String COLUMN_CLIENT_ADDR = "CLIENT_ADDR";
    public static final String COLUMN_CLIENT_LATITUDE = "CLIENT_LATITUDE";
    public static final String COLUMN_CLIENT_LONGITUDE = "CLIENT_LONGITUDE";
    public static final String COLUMN_CLIENT_ID = "ID";

    //declarations of the supplier table columns(ie data points)
    public static final String TABLE_SUPPLIER = "SUPPLIER_TABLE";
    public static final String COLUMN_SUPPLIER_FNAME = "SUPPLIER_FNAME";
    public static final String COLUMN_SUPPLIER_LNAME = "SUPPLIER_LNAME";
    public static final String COLUMN_SUPPLIER_PHONE = "SUPPLIER_PHONE";
    public static final String COLUMN_SUPPLIER_EMAIL = "SUPPLIER_EMAIL";
    public static final String COLUMN_SUPPLIER_ADDR = "SUPPLIER_ADDR";
    public static final String COLUMN_SUPPLIER_SERVICE = "SUPPLIER_SERVICE";
    public static final String COLUMN_SUPPLIER_RATING = "SUPPLIER_RATING";
    public static final String COLUMN_SUPPLIER_LATITUDE = "SUPPLIER_LATITUDE";
    public static final String COLUMN_SUPPLIER_LONGITUDE = "SUPPLIER_LONGITUDE";
    public static final String COLUMN_SUPPLIER_ID = "ID";


    //order table
    public static final String TABLE_ORDER = "ORDER_TABLE";
    public static final String COLUMN_ORDER_ID = "ORDER_ID";
    //   COLUMN_CLIENT_EMAIL
    //   COLUMN_SUPPLIER_EMAIL
    public static final String COLUMN_ORDER_DATE = "ORDER_DATE";
    public static final String COLUMN_ORDER_HOURS = "ORDER_HOURS"; //int
    public static final String COLUMN_ORDER_COST = "ORDER_COST";    //double
    public static final String COLUMN_ORDER_LOCATION = "ORDER_LOCATION";   //probs clients address

    public static final String TABLE_RATING =  "RATING_TABLE";
    public static final String COLUMN_RATING_ID = "RATING_ID";
    //COLOUMN_CLIENT_EMAIL
    //COLUMN_SUPPLIER_EMAIL
    public static final String COLUMN_RATING_NUMBER = "RATING_NUMBER";
    public static final String COLUMN_RATING_COMMENT = "RATING_COMMENT";

    private static final String CREATE_TABLE_RATING = "CREATE TABLE " +
            TABLE_RATING + " (" +
            COLUMN_RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CLIENT_EMAIL + " TEXT, " +
            COLUMN_SUPPLIER_EMAIL + " TEXT, " +
            COLUMN_RATING_NUMBER + " INTEGER, " +
            COLUMN_RATING_COMMENT + " TEXT)";

    //create table for order
    private static final String CREATE_TABLE_ORDER = "CREATE TABLE " +
            TABLE_ORDER + " (" +
            COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CLIENT_EMAIL + " TEXT, " +
            COLUMN_SUPPLIER_EMAIL + " TEXT, " +
            COLUMN_ORDER_DATE + " TEXT, " +
            COLUMN_ORDER_HOURS + " INTEGER, " +
            COLUMN_ORDER_COST + " DOUBLE, " +
            COLUMN_ORDER_LOCATION + " TEXT)";   //geoid?




    private static final String CREATE_TABLE_CLIENT = "CREATE TABLE " +
            TABLE_CLIENT + " (" +
            COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CLIENT_FNAME + " TEXT, " +
            COLUMN_CLIENT_LNAME + " TEXT, " +
            COLUMN_CLIENT_PHONE + " TEXT, " +
            COLUMN_CLIENT_EMAIL + " TEXT, " +
            COLUMN_CLIENT_ADDR + " TEXT, " +
            COLUMN_CLIENT_LATITUDE + " TEXT, " +
            COLUMN_CLIENT_LONGITUDE + " TEXT)";


    //pretty sure the error is something to do with this, check the logcat
    private static final String CREATE_TABLE_SUPPLIER = "CREATE TABLE " +
            TABLE_SUPPLIER + " (" +
            COLUMN_SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_SUPPLIER_FNAME + " TEXT, " +
            COLUMN_SUPPLIER_LNAME + " TEXT, " +
            COLUMN_SUPPLIER_PHONE + " TEXT, " +
            COLUMN_SUPPLIER_EMAIL + " TEXT, " +
            COLUMN_SUPPLIER_ADDR + " TEXT, " +
            COLUMN_SUPPLIER_SERVICE + " TEXT, " +
            COLUMN_SUPPLIER_RATING + " INTEGER, " +
            COLUMN_SUPPLIER_LATITUDE + " TEXT, " +
            COLUMN_SUPPLIER_LONGITUDE + " TEXT)";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    //this is called the first time a database is accessed. Should be code to create a a new database

    //also called when new data added
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENT);
        db.execSQL(CREATE_TABLE_SUPPLIER);
        db.execSQL(CREATE_TABLE_ORDER);
        db.execSQL(CREATE_TABLE_RATING);
        Log.d("CREATION", "TABLES ARE BEING CREATED");
    }

    //this is called if the database version number changes. Prevents users from breaking database if making changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //need code here to remove stuff thats already there/hasnt been updated
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_CLIENT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_SUPPLIER + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_ORDER + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_RATING + "'");

        onCreate(db);
    }

    public boolean addRating(RatingsModel ratingsModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        //adding order inputs
        ContentValues cvRating = new ContentValues();
        cvRating.put(COLUMN_RATING_ID, ratingsModel.getRatingId());
        cvRating.put(COLUMN_CLIENT_EMAIL, ratingsModel.getClientEmail());
        cvRating.put(COLUMN_SUPPLIER_EMAIL, ratingsModel.getSupplierEmail());
        cvRating.put(COLUMN_RATING_NUMBER, ratingsModel.getRatingNumber());
        cvRating.put(COLUMN_RATING_COMMENT, ratingsModel.getRatingComment());


        long insert = db.insert(TABLE_RATING, null, cvRating);
        if(insert == -1) {
            return false;
        }else {
            return true;
        }
    }


    public boolean addOrder(OrderModel orderModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        //adding order inputs
        ContentValues cvOrder = new ContentValues();
        cvOrder.put(COLUMN_ORDER_ID, orderModel.getOrderId());
        cvOrder.put(COLUMN_CLIENT_EMAIL, orderModel.getClientEmail());
        cvOrder.put(COLUMN_SUPPLIER_EMAIL, orderModel.getSupplierEmail());
        cvOrder.put(COLUMN_ORDER_DATE, orderModel.getOrderDate());
        cvOrder.put(COLUMN_ORDER_HOURS, orderModel.getOrderHours());
        cvOrder.put(COLUMN_ORDER_COST, orderModel.getOrderCost());
        cvOrder.put(COLUMN_ORDER_LOCATION, orderModel.getOrderLocation());

        long insert = db.insert(TABLE_ORDER, null, cvOrder);
        if(insert == -1) {
            return false;
        }else {
            return true;
        }
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
        cvClient.put(COLUMN_CLIENT_LATITUDE, clientModel.getClientLatitude());
        cvClient.put(COLUMN_CLIENT_LONGITUDE, clientModel.getClientLongitude());

        long insert = db.insert(TABLE_CLIENT, null, cvClient);
        if(insert == -1) {
            return false;
        }else {
            return true;
        }
    }
    public boolean addSupplier(SupplierModel supplierModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        //adding suppliers
        ContentValues cvSupplier = new ContentValues();
        cvSupplier.put(COLUMN_SUPPLIER_FNAME, supplierModel.getSupplierFname());
        cvSupplier.put(COLUMN_SUPPLIER_LNAME, supplierModel.getSupplierLname());
        cvSupplier.put(COLUMN_SUPPLIER_PHONE, supplierModel.getSupplierPhone());
        cvSupplier.put(COLUMN_SUPPLIER_EMAIL, supplierModel.getSupplierEmail());
        cvSupplier.put(COLUMN_SUPPLIER_ADDR, supplierModel.getSupplierAddr());
        cvSupplier.put(COLUMN_SUPPLIER_SERVICE, supplierModel.getSupplierService());
        cvSupplier.put(COLUMN_SUPPLIER_RATING, supplierModel.getSupplierRating());
        cvSupplier.put(COLUMN_SUPPLIER_LATITUDE, supplierModel.getSupplierLatitude());
        cvSupplier.put(COLUMN_SUPPLIER_LONGITUDE, supplierModel.getSupplierLongitude());

        long insert = db.insert(TABLE_SUPPLIER, null, cvSupplier);
        Log.d("CREATION","addSupplier is being executed");
        if(insert == -1) {
            return false;
        }else {
            return true;
        }
    }

    //used to show all of the suppliers in the db
    //search will be implemented later to narrow down the list
    public ArrayList<SupplierModel> readSuppliers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorSuppliers = db.rawQuery("SELECT * FROM " + TABLE_SUPPLIER, null);
        ArrayList<SupplierModel> supplierModelArrayList = new ArrayList<>();

        //shows all of the stored data about the suppliers (I hope) will be edited later
        if(cursorSuppliers.moveToFirst()){
            do {
                supplierModelArrayList.add(new SupplierModel(cursorSuppliers.getInt(0),
                                                             cursorSuppliers.getString(1),
                                                             cursorSuppliers.getString(2),
                                                             cursorSuppliers.getString(3),
                                                             cursorSuppliers.getString(4),
                                                             cursorSuppliers.getString(5),
                                                             cursorSuppliers.getString(6),
                                                            cursorSuppliers.getInt(7),
                                                            cursorSuppliers.getString(8),
                                                            cursorSuppliers.getString(9)));
            }while(cursorSuppliers.moveToNext());
        }

        cursorSuppliers.close();
        return supplierModelArrayList;
    }


    //sorts the data by service
    public ArrayList<SupplierModel> searchService(String input){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor search = db.rawQuery("SELECT * FROM " + TABLE_SUPPLIER + " WHERE " + COLUMN_SUPPLIER_SERVICE + " LIKE " + "'" + input + "'", null);

        ArrayList<SupplierModel> services = new ArrayList<>();

        if(search.moveToFirst()) {
            do {
                services.add(new SupplierModel(search.getInt(0), search.getString(1), search.getString(2),
                        search.getString(3), search.getString(4), search.getString(5), search.getString(6),
                        search.getInt(7), search.getString(8), search.getString(9)));
            } while (search.moveToNext());
        }else {
            Log.d("CREATION", "NO DATA TO LOOK AT IN SEARCH FUNCTION");
        }
        search.close();
        return services;
    }


    public OrderModel searchOrder(int input) {
        SQLiteDatabase db = this.getReadableDatabase();

        //search based on their email
        Cursor search = db.rawQuery("SELECT * FROM " + TABLE_ORDER + " WHERE " + COLUMN_ORDER_ID + " LIKE " + "'" + input + "'", null);

        OrderModel order;

        if(search.moveToFirst()) {
            do {
                order = new OrderModel(search.getInt(0), search.getString(1), search.getString(2), search.getString(3),
                        search.getString(4),  search.getInt(5), search.getDouble(6), search.getString(7));
            } while (search.moveToNext());
        }else {
            Log.d("CREATION", "Order not found");
            order = null;
        }

        search.close();
        return order;

    }

    public RatingsModel searchRating(int input){

        SQLiteDatabase db = this.getReadableDatabase();

        //search based on their email
        Cursor search = db.rawQuery("SELECT * FROM " + TABLE_RATING + " WHERE " + COLUMN_RATING_ID + " LIKE " + "'" + input + "'", null);

        RatingsModel rating;

        if(search.moveToFirst()) {
            do {
                rating = new RatingsModel(search.getInt(0), search.getString(1), search.getString(2),
                        search.getInt(3), search.getString(4));
            } while (search.moveToNext());
        }else {
            Log.d("CREATION", "Rating not found");
            rating = null;
        }

        search.close();
        return rating;
    }


    //finds a supplier by their email
    public SupplierModel searchSupplier(String input){

        SQLiteDatabase db = this.getReadableDatabase();

        //search based on their email
        Cursor search = db.rawQuery("SELECT * FROM " + TABLE_SUPPLIER + " WHERE " + COLUMN_SUPPLIER_EMAIL + " LIKE " + "'" + input + "'", null);

        SupplierModel supplier;

        if(search.moveToFirst()) {
            do {
                supplier = new SupplierModel(search.getInt(0), search.getString(1), search.getString(2),
                        search.getString(3), search.getString(4), search.getString(5), search.getString(6),
                        search.getInt(7), search.getString(8), search.getString(9));
            } while (search.moveToNext());
        }else {
            Log.d("CREATION", "Person not found");
            supplier = null;
        }

        search.close();
        return supplier;
    }


    //finds a client by their email
    public ClientModel searchClient(String input){

        SQLiteDatabase db = this.getReadableDatabase();

        //search based on their email
        Cursor search = db.rawQuery("SELECT * FROM " + TABLE_CLIENT + " WHERE " + COLUMN_CLIENT_EMAIL + " LIKE " + "'" + input + "'", null);

        ClientModel client;

        if(search.moveToFirst()) {
            do {
                client = new ClientModel(search.getInt(0), search.getString(1), search.getString(2),
                        search.getString(3), search.getString(4), search.getString(5), search.getString(6), search.getString(7));
            } while (search.moveToNext());
        }else {
            Log.d("CREATION", "Person not found");
            client = null;
        }

        search.close();
        return client;
    }
    public void updateClient(String user, String fname, String lname, String addr, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CLIENT_FNAME, fname);
        contentValues.put(COLUMN_CLIENT_LNAME, lname);
        contentValues.put(COLUMN_CLIENT_ADDR, addr);
        contentValues.put(COLUMN_CLIENT_PHONE, phone);

        db.update(TABLE_CLIENT, contentValues, "CLIENT_EMAIL=?", new String[]{user});

    }

    public void updateSupplier(String user, String fname, String lname, String addr, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_SUPPLIER_FNAME, fname);
        contentValues.put(COLUMN_SUPPLIER_LNAME, lname);
        contentValues.put(COLUMN_SUPPLIER_ADDR, addr);
        contentValues.put(COLUMN_SUPPLIER_PHONE, phone);

        db.update(TABLE_SUPPLIER, contentValues, "SUPPLIER_EMAIL=?", new String[]{user});
    }

    public boolean deleteClient(ClientModel clientModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "DELETE FROM " + TABLE_CLIENT + " WHERE " + COLUMN_CLIENT_ID + " = " + clientModel.getClientId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        }else {
            return false;
        }

    }

    //if the supplier is found in the database delete it and return true
//if not found return false
    public boolean deleteSupplier(SupplierModel supplierModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "DELETE FROM " + TABLE_SUPPLIER + " WHERE " + COLUMN_SUPPLIER_ID + " = " + supplierModel.getSupplierId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        }else {
            return false;
        }

    }

}
