package com.example.bostonhacks2019;

import android.provider.AlarmClock;

public class alert {

    private String medication;
    private AlarmClock dueTime;


    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public AlarmClock getDueTime() {
        return dueTime;
    }

    public void setDueTime(AlarmClock dueTime) {
        this.dueTime = dueTime;
    }
}
