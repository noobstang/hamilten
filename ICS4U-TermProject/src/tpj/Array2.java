/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan16.ics4u.tpj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jackie
 */
public class Array2 {

    private int counter;
    //master list of all entries
    private ArrayList<Entries> Array2 = new ArrayList<Entries>();
    //display results of whatever sort/search
    private ArrayList<Entries> result = new ArrayList<Entries>();
    //need to make index for changes
    private int resultStatus=0;
//    private ArrayList<Integer> years = new ArrayList<Integer>();
//    private ArrayList<Month_Day> DateALive = new ArrayList<Month_Day>();
//    private final int Jan = 1;
//    private final int Feb = 2;
//    private final int Mar = 3;
//    private final int Apr = 4;
//    private final int May = 5;
//    private final int Jun = 6;
//    private final int Jul = 7;
//    private final int Aug = 8;
//    private final int Sep = 9;
//    private final int Oct = 10;
//    private final int Nov = 11;
//    private final int Dec = 12;

    public void changeStatus(){
        resultStatus=1;
    }
    
    public void revertStatus(){
        resultStatus=0;
    }
    public Array2(ResultSet a) {
        fill(a);

    }

    public Array2(DatabaseDummy a) {
        fill(a);
    }

//    public ArrayList<Integer> getYearArray() {
//        return years;
//    }
//needs database
    public void fill(ResultSet a) {
        try {
            while (a.next()) {
                try {
                    Array2.add(new Entries(a.getInt("ID"), a.getBoolean("Favorite"), a.getString("SUBJECT"),
                            a.getString("DATE"), a.getString("AMOUNT"), a.getString("NOTES")));
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//// use this to for fake data array list

    public void fill(DatabaseDummy a) {

        for (int j = 0; j < a.test2.size(); j++) {

            Array2.add(new Entries(a.test2.get(j).getId(), a.test2.get(j).getFavorite(), a.test2.get(j).getSubject(),
                    a.test2.get(j).date(4), a.test2.get(j).getPrice().getFull(), a.test2.get(j).getNotes()));

        }
    }
    
    public void fill2(ArrayList<Entries> a) {
        for (int p = 0; p < a.size(); p++) {

            result.add(new Entries());
           result.get(p).changeEntries(a.get(p).getId(), a.get(p).getFavorite(), a.get(p).getSubject(),
                    a.get(p).date(4), a.get(p).getPrice().getFull(),a.get(p).getNotes());
        }
    }
    
    public void clearResults(){
        result=null;
    }

    public ArrayList<Entries> getArray2() {
        return Array2;
    }
    
    public ArrayList<Entries> getResults(){
        return result;
    }
//dead don't use will figure out use later

    public void setCounter(ResultSet a) {
        int i = 0;
        try {
            while (a.next()) {
                i++;
            }
            counter = i;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
//dead don't use for now

    public int getCounter() {
        return counter;
    }

    public void sortSubject() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            String key1 = getResults().get(j).getSubject();
//            System.out.println(key1);
            String ender1 = getResults().get(ender).getSubject();
//            System.out.println(ender1);
            int var1 = key1.compareToIgnoreCase(ender1);
//            System.out.println(var1);
            if (key == 1) {
                while (ender == 0 && var1 < 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && var1 < 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getResults().get(key).getSubject();
                    ender1 = getResults().get(ender).getSubject();
                    var1 = key1.compareToIgnoreCase(ender1);
                }
            }

        }
    }
    
    public void sortSubjectGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            String key1 = getResults().get(j).getSubject();
//            System.out.println(key1);
            String ender1 = getResults().get(ender).getSubject();
//            System.out.println(ender1);
            int var1 = key1.compareToIgnoreCase(ender1);
//            System.out.println(var1);
            if (key == 1) {
                while (ender == 0 && var1 < 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && var1 < 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getResults().get(key).getSubject();
                    ender1 = getResults().get(ender).getSubject();
                    var1 = key1.compareToIgnoreCase(ender1);
                }
            }

        }
    }
//run twice to work

    public void RsortSubject() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            String key1 = getResults().get(j).getSubject();
            System.out.println(key1);
            String ender1 = getResults().get(ender).getSubject();
            System.out.println(ender1);
            int var1 = key1.compareToIgnoreCase(ender1);
            System.out.println(var1);
            if (key == 1) {
                while (ender == 0 && var1 > 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && var1 > 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                   getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getResults().get(key).getSubject();
                    ender1 = getResults().get(ender).getSubject();
                    var1 = key1.compareToIgnoreCase(ender1);
                }
            }

        }
    }
    
    public void RsortSubjectGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            String key1 = getResults().get(j).getSubject();
            System.out.println(key1);
            String ender1 = getResults().get(ender).getSubject();
            System.out.println(ender1);
            int var1 = key1.compareToIgnoreCase(ender1);
            System.out.println(var1);
            if (key == 1) {
                while (ender == 0 && var1 > 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && var1 > 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                   getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getResults().get(key).getSubject();
                    ender1 = getResults().get(ender).getSubject();
                    var1 = key1.compareToIgnoreCase(ender1);
                }
            }

        }
    }
///run twice to work

    public void sortYear() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(3));
            
            int ender1 = Integer.parseInt(getResults().get(ender).date(3));

            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(getResults().get(key).date(3));
                    ender1 = Integer.parseInt(getResults().get(ender).date(3));
                }
            }
        }

    }
    
    
    ///has to run twicte to work
    public void RsortYearGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getArray2().get(key).date(3));
            int ender1 = Integer.parseInt(getArray2().get(ender).date(3));
            if (key == 1) {
                while (ender == 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(getArray2().get(key).date(3));
                    ender1 = Integer.parseInt(getArray2().get(ender).date(3));
                }
            }
        }

    }

    public void sortDay() {
        fill2(Array2);
        for (int j = 1; j <  getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(1));
            System.out.println(key1);
            int ender1 = Integer.parseInt(getResults().get(ender).date(1));
            System.out.println(ender1);

            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                   getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(getResults().get(key).date(1));
                    ender1 = Integer.parseInt(getResults().get(ender).date(1));

                }

            }
        }
    }
    public void sortDayGhost() {
        
        for (int j = 1; j <  getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(1));
            System.out.println(key1);
            int ender1 = Integer.parseInt(getResults().get(ender).date(1));
            System.out.println(ender1);

            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                   getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(getResults().get(key).date(1));
                    ender1 = Integer.parseInt(getResults().get(ender).date(1));

                }

            }
        }
    }

    public void RsortDay() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(1));
            System.out.println(key1);
            int ender1 = Integer.parseInt(getResults().get(ender).date(1));
            System.out.println(ender1);


            if (key == 1) {
                while (ender == 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                    getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(getResults().get(key).date(1));
                    ender1 = Integer.parseInt(getResults().get(ender).date(1));
                }
            }
        }
    }
    
    public void RsortDayGhost() {
      
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(1));
            System.out.println(key1);
            int ender1 = Integer.parseInt(getResults().get(ender).date(1));
            System.out.println(ender1);


            if (key == 1) {
                while (ender == 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                    getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(getResults().get(key).date(1));
                    ender1 = Integer.parseInt(getResults().get(ender).date(1));
                }
            }
        }
    }

    public void sortMonth() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(2));
            System.out.println(key1);
            int ender1 = Integer.parseInt(getResults().get(ender).date(2));
            System.out.println(ender1);


            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                   getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(getResults().get(key).date(2));
                    ender1 = Integer.parseInt(getResults().get(ender).date(2));
                }
            }
        }
    }
    
     public void sortMonthGhost() {
        
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(2));
            System.out.println(key1);
            int ender1 = Integer.parseInt(getResults().get(ender).date(2));
            System.out.println(ender1);


            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                   getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(getResults().get(key).date(2));
                    ender1 = Integer.parseInt(getResults().get(ender).date(2));
                }
            }
        }
    }

    public void RsortMonth(){
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(2));
            System.out.println(key1);
            int ender1 = Integer.parseInt(getResults().get(ender).date(2));
            System.out.println(ender1);
            if (key == 1) {
                while (ender == 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(getResults().get(key).date(2));
                    ender1 = Integer.parseInt(getResults().get(ender).date(2));
                }

            }
        }
    }
    
     public void RsortMonthGhost(){
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(2));
            System.out.println(key1);
            int ender1 = Integer.parseInt(getResults().get(ender).date(2));
            System.out.println(ender1);
            if (key == 1) {
                while (ender == 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(getResults().get(key).date(2));
                    ender1 = Integer.parseInt(getResults().get(ender).date(2));
                }

            }
        }
    }

//    public void getAllYears() {
//        sortDateYearAD();
//        sortDateYearAD();
//        int cursor = Integer.parseInt(getArray2().get(0).date(3));
//        years.add(cursor);
//        for (int j = 1; j < getArray2().size(); j++) {
//            cursor = Integer.parseInt(getArray2().get(j).date(3));
//            int counter = 0;
//
//            for (int i = 0; i < getYearArray().size(); i++) {
//                if (cursor == getYearArray().get(i)) {
//                    counter--;
//                }
//                if (cursor != getYearArray().get(i)) {
//                    counter++;
//                    counter--;
//                }
//            }
//            if (counter == 0) {
//                getYearArray().add(cursor);
//            }
//
//        }
//        for(int k= 1; k<years.size();k++){
//            int key=k;
//            int ender=k-1;
//            int key1=years.get(key);
//            int ender1=years.get(ender);
//            while(ender>0&& key1>ender1){
//                int sub= key1;
//                key1=ender1;
//                ender1=sub;
//                ender--;
//                key--;
//                key1=years.get(key);
//                ender1=years.get(ender);
//            }
//        }
//    public ArrayList<Month_Day> getDateALive() {
//        return DateALive;
//    }
//    public void makeCalenderList() {
//        getAllYears();
//        for (int j = 0; j < getYearArray().size(); j++) {
//            int header = getYearArray().get(j);
//            DateALive.add(new Month_Day());
//            int header2 = getYearArray().get(j);
//            for (int i = 0; i < getArray2().size(); i++) {
//                if (getArray2().get(i).date2(2) == header) {
//                    int switcher = getArray2().get(i).date2(2);
//                    switch (switcher) {
//                        case Jan:
//                            DateALive.get(j).getJan().add(getArray2().get(i));
//                            break;
//
//                        case Feb:
//                            DateALive.get(j).getFeb().add(getArray2().get(i));
//                            break;
//
//                        case Mar:
//                            DateALive.get(j).getMar().add(getArray2().get(i));
//                            break;
//
//                        case Apr:
//                            DateALive.get(j).getApr().add(getArray2().get(i));
//                            break;
//
//                        case May:
//                            DateALive.get(j).getMay().add(getArray2().get(i));
//                            break;
//
//                        case Jun:
//                            DateALive.get(j).getJun().add(getArray2().get(i));
//                            break;
//
//                        case Jul:
//                            DateALive.get(j).getJul().add(getArray2().get(i));
//                            break;
//
//                        case Aug:
//                            DateALive.get(j).getAug().add(getArray2().get(i));
//                            break;
//
//                        case Sep:
//                            DateALive.get(j).getSep().add(getArray2().get(i));
//                            break;
//
//                        case Oct:
//                            DateALive.get(j).getOct().add(getArray2().get(i));
//                            break;
//
//                        case Nov:
//                            DateALive.get(j).getNov().add(getArray2().get(i));
//                            break;
//
//                        case Dec:
//                            DateALive.get(j).getDec().add(getArray2().get(i));
//                            break;
//                            
//                        default:
//                          break;  
//                    }
//                }
//            }
//        }
//    }
    public void sortDateL() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getArray2().get(key).getDate().showFullDay();
            
            int ender1 = getArray2().get(ender).getDate().showFullDay();
            
//            boolean var1=false;
//            if (key1 > ender1 || key1 == ender1) {
//                var1 = true;
//            } else if (key1 < ender1) {
//                var1 = false;
//            }

            if (key==1) {
                while(ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                       
                }
            }
            if (key > 1 && ender!=0) {
                while (ender > 0  && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                   key1 = getResults().get(key).getDate().showFullDay();
                   ender1 = getResults().get(ender).getDate().showFullDay();
                }
                

            }
        }
    }

    public void RsortDateL() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getResults().get(key).getDate().showFullDay();
            
            int ender1 = getResults().get(ender).getDate().showFullDay();
            


            if (key==1) {
                while(ender == 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                       
                }
            }
            if (key > 1 ) {
                while (ender > 0  && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                      getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                   key1 = getResults().get(key).getDate().showFullDay();
                   ender1 = getResults().get(ender).getDate().showFullDay();
                }
            }
        }
    }
    
    public void RsortDateLGhost() {
       
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getResults().get(key).getDate().showFullDay();
            
            int ender1 = getResults().get(ender).getDate().showFullDay();
            

            if (key==1) {
                while(ender == 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                       
                }
            }
            if (key > 1 ) {
                while (ender > 0  && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                      getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                   key1 = getResults().get(key).getDate().showFullDay();
                   ender1 = getResults().get(ender).getDate().showFullDay();
                }
            }
        }
    }
   
    public void sortGains(){
         ///goes through array
        int i= 0;
        ///place in result array
        int j=0;
        while(i<getArray2().size()){ 
            if(getArray2().get(i).showEORL()==true){
               int t=0; 
                getResults().get(j).changeEntries(getArray2().get(i).getId(), getArray2().get(i).getFavorite(),
                            getArray2().get(i).getSubject(), getArray2().get(i).date(4), getArray2().get(i).getPrice().getFull(), getArray2().get(i).getNotes());
                i++;
                j++;        
            }  
            else{
            i++;    
            }
        }
    
    }
    
    public void sortGainsGhost(){
      for (int j = 1; j <  getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getResults().get(key).showEORL();
            
            boolean ender1 = getResults().get(ender).showEORL();
            

            if (key == 1) {
                while (ender == 0 && key1==true) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                   getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1==true) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getResults().get(key).showEORL();
                    ender1 = getResults().get(ender).showEORL();

                }

            }
        }
      
    }  
    
    public void sortLoses(){
        ///goes through array
        int i= 0;
        ///place in result array
        int j=0;
        while(i<getArray2().size()){ 
            if(getArray2().get(i).showEORL()==false){
                result.add(new Entries());
                getResults().get(j).changeEntries(getArray2().get(i).getId(), getArray2().get(i).getFavorite(),
                            getArray2().get(i).getSubject(), getArray2().get(i).date(4), getArray2().get(i).getPrice().getFull(), getArray2().get(i).getNotes());
                i++;
                j++;        
            }  
            else{
            i++;    
            }
        }
    }
    
    public void sortLosesGhost(){
       
     for (int j = 1; j <  getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getResults().get(key).showEORL();
            double key3=getResults().get(key).showPrice();
            boolean ender1 = getResults().get(ender).showEORL();
            double ender3=getResults().get(ender).showPrice();

            if (key == 1) {
                while (ender == 0 && ender1==true && ender3>=key3 ) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                   getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && ender1==true&& ender3>=key3) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getResults().get(key).showEORL();
                    ender1 = getResults().get(ender).showEORL();
                    key3=getResults().get(key).showPrice();
                    ender3=getResults().get(ender).showPrice();

                }

            }
        }
      
    }  
    
    public void printArray2(){
        for(int j=0; j<getArray2().size();j++){
   System.out.println(getArray2().get(j).getId()+" "+getArray2().get(j).getFavorite()+" "+getArray2().get(j).getSubject()+" "+getArray2().get(j).date(4)+" "+getArray2().get(j).getPrice().getFull()+
      " "+getArray2().get(j).getNotes());
        }
    }
    
    public void printResults(){
        for(int i=0; i<getResults().size();i++){
   System.out.println(getResults().get(i).getId()+" "+getResults().get(i).getFavorite()+" "+getResults().get(i).getSubject()+" "+getResults().get(i).date(4)+" "+getResults().get(i).getPrice().getFull()+
      " "+getResults().get(i).getNotes());
    }
    }
    
    public void sortAmount(){
        fill2(Array2);
    for (int j = 1; j <  getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            double key1 = getResults().get(key).showPrice();
            System.out.println(key1);
            double ender1 = getResults().get(ender).showPrice();;
            System.out.println(ender1);

            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                   getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getResults().get(key).showPrice();
                    ender1 = getResults().get(ender).showPrice();

                }

            }
        }
    }    
        
     public void sortAmountGhost(){
    for (int j = 1; j <  getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            double key1 = getResults().get(key).showPrice();
            System.out.println(key1);
            double ender1 = getResults().get(ender).showPrice();;
            System.out.println(ender1);

            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                   getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getResults().get(key).showPrice();
                    ender1 = getResults().get(ender).showPrice();

                }

            }
        }
    }  
    
      public void RsortAmount(){
        fill2(Array2);
    for (int j = 1; j <  getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            double key1 = getResults().get(key).showPrice();
            System.out.println(key1);
            double ender1 = getResults().get(ender).showPrice();;
            System.out.println(ender1);

            if (key == 1) {
                while (ender == 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                   getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getResults().get(key).showPrice();
                    ender1 = getResults().get(ender).showPrice();

                }

            }
        }
    }
    
        public void RsortAmountGhost(){
        
    for (int j = 1; j <  getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            double key1 = getResults().get(key).showPrice();
            System.out.println(key1);
            double ender1 = getResults().get(ender).showPrice();;
            System.out.println(ender1);

            if (key == 1) {
                while (ender == 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                   getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getResults().get(key).getId(), getResults().get(key).getFavorite(),
                            getResults().get(key).getSubject(), getResults().get(key).date(4), getResults().get(key).getPrice().getFull(), getResults().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getResults().get(key).changeEntries(getResults().get(ender).getId(), getResults().get(ender).getFavorite(),
                            getResults().get(ender).getSubject(), getResults().get(ender).date(4), getResults().get(ender).getPrice().getFull(),
                            getResults().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getResults().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getResults().get(key).showPrice();
                    ender1 = getResults().get(ender).showPrice();

                }

            }
        }
    }
        
        
      
    
    }
    