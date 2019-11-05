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
public class Entries {

    private int id;
    private boolean favorite;
    private String subject;
    private Date time;
    private Amount price;
    private String notes;

    public void fillEntrie(int i, boolean f, String s, String d, String a, String n) {
        id = i;
        favorite = f;
        subject = s;
        time = new Date(d);
        price = new Amount(a);
        notes = n;
    }

    public int getId() {
        return id;
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

            default:
                return "error";

        }
    }
public boolean showEORL(){
    return price.check();
}

public double showPrice(){
    return price.checkAmount();
}

public String getNotes(){
    return notes;
}
}
