package com.example.bostonhacks2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.bostonhacks2019.R.id.createAlertButton;
import static com.example.bostonhacks2019.R.id.start;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();

        //if there are already objects passed from intent, retrieve them
        if (i.getExtras() != null){

        }

        SQLiteDatabase mydatabase = openOrCreateDatabase("alerts_database",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS existingAlerts(firstName VARCHAR, lastName VARCHAR,Password VARCHAR);");


    }

    public void createAlert(View view){
        Intent i = new Intent(MainActivity.this, alertCreate.class);
        startActivity(i);
    }


    //add alerts to recycler View
    public void addItem(String item){

    }
}
