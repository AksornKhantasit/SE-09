package com.se9project.se_9_project;

/**
 * Created by jenny_000 on 11/17/2016.
 */

import android.provider.BaseColumns;

public class Friend {

    //Database
    public static final String DATABASE_NAME = "table.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "friend";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
    }
    public int id;
    public String username;
    public String password;
    public String email;

    //Default Constructor
    public Friend() {

    }
    //Constructor
    public Friend(String user, String pass,
                  String email) {

//        this.id = id;
        this.username = user;
        this.password = pass;
        this.email = email;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getcolumn(){return BaseColumns._ID;}
    public String getDatabaseName(){return DATABASE_NAME;}

}