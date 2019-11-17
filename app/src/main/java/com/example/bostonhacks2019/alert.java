package com.example.bostonhacks2019;

import android.provider.AlarmClock;

public class alert {

    private String medication;
    private String dueTime;

    public alert(String medication, String dueTime) {
        this.medication = medication;
        this.dueTime = dueTime;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }
}
