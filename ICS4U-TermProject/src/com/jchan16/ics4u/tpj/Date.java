/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ics4u;

/**
 *
 * @author Jackie
 */
public class Date {
private String day;
private String month;
private String year;

public Date(String t){
setDate(t);    
}

public void setDate(String t){
int stop= t.indexOf("/");
int stop2=t.indexOf("/",stop);
int stop3=t.lastIndexOf("/");

String day=t.substring(0,stop);
String month=t.substring(stop+1,stop2);
String year=t.substring(stop3+1,t.length());
}
public String showDay(){
    return day;
}
public String showMonth(){
    return month;
}
public String showYear(){
    return year;
}
}
