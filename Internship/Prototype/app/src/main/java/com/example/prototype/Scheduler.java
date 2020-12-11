package com.example.prototype;

public class Scheduler {

    private String frequency;
    private String date;

    public Scheduler(String frequency, String date) {
        this.frequency = frequency;
        this.date = date;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String title) {
        this.frequency = frequency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String details) {
        this.date = date;
    }

}
