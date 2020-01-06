/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan16.ics4u.tpj3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jackie
 */
public class Array2 {

    ///Read:
    //subject sort = a-z while r=z-a logic applies to numbers as well but int sort=least-greatest
    //non ghosted sorts, copy from master array list or array2 and then sort
    //ghosted sorts, sort results array, do no repeat non ghosted twice as you will duplicate every entry
    //search sorts, pull from array3 or search array another copy of master list, at the moment can't 
    //search from results only from master list
    private int counter;
    //master list of all entries
    private ArrayList<Entries> Array2 = new ArrayList<Entries>();
    //copy of master to be used with search
    private ArrayList<Entries> Array3 = new ArrayList<Entries>();
    //display results of whatever sort/search
    private ArrayList<Entries> result = new ArrayList<Entries>();

    private ArrayList<Integer> years = new ArrayList<Integer>();

    private ArrayList<String> edits = new ArrayList<String>();
    //index for results:
    //0=empty results, 1=array2 copied, 2=sorted results,3=search
    private boolean SearchStatus;
    private boolean ResultStatus;
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

    public void clearSearch() {
        Array3.clear();
        revertSearchStatus();
    }

    public ArrayList<String> getEdits() {
        return edits;
    }

    public void changeSearchStatus() {
        SearchStatus = true;
    }

    public boolean showSearchStatus() {
        return SearchStatus;
    }

    public void revertSearchStatus() {
        SearchStatus = false;
    }

    public Array2(ResultSet a) {
        fill(a);

    }

    public Array2(DatabaseDummy a) {
        fill(a);
        SearchStatus = false;
        ResultStatus = false;
    }

//    public Array2(){
//        
//    }
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
                    a.get(p).date(4), a.get(p).getPrice().getFull(), a.get(p).getNotes());
        }
    }

    public void fill3(ArrayList<Entries> a) {
        for (int p = 0; p < a.size(); p++) {

            Array3.add(new Entries());
            Array3.get(p).changeEntries(a.get(p).getId(), a.get(p).getFavorite(), a.get(p).getSubject(),
                    a.get(p).date(4), a.get(p).getPrice().getFull(), a.get(p).getNotes());
        }
    }

    public void clearResults() {
        result.clear();
        revertResultStatus();
    }

    public void changeResultStatus() {
        ResultStatus = true;
    }

    public boolean showResultStatus() {
        return ResultStatus;
    }

    public void revertResultStatus() {
        ResultStatus = false;
    }

    public ArrayList<Entries> getArray2() {
        return Array2;
    }

    public ArrayList<Entries> getResults() {
        return result;
    }

    public ArrayList<Entries> getSearch() {
        return Array3;
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

            String ender1 = getResults().get(ender).getSubject();

            int var1 = key1.compareToIgnoreCase(ender1);

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

            String ender1 = getResults().get(ender).getSubject();

            int var1 = key1.compareToIgnoreCase(ender1);

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

            String ender1 = getResults().get(ender).getSubject();

            int var1 = key1.compareToIgnoreCase(ender1);

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

    public void sortYearGhost() {
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

    public void RsortYear() {
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
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(1));
            int ender1 = Integer.parseInt(getResults().get(ender).date(1));

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

        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(1));
            int ender1 = Integer.parseInt(getResults().get(ender).date(1));
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
            int ender1 = Integer.parseInt(getResults().get(ender).date(1));

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
            int ender1 = Integer.parseInt(getResults().get(ender).date(1));
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
            int ender1 = Integer.parseInt(getResults().get(ender).date(2));
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
            int key1 = Integer.parseInt(getResults().get(key).date(2));;
            int ender1 = Integer.parseInt(getResults().get(ender).date(2));
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

    public void RsortMonth() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(2));
            int ender1 = Integer.parseInt(getResults().get(ender).date(2));
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

    public void RsortMonthGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(2));
            int ender1 = Integer.parseInt(getResults().get(ender).date(2));
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

    public ArrayList<Integer> getYearArray() {
        return years;
    }

    public void showYears() {
        for (int i = 0; i < getYearArray().size(); i++) {
            System.out.println(getYearArray().get(i));
        }
    }

    public void getAllYears() {
        sortYear();
        sortYearGhost();
        int cursor = Integer.parseInt(getArray2().get(0).date(3));
        years.add(cursor);
        for (int j = 1; j < getArray2().size(); j++) {
            cursor = Integer.parseInt(getArray2().get(j).date(3));
            int counter = 0;

            for (int i = 0; i < getYearArray().size(); i++) {
                if (cursor == getYearArray().get(i)) {
                    counter--;
                }
                if (cursor != getYearArray().get(i)) {
                    counter++;
                    counter--;
                }
            }
            if (counter == 0) {
                getYearArray().add(cursor);
            }
        }
    }

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
                    key1 = getResults().get(key).getDate().showFullDay();
                    ender1 = getResults().get(ender).getDate().showFullDay();
                }

            }
        }
    }

    public void sortDateLGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getResults().get(key).getDate().showFullDay();
            int ender1 = getResults().get(ender).getDate().showFullDay();

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
                    key1 = getResults().get(key).getDate().showFullDay();
                    ender1 = getResults().get(ender).getDate().showFullDay();
                }
            }
        }
    }

    public void searchGains() {
        ///goes through array
        int i = 0;
        ///place in result array
        int j = 0;
        while (i < getArray2().size()) {
            if (getArray2().get(i).showEORL() == true) {
                System.out.println("ree");
                result.add(new Entries());
                getResults().get(j).changeEntries(getArray2().get(i).getId(), getArray2().get(i).getFavorite(),
                        getArray2().get(i).getSubject(), getArray2().get(i).date(4), getArray2().get(i).getPrice().getFull(), getArray2().get(i).getNotes());
                j++;
                i++;
            } else if (getArray2().get(i).showEORL() == false) {
                System.out.println("nee");
                i++;
            }
        }
    }

    public void searchGainsGhost() {
        int g = getResults().size();
        ///goes through array
        int i = 0;
        ///place in result array
        int j = 0;
        while (j < g) {
            if (getResults().get(i).showEORL() == false) {
                getResults().remove(i);
                g = getResults().size();
            } else {
                i++;
                j++;
            }
        }
    }

    public void sortGainsGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getResults().get(key).showEORL();

            boolean ender1 = getResults().get(ender).showEORL();

            if (key == 1) {
                while (ender == 0 && key1 == true) {

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
                while (ender > 0 && key1 == true) {

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

    public void searchLosses() {
        ///goes through array
        int i = 0;
        ///place in result array
        int j = 0;
        while (i < getArray2().size()) {
            if (getArray2().get(i).showEORL() == false) {
                result.add(new Entries());
                getResults().get(j).changeEntries(getArray2().get(i).getId(), getArray2().get(i).getFavorite(),
                        getArray2().get(i).getSubject(), getArray2().get(i).date(4), getArray2().get(i).getPrice().getFull(), getArray2().get(i).getNotes());
                i++;
                j++;
            } else {
                i++;
            }
        }
    }

    public void searchLossesGhost() {
        int g = getResults().size();
        ///goes through array
        int i = 0;
        ///place in result array
        int j = 0;
        while (j < g) {
            if (getResults().get(i).showEORL() == true) {
                getResults().remove(i);
                g = getResults().size();
            } else {
                i++;
                j++;
            }
        }
    }

    public void sortLosesGhost() {

        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getResults().get(key).showEORL();
            double key3 = getResults().get(key).showPrice();
            boolean ender1 = getResults().get(ender).showEORL();
            double ender3 = getResults().get(ender).showPrice();

            if (key == 1) {
                while (ender == 0 && ender1 == true) {

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
                while (ender > 0 && ender1 == true) {

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
                    key3 = getResults().get(key).showPrice();
                    ender3 = getResults().get(ender).showPrice();

                }

            }
        }

    }

    public void printArray2() {
        for (int j = 0; j < getArray2().size(); j++) {
            System.out.println(getArray2().get(j).getId() + " " + getArray2().get(j).getFavorite() + " " + getArray2().get(j).getSubject() + " " + getArray2().get(j).date(4) + " " + getArray2().get(j).getPrice().getFull()
                    + " " + getArray2().get(j).getNotes());
        }
    }

    public void printSearch() {
        for (int j = 0; j < getSearch().size(); j++) {
            System.out.println(getSearch().get(j).getId() + " " + getSearch().get(j).getFavorite() + " " + getSearch().get(j).getSubject() + " " + getSearch().get(j).date(4) + " " + getSearch().get(j).getPrice().getFull()
                    + " " + getSearch().get(j).getNotes());
        }
    }

    public void printResults() {
        for (int i = 0; i < getResults().size(); i++) {
            System.out.println(getResults().get(i).getId() + " " + getResults().get(i).getFavorite() + " " + getResults().get(i).getSubject() + " " + getResults().get(i).date(4) + " " + getResults().get(i).getPrice().getFull()
                    + " " + getResults().get(i).getNotes());
        }
    }

    public void sortAmount() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            double key1 = getResults().get(key).showPrice();
            double ender1 = getResults().get(ender).showPrice();;

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

    public void sortAmountGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            double key1 = getResults().get(key).showPrice();
            double ender1 = getResults().get(ender).showPrice();;

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

    public void RsortAmount() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            double key1 = getResults().get(key).showPrice();
            double ender1 = getResults().get(ender).showPrice();;

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

    public void RsortAmountGhost() {

        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            double key1 = getResults().get(key).showPrice();
            double ender1 = getResults().get(ender).showPrice();;
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

    public void searchSubjectSort() {
        fill3(Array2);
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            String key1 = getSearch().get(key).getSubject();
            String ender1 = getSearch().get(ender).getSubject();
            int var1 = key1.compareToIgnoreCase(ender1);

            if (key == 1) {
                while (ender == 0 && var1 < 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && var1 < 0) {
                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).getSubject();
                    ender1 = getSearch().get(ender).getSubject();
                    var1 = key1.compareToIgnoreCase(ender1);
                }
            }
        }
    }

    public void searchDateSort() {
        fill3(Array2);
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getSearch().get(key).date2(4);
            int ender1 = getSearch().get(ender).date2(4);

            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {
                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).date2(4);
                    ender1 = getSearch().get(ender).date2(4);
                }
            }
        }
    }

    public void searchDateSortGhost() {
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getSearch().get(key).date2(4);
            int ender1 = getSearch().get(ender).date2(4);

            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {
                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).date2(4);
                    ender1 = getSearch().get(ender).date2(4);
                }
            }
        }
    }

//    public ArrayList<entry> searchMaster() {
//        ArrayList<> result = masterAL;
//        
//        if (date != null) {
//            
//        }
//        
//        
//        
//    }
    public void searchSubjectSortGhost() {
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            String key1 = getSearch().get(j).getSubject();
            String ender1 = getSearch().get(ender).getSubject();
            int var1 = key1.compareToIgnoreCase(ender1);

            if (key == 1) {
                while (ender == 0 && var1 < 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && var1 < 0) {
                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).getSubject();
                    ender1 = getSearch().get(ender).getSubject();
                    var1 = key1.compareToIgnoreCase(ender1);
                }
            }

        }
    }

    public void searchSubject(String a1) {
        searchSubjectSort();
        searchSubjectSortGhost();

        if (getSearch().size() > 10) {
            int compare = getSearch().size() / 2;
//            System.out.println(compare);
            int compareHigher = 0 + (compare / 2);
//            System.out.println(compareHigher);   
            String ident = a1.substring(0);
            String compare2 = getSearch().get(compare).getSubject().substring(0);
//            System.out.println(compare2);
//            System.out.println(ident.compareTo(compare2));
            if (ident.compareTo(getSearch().get(0).getSubject()) == 0 && ident.compareTo(getSearch().get(getSearch().size() - 1).getSubject()) == 0) {
                ///goes through array
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (a1.equalsIgnoreCase(getSearch().get(i).getSubject()) == true) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
            if (ident.compareTo(compare2) > 0) {
//                System.out.println("comapre2 is above ident");
                ///goes through array
                int i = compare;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (a1.equalsIgnoreCase(getSearch().get(i).getSubject()) == true) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }

            if (ident.compareTo(compare2) == 0) {
//                System.out.println("compare2 is equal to ident");
                ///goes through array
                int i = compareHigher;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (a1.equalsIgnoreCase(getSearch().get(i).getSubject()) == true) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }

            if (ident.compareTo(compare2) < 0) {
//                System.out.println("comapre2 is less than ident");
                ///goes through array
                int i = compare;
                ///place in result array
                int j = 0;
                while (i > 0) {
                    if (a1.equalsIgnoreCase(getSearch().get(i).getSubject()) == true) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i--;
                        j++;
                    } else {
                        i--;
                    }
                }
            }

        } else {
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            while (i < getSearch().size()) {
                if (a1.equalsIgnoreCase(getSearch().get(i).getSubject()) == true) {
                    result.add(new Entries());
                    result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                            getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                    i++;
                    j++;
                } else {
                    i++;
                }
            }
        }
    }

    public void searchSubjectGhost(String a1) {
        int g = getResults().size();
        ///goes through array
        int i = 0;
        ///place in result array
        int j = 0;
        while (j < g) {
            if (a1.equalsIgnoreCase(getResults().get(i).getSubject()) == false) {
                getResults().remove(i);
                g = getResults().size();
            } else {
                i++;
                j++;
            }
        }
    }

    //a is first input b is second
    //have to take in account order if b is greater than a or a is greater then b 
    public void searchDate(int a, int b) {
        if (a < b) {
            System.out.println("Start");
            searchDateSort();
            searchDateSortGhost();
            if (getSearch().size() >= 10) {
                System.out.println("1");
                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                int compare2 = getSearch().get(compare).date2(4);

                if (getSearch().get(0).date2(4) > a && getSearch().get(0).date2(4) < b && getSearch().get(getSearch().size() - 1).date2(4) > a && getSearch().get(getSearch().size() - 1).date2(4) < b) {
                    ///goes through array
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).date2(4) >= a && getSearch().get(i).date2(4) <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 > b && compare2 > a) {
                    System.out.println("a");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < compare) {
                        if (getSearch().get(i).date2(4) >= a && getSearch().get(i).date2(4) <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 < a) {
                    System.out.println("b");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).date2(4) >= a && getSearch().get(i).date2(4) <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 > a) {
                    System.out.println("c");
                    int i = compareHigher;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).date2(4) >= a && getSearch().get(i).date2(4) <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
            } else {
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).date2(4) >= a && getSearch().get(i).date2(4) <= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
        } else if (a > b) {
            System.out.println("Start2");
            searchDateSort();
            searchDateSortGhost();
            if (getSearch().size() >= 10) {

                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                int compare2 = getSearch().get(compare).date2(4);

                if (getSearch().get(0).date2(4) > a && getSearch().get(0).date2(4) < b && getSearch().get(getSearch().size() - 1).date2(4) > a && getSearch().get(getSearch().size() - 1).date2(4) < b) {
                    ///goes through array
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).date2(4) >= a && getSearch().get(i).date2(4) <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }

                if (compare2 > b && compare2 > a) {
                    System.out.println("a2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < compare) {
                        if (getSearch().get(i).date2(4) <= a && getSearch().get(i).date2(4) >= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 < a) {
                    System.out.println("b2");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).date2(4) <= a && getSearch().get(i).date2(4) >= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 > a) {
                    System.out.println("c2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).date2(4) <= a && getSearch().get(i).date2(4) >= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
            } else {
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).date2(4) <= a && getSearch().get(i).date2(4) >= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    public void searchDateGhost(int a, int b) {
        if (a < b) {
            System.out.println("ree");
            int g = getResults().size();
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            while (j < g) {
                if (getResults().get(i).date2(4) < a || getResults().get(i).date2(4) > b) {
                    getResults().remove(i);
                    g = getResults().size();
                } else {
                    i++;
                    j++;
                }
            }
        } else if (a > b) {
            System.out.println("ree2");
            int g = getResults().size();
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            while (j < g) {
                if (getResults().get(i).date2(4) > a || getResults().get(i).date2(4) < b) {
                    getResults().remove(i);
                    g = getResults().size();
                } else {
                    i++;
                    j++;
                }
            }
        }
    }

    public void searchDate(String a, int b) {
        searchDateSort();
        searchDateSortGhost();
        switch (a) {
            case "<":
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).date2(4) < b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case ">":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).date2(4) > b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case "<=":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).date2(4) <= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case ">=":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).date2(4) >= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case "":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).date2(4) == b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            default:
                break;
        }
    }

    public void searchDateGhost(String a, int b) {
        int g = getResults().size();
        switch (a) {
            case "<":
                ///goes through array
                int i = 0;
                ///place in result array
                int j = 0;
                while (j < g) {
                    if (getResults().get(i).date2(4) > b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case ">":
                g = getResults().size();
                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).date2(4) < b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case "<=":
                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).date2(4) > b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case ">=":
                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).date2(4) < b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case "":

                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).date2(4) != b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }

                break;

            default:
                break;
        }
    }

    public void sortID() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getResults().get(key).getId();
            int ender1 = getResults().get(ender).getId();

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
                    key1 = getResults().get(key).getId();
                    ender1 = getResults().get(ender).getId();

                }
            }
        }
    }

    public void sortIDGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getResults().get(key).getId();
            int ender1 = getResults().get(ender).getId();

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
                    key1 = getResults().get(key).getId();
                    ender1 = getResults().get(ender).getId();

                }
            }
        }
    }

    public void RsortID() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getResults().get(key).getId();
            int ender1 = getResults().get(ender).getId();

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
                    key1 = getResults().get(key).getId();
                    ender1 = getResults().get(ender).getId();

                }
            }
        }
    }

    public void RsortIDGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getResults().get(key).getId();
            int ender1 = getResults().get(ender).getId();

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
                    key1 = getResults().get(key).getId();
                    ender1 = getResults().get(ender).getId();

                }
            }
        }
    }

    public void searchIDSort() {
        fill3(Array2);
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getSearch().get(key).getId();
            int ender1 = getSearch().get(ender).getId();

            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).getId();
                    ender1 = getSearch().get(ender).getId();

                }
            }
        }
    }

    public void searchIDSortGhost() {
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = getSearch().get(key).getId();
            int ender1 = getSearch().get(ender).getId();

            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).getId();
                    ender1 = getSearch().get(ender).getId();

                }
            }
        }
    }

    public void searchID(int a, int b) {
        if (a < b) {
            searchIDSort();
            searchIDSortGhost();
            if (getSearch().size() >= 10) {
                System.out.println("1");
                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                int compare2 = getSearch().get(compare).getId();

                if (compare2 > b && compare2 > a) {
                    System.out.println("a");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < compare) {
                        if (getSearch().get(i).getId() >= a && getSearch().get(i).getId() <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 < a) {
                    System.out.println("b");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).getId() >= a && getSearch().get(i).getId() <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 > a) {
                    System.out.println("c");
                    int i = compareHigher;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).getId() >= a && getSearch().get(i).getId() <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
            } else {
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).getId() >= a && getSearch().get(i).getId() <= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
        } else if (a > b) {
            System.out.println("Start2");
            searchDateSort();
            searchDateSortGhost();
            if (getSearch().size() >= 10) {

                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                int compare2 = getSearch().get(compare).getId();

                if (compare2 > b && compare2 > a) {
                    System.out.println("a2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < compare) {
                        if (getSearch().get(i).getId() <= a && getSearch().get(i).getId() >= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 < a) {
                    System.out.println("b2");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).getId() <= a && getSearch().get(i).getId() >= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 > a) {
                    System.out.println("c2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).getId() <= a && getSearch().get(i).getId() >= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
            } else {
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).getId() <= a && getSearch().get(i).getId() >= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    public void searchID(String a, int b) {
        searchIDSort();
        searchIDSortGhost();
        switch (a) {
            case "<":
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).getId() < b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case ">":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).getId() > b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case "<=":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).getId() <= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case ">=":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).getId() >= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case "":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).getId() == b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            default:
                break;
        }
    }

    public void searchID(int a) {
        searchIDSort();
        searchIDSortGhost();
        if (getSearch().size() >= 10) {
            System.out.println("1");
            int compare = getSearch().size() / 2;
            int compareHigher = 0 + (compare / 2);
            int compare2 = getSearch().get(compare).getId();

            if (compare2 > a) {
                System.out.println("a");
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < compare) {
                    if (getSearch().get(i).getId() == a) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
            if (compare2 < a) {
                System.out.println("b");
                int i = compare;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).getId() == a) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
            if (compare2 == a) {
                System.out.println("c");
                int i = compareHigher;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).getId() == a) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
        } else {
            int i = 0;
            ///place in result array
            int j = 0;
            while (i < getSearch().size()) {
                if (getSearch().get(i).getId() == a) {
                    result.add(new Entries());
                    result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                            getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                    i++;
                    j++;
                } else {
                    i++;
                }
            }
        }
    }

    public void searchIDGhost(int a, int b) {
        int g = getResults().size();
        if (a < b) {
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            while (j < g) {
                if (getResults().get(i).getId() < a || getResults().get(i).getId() > b) {
                    getResults().remove(i);
                    g = getResults().size();
                } else {
                    i++;
                    j++;
                }
            }
        } else {
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            while (j < g) {
                if (getResults().get(i).getId() > a || getResults().get(i).getId() < b) {
                    getResults().remove(i);
                    g = getResults().size();
                } else {
                    i++;
                    j++;
                }
            }
        }
    }

    public void searchIDGhost(String a, int b) {
        int g = getResults().size();
        switch (a) {
            case "<":
                ///goes through array
                int i = 0;
                ///place in result array
                int j = 0;
                while (j < g) {
                    if (getResults().get(i).getId() > b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case ">":
                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).getId() < b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case "<=":
                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).getId() > b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case ">=":
                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).getId() < b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case "":
                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).getId() != b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }

                break;

            default:
                break;
        }
    }

    public void searchIDGhost(int a) {
        int g = getResults().size();
        ///goes through array
        int i = 0;
        ///place in result array
        int j = 0;
        while (j < g) {
            if (getResults().get(i).getId() != a) {
                getResults().remove(i);
                g = getResults().size();
            } else {
                i++;
                j++;
            }
        }
    }

    public void searchFavorite(boolean a) {
        if (a == true) {
            System.out.println("Start");
            searchFavoriteSort();
            searchFavoriteSortGhost();
            if (getSearch().size() >= 10) {
                System.out.println("1");
                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                boolean compare2 = getSearch().get(compare).getFavorite();

                if (getSearch().get(0).getFavorite() == true && getSearch().get(getSearch().size() - 1).getFavorite() == true) {
                    ///goes through array
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).getFavorite() == true) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 == false) {
                    System.out.println("a");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < compare) {
                        if (getSearch().get(i).getFavorite() == true) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 == true) {
                    System.out.println("b");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).getFavorite() == true) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
            } else {
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).getFavorite() == true) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
        } else {
            System.out.println("Start2");
            searchFavoriteSort();
            searchFavoriteSortGhost();
            if (getSearch().size() >= 10) {
                System.out.println("12");
                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                boolean compare2 = getSearch().get(compare).getFavorite();

                if (getSearch().get(0).getFavorite() == false && getSearch().get(getSearch().size() - 1).getFavorite() == false) {
                    ///goes through array
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).getFavorite() == false) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 == true) {
                    System.out.println("a2");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).getFavorite() == false) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 == false) {
                    System.out.println("b2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).getFavorite() == false) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
            } else {
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).getFavorite() == false) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    public void searchFavoriteGhost(boolean b) {
        int g = getResults().size();
        if (b == true) {
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            while (j < g) {
                if (getResults().get(i).getFavorite() == false) {
                    getResults().remove(i);
                    g = getResults().size();
                    System.out.println("\n");
                    printResults();
                } else {
                    i++;
                    j++;
                }
            }
        } else {
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            while (j < g) {
                if (getResults().get(i).getFavorite() == true) {
                    getResults().remove(i);
                    g = getResults().size();
                } else {
                    i++;
                    j++;
                }
            }
        }
    }

    public void sortFavorite() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getResults().get(key).getFavorite();
            boolean ender1 = getResults().get(ender).getFavorite();

            if (key == 1) {
                while (ender == 0 && ender1 == false) {

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
                while (ender > 0 && ender1 == false) {

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
                    key1 = getResults().get(key).getFavorite();
                    ender1 = getResults().get(ender).getFavorite();

                }
            }
        }
    }

    public void sortFavoriteGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getResults().get(key).getFavorite();
            boolean ender1 = getResults().get(ender).getFavorite();

            if (key == 1) {
                while (ender == 0 && ender1 == false) {

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
                while (ender > 0 && ender1 == false) {

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
                    key1 = getResults().get(key).getFavorite();
                    ender1 = getResults().get(ender).getFavorite();

                }
            }
        }
    }

    public void searchFavoriteSort() {
        fill3(Array2);
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getSearch().get(key).getFavorite();
            boolean ender1 = getSearch().get(ender).getFavorite();

            if (key == 1) {
                while (ender == 0 && ender1 == false) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && ender1 == false) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).getFavorite();
                    ender1 = getSearch().get(ender).getFavorite();

                }
            }
        }
    }

    public void searchFavoriteSortGhost() {
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getSearch().get(key).getFavorite();
            boolean ender1 = getSearch().get(ender).getFavorite();

            if (key == 1) {
                while (ender == 0 && ender1 == false) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && ender1 == false) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).getFavorite();
                    ender1 = getSearch().get(ender).getFavorite();

                }
            }
        }
    }

    public void searchAmountSort() {
        fill3(Array2);
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            double key1 = getSearch().get(key).showPrice();
            double ender1 = getSearch().get(ender).showPrice();

            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).showPrice();
                    ender1 = getSearch().get(ender).showPrice();

                }
            }
        }
    }

    public void searchAmountSortGhost() {
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            double key1 = getSearch().get(key).showPrice();
            double ender1 = getSearch().get(ender).showPrice();

            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).showPrice();
                    ender1 = getSearch().get(ender).showPrice();

                }
            }
        }
    }

    public void searchGainsSort() {
        fill3(Array2);
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getSearch().get(key).showEORL();
            boolean ender1 = getSearch().get(ender).showEORL();

            if (key == 1) {
                while (ender == 0 && ender1 == true) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && ender1 == true) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).getFavorite();
                    ender1 = getSearch().get(ender).getFavorite();

                }
            }
        }
    }

    public void searchGainsSortGhost() {
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getSearch().get(key).showEORL();
            boolean ender1 = getSearch().get(ender).showEORL();

            if (key == 1) {
                while (ender == 0 && ender1 == true) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && ender1 == true) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).getFavorite();
                    ender1 = getSearch().get(ender).getFavorite();

                }
            }
        }
    }

    public void searchLossesSort() {
        fill3(Array2);
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getSearch().get(key).showEORL();
            boolean ender1 = getSearch().get(ender).showEORL();

            if (key == 1) {
                while (ender == 0 && ender1 == false) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && ender1 == false) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).getFavorite();
                    ender1 = getSearch().get(ender).getFavorite();

                }
            }
        }
    }

    public void searchLossesSortGhost() {
        for (int j = 1; j < getSearch().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getSearch().get(key).showEORL();
            boolean ender1 = getSearch().get(ender).showEORL();

            if (key == 1) {
                while (ender == 0 && ender1 == false) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && ender1 == false) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getSearch().get(key).getId(), getSearch().get(key).getFavorite(),
                            getSearch().get(key).getSubject(), getSearch().get(key).date(4), getSearch().get(key).getPrice().getFull(), getSearch().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getSearch().get(key).changeEntries(getSearch().get(ender).getId(), getSearch().get(ender).getFavorite(),
                            getSearch().get(ender).getSubject(), getSearch().get(ender).date(4), getSearch().get(ender).getPrice().getFull(),
                            getSearch().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getSearch().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getSearch().get(key).getFavorite();
                    ender1 = getSearch().get(ender).getFavorite();

                }
            }
        }
    }

    public void searchAmount(double a, double b) {
        if (a < b) {
            System.out.println("Start");
            searchAmountSort();
            searchAmountSortGhost();
            if (getSearch().size() >= 10) {
                System.out.println("1");
                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                double compare2 = getSearch().get(compare).showPrice();

                if (getSearch().get(0).showPrice() >= a && getSearch().get(0).showPrice() <= b && getSearch().get(getSearch().size() - 1).showPrice() > a && getSearch().get(getSearch().size() - 1).showPrice() < b) {
                    ///goes through array
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).showPrice() >= a && getSearch().get(i).showPrice() <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 > b && compare2 > a) {
                    System.out.println("a");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < compare) {
                        if (getSearch().get(i).showPrice() >= a && getSearch().get(i).showPrice() <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 < a) {
                    System.out.println("b");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).showPrice() >= a && getSearch().get(i).showPrice() <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 > a) {
                    System.out.println("c");
                    int i = compareHigher;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).showPrice() >= a && getSearch().get(i).showPrice() <= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
            } else {
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).showPrice() >= a && getSearch().get(i).showPrice() <= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
        } else if (a > b) {
            System.out.println("Start2");
            searchAmountSort();
            searchAmountSortGhost();
            if (getSearch().size() >= 10) {

                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                double compare2 = getSearch().get(compare).showPrice();

                if (getSearch().get(0).showPrice() > a && getSearch().get(0).showPrice() < b && getSearch().get(getSearch().size() - 1).showPrice() > a && getSearch().get(getSearch().size() - 1).showPrice() < b) {
                    ///goes through array
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).showPrice() <= a && getSearch().get(i).showPrice() >= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }

                if (compare2 > b && compare2 > a) {
                    System.out.println("a2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < compare) {
                        if (getSearch().get(i).showPrice() <= a && getSearch().get(i).showPrice() >= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 < a) {
                    System.out.println("b2");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).showPrice() <= a && getSearch().get(i).showPrice() >= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
                if (compare2 < b && compare2 > a) {
                    System.out.println("c2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < getSearch().size()) {
                        if (getSearch().get(i).showPrice() <= a && getSearch().get(i).showPrice() >= b) {
                            result.add(new Entries());
                            result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                    getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                            i++;
                            j++;
                        } else {
                            i++;
                        }
                    }
                }
            } else {
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).showPrice() <= a && getSearch().get(i).showPrice() >= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    public void searchAmount(String a, double b) {
        searchDateSort();
        searchDateSortGhost();
        switch (a) {
            case "<":
                int i = 0;
                ///place in result array
                int j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).showPrice() < b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case ">":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).showPrice() > b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case "<=":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).showPrice() <= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case ">=":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).showPrice() >= b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            case "":
                i = 0;
                ///place in result array
                j = 0;
                while (i < getSearch().size()) {
                    if (getSearch().get(i).showPrice() == b) {
                        result.add(new Entries());
                        result.get(j).changeEntries(getSearch().get(i).getId(), getSearch().get(i).getFavorite(),
                                getSearch().get(i).getSubject(), getSearch().get(i).date(4), getSearch().get(i).getPrice().getFull(), getSearch().get(i).getNotes());
                        i++;
                        j++;
                    } else {
                        i++;
                    }
                }
                break;

            default:
                break;
        }
    }

    public void searchAmountGhost(double a, double b) {
        if (a < b) {
            System.out.println("ree");
            int g = getResults().size();
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            while (j < g) {
                if (getResults().get(i).showPrice() < a || getResults().get(i).showPrice() > b) {
                    getResults().remove(i);
                    g = getResults().size();
                } else {
                    i++;
                    j++;
                }
            }
        } else if (a > b) {
            System.out.println("ree2");
            int g = getResults().size();
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            while (j < g) {
                if (getResults().get(i).showPrice() > a || getResults().get(i).showPrice() < b) {
                    getResults().remove(i);
                    g = getResults().size();
                } else {
                    i++;
                    j++;
                }
            }
        }
    }

    public void searchAmountGhost(String a, double b) {
        int g = getResults().size();
        switch (a) {
            case "<":
                ///goes through array
                int i = 0;
                ///place in result array
                int j = 0;
                while (j < g) {
                    if (getResults().get(i).showPrice() > b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case ">":
                g = getResults().size();
                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).showPrice() < b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case "<=":
                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).showPrice() > b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case ">=":
                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).showPrice() < b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }
                break;

            case "":

                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                while (j < g) {
                    if (getResults().get(i).showPrice() != b) {
                        getResults().remove(i);
                        g = getResults().size();
                    } else {
                        i++;
                        j++;
                    }
                }

                break;

            default:
                break;
        }
    }

    public void MasterSearch(String ID, int ID2, int ID3, boolean fav, boolean fav2, String Sub, String Date, int Date2, int Date3, boolean EORL, boolean EORL2, String Amount, int Amount2, int Amount3) {
        //status is an indicator of whether or not to use the non  or the ghost   method of search 
        masterID(ID, ID2, ID3);
        masterFavorite(fav, fav2);
        masterSubject(Sub);
        masterDate(Date, Date2, Date3);
        masterEORL(EORL, EORL2);
        masterAmount(Amount, Amount2, Amount3);
    }

    public void masterID(String ID, int ID2, int ID3) {
        switch (ID) {
            case "":
                System.out.println("ree");
                if (ID2 > 0 && ID3 > 0) {
                    searchID(ID2, ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 <= 0) {
                    ;
                }

                if (ID2 > 0 && ID3 <= 0) {
                    searchID(ID2);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 > 0) {
                    searchID(ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }
                break;

            case "<":
                if (ID2 > 0 && ID3 <= 0) {
                    searchID("<", ID2);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID3 > 0 && ID2 <= 0) {
                    searchID("<", ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 > 0 && ID3 > 0) {
                    searchID(ID2, ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 <= 0) {
                    ;
                }
                break;

            case ">":
                if (ID2 > 0 && ID3 <= 0) {
                    searchID(">", ID2);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID3 > 0 && ID2 <= 0) {
                    searchID(">", ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 > 0 && ID3 > 0) {
                    searchID(ID2, ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 <= 0) {
                    ;
                }
                break;

            case "<=":
                if (ID2 > 0 && ID3 <= 0) {
                    searchID("<=", ID2);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID3 > 0 && ID2 <= 0) {
                    searchID("<=", ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 > 0 && ID3 > 0) {
                    searchID(ID2, ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 <= 0) {
                    ;
                }
                break;

            case ">=":
                if (ID2 > 0 && ID3 <= 0) {
                    searchID(">=", ID2);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID3 > 0 && ID2 <= 0) {
                    searchID(">=", ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 > 0 && ID3 > 0) {
                    searchID(ID2, ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 <= 0) {
                    ;
                }
                break;

            default:
                if (ID2 > 0 && ID3 > 0) {
                    searchID(ID2, ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 <= 0) {
                    ;
                }

                if (ID2 > 0 && ID3 <= 0) {
                    searchID(ID2);
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 > 0) {
                    searchID(ID3);
                    changeSearchStatus();
                    changeResultStatus();
                }
                break;
        }
    }

    public void masterFavorite(boolean fav, boolean fav2) {
        if (SearchStatus) {
            if (fav) {
                if (fav2) {
                    searchFavoriteGhost(fav2);
                } else {
                    searchFavoriteGhost(fav2);
                }
            } else {
                ;
            }
        } else {
            if (fav) {
                if (fav2) {
                    searchFavorite(fav2);
                    changeSearchStatus();
                    changeResultStatus();
                } else {
                    searchFavoriteGhost(fav2);
                    changeSearchStatus();
                    changeResultStatus();
                }
            } else {
                ;
            }
        }
    }

    public void masterSubject(String Sub) {
        if (SearchStatus) {
            if (Sub.equals("")) {
                ;
            } else {
                searchSubjectGhost(Sub);
            }
        } else {
            if (Sub.equals("")) {
                ;
            } else {
                searchSubject(Sub);
                changeSearchStatus();
                changeResultStatus();
            }
        }
    }

    public void masterDate(String date, int date2, int date3) {
        if (SearchStatus) {
            switch (date) {
                case "":
                    if (date2 > 0 && date3 > 0) {
                        searchDateGhost(date2, date3);
                    }

                    if ((date2 <= 0 && date3 <= 0) || (date2 > 0 && date3 <= 0) || (date2 <= 0 && date3 > 0)) {
                        ;
                    }
                    break;

                case "<":
                    if (date2 > 0 && date3 <= 0) {
                        searchDateGhost("<", date2);
                    }

                    if (date3 > 0 && date2 <= 0) {
                        searchDateGhost("<", date3);
                    }

                    if (date2 > 0 && date3 > 0) {
                        searchDateGhost(date2, date3);
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        ;
                    }
                    break;

                case ">":
                    if (date2 > 0 && date3 <= 0) {
                        searchDateGhost(">", date2);
                    }

                    if (date3 > 0 && date2 <= 0) {
                        searchDateGhost(">", date3);
                    }

                    if (date2 > 0 && date3 > 0) {
                        searchDateGhost(date2, date3);
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        ;
                    }
                    break;

                case "<=":
                    if (date2 > 0 && date3 <= 0) {
                        searchDateGhost("<=", date2);
                    }

                    if (date3 > 0 && date2 <= 0) {
                        searchDateGhost("<=", date3);
                    }

                    if (date2 > 0 && date3 > 0) {
                        searchDateGhost(date2, date3);
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        ;
                    }
                    break;

                case ">=":
                    if (date2 > 0 && date3 <= 0) {
                        searchDateGhost(">=", date2);
                    }

                    if (date3 > 0 && date2 <= 0) {
                        searchDateGhost(">=", date3);
                    }

                    if (date2 > 0 && date3 > 0) {
                        searchDateGhost(date2, date3);
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        ;
                    }
                    break;

                default:
                    if (date2 > 0 && date3 > 0) {
                        searchDateGhost(date2, date3);
                    }

                    if ((date2 <= 0 && date3 <= 0) || (date2 > 0 && date3 <= 0) || (date2 <= 0 && date3 > 0)) {
                        ;
                    }
                    break;
            }
        } else {
            switch (date) {
                case "":
                    if (date2 > 0 && date3 > 0) {
                        searchDate(date2, date3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if ((date2 <= 0 && date3 <= 0) || (date2 > 0 && date3 <= 0) || (date2 <= 0 && date3 > 0)) {
                        ;
                    }
                    break;

                case "<":
                    if (date2 > 0 && date3 <= 0) {
                        searchDate("<", date2);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date3 > 0 && date2 <= 0) {
                        searchDate("<", date3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 > 0 && date3 > 0) {
                        searchDate(date2, date3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        ;
                    }
                    break;

                case ">":
                    if (date2 > 0 && date3 <= 0) {
                        searchDate(">", date2);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date3 > 0 && date2 <= 0) {
                        searchDate(">", date3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 > 0 && date3 > 0) {
                        searchDate(date2, date3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        ;
                    }
                    break;

                case "<=":
                    if (date2 > 0 && date3 <= 0) {
                        searchDate("<=", date2);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date3 > 0 && date2 <= 0) {
                        searchDate("<=", date3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 > 0 && date3 > 0) {
                        searchDate(date2, date3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        ;
                    }
                    break;

                case ">=":
                    if (date2 > 0 && date3 <= 0) {
                        searchDate(">=", date2);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date3 > 0 && date2 <= 0) {
                        searchDate(">=", date3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 > 0 && date3 > 0) {
                        searchDate(date2, date3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        ;
                    }
                    break;

                default:
                    if (date2 > 0 && date3 > 0) {
                        searchDate(date2, date3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if ((date2 <= 0 && date3 <= 0) || (date2 > 0 && date3 <= 0) || (date2 <= 0 && date3 > 0)) {
                        ;
                    }
                    break;
            }
        }
    }

    public void masterEORL(boolean EORL, boolean EORL2) {
        if (SearchStatus) {
            if (EORL) {
                if (EORL2) {
                    searchGainsGhost();
                } else {
                    searchLossesGhost();
                }
            } else {
                ;
            }
        } else {
            if (EORL) {
                if (EORL2) {
                    searchGainsGhost();
                    changeSearchStatus();
                    changeResultStatus();
                } else {
                    searchLossesGhost();
                    changeSearchStatus();
                    changeResultStatus();
                }
            } else {
                ;
            }
        }
    }

    public void masterAmount(String Amount, int Amount2, int Amount3) {
        if (SearchStatus) {
            switch (Amount) {
                case "":
                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmountGhost(Amount2, Amount3);
                    }

                    if ((Amount2 <= 0 && Amount3 <= 0) || (Amount2 > 0 && Amount3 <= 0) || (Amount2 <= 0 && Amount3 > 0)) {
                        ;
                    }
                    break;

                case "<":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        searchAmountGhost("<", Amount2);
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        searchAmountGhost("<", Amount3);
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmountGhost(Amount2, Amount3);
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        ;
                    }
                    break;

                case ">":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        searchAmountGhost(">", Amount2);
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        searchAmountGhost(">", Amount3);
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmountGhost(Amount2, Amount3);
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        ;
                    }
                    break;

                case "<=":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        searchAmountGhost("<=", Amount2);
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        searchAmountGhost("<=", Amount3);
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmountGhost(Amount2, Amount3);
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        ;
                    }
                    break;

                case ">=":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        searchAmountGhost(">=", Amount2);
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        searchAmountGhost(">=", Amount3);
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmountGhost(Amount2, Amount3);
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        ;
                    }
                    break;

                default:
                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmountGhost(Amount2, Amount3);
                    }

                    if ((Amount2 <= 0 && Amount3 <= 0) || (Amount2 > 0 && Amount3 <= 0) || (Amount2 <= 0 && Amount3 > 0)) {
                        ;
                    }
                    break;
            }
        } else {
            switch (Amount) {
                case "":
                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmount(Amount2, Amount3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if ((Amount2 <= 0 && Amount3 <= 0) || (Amount2 > 0 && Amount3 <= 0) || (Amount2 <= 0 && Amount3 > 0)) {
                        ;
                    }
                    break;

                case "<":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        searchAmount("<", Amount2);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        searchAmount("<", Amount3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmount(Amount2, Amount3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        ;
                    }
                    break;

                case ">":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        searchAmount(">", Amount2);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        searchAmount(">", Amount3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmount(Amount2, Amount3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        ;
                    }
                    break;

                case "<=":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        searchAmount("<=", Amount2);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        searchAmount("<=", Amount3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmount(Amount2, Amount3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        ;
                    }
                    break;

                case ">=":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        searchAmount(">=", Amount2);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        searchAmount(">=", Amount3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmount(Amount2, Amount3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        ;
                    }
                    break;

                default:
                    if (Amount2 > 0 && Amount3 > 0) {
                        searchAmount(Amount2, Amount3);
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if ((Amount2 <= 0 && Amount3 <= 0) || (Amount2 > 0 && Amount3 <= 0) || (Amount2 <= 0 && Amount3 > 0)) {
                        ;
                    }
                    break;
            }
        }

    }

    public void resetSearch() {
        clearSearch();
        clearResults();
    }
//a represents entry id will the method will the element that enrty resides in master array

    public int searchEntryPlacement(int a) {
        int place = 0;
        while (place < getArray2().size()) {
            if (getArray2().get(place).getId() != a) {
                place++;
            } else {
                return place;
            }
        }
        return -1;
    }

    public void editAddEntry(boolean f, String s, String d, String a, String n) {
        getArray2().add(new Entries(f, s, d, a, n));
        getEdits().add("e");
    }

    public void editRemoveEntry(int a) {
        getArray2().remove(a);
        for (int i = getArray2().size(); i > a; i--) {
            getArray2().get(i).changeId((getArray2().get(i).getId() - 1));
        }
        getEdits().add("r");
        
    }
/// a for all the edit methods will be form searchEntryPlacement()

    public void editEntriesFavorite(int a, boolean b) {
        getArray2().get(a).changeFavorite(b);
        getEdits().add(a + "f");
    }

    public void editEntriesFavorite(int a, String b) {
        getArray2().get(a).changeFavorite(Boolean.parseBoolean(b));
        getEdits().add(a + "f");
    }

    public void editEntriesSubject(int a, String b) {
        getArray2().get(a).changeSubject(b);
        getEdits().add(a + "s");
    }

    public void editEntriesDate(int a, String b) {
        getArray2().get(a).changeDate(b);
        getEdits().add(a + "d");
    }

    public void editEntriesAmount(int a, String b) {
        getArray2().get(a).changePrice(b);
        getEdits().add(a + "a");
    }

    public void editEntriesNotes(int a, String b) {
        getArray2().get(a).changeNotes(b);
        getEdits().add(a + "n");
    }

    public double addGains() {
        MasterSearch("", 0, 0, false, true, "", "", 0, 0, true, true, "", 0, 0);
        double total = 0;
        for (int i = 0; i < getResults().size(); i++) {
            total += getResults().get(i).showPrice();
        }
        ///would be on exit or back button on gui
        clearResults();
        clearSearch();
        return total;
    }

    public double addLosses() {
        MasterSearch("", 0, 0, false, true, "", "", 0, 0, true, false, "", 0, 0);
        double total = 0;
        for (int i = 0; i < getResults().size(); i++) {
            total += getResults().get(i).showPrice();
        }
        ///would be on exit or back button on gui
        clearResults();
        clearSearch();
        return total;
    }

    public double getTotal() {
        double total = addGains() - addLosses();
        return total;
    }

    public String[] masterSave(ArrayList<Entries> a) {
        String[] save = new String[50];
        for (int i = 0; i < a.size(); i++) {
            save[i] = a.get(i).getEntrySave();
        }
        return save;
    }

}
