package com.reach.REACH;

import android.location.Location;

public class Event {
    private String name;
    private String description;
    private String address;
    private String startTime;
    private String endTime;
    private int capacity;
    private Location location;
    private int ageReq;
    private int days_left;

    //Constructor
    public Event Events[];
    public int index= 0;
    public Event(String name, String address, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.location = new Location("");
        this.location.setLatitude(latitude);
        this.location.setLongitude(longitude);
        this.days_left = 0;
        Events[index] = this;
        index++;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Location getLocation() {
        return location;
    }


    public int getCapacity() {
        return this.capacity;
    }
    public int getDays() {
        return this.days_left;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setAgeReq(int age) {
        this.ageReq = age;
    }

    public void setDays(int days) {
        this.days_left = days;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}