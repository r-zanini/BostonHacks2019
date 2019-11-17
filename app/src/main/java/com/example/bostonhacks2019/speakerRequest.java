package com.example.bostonhacks2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class speakerRequest extends AppCompatActivity {

    private TextView message;

    private class sendPost extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            //firstName,Speaker,medicine
            try {
                URL url = new URL(strings[1]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                //urlConnection.setRequestProperty("Content-Type","text/xml");
                try {
                    urlConnection.setDoOutput(true);
                    urlConnection.setChunkedStreamingMode(0);

                    OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

                    Log.d("out before write", out.toString());


                    writeStream(out, strings[0], strings[2], strings[1]);
                    out.flush();
                    out.close();
                    Log.d("out after write", out.toString());

                    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder answer = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            answer.append(responseLine.trim());
                        }
                        Log.d("response", answer.toString());

//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            readStream(in);
                } finally {
                    if(urlConnection !=null)
                        urlConnection.disconnect();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_request);


        Intent i = getIntent();
        String medication = i.getStringExtra("medication");

        Log.d("Speaker request created", medication);


        SQLiteDatabase mydatabase = openOrCreateDatabase("users",MODE_PRIVATE,null);
        Cursor c = mydatabase.rawQuery("SELECT * FROM userInfo",null);


        int firstNameIndex = c.getColumnIndex("firstName");
        int speakerAddressIndex = c.getColumnIndex("speakerAddress");

        c.moveToFirst();
        String firstName;
        String speaker;

        c.moveToFirst();

        firstName = c.getString(firstNameIndex) ;
        speaker = c.getString(speakerAddressIndex) ;

        Log.d("first name", firstName);
        Log.d("Speaker address", speaker);
        String[] elements = {firstName, speaker, medication};

        new sendPost().execute(elements);

        message = findViewById(R.id.message);
        message.setText("Hello " + firstName + ", it is now time to take your medication," + medication);

    }

    private void writeStream(OutputStream out, String firstName, String medicationName, String speakerAddress) throws Exception{
        String message = "<?xml version=\"1.0\" ?><play_info><app_key>soKAl19cXEGTCE9e4mTsYD6Fd7KL3kox</app_key><url>http://us-central1-bostonhacks19.cloudfunctions.net/BostonHacks_Bose?message=Hello%20" +
                firstName + ",%20it%20is%20now%20time%20to%20take%20your%20medication," + medicationName +
                "</url><service>PHA</service><volume>70</volume></play_info>";
//        String message = "<play_info><app_key>soKAl19cXEGTCE9e4mTsYD6Fd7KL3kox</app_key><url>http://us-central1-bostonhacks19.cloudfunctions.net/BostonHacks_Bose?message=Hello%20" +
//                 + ",%20it%20is%20now%20time%20to%20take%20your%20medication," + medicationName +
//                "</url><service>PHA Medication Alert</service><reason>Medication Time</reason><message>Medication Time</message><volume>70</volume></play_info>";

        Log.d("messageOut", message);

        byte[] toSend = message.getBytes();
        out.write(toSend, 0, toSend.length);
    }


    public void goHome(View view){
        Intent i = new Intent(this.getApplicationContext(), MainActivity.class);

        startActivity(i);
    }
}
