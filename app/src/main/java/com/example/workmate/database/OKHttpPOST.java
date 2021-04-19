package com.example.workmate.database;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workmate.models.ClientModel;
import com.example.workmate.models.OrderModel;
import com.example.workmate.models.RatingsModel;
import com.example.workmate.models.SupplierModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpPOST extends AppCompatActivity{
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public OKHttpPOST() {
    }

    //to save a client
    void saveClientData(ClientModel clientModel) {
        String json = clientModel.toString();
        Log.d("JSON", json);


        RequestBody formBody = new FormBody.Builder()
                .add("client_id", String.valueOf(clientModel.getClientId()))
                .add("client_fname", clientModel.getClientFname())
                .add("client_lname", clientModel.getClientLname())
                .add("client_phone", clientModel.getClientPhone())
                .add("client_email", clientModel.getClientEmail())
                .add("client_addr", clientModel.getClientAddr())
                .add("client_latitude", clientModel.getClientLatitude())
                .add("client_longitude", clientModel.getClientLongitude())
                .build();
        Log.d("FORMBODY", formBody.toString());
//        setContentView(R.layout.activity_main);

        //get request   client_table
        OkHttpClient clientc = new OkHttpClient();
        String urlClient = "https://workmatedb.ddns.net/workmateFiles/insert_client.php";
        Request request = new Request.Builder()
                .url(urlClient)
                .post(formBody)
                .build();
        clientc.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    Log.d("SUCCESS", myResponse);
                } else {
                    Log.d("FAILURE", "failed on response");
                }
            }
        });
    }

    //to save a supplier
    void saveSupplierData(SupplierModel supplierModel) {
        String json = supplierModel.toString();
        Log.d("JSON", json);


        RequestBody formBody = new FormBody.Builder()
                .add("supplier_fname", supplierModel.getSupplierFname())
                .add("supplier_lname", supplierModel.getSupplierLname())
                .add("supplier_phone", supplierModel.getSupplierPhone())
                .add("supplier_email", supplierModel.getSupplierEmail())
                .add("supplier_addr", supplierModel.getSupplierAddr())
                .add("supplier_service", supplierModel.getSupplierService())
                .add("supplier_rating", String.valueOf(supplierModel.getSupplierRating()))
                .add("supplier_latitude", supplierModel.getSupplierLatitude())
                .add("suppler_latitude", supplierModel.getSupplierLongitude())
                .build();
        Log.d("FORMBODY", formBody.toString());
//        setContentView(R.layout.activity_main);

        //get request   client_table
        OkHttpClient clients = new OkHttpClient();
        String urlSupplier = "https://workmatedb.ddns.net/workmateFiles/insert_supplier.php";
        Request request = new Request.Builder()
                .url(urlSupplier)
                .post(formBody)
                .build();
        clients.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    Log.d("SUCCESS", myResponse);
                } else {
                    Log.d("FAILURE", "failed on response");
                }
            }
        });
    }

    //to save a rating
    void saveRatingsData(RatingsModel ratingsModel) {
        String json = ratingsModel.toString();
        Log.d("JSON", json);


        RequestBody formBody = new FormBody.Builder()
                .add("rating_id", String.valueOf(ratingsModel.getRatingId()))
                .add("client_email", ratingsModel.getClientEmail())
                .add("supplier_email", ratingsModel.getSupplierEmail())
                .add("rating_number", String.valueOf(ratingsModel.getRatingNumber()))
                .add("rating_comment", ratingsModel.getRatingComment())
                .build();
        Log.d("FORMBODY", formBody.toString());

        //get request   rating_table
        OkHttpClient clientr = new OkHttpClient();
        String urlRating = "https://workmatedb.ddns.net/workmateFiles/insert_rating.php";
        Request request = new Request.Builder()
                .url(urlRating)
                .post(formBody)
                .build();
        clientr.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    Log.d("SUCCESS", myResponse);
                } else {
                    Log.d("FAILURE", "failed on response");
                }
            }
        });
    }

    //to save an order
    void saveOrderData(OrderModel orderModel) {
        String json = orderModel.toString();
        Log.d("JSON", json);


        RequestBody formBody = new FormBody.Builder()
                .add("client_email", orderModel.getClientEmail())
                .add("supplier_email", orderModel.getSupplierEmail())
                .add("supplier_service", orderModel.getSupplierService())
                .add("order_date", orderModel.getOrderDate())
                .add("order_hours", String.valueOf(orderModel.getOrderHours()))
                .add("order_cost", String.valueOf(orderModel.getOrderCost()))
                .add("order_location", orderModel.getOrderLocation())
                .build();
        Log.d("FORMBODY", formBody.toString());
//        setContentView(R.layout.activity_main);

        //get request   client_table
        OkHttpClient cliento = new OkHttpClient();
        String urlOrder = "https://workmatedb.ddns.net/workmateFiles/insert_order.php";
        Request request = new Request.Builder()
                .url(urlOrder)
                .post(formBody)
                .build();
        cliento.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    Log.d("SUCCESS", myResponse);
                } else {
                    Log.d("FAILURE", "failed on response");
                }
            }
        });
    }




}
