package com.batch.mcs.finalproject.helperobjects;

public class SelectDate {

    private int Month;
    private int Day;
    private int Year;

    public SelectDate(int year, int month, int day) {
        Month = month;
        Day = day;
        Year = year;
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
}
