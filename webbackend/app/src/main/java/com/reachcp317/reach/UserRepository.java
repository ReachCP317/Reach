package com.reachcp317.reach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

/**
 * Retrieves/updates User information through a MySQL database
 * @author Morgenne Besenschek
 * For more documentation see https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/jdbc.html
 */

@Repository
public class UserRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Populates a User object with data; database test
	 * @author Morgenne Besenschek
	 *
	 */
	public static final class UserMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User(rs.getInt("userID"));
			user.setUsername(rs.getString("userName"));
			user.setPassword(rs.getString("pwd"));
			user.setEmail(rs.getString("email"));
			return user;
		}
		
	}
	
	/**
	 * Database test method
	 * @return
	 */
	public List<User> viewAllUsernames(){
		return this.jdbcTemplate.query("SELECT * FROM user", new UserMapper());
	}

	/**
	 * Returns User data given an id number, if the User exists.
	 * @param id
	 * @return
	 */
	public User getById(int id) {
		User user = this.jdbcTemplate.queryForObject("SELECT * FROM user WHERE userID = ?", new Object[] {id}, new UserMapper());
		//User user = this.jdbcTemplate.query("SELECT * FROM user WHERE userID = ?", new Object[] {id}, new UserMapper());
		
		return user;
	}
	
	public String getPasswordTest() {
		return this.jdbcTemplate.queryForObject("SELECT pwd FROM user WHERE userName = ?",
				new Object[] {"TheFunk"}, String.class);
	}
	
	public int verifyLogin(User user) {
		int valid = 0;
		
		boolean validUsername = verifyUsername(user.getUsername());
		
		if (!validUsername) {
			//valid = ?;
		}else {
			
		}
		
		return valid;
	}
	
	private boolean verifyUsername(String username) {
		boolean valid = true;
		
		int id = this.jdbcTemplate.queryForObject("SELECT id FROM user WHERE userName = ?",
				new Object[] {username}, Integer.class);
		
		if (id == -1) {
			
		}
		
		return valid;
	}
	
	private boolean verifyPassword(String password) {
		boolean valid = true;
		
		return valid;
	}
	
	public int createUser() {
		
		return 0;
	}
}
