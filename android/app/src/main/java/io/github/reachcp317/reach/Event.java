package io.github.reachcp317.reach;


 // import statements
import android.location.Location;

import java.util.Date;

/**
 * @author  Joshua Varga
 * @version 0.1
 * @since   0.1
 */
public class Event {
    private String name;
    private String description;
    private String address;
    private Location location;
    private String startTime;
    private String endTime;
    private int totalInterested;

    //Constructor
    public Event() {
        this.name = "default";
        this.description = "default";
        this.address = "default";
        this.location = new Location("");
        this.startTime = "default";
        this.endTime = "default";
        this.totalInterested = 0;
    }

    public Event(String name, String description, String address, Location location, String startTime, String endTime) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalInterested = 0;
    }

    // Getters

    /**
     * Gets the name of the event.
     *
     * @return The name of the event.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the event.
     *
     * @return The description of the event
     */

    public String getDescription() {
        return description;
    }

    /**
     * Gets the address of the event.
     *
     * @return The address of the event.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the location of the event.
     *
     * @return The location of the event.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets the starting time of the event.
     *
     * @return The starting time of the event.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Gets the ending time of the event.
     *
     * @return The ending time of the event.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Gets the total amount of users interested in the event.
     *
     * @return The total amount of users interested in the event.
     */
    public int getTotalInterested() {
        return totalInterested;
    }

    // Setters

    /**
     * Sets the name of the event.
     *
     * @param name The new name of the event.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the event.
     *
     * @param description the new description of the event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the address of the event.
     *
     * @param address the new address of the event.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the location of the event.
     *
     * @param location The new location of the event.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Sets the starting time of the event.
     *
     * @param startTime The new starting time of the event.
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the ending time of the event.
     *
     * @param endTime The new ending time of the event.
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Sets the total amount of users interested in the event.
     *
     * @param totalInterested The new total amount of users interested in the event.
     */
    public void setTotalInterested(int totalInterested) {
        this.totalInterested = totalInterested;
    }
}
