package com.reach.REACH;

import java.util.ArrayList;

public class MainDB {


    public static void main(String args[]) {
        DBConnect connect = new DBConnect();
        //connect.getData();
        //connect.createUser("buddy@rogers.ca", "Will", "Smith", "password123");
        //System.out.println(connect.getUserID("roger@rogers.ca"));
        //connect.createEvent("buddy@rogers.ca", "WLU Party3", 434, 100, "1979-01-01 00:00:01", "1979-01-01 00:00:01", "1235 Steet");
        //System.out.println(connect.passwordChecker("buddy@rogers.ca", "password1233"));

        ArrayList<Integer> events = null;
        events = connect.closeLocation(50, 200, 0.5);
        int count =0;
        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i));
            connect.queryEvent(events.get(i));
        }
    }




}