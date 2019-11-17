package com.example.bostonhacks2019;

import android.provider.AlarmClock;

import java.sql.Time;

public class alert {

    private String medication;
    private Time dueTime;

    public alert(String medication, Time dueTime) {
        this.medication = medication;
        this.dueTime = dueTime;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public Time getDueTime() {
        return dueTime;
    }

    public void setDueTime(Time dueTime) {
        this.dueTime = dueTime;
    }
}
