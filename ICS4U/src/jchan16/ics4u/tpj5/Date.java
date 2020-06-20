/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jchan16.ics4u.tpj5;

/**
 *
 * @author Jackie
 */
public class Date {

    private String day;
    private String month;
    private String year;
    private boolean leap_year;
    private int day2;
    private int month2;
    private int year2;
    private int fullDayValue;
    // each month is 1 minus what they are in the array, ex 0 element is jan, 1 element id feb
    //calender1 is used for leap year calculations
    static final int[] CALENDER = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static final int[] CALENDERL = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Date(String t) {
        setDate(t);
        day2 = Integer.parseInt(day);
        month2 = Integer.parseInt(month);
        year2 = Integer.parseInt(year);
        calculateLeap(year2);
        setFullDay(day2,month2,year2,leap_year);
    }
//substrings string using dashes as guiders to get string of day,month and yeat
    public void setDate(String t) {
        int stop = t.indexOf("/");
        int stop3 = t.lastIndexOf("/");

        String day1 = t.substring(0, stop);

        String month1 = t.substring(stop + 1, stop3);

        String year1 = t.substring(stop3 + 1, t.length());

        day = day1;
        month = month1;
        year = year1;

    }
//sets leap_year variable
    public void calculateLeap(int a) {
        boolean leap=false;
        
        if(a%4==0){
            leap=true;
            //every century is not a leap year
            if(a%100==0){
                leap=false;
                //but every 4th century is a leap year
                if(a%400==0){
                    leap=true;
                }
            }
        }
        leap_year=leap;
    }

    public String showDay() {
        return day;
    }

    public boolean showLeap() {
        return leap_year;
    }

    public String showMonth() {
        return month;
    }

    public String showYear() {
        return year;
    }

    public int showDay2() {
        return day2;
    }

    public int showMonth2() {
        return month2;
    }

    public int showYear2() {
        return year2;
    }
    
    public int showFullDay(){
        return fullDayValue;
    }

    public void setFullDay(int d, int m, int y,boolean f) {

        if(m==1){
            // calculations also include leap year days that passed before 
            //hence the add (y/4) as every 4 years theres a leap year, -(y/100) because 
            //every century doesn't have a leap year but +(y/400 beacuse each century has 
            //a leap year
        int year =  y*365+(y/4)-(y/100)+(y/400) ;
        int day = d;
        int month=0; 
        fullDayValue=year+month+day;
        }
        
        if(m>1){
        int year =  y*365+(y/4)-(y/100)+(y/400) ;
        int day = d;
        int month=0;
        //leap year
        if(f==true){
         for (int i = 0; i < m-1; i++) {
                month += CALENDERL[i]; 
            } 
         fullDayValue=year+month+day;    
        } 
//not leap year       
        else{
        for (int i = 0; i < m-1; i++) {
                month += CALENDER[i];  
            } 
         fullDayValue=year+month+day;      
        }

    }
    }
    //used in tandom with edit, acts just like setter for this class
    public void editDate(String t){
     setDate(t);
        day2 = Integer.parseInt(day);
        month2 = Integer.parseInt(month);
        year2 = Integer.parseInt(year);
        calculateLeap(year2);
        setFullDay(day2,month2,year2,leap_year);    
    }
}
