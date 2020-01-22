/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan16.ics4u.tpj1;

/**
 *
 * @author Jackie
 */
public class Date {

    private String day;
    private String month;
    private String year;

    public Date(String t) {
        setDate(t);
    }

    public void setDate(String t) {
        int stop = t.indexOf("/");
        int stop3 = t.lastIndexOf("/");

        String day1 = t.substring(0, stop);

        String month1 = t.substring(stop + 1, stop3);

        String year1 = t.substring(stop3+1 , t.length());
        
        day=day1;
        month=month1;
        year=year1;
    }

    public String showDay() {
        return day;
    }

    public String showMonth() {
        return month;
    }

    public String showYear() {
        return year;
    }
}
