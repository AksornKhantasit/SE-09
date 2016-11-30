package com.se9project.se_9_project;


public class Bus {
    public static final String DATABASE_NAME = "com.se9project.se_9_project.Bus.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "Bus_table";

    public class Column {
        public static final String HOUR = "hour";
        public static final String MINUTE = "minute";
        public static final String NUMBER = "bus_number";
        public static final String AMOUNT = "amount";
    }
    private int hour,minute;
    private int number;
    private int amount;
    public Bus(){

    }
    public Bus(int hour, int minute, int num) {
        super();
        this.hour = hour;
        this.minute = minute;
        this.number = num;
        this.amount = 0;
    }

    public String getHour() {
        return ""+hour;
    }
    public String getMinute() {
        return ""+minute;
    }
    public String getNumber() {
        return ""+number;
    }
    public String getAmount() {
        return ""+amount;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }
    public void setNumber(int num) {
        this.number = num;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void addAmount() {
        this.amount++;
    }
}
