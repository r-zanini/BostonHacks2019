package com.example.bostonhacks2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class alertCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_create);

        Intent intent = getIntent();

        Spinner spinner = (Spinner) findViewById(R.id.alertTypeSpinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Medication");

        // Creating adapter for spinner and add categories
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    public void createAlertAndReturnToMain(View vew){

        //create a new alert object and store it in the database
        alert newAlert = new alert();



        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
