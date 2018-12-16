package io.github.reachcp317.reach;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;

public class User {

    private int id;
    private int number_ratings;
    private float radius;
    private float rating;
    private String name;
    private Date date_of_birth;

    public User(String name, Date date_of_birth, float radius) {

        this.radius = radius;
        this.rating = 0;
        this.name = name;
        this.date_of_birth = date_of_birth;
    }

    /**
     * Gets the user's ID
     *
     * @return userID The user's ID number
     * @author Dustin Dugal
     */
    public int getID() {
        return this.id;
    }


    /**
     * Gets the user's current rating
     *
     * @return rating The user's current rating
     * @author Dustin Dugal
     * @SQA Ali Shahid
     */
    public float getRating() {
        return this.rating;
    }

    /**
     * Gets the user's age
     *
     * @return The user's age
     * @author Dustin Dugal
     * @SQA Ali Shahid
     */
    public int getAge() {
        LocalDate birthDate = this.date_of_birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    /**
     * Sets the user's rating
     *
     * @param rating The current average rating of the user
     * @param number_ratings The number of ratings the user has received
     *
     * @return rating The updated average rating of the user
     * @author Ali Shahid
     */
    public float setRating(float rating, int number_ratings) {
        float temp = this.rating * this.number_ratings;
        rating = this.rating / (this.number_ratings+1);
        this.number_ratings++;
        return rating;
    }
}