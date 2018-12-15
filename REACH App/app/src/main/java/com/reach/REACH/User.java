package com.reach.REACH;
public class User {

    private String name;
    private String username;
    private String password;
    public User Users[];
    public int index = 0;
    public User(String Username, String Password){
        this.username = Username;
        this.password = Password;
        Users[index] = this;
        index++;
    }

    //Getters
    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String Username) {
        this.username = Username;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }
}