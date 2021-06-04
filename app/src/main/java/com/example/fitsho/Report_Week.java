package com.example.fitsho;

public class Report_Week {


    int id;
    String name_day;
    String date;
    String calorie;
    String speed;
    String distane;

    public Report_Week(int id, String name_day, String date, String calorie, String speed, String distane) {
        this.id = id;
        this.name_day = name_day;
        this.date = date;
        this.calorie = calorie;
        this.speed = speed;
        this.distane = distane;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_day() {
        return name_day;
    }

    public void setName_day(String name_day) {
        this.name_day = name_day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDistane() {
        return distane;
    }

    public void setDistane(String distane) {
        this.distane = distane;
    }
}
