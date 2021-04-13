package com.example.workmate;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

//this class connects to the mysql and loads the values into sqlite
public class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;



    private MySingleton(Context context){
        mContext= context;
        requestQueue = getRequestQueue();

    }


    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getmInstance(Context context){
        if(mInstance ==null){
            mInstance= new MySingleton(context);
        }
        return mInstance;
    }


    public <T> void addToRequestQueue(StringRequest request){
        requestQueue.add(request);
    }

}
