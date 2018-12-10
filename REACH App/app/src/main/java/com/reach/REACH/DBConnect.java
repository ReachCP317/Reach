package com.reach.REACH;

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
    private PreparedStatement ps;

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
     *
     * @author michaelpintur
     *
     * @sqa JuliusFan
     */
    public ArrayList<Integer> closeLocation(double lat, double lon, double condition) {
        String query;
        double lonHigh =(lon + condition);
        double lonLow = (lon - condition);
        double latHigh  = (lat + condition);
        double latLow = (lat - condition);
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

        try {
            ps = con.prepareStatement("SELECT * from event where (longitude BETWEEN ? AND ?) and latitude between ? and ? and endDate > CURDATE()");
            ps.setDouble(1, lonLow);
            ps.setDouble(2, lonHigh);
            ps.setDouble(3, latLow);
            ps.setDouble(4, latHigh);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<Integer> events = new ArrayList<Integer>(eventCounted);
        try {
            rs = ps.executeQuery();
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
     * SHA2 and auto generates userID. No null items allows
     *
     * @param email - WLU email of the user (maximum of 50 character)
     * @param userName - desired user name of the user: (maximum of 50 characters)
     * @param password - password that the user wishes to create account with
     *
     * @author michaelpintur
     */
    public void createUser(String email, String userName, String password) {
        try {
            ps = con.prepareStatement("INSERT INTO user (email, pwd, userName) VALUES (?,?,?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ps.execute();
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
     * @param capacity - the capacity of the event
     *
     * @author michaelpintur
     * @sqa JuliusFan
     *
     */
    public void createEvent(String email, String desc, double lon, double lat, String start, String end, String address, int capacity ) {
        int userID = getUserID(email);
        try {
            ps = con.prepareStatement("INSERT INTO event (hostID, address, description, latitude, longitude, startDate, endDate, capacity)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1,  userID);
            ps.setString(2, address);
            ps.setString(3, desc);
            ps.setDouble(4, lat);
            ps.setDouble(5, lon);
            ps.setString(6, start);
            ps.setString(7, end);
            ps.setInt(8, capacity);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(ps.toString());
        try {
            ps.execute();
        }catch (Exception ex) {
            System.out.println("Error: "+ ex);
        }
    }


    /**
     * Returns a result set from the database view v_event_name when given an event ID.
     * @param eventID - Possibly from an ArrayList of event ID's
     * @author michaelpintur
     * @SQA kobilee - need prepared statemnt
     */
    public void queryEvent(int eventID) {
        String query = null;
        query = String.format("SELECT * FROM v_event_name WHERE eventID = %d",eventID);
        int lon = 0;
        int lat = 0;
        String address;
        String startDate;
        String endDate;



        try {
            rs = st.executeQuery(query);
            while (rs.next()) {
                lon = rs.getInt("longitude");
                lat = rs.getInt("latitude");
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
     * @author michaelpintur
     * @SQA kobilee - need prepared statemnt
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
     * Updates users password into the database, the database automatically hashes this string to sha256
     *
     * @param userID User's ID
     * @param currentPass Users current password to check against db
     * @param newPass Users new password
     * @author michaelpintur
     *
     * 	 * @return -1 if no result set 0- if hash code does not match database hash code 1- if hash codes match
     *
     */
    public int changePassword(int userID, String currentPass, String newPass) {
        if (passwordChecker(userID, currentPass) == 1) {
            try {
                ps = con.prepareStatement("UPDATE user SET pwd = ? where userID = ?");
                ps.setString(1, newPass);
                ps.setInt(2, userID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Password changed.");
            return 1;
        } else if (passwordChecker(userID, currentPass) == -1) {
            System.out.println("User does not exist");
            return -1;
        } else {
            System.out.println("Incorrect Current Password");
            return 0;

        }
    }


    /**
     *
     * Method will hash the given password to SHA-256 then will compare hash codes with the database to verify the passwords are correct.
     *
     * @param email
     * @param password
     * @return -1 if no result set 0- if hash code does not match database hash code 1- if hash codes match
     *
     * @author michaelpintur
     */
    public int passwordChecker(int userID, String password) {
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

        //String query = String.format("SELECT pwd FROM user WHERE email = '%s'", email);
        try {
            ps = con.prepareStatement("SELECT pwd FROM user WHERE userID = ?");
            ps.setInt(1, userID);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            rs= ps.executeQuery();
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

    /**
     * Debugging to show all users in database
     * @author michaelpintur
     */
    public void displayUsers() {
        String userQuery = "SELECT * from user";
        try {
            rs = st.executeQuery(userQuery);
            System.out.println("ALL USERS IN TABLE");
            while (rs.next()) {

                System.out.print("UserID: " + rs.getInt("userID") + " ");
                System.out.print("Email: " + rs.getString("email") + " ");
                System.out.print("pwd: " + rs.getString("pwd") + " ");
                System.out.print("userName: " + rs.getString("userName") + " ");
                System.out.println();
            }
        } catch (Exception ex) {
            System.out.println("Error: "+ ex);
        }
    }


    /**
     * Debugging to show all events in database
     * @author michaelpintur
     */
    public void displayEvents() {
        String eventQuery = "SELECT * from event";
        try {
            rs = st.executeQuery(eventQuery);
            System.out.println("ALL EVENTS IN TABLE");

            while (rs.next()) {
                System.out.print("EventID: " + rs.getInt("eventID") + " ");
                System.out.print("HostID: " + rs.getInt("hostID") + " ");
                System.out.print("Address: " + rs.getString("address") + " ");
                System.out.print("Desc: " + rs.getString("description") + " ");
                System.out.print("Lat: " + rs.getInt("latitude") + " ");
                System.out.print("Lat: " + rs.getInt("longitude") + " ");
                System.out.print("startDate: " + rs.getString("startDate") + " ");
                System.out.print("endDate: " + rs.getString("endDate") + " ");
                System.out.print("Capcity: " + rs.getInt("capacity") + " ");

                System.out.println();
            }
        } catch (Exception ex) {
            System.out.println("Error: "+ ex);
        }
    }

    /**
     * Method returns void on default
     * Method to fetch what email, pwd(hashed), icon (directory to image), username  when given a userID
     * @param userID Users ID number
     *
     *
     * @author Jordan Harris
     * @sqa Michael Pintur
     * 2018/12/09
     */
    public void queryUser(int userID)
    {
        try {
            ps = con.prepareStatement("SELECT * FROM user WHERE userID = ?");
            ps.setInt(1,userID);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String email;
        String pwd;
        String icon;
        String userName = null;
        try
        {
            rs = ps.executeQuery();
            while (rs.next())
            {
                email = rs.getString("email");
                pwd  = rs.getString("pwd");
                icon = rs.getString("icon");
                userName = rs.getString("userName");
            }
            System.out.println(userID + " " + userName);
        }
        catch (Exception ex)
        {
            System.out.println("Error: "+ ex);
        }

    }



}
