package com.example.fitsho;

public class Report_Today {

    int id;
    String calorie;
    String speed;
    String distance;
    String hour;

    public Report_Today(int id, String calorie, String speed, String distance, String hour) {
        this.id = id;
        this.calorie = calorie;
        this.speed = speed;
        this.distance = distance;
        this.hour = hour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
