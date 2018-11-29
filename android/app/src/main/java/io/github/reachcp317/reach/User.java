package io.github.reachcp317.reach;

import java.util.Date;
import java.time.LocalDate

public class User {
	
	private int id;
	private float radius;
	private float rating;
	private String name;
	private Date date_of_birth;

	public User(String name, Date date_of_birth, float radius) {
		// this.id = // needs to be assigned by the application/database
		this.radius = radius;
		this.rating = 0.0;
		this.name = name;
		this.date_of_birth = date_of_birth;
	}
	
	public int getID() {
		return this.id;
	}
	
	public float getRadius() {
		return this.radius;
	}
	
	public float getRating() {
		return this.rating;
	}
	
	public int getAge() {
		LocalDate birthDate = this.date_of_birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();		
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	public void setRating(float rating) {
		// not sure how we want to implement this
	}
}