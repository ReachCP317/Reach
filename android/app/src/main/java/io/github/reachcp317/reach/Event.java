package io.github.reachcp317.reach;

import android.location.Location;

import java.io.Serializable;
import java.util.Date;

public class Event {
    private String name;
    private String description;
    private String address;
    private Date startTime;
    private Date endTime;
    private int totalInterested;
    private Location location;

    //Constructor
    public Event(String name, String description, String address, Date start, Date end, double latitude, double longitude, int totalInt) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.location = new Location("");
        this.location.setLatitude(latitude);
        this.location.setLongitude(longitude);
        this.startTime = start;
        this.endTime = end;
        this.totalInterested = totalInt;
    }

    public Event(){

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

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Location getLocation() {
        return location;
    }

    public int getTotalInterested(){
        return this.totalInterested;
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

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setTotalInterested(int totalInterested){
        this.totalInterested = totalInterested;
    }


}
