package com.example.bostonhacks2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import static com.example.bostonhacks2019.R.id.createAlertButton;
import static com.example.bostonhacks2019.R.id.start;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {

            SQLiteDatabase myDataBase = this.openOrCreateDatabase("users", MODE_PRIVATE,null);
            Cursor c = myDataBase.rawQuery("SELECT * FROM alerts",null);

            int medicationNameIndex = c.getColumnIndex("medicationName");
            int dueTimeIndex = c.getColumnIndex("dueTime");

            Log.d("SOUT1", Integer.toString(medicationNameIndex));
            Log.d("SOUT2", Integer.toString(dueTimeIndex));

            c.moveToFirst();

            ArrayList<String> myDataset = new ArrayList<>();

            if(c.moveToFirst()){
                do{
                    String message = "" ;
                    message += c.getString(medicationNameIndex) + " ";
                    message += c.getString(dueTimeIndex);
                    Log.d("SOUT3", message + "\n");
                    myDataset.add(message);
                }while(c.moveToNext());
            }


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myDataset);

            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }else{
            Log.d("SOUT","Bundle is empty");
        }


        User newUser = new User("Sonam", "Tamang", "192.168.1.157:8090");

        SQLiteDatabase mydatabase = openOrCreateDatabase("users",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS userInfo(firstName VARCHAR, lastName VARCHAR,speakerAddress VARCHAR);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS alerts(medicationName VARCHAR, dueTime  VARCHAR);");


        ContentValues values = new ContentValues();
        values.put("firstName", newUser.getFirstName());
        values.put("lastName",newUser.getLastName());
        values.put("speakerAddress",newUser.getSpeakerAddress());
        mydatabase.insert("userInfo", null, values);

    }

    public void createAlert(View view){
        Intent i = new Intent(MainActivity.this, alertCreate.class);
        startActivity(i);
    }


    //add alerts to recycler View
    public void addItem(String item){

    }
}
