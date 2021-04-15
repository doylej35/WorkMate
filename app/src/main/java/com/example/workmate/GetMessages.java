package com.example.workmate;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//this class is for use in testing messaging functionality. It connects to the server and returns ALL
//of the messages in the server (obvs this will have to change..)
public class GetMessages extends AppCompatActivity {
    private TextView mTextViewResult;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  mTextViewResult = findViewById(R.id.text_view_result); just a print box i had in my project

        //get request   client_table
        OkHttpClient client = new OkHttpClient();
        String urlClient = "https://workmatedb.ddns.net/workmateFiles/json_output_messages.php";
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
                    loadMessages(myResponse);

                    //the json object is printed to the xml file. If you want to seperate it before printing
                    //then maybe comment this out and just print from the load messages function instead
                   /* GetMessages.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewResult.setText(myResponse);
                        }
                    });

                    */

                }
            }
        });
    }

    private void loadMessages(String stringResponse) {
        try {
            JSONArray array = new JSONArray((stringResponse));

            for(int i = 0; i<array.length();i++) {
                JSONObject message = array.getJSONObject(i);

                MessageModel messageModel = new MessageModel(message.getInt("ID"), message.getString("Message"), message.getString("Title"),
                                                            message.getInt("Synchstatus"));

                //print all messages to logcat as a string
                Log.d("STRING", messageModel.toString());

            }
            //there is now an array containing all the messages :)

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
