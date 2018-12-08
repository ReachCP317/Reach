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
 *
 */

@Repository
public class UserRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Database test method
	 * @return
	 */
	public List<User> viewAllUsernames(){
		return this.jdbcTemplate.query("SELECT userName FROM user", new UserMapper());
	}
	
	/**
	 * Populates List with User objects
	 * @author Morgenne Besenschek
	 *
	 */
	public static final class UserMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUsername(rs.getString("userName"));
			return user;
		}
		
	}
	
	public String getPasswordTest() {
		return this.jdbcTemplate.queryForObject("SELECT pwd FROM user WHERE userName = ?", new Object[] {"TheFunk"}, String.class);
	}
	
	public int verifyLogin() {
		
		return 0;
	}
}
