package com.reachcp317.reach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;

/**
 * Retrieves/updates User information through a MySQL database
 * @author Morgenne Besenschek
 * For more documentation see https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/jdbc.html
 * Note: JdbcTemplate methods will always create a PreparedStatement, even if it is only given an SQL String.
 * See: https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
 */

@Repository
public class UserRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Populates a User object with data. Does not map a User password; if password is needed, 
	 * UserMapperWithPassword is called.
	 * @author Morgenne Besenschek
	 *
	 */
	public static class UserMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User(rs.getInt("userID"));
			user.setUsername(rs.getString("userName"));
			user.setEmail(rs.getString("email"));
			return user;
		}
		
	}
	
	/**
	 * Populates a User object with data, including a User password; 
	 * @author Morgenne Besenschek
	 *
	 */
	public static final class UserMapperWithPassword extends UserMapper{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = super.mapRow(rs, rowNum);
			user.setPassword(rs.getString("pwd"));
			return user;
		}
		
	}
	
	/**
	 * Database test method
	 * @return
	 */
	public List<User> viewAllUsernames(){
		return this.jdbcTemplate.query("SELECT * FROM user", new UserMapperWithPassword());
	}

	/**
	 * Returns User data given an id number, if the User exists.
	 * @param id
	 * @return
	 */
	public User getById(int id) {
		User user;
		try {
			user = this.jdbcTemplate.queryForObject("SELECT * FROM user WHERE userID = ?",
				new Object[] {id}, new UserMapper());
		} catch (EmptyResultDataAccessException e){
			//User by given ID does not exist
			user = null;
		}
		
		return user;		
	}
	
	public String getPasswordTest() {
		return this.jdbcTemplate.queryForObject("SELECT pwd FROM user WHERE userName = ?",
				new Object[] {"TheFunk"}, String.class);
	}
	
	/**
	 * User login verification with the database
	 * @param user
	 * @return User's ID if login is valid, error code 0 (incorrect password) or -1 (User does not exist) otherwise
	 */
	public int verifyLogin(User user) {
		User login;
		int valid = 1;
		
		try {
			login = this.jdbcTemplate.queryForObject("SELECT * FROM user WHERE userName = ?",
					new Object[] {user.getUsername()}, new UserMapperWithPassword());
			
			if (user.getPassword().compareTo(login.getPassword()) != 0) {
					valid = 0;
			}else {
				valid = login.getID();
			}
			
		}catch (EmptyResultDataAccessException e) {
			valid = -1;
		}
		
		return valid;
	}
	
	public int createUser(User user) {
		int success = 0;
		int update = 0;
		
		try {
			this.jdbcTemplate.queryForObject("SELECT email FROM user WHERE userName = ?"
					, new Object[] {user.getUsername()}, String.class);
		}catch (EmptyResultDataAccessException e) {
			//returns 1 if a row in the database has been updated, 0 otherwise
			update = this.jdbcTemplate.update("INSERT INTO user (email, pwd, userName) VALUES(?, ?, ?)",
					new Object[] {user.getEmail(), user.getPassword(), user.getUsername()});
		}finally {
			//if no change was made to the database, user creation failed; otherwise, retrieve new User ID
			if (update == 1) {
				success = getID(user);
			}else {
				success = -1;
			}
		}
		
		return success;
	}
	
	/**
	 * Updates User password and email
	 * @param user
	 * @param id
	 * @return
	 */
	public boolean updateUser(User user, int id) {
		boolean success = true;
		int update = 0;
		try {
			update = this.jdbcTemplate.update("UPDATE user SET email = ?, pwd = ? WHERE userID = ? AND pwd = ?",
					new Object[] {user.getEmail(), user.getPasswordUpdate(), id, user.getPassword()});
		}catch (EmptyResultDataAccessException e) {
			success = false;
		}finally {
			if (update == 0) {
				success = false;
			}
		}
		return success;
	}
	
	/**
	 * Retrieves userID for a newly created User
	 * @param user
	 * @return
	 */
	private int getID(User user) {
		int id = this.jdbcTemplate.queryForObject("SELECT userID FROM user WHERE userName = ?"
				, new Object[] {user.getUsername()}, Integer.class);
		
		return id;
	}
}
