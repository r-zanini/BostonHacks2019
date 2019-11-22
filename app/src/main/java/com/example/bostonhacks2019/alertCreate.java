package com.example.bostonhacks2019;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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

        Time dueTime = Time.valueOf(hour + ':' + minutes + ":00");

        //create a new alert object and store it in the database
        alert newAlert = new alert(medicationName.getText().toString(), dueTime);

        Log.d("medicationName onCreate", newAlert.getMedication());

        SQLiteDatabase mydatabase = openOrCreateDatabase("users",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS alerts(medicationName VARCHAR, dueTime  TIME);");

        ContentValues values = new ContentValues();
        values.put("medicationName", newAlert.getMedication());
        values.put("dueTime",newAlert.getDueTime().toString());
        long rowId = mydatabase.insert("alerts", null, values);

        Log.d("Alertrow inserted", Long.toString(rowId));
//        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
//        i.putExtra(AlarmClock.EXTRA_HOUR, hour + Integer.parseInt(hour));
//        i.putExtra(AlarmClock.EXTRA_MINUTES, minutes + Integer.parseInt(minutes));
//        startActivity(i);

        AlarmManager newAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this,speakerRequest.class);
        i.putExtra("medication",newAlert.getMedication());
        PendingIntent newPendingIntent = PendingIntent.getActivity(this,0,i,0);

        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);

        Log.d("DateOut",today.toString());

        newAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,today.toEpochSecond(ZoneOffset.UTC) + ((timePicker.getMinute() + timePicker.getHour()*60)*60000),24L*60L*60L*1000L,newPendingIntent);


        //startActivity(i);










        Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
        i2.putExtra("Alert status","Success");
        startActivity(i2);
    }


}
