package com.reachcp317.reach;

import org.springframework.dao.EmptyResultDataAccessException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

/**
 * 
 * @author Michael Child-Wynne-Jones
 * 
 *	chil5440@mylaurier.ca
 *
 *		This is a basic unit-testing class to test the UserRepository class methods
 *	
 */
@SuppressWarnings("unused")
public class UserRepositoryTest {
	public User user;
	public UserRepository uRep;
	
	@BeforeTest
	public void setUp() {
		uRep = new UserRepository();
	}
	
	@Test(expectedExceptions = EmptyResultDataAccessException.class)
	public void getByID_throwsException_withBadInput() {
		int id = -1;
		user = uRep.getById(id);		
	}
	
	@Test
	public void verifyLogin_detects_non_user() {
		//user = new User(0,"none","none","_");
		user = new User(0,"noUserName","noEmail","password");
		
		int check = uRep.verifyLogin(user);
		
		Assert.assertTrue(check == -1);
	}//*/
	
	@Test(expectedExceptions = EmptyResultDataAccessException.class)
	public void verifyLogin_throwsException_withBadInput() {
		//user = new User(0,"none","none","_");
		user = null;
		
		int check = uRep.verifyLogin(user);
		
		
		//Assert.assertTrue(check == -1);
	}//*/
	
	@Test
	public void createUser_Returns_Success() {
		user = new User(0,"test name", "test@email.com", "testPassword");
		
		//Boolean condition = uRep.createUser(user);
		
		//Assert.assertTrue(condition);
	}
	
	@AfterTest
	public void tearDown() {
		uRep = null;
	}
}
