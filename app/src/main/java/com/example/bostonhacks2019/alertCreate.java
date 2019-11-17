package com.example.bostonhacks2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

public class alertCreate extends AppCompatActivity {

    //create references to timepicker and medication name
    private TimePicker timePicker;
    private EditText medicationName;


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

        //get references to medication editText and timePicker
        medicationName = findViewById(R.id.medicationNameText);
        timePicker = findViewById(R.id.simpleTimePicker);
        String hour = Integer.toString(timePicker.getHour());
        String minutes = Integer.toString(timePicker.getMinute());
        String dueTime = hour + minutes;
        String amorpm;

        if ( timePicker.getHour() == 0){
            amorpm = "AM";
        } else if ( timePicker.getHour()== 12) {
            amorpm = "PM";
        } else if ( timePicker.getHour() > 12) {
            amorpm = "PM";
        } else {
            amorpm = "AM";
        }

        AlarmClock newAlarmClock = new AlarmClock();

        //create a new alert object and store it in the database
        alert newAlert = new alert(medicationName.getText().toString(), dueTime);


        SQLiteDatabase mydatabase = openOrCreateDatabase("users",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS alerts(medicationName VARCHAR, dueTime  VARCHAR);");

        ContentValues values = new ContentValues();
        values.put("medicationName", newAlert.getMedication());
        values.put("dueTime",newAlert.getDueTime());
        mydatabase.insert("alerts", null, values);

        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_HOUR, hour + Integer.parseInt(hour));
        i.putExtra(AlarmClock.EXTRA_MINUTES, minutes + Integer.parseInt(minutes));
        startActivity(i);

        Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
        i2.putExtra("Alert status","Success");
        startActivity(i2);
    }


}
