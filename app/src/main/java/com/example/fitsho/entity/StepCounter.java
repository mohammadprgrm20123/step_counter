package com.example.fitsho.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class StepCounter {

    @PrimaryKey(autoGenerate = true)
    int id;

    String userId;
    double distance;
    double calories;
    String date;
    String hour;


    public StepCounter(String userId, double distance, double calories, String date, String hour) {
        this.userId = userId;
        this.distance = distance;
        this.calories = calories;
        this.date = date;
        this.hour = hour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
