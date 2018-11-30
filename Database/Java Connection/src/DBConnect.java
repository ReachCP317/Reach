import java.sql.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @author Michael Pintur
 * pint5070@mylaurier.ca
 *
 */


public class DBConnect {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	/**
	 * Constructor to connect to the MYSQL database
	 * 
	 */
	public DBConnect () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://reachdb.mysql.database.azure.com:3306/reachdb?useSSL=true&requireSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
			con = DriverManager.getConnection(url, "reachAndroid@reachdb", "reachWLU@");
			
			st = con.createStatement();
			System.out.println("Successful Connection to Database");
		} catch(Exception ex) {
			System.out.println("Error: "+ ex);
		}
	}
	
	
	
	

	/**
	 * 
	 * Returns an ArrayList of EventIds that are within a condition of the users current Latitude and Longitude. 
	 * 
	 * @param lat - Users current latitude
	 * @param lon  - Users current longitude 
	 * @param condition - Specified range for comparison (i.e 0.5) 
	 * @return ArrayList of eventIds within the specified range. 
	 */
	public ArrayList<Integer> closeLocation(int lat, int lon, double condition) {
		String query;
		int lonHigh = (int) (lon + condition);
		int lonLow = (int) (lon - condition);
		int latHigh  = (int) (lat + condition);
		int latLow = (int) (lat - condition);
		//First query statement to get number of events in database
		String eventCount = "SELECT COUNT(1) FROM event";
		//eventCounted to hold number of events counted from first query
		int eventCounted = 0;
		
		
		//Separate query to get number of events to find ArrayList size
		try {
			rs = st.executeQuery(eventCount);
			while (rs.next()) {
				eventCounted = rs.getInt(1);
				//System.out.println(rs.getInt(1));
			}
		} catch (Exception ex) {
			System.out.println("Error: "+ ex);
		}
		
		query = String.format("SELECT * from event where (longitude BETWEEN %d AND %d) and latitude between %d and %d", lonLow, lonHigh, latLow, latHigh);
		ArrayList<Integer> events = new ArrayList<Integer>(eventCounted);
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				//System.out.println("EventID: " + rs.getInt("eventID"));
				events.add(rs.getInt("eventID"));
				/**
				 * rs.getInt("longitude");
				 * rs.getInt("latitude");

				 */
			}
		} catch (Exception ex) {
			System.out.println("Error: "+ ex);
		}
		
		
		
		return events;
		
	}
	
	
	/*
	 * Inserts a user into the user database table. The database automatically encrypts the password to 
	 * SHA2 and auto generates userID
	 * 
	 * @param email - WLU email of the user 
	 * @param fName - first name of the user
	 * @param lName - last name of the user
	 * @param password - password that the user wishes to create account with
	 * 
	 */
	public void createUser(String email, String fName, String lName, String password) {
		String name = fName + " "+ lName;
		String insertUser;
		insertUser = String.format("INSERT INTO user (email, pwd, name) VALUES ('%s','%s','%s')", email, password, name );
		try {
			st.execute(insertUser);
		} catch(Exception ex) {
			System.out.println("Error: "+ ex);

		}
	}
	
	
	/*
	 * createEvent into event table
	 * 
	 * event table fields -
	 * 
	 * eventID, hostID, address, description, latitude, longitude, startDate, endDate
	 * 
	 * @param email - host email
	 * @param desc - description of event
	 * @param lon - longitude coordinate
	 * @param lat - latitude coordinate
	 * @param start - start date of the event
	 * @param end - end date of the event
	 * @param address - address of the event
	 */
	public void createEvent(String email, String desc, int lon, int lat, String start, String end, String address ) {
		int userID = getUserID(email);
		String insertEvent;
		//insertEvent = String.format("INSERT INTO event (eventID, desc, longitude, latitude, startDate, endDate, address)"
		//		+ " VALUES (%d, '%s', %d, %d, '%s', '%s', '%s')", userID, desc, lon, lat, start, end, address);
		insertEvent = String.format("INSERT INTO event (hostID, address, description, latitude, longitude, startDate, endDate)"
				+ " VALUES (%d, '%s', '%s', %d, %d, '%s', '%s')", userID, "address", desc,lat, lon, start, end);
		System.out.println(insertEvent);
		try {
			st.execute(insertEvent);
		}catch (Exception ex) {
			System.out.println("Error: "+ ex);
		}
	}
	
	
	/**
	 * Returns a result set from the database view v_event_name when given an event ID.
	 * @param eventID - Possibly from an ArrayList of event ID's
	 */
	public void queryEvent(int eventID) {
		String query = null;
		query = String.format("SELECT * FROM v_event_name WHERE eventID = %d",eventID);
		int lon = 0;
		int lat = 0;
		String hostName = null;
		String address;
		String startDate;
		String endDate;
		
		
		
		try {
			rs = st.executeQuery(query);
			while (rs.next()) {
				lon = rs.getInt("longitude");
				lat = rs.getInt("latitude");
				hostName = rs.getString("hostName");
				address = rs.getString("address");
				startDate = rs.getString("startDate");
				endDate = rs.getString("endDate");
			}
			System.out.println(eventID + " " + hostName);
		} catch (Exception ex) {
			System.out.println("Error: "+ ex);
		}
		
	}

	
	
	/**
	 * fetches userID from user table when given email as a parameter
	 * @param email - user email
	 * @return - returns the unique userID from the given email address.
	 */
	public int getUserID(String email) {
		int userID = 0;
		String query = String.format("SELECT userID FROM user"
				+ " WHERE email = '%s'", email);
		try {
			rs = st.executeQuery(query);
			while(rs.next()) {
				userID = rs.getInt("userID");
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		
			return userID;
	}
	
	/**
	 * 
	 * Method will hash the given password to SHA-256 then will compare hash codes with the database to verify the passwords are correct.
	 * 
	 * @param email
	 * @param password 
	 * @return -1 if no result set 0- if hash code does not match database hash code 1- if hash codes match
	 */
	public int passwordChecker(String email, String password) {
		MessageDigest mDigest;
		String hashCode ="";
		StringBuffer sb = new StringBuffer();
		try {
			mDigest = MessageDigest.getInstance("SHA-256");
			byte[] result = mDigest.digest(password.getBytes());
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String query = String.format("SELECT pwd FROM user WHERE email = '%s'", email);
		try {
			rs= st.executeQuery(query);
			if (rs.next()) {
				hashCode = rs.getString("pwd");
			} else {
				return -1;
			}
		} catch (Exception ex) {
			System.out.println("Error: "+ ex);
		}

		if (hashCode.equals(sb.toString())) {
			return 1;
		}
		
		return 0;	
	}
	
	
  }


