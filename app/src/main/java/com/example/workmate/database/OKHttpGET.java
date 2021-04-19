package com.example.workmate.database;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workmate.R;
import com.example.workmate.models.ClientModel;
import com.example.workmate.activities.MainActivity;
import com.example.workmate.models.OrderModel;
import com.example.workmate.models.RatingsModel;
import com.example.workmate.models.SupplierModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//pull all the tables at the same time
public class OKHttpGET extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get request   client_table
        OkHttpClient client = new OkHttpClient();
        String urlClient = "https://workmatedb.ddns.net/workmateFiles/json_output_client.php";
        Request request = new Request.Builder()
                .url(urlClient)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    ClientModel[] clientArray;
                    clientArray= loadClients(myResponse);
                    ClientModel client;
                    boolean added;
                    DatabaseHelper databaseHelper = new DatabaseHelper(OKHttpGET.this);

                    if(clientArray!=null) {

                        for (ClientModel clientModel : clientArray) {
                            client = databaseHelper.searchClient(clientModel.getClientEmail());

                            //if not already in database, add them
                            if (client == null) {
                                added = databaseHelper.addClient(clientModel);
                                if (!added) {
                                    Log.d("OKHTTPGET", "Error adding the client:");
                                } else {
                                    Log.d("OKHTTPGET", "added client succesfully" + clientModel.toString());
                                }
                            } else {
                                Log.d("OKHTTPGET", "Client Already in database" + client.getClientFname());
                            }

                        }
                    }

                }
            }
        });
        //get request   supplier_table
        OkHttpClient supplier = new OkHttpClient();
        String urlSupplier = "https://workmatedb.ddns.net/workmateFiles/json_output_supplier.php";
        Request requestS = new Request.Builder()
                .url(urlSupplier)
                .build();

        supplier.newCall(requestS).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponses = response.body().string();

                    SupplierModel[] supplierArray = loadSuppliers(myResponses);;

                    SupplierModel supplier;
                    boolean added;
                    DatabaseHelper databaseHelper = new DatabaseHelper(OKHttpGET.this);

                    if(supplierArray!=null) {

                        for (SupplierModel supplierModel : supplierArray) {
                            supplier = databaseHelper.searchSupplier(supplierModel.getSupplierEmail());

                            //if not already in database, add them
                            if (supplier == null) {
                                added = databaseHelper.addSupplier(supplierModel);
                                if (!added) {
                                    Log.d("OKHTTPGET", "Error adding the supplier:");
                                } else {
                                    Log.d("OKHTTPGET", "Added " + supplierModel.getSupplierFname());
                                }
                            } else {
                                Log.d("OKHTTPGET", "Supplier in database" + supplier.toString());
                            }

                        }
                    }

                }
            }
        });

        //get request   order_table
        OkHttpClient order = new OkHttpClient();
        String urlOrder = "https://workmatedb.ddns.net/workmateFiles/json_output_order.php";
        Request requestO = new Request.Builder()
                .url(urlOrder)
                .build();

        order.newCall(requestO).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponseo = response.body().string();

                    OrderModel[] orderArray = loadOrders(myResponseo);

                    OrderModel order;
                    boolean added;
                    DatabaseHelper databaseHelper = new DatabaseHelper(OKHttpGET.this);

                    if(orderArray!=null) {

                        for (OrderModel orderModel : orderArray) {
                            //need to search based on something unique....order id
                            Log.d("OKHTTPGET", orderModel.toString());
                            order = databaseHelper.searchOrder(orderModel.getOrderId());

                            //if not already in database, add them
                            if (order == null) {
                                added = databaseHelper.addOrder(orderModel);
                                if (!added) {
                                    Log.d("OKHTTPGET", "Error adding the order:");
                                } else {
                                    Log.d("OKHTTPGET", "Order added succesfully: " + orderModel.toString());
                                }
                            } else {
                                Log.d("OKHTTPGET", "Order already in database" );
                            }

                        }
                    }

                }
            }
        });

        //get request   rating_table
        OkHttpClient rating = new OkHttpClient();
        String urlRating = "https://workmatedb.ddns.net/workmateFiles/json_output_rating.php";
        Request requestR = new Request.Builder()
                .url(urlRating)
                .build();

        rating.newCall(requestR).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponser = response.body().string();
                    loadRatings(myResponser);

                    RatingsModel[] ratingArray = loadRatings(myResponser);;

                    RatingsModel rating;
                    boolean added;
                    DatabaseHelper databaseHelper = new DatabaseHelper(OKHttpGET.this);

                    if(ratingArray!=null) {

                        for (RatingsModel ratingsModel : ratingArray) {
                            //need to search based on something unique....rating id
                            rating = databaseHelper.searchRating(ratingsModel.getRatingId());

                            //if not already in database, add them
                            if (rating == null) {
                                added = databaseHelper.addRating(ratingsModel);
                                if (!added) {
                                    Log.d("OKHTTPGET", "Error adding the rating:" );
                                } else {
                                    Log.d("OKHTTPGET", "Rating added succesfully" + ratingsModel.toString());
                                }
                            } else {
                                Log.d("OKHTTPGET", "Rating already in table" + rating.toString());
                            }

                        }
                    }

                }
            }
        });
        //go to main activity
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }

    private ClientModel[] loadClients(String stringResponse) {
        try {
            JSONArray array = new JSONArray((stringResponse));
            ClientModel[] clientArray = new ClientModel[array.length()];

            for(int i = 0; i<array.length();i++) {
                JSONObject client = array.getJSONObject(i);

                ClientModel clientModel = new ClientModel(client.getInt("client_id"), client.getString("client_fname"),client.getString("client_lname"), client.getString("client_email"),
                        client.getString("client_phone"), client.getString("client_addr"), client.getString("client_latitude"), client.getString("client_longitude"));
                Log.d("STRING", clientModel.toString());

                clientArray[i] = clientModel;

            }
            return clientArray;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SupplierModel[] loadSuppliers(String stringResponse) {
        try {
            JSONArray array = new JSONArray((stringResponse));
            SupplierModel[] supplierArray = new SupplierModel[array.length()];

            for(int i = 0; i<array.length();i++) {
                JSONObject supplier = array.getJSONObject(i);

                SupplierModel supplierModel = new SupplierModel(supplier.getInt("supplier_id"), supplier.getString("supplier_fname"), supplier.getString("supplier_lname"),
                        supplier.getString("supplier_email"), supplier.getString("supplier_phone"), supplier.getString("supplier_addr"),supplier.getString("supplier_service"),
                        supplier.getInt("supplier_rating"), supplier.getString("supplier_latitude"), supplier.getString("supplier_longitude"));
                Log.d("STRING", supplierModel.toString());

                supplierArray[i] = supplierModel;

            }
            return supplierArray;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private OrderModel[] loadOrders(String stringResponse) {
        try {
            JSONArray array = new JSONArray((stringResponse));
            OrderModel[] orderArray = new OrderModel[array.length()];

            for(int i = 0; i<array.length();i++) {
                JSONObject order = array.getJSONObject(i);

                OrderModel orderModel = new OrderModel(order.getInt("order_id"), order.getString("client_email"), order.getString("supplier_email"), order.getString("supplier_service"),
                        order.getString("order_date"), order.getInt("order_hours"), order.getDouble("order_cost"),order.getString("order_location"));
                Log.d("STRING", orderModel.toString());

                orderArray[i] = orderModel;

            }
            return orderArray;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RatingsModel[] loadRatings(String stringResponse) {
        try {
            JSONArray array = new JSONArray((stringResponse));
            RatingsModel[] ratingArray = new RatingsModel[array.length()];

            for(int i = 0; i<array.length();i++) {
                JSONObject rating = array.getJSONObject(i);

                RatingsModel ratingModel = new RatingsModel(rating.getInt("rating_id"), rating.getString("client_email"),
                        rating.getString("supplier_email"), rating.getInt("rating_number"), rating.getString("rating_comment"));
                Log.d("STRING", ratingModel.toString());

                ratingArray[i] = ratingModel;

            }
            return ratingArray;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}

