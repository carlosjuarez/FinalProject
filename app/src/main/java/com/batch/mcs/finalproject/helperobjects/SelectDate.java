package com.batch.mcs.finalproject.helperobjects;

public class SelectDate {

    private int Month;
    private int Day;
    private int Year;
    private String Date;

    public SelectDate(int year, int month, int day, String date) {
        Month = month;
        Day = day;
        Year = year;
        Date = date;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
