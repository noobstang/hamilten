package com.jchan16.ics4u.tpj1;

/**
 * Entry file - test
 *  https://github.com/noobstang/hamilten.git
 * 
 * This class holds the constructor methods for the table entries of the program 
* 
 * @author Jackie
 * @date Nov 25, 2019
 * @version beta
 */
public class Entries {
    
    private int id;
    private boolean favorite;
    private String subject;
    private Date time;
    private Amount price;
    private String notes;
    
    private static int entryCount = 0;
    private final int STATIC_ID;
    
    
    public Entries() {
        id = 0;
        favorite = false;
        subject = "null";
        time = new Date("01/01/2000");
        price = new Amount("-0.00");
        notes = "";
       
        entryCount++;
        entryCount--;
        STATIC_ID = entryCount;
        
    }

    public void changeEntries(int i, boolean f, String s, String d, String a, String n) {
        id = i;
        favorite = f;
        subject = s;
        time = new Date(d);
        price = new Amount(a);
        notes = n;
        
     
    }

    public Entries(int i, boolean f, String s, String d, String a, String n) {
        id = i;
        favorite = f;
        subject = s;
        time = new Date(d);
        price = new Amount(a);
        notes = n;
        
       entryCount++;
        STATIC_ID = entryCount; 
    }

    public Entries(boolean f, String s, String d, String a, String n) {
        id = 0;
        favorite = f;
        subject = s;
        time = new Date(d);
        price = new Amount(a);
        notes = n;
        
        entryCount++;
        STATIC_ID = entryCount;
    }

    public int getId() {
        return id;
    }
    
    public void changeId(){
        id++;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public String getSubject() {
        return subject;
    }

    public Date getDate() {
        return time;
    }

    public String date(int d) {
        switch (d) {
            case 1:
                return time.showDay();

            case 2:
                return time.showMonth();

            case 3:
                return time.showYear();

            case 4:
                return time.showDay() + "/" + time.showMonth() + "/" + time.showYear();

            default:
                return "error";
        }
    }

    public boolean showEORL() {
        return price.check();
    }

    public double showPrice() {
        return price.checkAmount();
    }

    public String showPrice2() {
        return price.getFull();
    }

    public Amount getPrice() {
        return price;
    }

    public String getNotes() {
        return notes;
    }

    public void getEntry() {
        System.out.print(getId() + " " + getFavorite() + " " + getSubject() + " " + date(4) + " " + price.getFull() + " " + getNotes());

    }
}
