package com.example.nine.mine.myUtil;

public class getNetDate {
    private String netdate;
    private String day;
    public static getNetDate getNetDate = new getNetDate();
    private getNetDate(){

    }

    public static getNetDate getInstance(){
        return getNetDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNetdate() {
        return netdate;
    }

    public void setNetdate(String netdate) {
        this.netdate = netdate;
    }
}

