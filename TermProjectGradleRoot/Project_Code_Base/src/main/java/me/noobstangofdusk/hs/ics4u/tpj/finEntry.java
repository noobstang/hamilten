/*
 * ICS4U Term Project, Earl of March Secondary School
 * 
 * Copyright 2019 under the GNU 2.0 License
 * All rights reserved.
 */

package me.noobstangofdusk.hs.ics4u.tpj;

import java.util.Date;
/**
 * Finance Entry Object for table initialization
 * 
 * @author noobstang
 * @date Oct 29, 2019
 * @version 1.0
 * @see java.lang.System
 */

public class finEntry {
    
    private String subjectt;
    private Date datee;
    private double changee;
    private static double balancee;
    private boolean favouritee;
    private String notess;
    
    private static int entryNumber;
    private final int ENTRY_ID;
    
    // Default constructor should never be used.
    public finEntry() {
        subjectt = null;
        datee = null;
        changee = 0;
        balancee = 0;
        favouritee = false;
        notess = null;
        
        entryNumber++;
        ENTRY_ID = entryNumber;
    }
    
    
    public finEntry(String subject, Date date, double change, boolean favourite, String notes) {
        subjectt = subject;
        datee = date;
        changee = change;
        favouritee = favourite;
        notess = notes;
        
        entryNumber++;
        ENTRY_ID = entryNumber;
    }
    
    public String getSubject() {
        return this.subjectt;
    }
    
    public void changeSubject(String subject) {
        subjectt = subject;
    }
    
    public Date getDate() {
        return this.datee;
    }
    
    public void changeDate(Date date) {
        datee = date;
    }
    
    public double getChange() {
        return this.changee;
    }
    
    public void setChange(double change) {
        changee = change;
    }
    
    public static double getBalance() {
        return balancee;
    }
    
    public static void changeBalance(double balance) {
        balancee = balancee + balance;
    }
    
    public static void setBalance(double balance) {
        balancee = balance;
    }
    
    public boolean getFavourite() {
        return this.favouritee;
    }
    
    public void changeFavourite() {
        if (favouritee) {
            favouritee = false;
        } else {
            favouritee = true;
        }
    }
    
    public String getNotes() {
        return this.notess;
    }
    
    public void setNotes(String notes) {
        notess = notes;
    }
    
    public int getENTRY_ID() {
        return this.ENTRY_ID;
    }
    
    
    
}
