/*
 * ICS4U Term Project, Earl of March Secondary School
 * 
 * Copyright 2019 under the GNU 2.0 License
 * All rights reserved.
 */

package me.noobstangofdusk.hs.ics4u.tpj;

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
    private String datee;
    private double changee;
    private static double balancee;
    private int favouritee;
    private String notess;
    
    private static int entryNumber;
    private final int ENTRY_ID;
    
    // Default constructor should never be used.
    public finEntry() {
        subjectt = null;
        datee = null;
        changee = 0;
        balancee = 0;
        favouritee = 0;
        notess = null;
        
        entryNumber++;
        ENTRY_ID = entryNumber;
    }
    
    
    public finEntry(String subject, String date, double change, int favourite, String notes) {
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
    
    public String getDate() {
        return this.datee;
    }
    
    public double getChange() {
        return this.changee;
    }
    
    public static double getBalance() {
        return balancee;
    }
    
    public int getFavourite() {
        return this.favouritee;
    }
    
    public String getNotes() {
        return this.notess;
    }
    
    public int getENTRY_ID() {
        return this.ENTRY_ID;
    }
    
}
