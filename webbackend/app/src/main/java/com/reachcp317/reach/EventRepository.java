package com.reachcp317.reach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.reachcp317.reach.UserRepository.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;

/**
 * Retrieves/updates User information through a MySQL database
 * @author James Robertson
 * For more documentation see https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/jdbc.html
 */

@Repository
public class EventRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Populates an Event object with data; database test
	 * @author James Robertson
	 *
	 */
	public static final class EventMapper implements RowMapper<Event>{

		@Override
		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
			Event event = new Event(rs.getInt("eventID"), rs.getInt("hostID"));
			event.setName(rs.getString("eventName"));
			//event.setTotallnterested(rs.getInt("totalInterested"));
			//event.setEventType(rs.getString("eventType"));
			event.setAddress(rs.getString("address"));
			event.setDescription(rs.getString("description"));
			event.setLatitude(rs.getDouble("latitude"));
			event.setLongitude(rs.getDouble("longitude"));
			event.setStartTime(rs.getString("startDate"));
			event.setEndTime(rs.getString("endDate"));
			event.setCapacity(rs.getInt("capacity"));
			//event.setEventRange(rs.getInt("eventRange"));
			return event;		
		}
	}

	/**
	 * Database test method
	 * @return
	 */
	public List<Event> viewAllEventnames(){
		return this.jdbcTemplate.query("SELECT * FROM event", new EventMapper());
	}

	/**
	 * Returns Event data given an Event ID number, if the Event exists.
	 * @param id
	 * @return
	 */
	public Event getById(int id) {
		Event event;

		try {
			event = this.jdbcTemplate.queryForObject("SELECT * FROM event WHERE eventID = ?", new Object[] {id}, new EventMapper());
		} catch (EmptyResultDataAccessException e) {
			//Event by given ID does not exist
			event = null;
		}
		return event;
	}
	
	/**
	 * Returns a list of Events hosted by a particular User
	 * @return
	 */
	public List<Event> getByHostID(int id){
		return this.jdbcTemplate.query("SELECT * FROM event WHERE hostID = ?", new Object[] {id}, new EventMapper());
	}
	
	/**
	 * Obtains the most recent event that a user has created that is still active
	 */
	public Event getCurrentUserEvent(int userID) {
		Event event;
		
		try{
			event = this.jdbcTemplate.queryForObject("SELECT * FROM event WHERE hostID = ? AND endDate > CURDATE()",
				new Object[] {userID}, new EventMapper());
		}catch (EmptyResultDataAccessException e) {
			event = null;
		}
		return event;
	}
	
	/**
	 * Event creation
	 * @param event
	 * @return
	 */
	public int createEvent(Event event, int hostID) {
		int success = 0;
		int update = 0;
		
		try {
		update = this.jdbcTemplate.update("INSERT INTO event (hostID, eventName, address, description, latitude, longitude, startDate, endDate, capacity)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] {hostID, event.getName(), event.getAddress(), event.getDescription(),
						event.getLatitude(), event.getLongitude(),
						event.getStartTime(), event.getEndTime(), event.getCapacity()});
		}catch (Exception e) {
			//error in data User submitted
			update = 0;
		}

		if (update == 1) {
			success = 1;
		}else {
			success = -1;
		}
		
		return success;
	}
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	//UPDATE event SET address = ?, description = ?, 
	//WHERE eventID = 
	public boolean updateEvent(Event event, int userID) {
		boolean success = true;
		int update;
		//try {
			update = this.jdbcTemplate.update("UPDATE event SET address = ?, eventName = ?, description = ?, latitude = ?, longitude = ?,"
					+ "startDate = ?, endDate = ?, capacity = ? WHERE hostID = ? AND endDate > CURDATE()",
					new Object[] {event.getAddress(), event.getName(), event.getDescription(),
							event.getLatitude(), event.getLongitude(),
							event.getStartTime(), event.getEndTime(), event.getCapacity(), userID});
			System.out.println("Update: " + update);
		//}catch (Exception e) {
			//success = false;
		//}
		
		return success;
	}
	
	//TODO: add selection by radius
	/**
	 * 
	 * @param radius
	 * @param userLat
	 * @param userLong
	 * @return
	 */
	public List<Event> getMapMarkers(int radius, double userLat, double userLong) {
		
		return this.jdbcTemplate.query("SELECT * FROM event WHERE endDate > CURDATE()", new EventMapper());
	}
}