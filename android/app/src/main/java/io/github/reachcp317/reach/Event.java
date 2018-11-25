package io.github.reachcp317.reach;

import android.location.Location;

import java.util.Date;

public class Event {
    private String name;
    private String description;
    private String address;
    private Location location;
    private Date startTime;
    private Date endTime;
    private int totalInterested;

    //Constructor
    public Event(String name, String description, String address, Location location, Date startTime, Date endTime) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalInterested = 0;
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

    public Location getLocation() {
        return location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getTotalInterested() {
        return totalInterested;
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

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setTotalInterested(int totalInterested) {
        this.totalInterested = totalInterested;
    }
}
