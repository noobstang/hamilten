/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */
package com.jchan16.ics4u.tpj5;

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
    public static ArrayList<Entries> Array2 = new ArrayList<Entries>();
    //copy of master to be used with search
    public static ArrayList<Entries> Array3 = new ArrayList<Entries>();
    //display results of whatever sort/search
    public static ArrayList<Entries> result = new ArrayList<Entries>();
//not used anymore but it would have been used to get unique years and store here
    private ArrayList<Integer> years = new ArrayList<Integer>();
//Array that stores simplified edits count
    private ArrayList<String> edits = new ArrayList<String>();
//Array that stores the new edited string of the edited entry 
    private ArrayList<String> edits2 = new ArrayList<String>();
    //index for results:
    //0=empty results, 1=array2 copied, 2=sorted results,3=search
    private boolean SearchStatus;
    private boolean ResultStatus;
//Used mostly in add/substracing entries in edit methods
    private int entryCount = 0;

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
    
    //public access to edits variable
    public ArrayList<String> getEditedStringArray() {
        return edits2;
    }
//subtracts entry count by 1
    public void subtractCount2() {
        entryCount--;
    }
//adds entrycount by 1
    public void addCount2() {
        entryCount++;
    }
//Wipes all entries only in search array, then sets searchStatus to false meaning no entries in it
    public void clearSearch() {
        Array3.clear();
        revertSearchStatus();
    }
    
    public void clearArray2(){
        Array2.clear();
    }
//a is supposed to getEntryTotal() then getCountInEntry() and a is getCOuntInEntry
    public void setCount(int a) {
        if(a<0){
        entryCount=0;    
        }else{
        entryCount = a;    
        }
    }
//returns entry count in program
    public int getCount2() {
        return entryCount;
    }
//returns edit array
    public ArrayList<String> getEdits() {
        return edits;
    }
//sets searchStatus to true meaning search array is full , called only in master*element of entry* methods
    //Should work since all seacrhes will go through master
    public void changeSearchStatus() {
        SearchStatus = true;
    }
//returns searchStatus
    public boolean showSearchStatus() {
        return SearchStatus;
    }
//Sets searchStatus to false, meaning search array is empty
    public void revertSearchStatus() {
        SearchStatus = false;
    }
//Looks for size of edits, if Less that or equal to 1 then returns false meaning no edits need to be made
    //If there is at least 1 edit then returns true
    public boolean getEditStatus() {
        if (getEdits().size() <= 0) {
            return false;
        } else {
            return true;
        }
    }
//wipes all elements in edits used after edits are run and user still in program    
    public void clearEdits(){
    edits.clear();    
    }
//Clears all elements in edits2 array, used used after edits are run and user still in program   
    public void clearEdits2(){
    edits2.clear();    
    }
    
    //Combination of clear edits and clearedits2
    public void clearEdits3(){
    edits.clear();
    edits2.clear();
    }
//Dead method, supposed to work with connector class , result set is data from a read database
    public Array2(ResultSet a) {
        fill(a);

    }
//Fills array 2 by copying test2 array list Entries from datbase dummy, mostly used for testing
    public Array2(DatabaseDummy a) {
        fill(a);
        SearchStatus = false;
        ResultStatus = false;
    }
//Fills array2 by coping an arrayList of entries, b is supposed to be save log from save class
    public Array2(ArrayList<Entries> b) {
        fill(b);

    }
//array2 is not filled in this setter,if user has no files or just starts the program, this method is used
    public Array2() {
        SearchStatus = false;
        ResultStatus = false;
    }

//    public Array2(){
//        
//    }
//    public ArrayList<Integer> getYearArray() {
//        return years;
//    }
//needs database, reads result set, works with connector class
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
//copies arraylist a,a should be savelog from save class when being called
    public void fill(ArrayList<Entries> a) {
        for (int p = 0; p < a.size(); p++) {
            Array2.add(new Entries());
            Array2.get(p).changeEntries(a.get(p).getId(), a.get(p).getFavorite(), a.get(p).getSubject(),
                    a.get(p).date(4), a.get(p).getPrice().getFull(), a.get(p).getNotes());
        }
    }
//results array copies array list a, a should always be array2, then sets result status as true meaning results arrayis filled
    public void fill2(ArrayList<Entries> a) {
        for (int p = 0; p < a.size(); p++) {

            result.add(new Entries());
            result.get(p).changeEntries(a.get(p).getId(), a.get(p).getFavorite(), a.get(p).getSubject(),
                    a.get(p).date(4), a.get(p).getPrice().getFull(), a.get(p).getNotes());
        }
        changeResultStatus();
    }
//search array copies arraylist a, a should be array2, then sets search status as true, meaning search array is filled 
    public void fill3(ArrayList<Entries> a) {
        for (int p = 0; p < a.size(); p++) {

            Array3.add(new Entries());
            Array3.get(p).changeEntries(a.get(p).getId(), a.get(p).getFavorite(), a.get(p).getSubject(),
                    a.get(p).date(4), a.get(p).getPrice().getFull(), a.get(p).getNotes());
        }
        changeSearchStatus();
    }
//Wipes all elements of results array then sets result status to false, meaning to elements 
    public void clearResults() {
        result.clear();
        revertResultStatus();
    }
//sets reultstatus as true meaning result has been filled and is not empty
    public void changeResultStatus() {
        ResultStatus = true;
    }

    public boolean showResultStatus() {
        return ResultStatus;
    }
//Sets resultstatus as false meaning result array is empty
    public void revertResultStatus() {
        ResultStatus = false;
    }
//
    public ArrayList<Entries> getArray2() {
        return Array2;
    }

    public ArrayList<Entries> getResults() {
        return result;
    }

    public ArrayList<Entries> getSearch() {
        return Array3;
    }

//counter is actually a dead variable, do not use this method
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

//counter is a dead variable, don't use method anywhere else in code
    public int getCounter() {
        return counter;
    }
//result array copies array2 using fill2 method, results array is then sorted by the subjects of entries
//in A-Z order
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
// results array is  sorted by the subjects of entries in A-Z order
    //Does not copy from array2, results array must be filled to make method works

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

//Works exatly like sortSubject but sorts results array by subjects Z-A order hence the R in method name
    
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
//works excatly like sortSubjectGhost but sorts entries in results array 
// by entries subject Z-A  
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
////Works exactly like soryYear but sorts results array by entries year from oldest to newest
    public void RsortYear() {
        fill2(Array2);
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(3));

            int ender1 = Integer.parseInt(getResults().get(ender).date(3));

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
                    key1 = Integer.parseInt(getResults().get(key).date(3));
                    ender1 = Integer.parseInt(getResults().get(ender).date(3));
                }
            }
        }
    }
//Works exactly like  RsortYear but sorts results by entry years oldest to newest
    public void RsortYearGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getResults().get(key).date(3));

            int ender1 = Integer.parseInt(getResults().get(ender).date(3));

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
                    key1 = Integer.parseInt(getResults().get(key).date(3));
                    ender1 = Integer.parseInt(getResults().get(ender).date(3));
                }
            }
        }
    }
//Fill2 is called(results copies elements of array 2 array), results are then sorted by their years
    //from latest to oldest
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
    //Does the exact same thing as sortYear but result set does not copy array2
    //it just sorts whatevers in result array at the moment
    public void sortYearGhost() {
        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(getArray2().get(key).date(3));
            int ender1 = Integer.parseInt(getArray2().get(ender).date(3));
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
                    key1 = Integer.parseInt(getArray2().get(key).date(3));
                    ender1 = Integer.parseInt(getArray2().get(ender).date(3));
                }
            }
        }

    }
//Result array copies array2, results array then is sorted by entry  day value lowest to greatest
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
//Works exactly like sort day but only sorts whatevers in result array already by entry day value from lowest to highest(1-31)
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
//Works exatly like sortDay but results array is sorted by entry day vlaues from g=highest to lowest(31-1)
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
//Works exatactly like RsortDay, but results array doesn't copy array 2
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
//Results array copies array2, results array is sorted by entries month value from lowest to highest(1-12)
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
//Works excatly like sortMonth,but Results array does not copy from array2,method only sorts what's already in results array
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
//Works exactly like sortMonth but entries month values are sorted from higest to lowest(12-1)
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
//Works exactly like RsortMonth but results array doesn't copy from array2
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
//year array varibale might be dead.don't use method
    public ArrayList<Integer> getYearArray() {
        return years;
    }
//Would have printed out contents of year array
    public void showYears() {
        for (int i = 0; i < getYearArray().size(); i++) {
            System.out.println(getYearArray().get(i));
        }
    }
//runs theough array2 to get indivual years with no repeats
    public void getAllYears() {
        sortYear();
        sortYearGhost();
        int cursor = Integer.parseInt(getArray2().get(0).date(3));
        years.add(cursor);
        for (int j = 1; j < getArray2().size(); j++) {
            cursor = Integer.parseInt(getArray2().get(j).date(3));
            //getArray2().get(i) is compared to all years inside year array
            //if it's different counter doesn't change  if not counter subtracts by 1
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
            //if counter is 0 that meansthat array2.get(i) is different
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
    //Results array copies array2, then results id sorted by the full day value of it's entries form
    //lowest to highest(Techinally newest to oldest)
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
//Works the same as sortDateL but only works with entries already in result instead of fill results with a copy of array2
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
//works just like sorteDateL but results array is sorted by highest to lowest full day value of entires
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
//works just like sortDateGhost but sorts result array by full day value of entry highest to lowest
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
//searches array2 entries, if entry has a true in it's checker variable then it adds it to the results array
    //this method should only be used when resutls array is empty
    public void searchGains() {
        System.out.println("gains");
        ///goes through array
        int i = 0;
        ///place in result array
        int j = 0;
        while (i < getArray2().size()) {
            if (getArray2().get(i).showEORL() == true) {
//                System.out.println("ree");
                result.add(new Entries());
                getResults().get(j).changeEntries(getArray2().get(i).getId(), getArray2().get(i).getFavorite(),
                        getArray2().get(i).getSubject(), getArray2().get(i).date(4), getArray2().get(i).getPrice().getFull(), getArray2().get(i).getNotes());
                //j only goes up here because if it always when up after each
                //element array2 the change entry wouldn't work as you'ree asking to change
                //like the 5th element of results when theres only 2 elements
                j++;
                i++;
            } else if (getArray2().get(i).showEORL() == false) {
                //j doesn't change here but i does so it continues looking through array2
//                System.out.println("nee");
                i++;
            }
        }
    }
    
//Method to be used when results array is filled, it will remove any entry woth checker varibale that is false
    public void searchGainsGhost() {
        int g = getResults().size();
        ///goes through array
        int i = 0;
        ///place in result array
        int j = 0;
        while (j < g) {
            //If statement remove any entry with a checker variable equal to false
            if (getResults().get(i).showEORL() == false) {
                getResults().remove(i);
                g = getResults().size();
            } else {
                //I doesn't need to be here, tail end of a copy paste essentially
//                i++;
                j++;
            }
        }
    }
//acts like the other sortchost methods, will only sort results array that is already filled rather than copying array2
    //will put gentry with gains in money at the top
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
//Works just search gains but with losses
    public void searchLosses() {
        System.out.println("Losses");
        ///goes through array
        int i = 0;
        ///place in result array
        int j = 0;
        while (i < getArray2().size()) {
            if (getArray2().get(i).showEORL() == false) {
                result.add(new Entries());
                getResults().get(j).changeEntries(getArray2().get(i).getId(), getArray2().get(i).getFavorite(),
                        getArray2().get(i).getSubject(), getArray2().get(i).date(4), getArray2().get(i).getPrice().getFull(), getArray2().get(i).getNotes());
                //j increase here only so that the next entry isn't a loss, it won't go up and
                //get an outofbounds error in results array
                i++;
                j++;
            } else if(getArray2().get(i).showEORL() == true){
                i++;
            }
        }
    }
//works just like searchGainsGhost instead if the entry has a true to t checker variable, it removes the element from results array
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
//works just like sortGains instead putting entries with checker variable of false near the top
    public void sortLosesGhost() {

        for (int j = 1; j < getResults().size(); j++) {
            int key = j;
            int ender = key - 1;
            boolean key1 = getResults().get(key).showEORL();
//            double key3 = getResults().get(key).showPrice();
            boolean ender1 = getResults().get(ender).showEORL();
//            double ender3 = getResults().get(ender).showPrice();

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
                    key1 = getResults().get(key).showEORL();
                    ender1 = getResults().get(ender).showEORL();
//                    key3 = getResults().get(key).showPrice();
//                    ender3 = getResults().get(ender).showPrice();

                }

            }
        }

    }
//prints out all entries in array2 out in console from top down
    //only used for testing
    public void printArray2() {
        for (int j = 0; j < getArray2().size(); j++) {
            System.out.println(getArray2().get(j).getId() + " " + getArray2().get(j).getFavorite() + " " + getArray2().get(j).getSubject() + " " + getArray2().get(j).date(4) + " " + getArray2().get(j).getPrice().getFull()
                    + " " + getArray2().get(j).getNotes());
        }
    }
//prints out all entries in search array from top down
    //only used for testing
    public void printSearch() {
        for (int j = 0; j < getSearch().size(); j++) {
            System.out.println(getSearch().get(j).getId() + " " + getSearch().get(j).getFavorite() + " " + getSearch().get(j).getSubject() + " " + getSearch().get(j).date(4) + " " + getSearch().get(j).getPrice().getFull()
                    + " " + getSearch().get(j).getNotes());
        }
    }
//prints out all elements in result array from top down
    public void printResults() {
        for (int i = 0; i < getResults().size(); i++) {
            System.out.println(getResults().get(i).getId() + " " + getResults().get(i).getFavorite() + " " + getResults().get(i).getSubject() + " " + getResults().get(i).date(4) + " " + getResults().get(i).getPrice().getFull()
                    + " " + getResults().get(i).getNotes());
        }
    }
//prints out all string s in edit array from top down
    //again mostly used for testing
    public void printEdits() {
        for (int j = 0; j < getEdits().size(); j++) {
            System.out.println(getEdits().get(j));
        }
    }
//prints out edited entries in text file format into console
    //again mostly used for testing
    public void printEditedStrings() {
        for (int j = 0; j < getEditedStringArray().size(); j++) {
            System.out.println(getEditedStringArray().get(j));
        }
    }
//results array copies array2, then results array is sorted by the double of the entry diregarding the gains or losses from lowest to highest
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
//works just like sortAMount but results doesn't copy array2 , method will sort results array by entry double 
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
//just like sort amount by instead of sorting to lowest to highest double value it sorts by highest to lowest
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
//Works similar to Rsort amount but results array doesn't copy array2, it just sorts the element s by double value higest to lowest with elements already in results
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
//works just like sortSubject but instead of result array copying array2, search array copies arry 2, again still sorts elements by a-z based off subject
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
//works just like sortDateL but instead of results copying array2 search array copies array2 and then sorts its elment sby lowest to highest full day value of its entries
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
//works just like searchDateSort but instead of search array copying array2, this method will just sort whetevers in search array by fulldayvalue lowest to highest
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
    //just like searchSubjectSort but search doesn't copy array 2 , still sorts elements by subject a-z
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
//search copies array2 and is sorted from a-z entries, then insertion sorts determines where it should read from
    //once it reads an entry with desired subject, it adds the entry to results array.
    public void searchSubject(String a1) {
        //search is sorted from subject a-z
        searchSubjectSort();
        searchSubjectSortGhost();

        if (getSearch().size() > 10) {
            //halfways point on search array
            int compare = getSearch().size() / 2;
//            System.out.println(compare);
            int compareHigher = 0 + (compare / 2);
//            System.out.println(compareHigher);   
            String ident = a1.substring(0);
            //1st char of the subject of the halfway element of search array
            String compare2 = getSearch().get(compare).getSubject().substring(0);
//            System.out.println(compare2);
//            System.out.println(ident.compareTo(compare2));
//if the 1st element of search and last element of search have the same 1st char meaning they share a similar subject
            if (ident.compareTo(getSearch().get(0).getSubject()) == 0 && ident.compareTo(getSearch().get(getSearch().size() - 1).getSubject()) == 0) {
                ///goes through array
                int i = 0;
                ///place in result array
                int j = 0;
                //method will then run through all of array 
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
            //if compare returns negative number then char of halfway is "lower" to string inouted
            //ex c compared to a.
            if (ident.compareTo(compare2) > 0) {
//                System.out.println("comapre2 is above ident");
                ///goes through array
                int i = compare;
                ///place in result array
                int j = 0;
                //looks at elements past halfway point to the length of array
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
//if halway point 1st char is the same 
            if (ident.compareTo(compare2) == 0) {
//                System.out.println("compare2 is equal to ident");
                ///goes through array
                int i = 0;
                ///place in result array
                int j = 0;
                //looks through all of the array
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
//if halway point char is "greater than string inputed ex a comapred to d
            if (ident.compareTo(compare2) < 0) {
//                System.out.println("comapre2 is less than ident");
                ///goes through array
                int i = compare;
                ///place in result array
                int j = 0;
                //looks only at halfway point to zero element
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
//if search only has 10 or less elements will just look through entire array
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
//use method only if results array is filled, goes through all of array, if element entry subject doesn not match inputted, it is removed
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
             //search is sorted from fulldayvalue lowest to highest
            searchDateSort();
            searchDateSortGhost();
            if (getSearch().size() >= 10) {
                System.out.println("1");
                //halfway point array
                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                //full day value of halway point element
                int compare2 = getSearch().get(compare).date2(4);
//If last and first element is inbetween range of a and b
                if (getSearch().get(0).date2(4) > a && getSearch().get(0).date2(4) < b && getSearch().get(getSearch().size() - 1).date2(4) > a && getSearch().get(getSearch().size() - 1).date2(4) < b) {
                    ///goes through array
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches through all of array
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
                //if halfway point value is greater than than both values
                if (compare2 > b && compare2 > a) {
                    System.out.println("a");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //looks through only first half of array
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
                //if halfway element fulldayvalue is less than both values
                if (compare2 < b && compare2 < a) {
                    System.out.println("b");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    //only looks at the later half of array
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
                //if the halway point fulldayvalue is inbetween both values
                if (compare2 < b && compare2 > a) {
                    System.out.println("c");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches through all of array
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
                //if search array size is less than 10 then searches through whole array
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
            //if a is greater than b
        } else if (a > b) {
            System.out.println("Start2");
             //search is sorted from fulldayvalue lowest to highest
            searchDateSort();
            searchDateSortGhost();
            if (getSearch().size() >= 10) {
//halfway point of search array
                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                //full day value of halfway point
                int compare2 = getSearch().get(compare).date2(4);
//if last and first element is inbetween a and b
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
//if halfway fulldayvalue is greater than both a and b
                if (compare2 > b && compare2 > a) {
                    System.out.println("a2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //only searches the first half of search array
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
                //if halfway point fulldayvalue is less than both a and b
                if (compare2 < b && compare2 < a) {
                    System.out.println("b2");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    //searches later half of array
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
                // if halfway point fulldayvalue is less in the range of a and b
                if (compare2 < b && compare2 > a) {
                    System.out.println("c2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches all of array
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
                // if search array size is equal to less than 10
            } else {
                int i = 0;
                ///place in result array
                int j = 0;
                //searches through all of the array
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
//works just like searchDate(int,int) but runs through results array instead of search array
    public void searchDateGhost(int a, int b) {
        //looks through all of the results array
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
            //looks through all of the array
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
//search array copies array2 and then seaches based off sign and number
    public void searchDate(String a, int b) {
        //search is sorted from fulldayvalue lowest to highest
        searchDateSort();
        searchDateSortGhost();
        switch (a) {
            case "<":
                int i = 0;
                ///place in result array
                int j = 0;
                //looks through all of the array
                while (i < getSearch().size()) {
                    if (getSearch().get(i).date2(4) < b) {
                        //will only add entry if fullday value is less than inputed fulldayvalue
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
                //searches through all of the array
                while (i < getSearch().size()) {
                    if (getSearch().get(i).date2(4) > b) {
                        //will only add entries from search to results if fulldayvalue is greater than inputed fulldayvalue
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
//works the same as case < but also equals to inputed fulldayvalue will accept 
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
//same as > case but also accepts equal to as a parameter to be put into results array
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
//works the same as searchdate9string,int) but only removes entries fromr esults array
    public void searchDateGhost(String a, int b) {
        int g = getResults().size();
        switch (a) {
            case "<":
                ///goes through array
                int i = 0;
                ///place in result array
                int j = 0;
                //searches through all of array
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
                //searches through all of array
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
                //searches through all of the array
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
                //searches through all of the arraya
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
//If there is no string the the method will only keep entries with the same fulldayvalue as the one inputed 
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
//Results array copies array2, elements then sorted by lowest to highest ID
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
//Same as sortID but only works in result array, result array doesn't copy array2 still sorts from lowest to highest ID
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
//Works just like sortID but arranges elements by highest to lowest ID
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
//Like sortIDGhost but arranges elements  form highest to lowest ID
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
// search array copies array2, elements then arranged from lowest to highest id
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
//works just like searchIDSORT but search array doesn't copy array2 method will just sort whats in search array from lowest to highest ID 
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
//search array copies array2,search is sorted with ids from lowest to high,method will look for entries with ids that fit in between range a to b
    public void searchID(int a, int b) {
        if (a < b) {
            searchIDSort();
            searchIDSortGhost();
            if (getSearch().size() >= 10) {
                System.out.println("1");
                //halfway point of search array
                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                //id of Halfway point
                int compare2 = getSearch().get(compare).getId();
//if halfway point ID is greater than a and b
                if (compare2 > b && compare2 > a) {
                    System.out.println("a");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //methods looks at first half of the search array
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
                //if halfway point ID is less than a and b
                if (compare2 < b && compare2 < a) {
                    System.out.println("b");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    //searches through latter half of array
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
                //if halfway point ID is inbetween a and b
                if (compare2 < b && compare2 > a) {
                    System.out.println("c");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches through the whole array
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
                //if search array size is 10 or less
            } else {
                int i = 0;
                ///place in result array
                int j = 0;
                //searches all of the array
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
            searchIDSort();
            searchIDSortGhost();
            if (getSearch().size() >= 10) {
//Halfway point of array
                int compare = getSearch().size() / 2;
//                int compareHigher = 0 + (compare / 2);
//Id of halfway point
                int compare2 = getSearch().get(compare).getId();
                System.out.println(compare2);
                // if halfway point is greater than a and b
                if (compare2 > b && compare2 > a) {
                    System.out.println("a2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches first half of array only
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
                //if halfway point id is less than a and b
                if (compare2 < b && compare2 < a) {
                    System.out.println("b2");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    //searches latter half pf array
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
                //if halfway point id is inbetween b and a
                if (compare2 > b && compare2 < a) {
                    System.out.println("c2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches all of the array
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
                //if search array size is less then 10
            } else {
                int i = 0;
                ///place in result array
                int j = 0;
                //searchs through all of the array
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
//search array copies array2, will then add entries to result array of they fit the parameter
    public void searchID(String a, int b) {
        //search is sorted with ids from lowest to high,
        searchIDSort();
        searchIDSortGhost();
        switch (a) {
       //parameter: id has to be <b
            case "<":
                int i = 0;
                ///place in result array
                int j = 0;
                //searches through all og the array
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
//Paramter id has to be >b
            case ">":
                i = 0;
                ///place in result array
                j = 0;
                //searches through all of the array
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
//paramater id has to be <=b
            case "<=":
                i = 0;
                ///place in result array
                j = 0;
                //searches through all of the array
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
//paramter >=b
            case ">=":
                i = 0;
                ///place in result array
                j = 0;
                //searches through all the array
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
//parameter: =b
            case "":
                i = 0;
                ///place in result array
                j = 0;
                //searches through all of the array
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
//searches for a specific id, search array copies array2
    public void searchID(int a) {
         //search is sorted with ids from lowest to high,
        searchIDSort();
        searchIDSortGhost();
        if (getSearch().size() >= 10) {
            System.out.println("1");
            //halfway point of array
            int compare = getSearch().size() / 2;
            int compareHigher = 0 + (compare / 2);
            //id of halfway point
            int compare2 = getSearch().get(compare).getId();
//if halfway is is greater than a
            if (compare2 > a) {
                System.out.println("a");
                int i = 0;
                ///place in result array
                int j = 0;
                //searches first half of the array
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
            //if halfway point is less than a
            if (compare2 < a) {
                System.out.println("b");
                int i = compare;
                ///place in result array
                int j = 0;
                //searches latter half of the array
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
            //if halfway point id is equal to a
            if (compare2 == a) {
                System.out.println("c");
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
            //if search array size is less than 10
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
//works just like searchID but only goes through results array than search array
    public void searchIDGhost(int a, int b) {
        int g = getResults().size();
        if (a < b) {
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            //searches through all of results array
            while (j < g) {
                if (getResults().get(i).getId() < a || getResults().get(i).getId() > b) {
                    getResults().remove(i);
                    g = getResults().size();
                } else {
                    i++;
                    j++;
                }
            }
            // if (a>b)
        } else {
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            //searches through all of results array
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
//works just like searchId(string,int) but reads results array rather than search array
    public void searchIDGhost(String a, int b) {
        int g = getResults().size();
        switch (a) {
            case "<":
                ///goes through array
                int i = 0;
                ///place in result array
                int j = 0;
                //searches through the whole array
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
                //searches through all of the array
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
                //searches through all of the array
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
                //searches through all of the array
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
//paramater if =b
            case "":
                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                //searches through all of array
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
//works just like searchId(int) but only runs trough results array
    public void searchIDGhost(int a) {
        int g = getResults().size();
        ///goes through array
        int i = 0;
        ///place in result array
        int j = 0;
        //searches through all of the array
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
//search array copies arry2, then sorted with favorites at the top
    public void searchFavorite(boolean a) {
        if (a == true) {
            System.out.println("Start");
            //search is sorted with favourites at the top
            searchFavoriteSort();
            searchFavoriteSortGhost();
            if (getSearch().size() >= 10) {
                System.out.println("1");
                //halfway point of array
                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                //favorite status of halfway point
                boolean compare2 = getSearch().get(compare).getFavorite();
//if last and first element is true
                if (getSearch().get(0).getFavorite() == true && getSearch().get(getSearch().size() - 1).getFavorite() == true) {
                    ///goes through array
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches the whole array
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
                //if halfway point is a non favorite 
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
                //if halfway point is a favorite
                if (compare2 == true) {
                    System.out.println("b");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches the whole array
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
                //if search array size is less than 10
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
            // if boolean is flase aka non favorites
        } else {
            System.out.println("Start2");
            //sorts search with favorites on top
            searchFavoriteSort();
            searchFavoriteSortGhost();
            if (getSearch().size() >= 10) {
                System.out.println("12");
                //halfway point of array
                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                //favorite status of halfway point entry
                boolean compare2 = getSearch().get(compare).getFavorite();
// if last and first entry are false
                if (getSearch().get(0).getFavorite() == false && getSearch().get(getSearch().size() - 1).getFavorite() == false) {
                    ///goes through array
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches the whole array
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
                // if halfway point is a favorite
                if (compare2 == true) {
                    System.out.println("a2");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    //searches through first half of the array
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
                //if halfway point is a non favorite
                if (compare2 == false) {
                    System.out.println("b2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches the whole array
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
                //if search array size is less than 10
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
//Works just like searchFavorite only runs through results array rather than seacrh array
    public void searchFavoriteGhost(boolean b) {
        int g = getResults().size();
        if (b == true) {
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            //searches through all of the array
            while (j < g) {
                if (getResults().get(i).getFavorite() == false) {
                    getResults().remove(i);
                    g = getResults().size();
//                    System.out.println("\n");
//                    printResults();
                } else {
                    i++;
                    j++;
                }
            }
            // if boolean a =false
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
//results array copies array2, then sorts elements with favoritess at the top
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
//works just like sortFavorite but instead of copying array2, tgis method just sorts results placing favorites at the top of the array
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
//search array copies array2, the sorts elements in search array with favorite entries first
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
//works just like searchFavoritessort, but search doesn't copy array2, this method just sorts whatevers in search array to have favorite entries on top
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
//seacrh array copies array2, search elements sorted by double values from lowest to highest no matter if it's a gains or lost
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
///works just like searchAmountsort but instead of copying array2 , method just sorts doubles values of elements already in search array by loweest to highest
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
//search array copies array 2 , search elements sorted where entries that gained money are placed at the top
    public void searchGainsSort() {
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
                    key1 = getSearch().get(key).showEORL();
                    ender1 = getSearch().get(ender).showEORL();

                }
            }
        }
    }
//works just like searchGainsSort but search doesn't copy array2 , method will just sort elements already insearch placing entries that gained money at the top
    public void searchGainsSortGhost() {
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
                    key1 = getSearch().get(key).showEORL();
                    ender1 = getSearch().get(ender).showEORL();

                }
            }
        }
    }
//search array copies array2, search elements are then arranged so that entries that lost money are placed at the top
    public void searchLossesSort() {
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
                    key1 = getSearch().get(key).showEORL();
                    ender1 = getSearch().get(ender).showEORL();

                }
            }
        }
    }
//same as searchLossesSort but search array doesn't copy array2, method will sort entriesmalready in search array that lost oney at the top of search array
    public void searchLossesSortGhost() {
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
                    key1 = getSearch().get(key).showEORL();
                    ender1 = getSearch().get(ender).showEORL();

                }
            }
        }
    }
//methods reads through sorted search array, if an entry double value is in range it is added to empty results array
    public void searchAmount(double a, double b) {
        if (a < b) {
            System.out.println("Start");
            //search array is sorted by double values lowest to highest
            searchAmountSort();
            searchAmountSortGhost();
            if (getSearch().size() >= 10) {
                System.out.println("1");
                //halfway point of the array
                int compare = getSearch().size() / 2;
//                int compareHigher = 0 + (compare / 2);
//double value of halfway point
                double compare2 = getSearch().get(compare).showPrice();
//if last and first element's double value is in range of a and b
                if (getSearch().get(0).showPrice() >= a && getSearch().get(0).showPrice() <= b && getSearch().get(getSearch().size() - 1).showPrice() > a && getSearch().get(getSearch().size() - 1).showPrice() < b) {
                    ///goes through array
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches through the whole array
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
                //if halway point double is greater than a and b
                if (compare2 > b && compare2 > a) {
                    System.out.println("a");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    while (i < compare) {
                        //searches first half of the array
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
                // if halfway point double is less than a and b
                if (compare2 < b && compare2 < a) {
                    System.out.println("b");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    //searches latter half of the array
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
                //if halfway double is inbetween a and b
                if (compare2 < b && compare2 > a) {
                    System.out.println("c");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches through the whole array
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
                // if search array size is less than 10
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
            //sorts search array by entries where double values go from lowest to highest
            searchAmountSort();
            searchAmountSortGhost();
            if (getSearch().size() >= 10) {
                //halfway point of the array
                int compare = getSearch().size() / 2;
                int compareHigher = 0 + (compare / 2);
                //double value of the halfway point
                double compare2 = getSearch().get(compare).showPrice();
//if first and last element is in range of a and b
                if (getSearch().get(0).showPrice() > a && getSearch().get(0).showPrice() < b && getSearch().get(getSearch().size() - 1).showPrice() > a && getSearch().get(getSearch().size() - 1).showPrice() < b) {
                    ///goes through array
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches through all of the array
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
//if halfway point double is greater than a and b
                if (compare2 > b && compare2 > a) {
                    System.out.println("a2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches the first half of the array
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
                //if halfway point double value is less than a and b
                if (compare2 < b && compare2 < a) {
                    System.out.println("b2");
                    int i = compare;
                    ///place in result array
                    int j = 0;
                    //searches latter half of the array
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
                //if halfway point is in range of a and b
                if (compare2 > b && compare2 < a) {
                    System.out.println("c2");
                    int i = 0;
                    ///place in result array
                    int j = 0;
                    //searches through whole array
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
                //if search array is less than 10
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
//reads through search array, if element meets paramters then it's copied to results array
    public void searchAmount(String a, double b) {
        //search array copis array2 then search array is sorted so that double values go from lowest to highest
        searchAmountSort();
        searchAmountSortGhost();
        switch (a) {
            //parmater <b
            case "<":
                int i = 0;
                ///place in result array
                int j = 0;
                //searches through the whole array
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
//paramter >b
            case ">":
                i = 0;
                ///place in result array
                j = 0;
                //searches through all of the array
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
//parameter <=
            case "<=":
                i = 0;
                ///place in result array
                j = 0;
                //searches through the whole array
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
//paramter >=
            case ">=":
                i = 0;
                ///place in result array
                j = 0;
                //searches through the whole array
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
// pramter =b
            case "":
                i = 0;
                ///place in result array
                j = 0;
                //searches through the whole array
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
//works similarily to searchAmount(double,double) but only runs through results array and removes elements that don't fit paramter 
    public void searchAmountGhost(double a, double b) {
        if (a < b) {
            System.out.println("ree");
            int g = getResults().size();
            ///goes through array
            int i = 0;
            ///place in result array
            int j = 0;
            //searches through the whole array
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
            //searches through the whole array
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
//works similarily to seacrhamount(string,double) but runs through results array and deletes elements thatdon't fit paramter
    public void searchAmountGhost(String a, double b) {
        int g = getResults().size();
        switch (a) {
            case "<":
                ///goes through array
                int i = 0;
                ///place in result array
                int j = 0;
                //searches through the whole array
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
                //searches through the whole array
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
                //searches through the whole array
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
                //searches through the whole array
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
//
            case "":

                ///goes through array
                i = 0;
                ///place in result array
                j = 0;
                //searches through the whole array
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
//combinations of other methods should always be used in a search, methods inside just to make this simpler
    public void MasterSearch(String ID, int ID2, int ID3, boolean fav, boolean fav2, String Sub, String Date, int Date2, int Date3, boolean EORL, boolean EORL2, String Amount, double Amount2, double Amount3) {
        //status is an indicator of whether or not to use the non  or the ghost   method of search 
        masterID(ID, ID2, ID3);
        masterFavorite(fav, fav2);
        masterSubject(Sub);
        masterDate(Date, Date2, Date3);
        masterEORL(EORL, EORL2);
        masterAmount(Amount, Amount2, Amount3);
    }
//always the first method in mast that runs through
    public void masterID(String ID, int ID2, int ID3) {
        switch (ID) {
            case "":
                System.out.println("ree");
                if (ID2 > 0 && ID3 > 0) {
                    //searchId(int,int)
                    searchID(ID2, ID3);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }
//if the ints are 0 or less than do nothing
                if (ID2 <= 0 && ID3 <= 0) {
                    ;
                }
//
                if (ID2 > 0 && ID3 <= 0) {
//if only one int is filled then searchId(int)
                    searchID(ID2);
 //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }
//if only one int is filled then searchId(int)
                if (ID2 <= 0 && ID3 > 0) {
                    //searchID(int)
                    searchID(ID3);
                     //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }
                break;

            case "<":
                if (ID2 > 0 && ID3 <= 0) {
                    //SearchID(string,int)
                    searchID("<", ID2);
                     //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID3 > 0 && ID2 <= 0) {
                    //SearchID(string,int)
                    searchID("<", ID3);
                 //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 > 0 && ID3 > 0) {
                    //SearchID(int,int)
                    searchID(ID2, ID3);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 <= 0) {
                    //skip because no int in both fields
                    ;
                }
                break;

            case ">":
                if (ID2 > 0 && ID3 <= 0) {
                    //SearchID(string,int)
                    searchID(">", ID2);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID3 > 0 && ID2 <= 0) {
                    //SearchID(string,int)
                    searchID(">", ID3);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 > 0 && ID3 > 0) {
                    //SearchID(int,int)
                    searchID(ID2, ID3);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 <= 0) {
                    //skip because no ints
                    ;
                }
                break;

            case "<=":
                if (ID2 > 0 && ID3 <= 0) {
                    //SearchID(string,int)
                    searchID("<=", ID2);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID3 > 0 && ID2 <= 0) {
                    //SearchID(string,int)
                    searchID("<=", ID3);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 > 0 && ID3 > 0) {
                    //searchID(int,int)
                    searchID(ID2, ID3);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 <= 0) {
                    //skips beacuse no ints need at least 1
                    ;
                }
                break;

            case ">=":
                if (ID2 > 0 && ID3 <= 0) {
                    //searchId(string,int)
                    searchID(">=", ID2);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID3 > 0 && ID2 <= 0) {
                    //searchId(string,int)
                    searchID(">=", ID3);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 > 0 && ID3 > 0) {
                    //searchId(int,int)
                    searchID(ID2, ID3);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 <= 0) {
                    //skips beacuse no ints
                    ;
                }
                break;
//if string is null
            default:
                if (ID2 > 0 && ID3 > 0) {
                    //searchID(int,int)
                    searchID(ID2, ID3);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }

                if (ID2 <= 0 && ID3 <= 0) {
                    //skips because no ints 
                    ;
                }
//if 1 int is filled
                if (ID2 > 0 && ID3 <= 0) {
                    //searchID(int)
                    searchID(ID2);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }
//if 1 int is filled
                if (ID2 <= 0 && ID3 > 0) {
                    //searchID(int)
                    searchID(ID3);
                    //changes both search and result array status to true meaning they both are full
                    changeSearchStatus();
                    changeResultStatus();
                }
                break;
        }
    }
//giant switch case bank but with if statements 
    public void masterFavorite(boolean fav, boolean fav2) {
        //if search already has entries in it or search status =true
        if (SearchStatus|| ResultStatus) {
            //fav means if they even want to sort by favorite
            if (fav) {
                //fav2 is either favorite or not favorite
                if (fav2) {
                    searchFavoriteGhost(fav2);
                } else {
                    searchFavoriteGhost(fav2);
                }
            } else {
                //skips
                ;
            }
            //if searchstatus is false ir there are no entries is search
        } else {
            //again fav is if they want to sort by favorites
            if (fav) {
                //fav2 ssignifies if they wnat to search by favorite or non favorite
                if (fav2) {
                    searchFavorite(fav2);
                    //sets sarch and result status to true meaning there are entries in both
                    changeSearchStatus();
                    changeResultStatus();
                } else {
                    searchFavorite(fav2);
                    //sets sarch and result status to true meaning there are entries in both
                    changeSearchStatus();
                    changeResultStatus();
                }
            } else {
                ;
            }
        }
    }
//giant if else statement 
    public void masterSubject(String Sub) {
        //if search has entries in it
        if (SearchStatus) {
            //if nothing is in string sub
            if (Sub.equals("")) {
                ;
            } else {
                searchSubjectGhost(Sub);
            }
            //if search does not have entries in it 
        } else {
            //if string is nothing
            if (Sub.equals("")) {
                ;
            } else {
                searchSubject(Sub);
                //sets search and result status to true meaning there are entries in both
                changeSearchStatus();
                changeResultStatus();
            }
        }
    }
//if else satement to guide what type of search program should do in regards to date 
    public void masterDate(String date, int date2, int date3) {
        //if search array has entries in it
        if (SearchStatus) {
            switch (date) {
                //no string 
                case "":
                    if (date2 > 0 && date3 > 0) {
                        //searchDate(int,int)
                        searchDateGhost(date2, date3);
                    }

                    if ((date2 <= 0 && date3 <= 0) || (date2 > 0 && date3 <= 0) || (date2 <= 0 && date3 > 0)) {
                        ///skip
                        ;
                    }
                    break;

                case "<":
                    if (date2 > 0 && date3 <= 0) {
                        //searchDateGhost(string,int)
                        searchDateGhost("<", date2);
                    }

                    if (date3 > 0 && date2 <= 0) {
                        //searchDateGhost(string,int)
                        searchDateGhost("<", date3);
                    }

                    if (date2 > 0 && date3 > 0) {
                        //searchDateGhost(int,int)
                        searchDateGhost(date2, date3);
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        //skip
                        ;
                    }
                    break;

                case ">":
                    if (date2 > 0 && date3 <= 0) {
                        //searchDateGhost(string,int)
                        searchDateGhost(">", date2);
                    }

                    if (date3 > 0 && date2 <= 0) {
                        //searchDateGhost(string,int)
                        searchDateGhost(">", date3);
                    }

                    if (date2 > 0 && date3 > 0) {
                        //searchDateGhost(int,int)
                        searchDateGhost(date2, date3);
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        //skip
                        ;
                    }
                    break;

                case "<=":
                    if (date2 > 0 && date3 <= 0) {
                        //searchDateGhost(string,int)
                        searchDateGhost("<=", date2);
                    }

                    if (date3 > 0 && date2 <= 0) {
                        //searchDateGhost(string,int)
                        searchDateGhost("<=", date3);
                    }

                    if (date2 > 0 && date3 > 0) {
                        //searchDateGhost(int,int)
                        searchDateGhost(date2, date3);
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        ;
                    }
                    break;

                case ">=":
                    if (date2 > 0 && date3 <= 0) {
                        //searchDateGhost(string,int)
                        searchDateGhost(">=", date2);
                    }

                    if (date3 > 0 && date2 <= 0) {
                        //searchDateGhost(string,int)
                        searchDateGhost(">=", date3);
                    }

                    if (date2 > 0 && date3 > 0) {
                        //searchDateGhost(int,int)
                        searchDateGhost(date2, date3);
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        ;
                    }
                    break;
//if string is null
                default:
                    if (date2 > 0 && date3 > 0) {
                        //searchDateGhost(int,int)
                        searchDateGhost(date2, date3);
                    }

                    if ((date2 <= 0 && date3 <= 0) || (date2 > 0 && date3 <= 0) || (date2 <= 0 && date3 > 0)) {
                        //skip
                        ;
                    }
                    break;
            }
            // if search doesn't have any entries in it
        } else {
            switch (date) {
                //string is empty
                case "":
                    if (date2 > 0 && date3 > 0) {
                        //searchDate(int,int)
                        searchDate(date2, date3);
                        //sets sarch and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if ((date2 <= 0 && date3 <= 0) || (date2 > 0 && date3 <= 0) || (date2 <= 0 && date3 > 0)) {
                        //skip
                        ;
                    }
                    break;

                case "<":
                    if (date2 > 0 && date3 <= 0) {
                        //searchDate(String,int)
                        searchDate("<", date2);
                        //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date3 > 0 && date2 <= 0) {
                        //searchDate(String,int)
                        searchDate("<", date3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 > 0 && date3 > 0) {
                        //searchDate(int,int)
                        searchDate(date2, date3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 <= 0 && date3 <= 0) {
                       //skip;
                        ;
                    }
                    break;

                case ">":
                    if (date2 > 0 && date3 <= 0) {
                        //searchDate(String,int)
                        searchDate(">", date2);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date3 > 0 && date2 <= 0) {
                        //searchDate(String,int)
                        searchDate(">", date3);
                        //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 > 0 && date3 > 0) {
                        //searchDate(int,int)
                        searchDate(date2, date3);
                        //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        //skip
                        ;
                    }
                    break;

                case "<=":
                    if (date2 > 0 && date3 <= 0) {
                        //searchDate(String,int)
                        searchDate("<=", date2);
                        //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date3 > 0 && date2 <= 0) {
                        //searchDate(String,int\)
                        searchDate("<=", date3);
                        //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 > 0 && date3 > 0) {
                        //searchDate(int,int)
                        searchDate(date2, date3);
                        //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 <= 0 && date3 <= 0) {
                       //skip
                        ;
                    }
                    break;

                case ">=":
                    if (date2 > 0 && date3 <= 0) {
                        //searchDate(Sting,a)
                        searchDate(">=", date2);
                        //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date3 > 0 && date2 <= 0) {
                        //searchDate(String ,int)
                        searchDate(">=", date3);
                        //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 > 0 && date3 > 0) {
                        //searchDate(int,int)
                        searchDate(date2, date3);
                        //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (date2 <= 0 && date3 <= 0) {
                        //skip
                        ;
                    }
                    break;
//if String is null
                default:
                    if (date2 > 0 && date3 > 0) {
                        //searchDate(int,int)
                        searchDate(date2, date3);
                        //sets search and result status to true meaning there are entries in both
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
        //if search array already has entries in it
        if (SearchStatus) {
            //EORL is whether or not the person want to sort by gains/losses
            if (EORL) {
                //true=gains,false=losses
                if (EORL2) {
                    searchGainsGhost();
                } else {
                    searchLossesGhost();
                }
            } else {
               //skip
                ;
            }
            //if search array has no entries in it
        } else {
            //wheteher nor not you want to search by gains.losses
            if (EORL) {
                //true=gains, false=losses
                if (EORL2) {
                    searchGains();
            //sets search and result status to true meaning there are entries in both
                    changeSearchStatus();
                    changeResultStatus();
                } else {
                    searchLosses();
                     //sets search and result status to true meaning there are entries in both
                    changeSearchStatus();
                    changeResultStatus();
                }
            } else {
                //skip
                ;
            }
        }
    }
//if else statement to see which searchAmount should use
    public void masterAmount(String Amount, double Amount2, double Amount3) {
       //if search array already has entries in it
        if (SearchStatus) {
            switch (Amount) {
                //string is empty
                case "":
                    if (Amount2 > 0 && Amount3 > 0 ||Amount2 >= 0 && Amount3 > 0||Amount2 > 0 && Amount3 >= 0) {
                        //searchAmountGhost(double,double)
                        searchAmountGhost(Amount2, Amount3);
                    }
                    

                    if ((Amount2 <= 0 && Amount3 <= 0) || (Amount2 > 0 && Amount3 <= 0) || (Amount2 <= 0 && Amount3 > 0)) {
                       //skip
                        ;
                    }
                    break;

                case "<":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        //searchAmountGhost(String,double)
                        searchAmountGhost("<", Amount2);
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        //searchAmountGhost(String,double)
                        searchAmountGhost("<", Amount3);
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        //searchAmountGhost(double,double)
                        searchAmountGhost(Amount2, Amount3);
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        //skip
                        ;
                    }
                    break;

                case ">":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        //searchAmountGhost(String,double)
                        searchAmountGhost(">", Amount2);
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        //searchAmountGhost(String,double)
                        searchAmountGhost(">", Amount3);
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        //searchAmountGhost(double,double)
                        searchAmountGhost(Amount2, Amount3);
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        //skip
                        ;
                    }
                    break;

                case "<=":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        //searchAmountGhost(String,double)
                        searchAmountGhost("<=", Amount2);
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        //searchAmountGhost(String,double)
                        searchAmountGhost("<=", Amount3);
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        //searchAmountGhost(double,double)
                        searchAmountGhost(Amount2, Amount3);
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        //skip
                        ;
                    }
                    break;

                case ">=":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        //searchAmountGhost(String,double)
                        searchAmountGhost(">=", Amount2);
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        //searchAmountGhost(String,double)
                        searchAmountGhost(">=", Amount3);
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        //searchAmountGhost(double,double)
                        searchAmountGhost(Amount2, Amount3);
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        //skip
                        ;
                    }
                    break;

                default:
                    if (Amount2 > 0 && Amount3 > 0) {
                        //searchAmountGhost(double,double)
                        searchAmountGhost(Amount2, Amount3);
                    }

                    if ((Amount2 <= 0 && Amount3 <= 0) || (Amount2 > 0 && Amount3 <= 0) || (Amount2 <= 0 && Amount3 > 0)) {
                        //skip
                        ;
                    }
                    break;
            }
            //search array has no entries in it
        } else {
            switch (Amount) {
                //string has nothing
                case "":
                    if (Amount2 > 0 && Amount3 > 0||Amount2 >= 0 && Amount3 > 0||Amount2 > 0 && Amount3 >= 0) {
                        //searchAmount(double,double)
                        searchAmount(Amount2, Amount3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }


                    if ((Amount2 <= 0 && Amount3 <= 0) || (Amount2 > 0 && Amount3 <= 0) || (Amount2 <= 0 && Amount3 > 0)) {
                       //skip
                        ;
                    }
                    break;

                case "<":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        //searchAmount(String,double)
                        searchAmount("<", Amount2);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        //searchAmount(String,double)
                        searchAmount("<", Amount3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        //searchAmount(double,double)
                        searchAmount(Amount2, Amount3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        //skip
                        ;
                    }
                    break;

                case ">":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        //searchAmount(String,double)
                        searchAmount(">", Amount2);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        //searchAmount(String,double)
                        searchAmount(">", Amount3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        //searchAmount(double,double)
                        searchAmount(Amount2, Amount3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        //skip
                        ;
                    }
                    break;

                case "<=":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        //searchAmount(String,double)
                        searchAmount("<=", Amount2);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        //searchAmount(String,double)
                        searchAmount("<=", Amount3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        //searchAmount(String,double)
                        searchAmount(Amount2, Amount3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        //skip
                        ;
                    }
                    break;

                case ">=":
                    if (Amount2 > 0 && Amount3 <= 0) {
                        //searchAmount(String,double)
                        searchAmount(">=", Amount2);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount3 > 0 && Amount2 <= 0) {
                        //searchAmount(String,double)
                        searchAmount(">=", Amount3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 > 0 && Amount3 > 0) {
                        //searchAmount(double,double)
                        searchAmount(Amount2, Amount3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if (Amount2 <= 0 && Amount3 <= 0) {
                        //skip
                        ;
                    }
                    break;

                default:
                    if (Amount2 > 0 && Amount3 > 0) {
                        //searchAmount(double,double)
                        searchAmount(Amount2, Amount3);
                         //sets search and result status to true meaning there are entries in both
                        changeSearchStatus();
                        changeResultStatus();
                    }

                    if ((Amount2 <= 0 && Amount3 <= 0) || (Amount2 > 0 && Amount3 <= 0) || (Amount2 <= 0 && Amount3 > 0)) {
                        //skip
                        ;
                    }
                    break;
            }
        }

    }
//clears both search and results array and sets both search and result status to false
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

    public int getNewEntryID() {
        return getCount2() + 1;
    }

    //int a2 call upon getNewEntryID()
    public void editAddEntry(int a2, boolean f, String s, String d, String a, String n) {
        getArray2().add(new Entries(a2, f, s, d, a, n));
        String f2 = getArray2().get(searchEntryPlacement(a2)).getEntrySave();
        getEditedStringArray().add(f2);
        //this string is in edit format
        getEdits().add((getCount2() + 1) + "e");
        //adds entry count
        addCount2();
    }
//removes entries from array 2
    public void editRemoveEntry(int a) {
        getArray2().remove(searchEntryPlacement(a));
        //changes all id under removed entry by -1
        for (int i = getArray2().size(); i >= a; i--) {
            getArray2().get(i - 1).changeId((getArray2().get(i - 1).getId() - 1));
        }
        //this string is in edit format
        getEdits().add(a + "r");
        //substracts entry count
        subtractCount2();

    }
/// a for all the edit methods will be form searchEntryPlacement()

    public void editEntriesFavorite(int a, boolean b) {
        getArray2().get(searchEntryPlacement(a)).changeFavorite(b);
        //this string is in text file format 
        String f = getArray2().get(searchEntryPlacement(a)).getEntrySave();
        getEditedStringArray().add(f);
        //this string is in edit format
        getEdits().add(a + 1 + "f");
    }

    public void editEntriesFavorite(int a, String b) {
        getArray2().get(searchEntryPlacement(a)).changeFavorite(Boolean.parseBoolean(b));
        //this string is in text file format 
        String f = getArray2().get(searchEntryPlacement(a)).getEntrySave();
        getEditedStringArray().add(f);
        //this string is in edit format 
        getEdits().add(a + 1 + "f");
    }

    public void editEntriesSubject(int a, String b) {
        getArray2().get(searchEntryPlacement(a)).changeSubject(b);
        //this string is in text file format 
        String f = getArray2().get(searchEntryPlacement(a)).getEntrySave();
        getEditedStringArray().add(f);
        //this string is in edit format 
        getEdits().add(a + 1 + "s");
    }

    public void editEntriesDate(int a, String b) {
        getArray2().get(searchEntryPlacement(a)).changeDate(b);
        //this string is in text file format 
        String f = getArray2().get(searchEntryPlacement(a)).getEntrySave();
        getEditedStringArray().add(f);
        //this string is in edit format 
        getEdits().add(a + 1 + "d");
    }

    public void editEntriesAmount(int a, String b) {
        getArray2().get(searchEntryPlacement(a)).changePrice(b);
        //this string is in text file format 
        String f = getArray2().get(searchEntryPlacement(a)).getEntrySave();
        getEditedStringArray().add(f);
        //this string is in edit format 
        getEdits().add(a + 1 + "a");
    }

    public void editEntriesNotes(int a, String b) {
        getArray2().get(searchEntryPlacement(a)).changeNotes(b);
        //this string is in text file format 
        String f = getArray2().get(searchEntryPlacement(a)).getEntrySave();
        getEditedStringArray().add(f);
        //this string is in edit format 
        getEdits().add(a + "n");
    }

    public double addGains() {
        //searches all entries in array 2 that add money
        MasterSearch("", 0, 0, false, true, "", "", 0, 0, true, true, "", 0, 0);
        double total = 0;
        for (int i = 0; i < getResults().size(); i++) {
            total += getResults().get(i).showPrice();
//            System.out.println(total);
        }
        ///would be on exit or back button on gui
        clearResults();
        clearSearch();
        return total;
    }

    public double addLosses() {
        //searches all entries in array 2 that add money
        MasterSearch("", 0, 0, false, true, "", "", 0, 0, true, false, "", 0, 0);
        double total = 0;
        for (int i = 0; i < getResults().size(); i++) {
            total += getResults().get(i).showPrice();
            //            System.out.println(total);
        }
        ///would be on exit or back button on gui
        clearResults();
        clearSearch();
        return total;
    }
//subbstracts total gains by total losses
    public double getTotal() {
        double total = addGains() - addLosses();
        return total;
    }
//prints array2 entries in text file format, not so useful now
    public ArrayList<String> masterSave(ArrayList<Entries> a) {
        ArrayList<String> save = new ArrayList<String>();
        for (int i = 0; i < a.size(); i++) {
            save.add(a.get(i).getEntrySave());
        }
        return save;
    }
//prints out edit array, used for testing
    public void showEdits() {
        for (int i = 0; i < getEdits().size(); i++) {
            System.out.println(getEdits().get(i));
        }
    }

//    public String getEditedSubject(int a){
//         return getArray2().get(searchEntryPlacement(a)).getSubject();    
//    }
//    
//    public String getEditedFavourite(int a){
//        return Boolean.toString(getArray2().get(searchEntryPlacement(a)).getFavorite());
//    }
//    
//    public String getEditedDate(int a){
//        return getArray2().get(searchEntryPlacement(a)).date(4);
//    }
//    
//    public String getEditedAmount(int a){
//       return getArray2().get(searchEntryPlacement(a)).showPrice2(); 
//    }
//    
//    public String getEditedNotes(int a){
//        return getArray2().get(searchEntryPlacement(a)).getNotes();
//    }
    
    //uses seacrhentryplacement to find the newly changed entry and get it's string
    public String getEditedEntry(int a) {
        return getArray2().get(searchEntryPlacement(a)).getEntrySave();
    }

//    public boolean searchEdits(boolean a) {
//        int counter = 0;
//        for (int i = 0; i < getEdits().size(); i++) {
//            if (a) {
//                if (getEdits().get(i).equalsIgnoreCase("r")) {
//                    counter++;
//                } else {
//                    counter++;
//                    counter--;
//                }
//            } else {
//                if (getEdits().get(i).equalsIgnoreCase("e")) {
//                    counter++;
//                } else {
//                    counter++;
//                    counter--;
//                }
//            }
//        }
//
//            if (counter > 0) {
//                return true;
//            } else if (counter == 0) {
//                return false;
//            }
//            return false;
//        }
    
    //calulaute leap year years suing ifs
    public static boolean calculateLeap2(int a) {
        boolean leap = false;
        if (a % 4 == 0) {
            leap = true;
            //every century can't be a leap year
            if (a % 100 == 0) {
                leap = false;
                //but every 4th century can be a leap year
                if (a % 400 == 0) {
                    leap = true;
                }
            }
        }
        return leap;
    }
//will not work if leap 29/2 is put in when year is not leap year

    public static String getDateFromInt(int a) {
        //get approximate year of int
        double b = (a / 365) - 1;
        //caluculates the leap years the int has passed
        double b2 = (b / 4) + (b / 100) + (b / 400);
        //substracts leap year days from int
        double b3 = a - b2;
        //gets b3 and gets more accurate full day value
        int b4 = (int) Math.rint(b3 / 365);
        //calulates fulldayvalue of closer year
        int b5 = b4 * 365 + (b4 / 4) - (b4 / 100) + (b4 / 400);
        //b5 is substracted from a to get a number less than 365 so basically the current date of the  year it's on
        int b6 = a - b5;
        //both of these arrays just store days in a month ex 0 element is days in januaray 1 is days in feb
        //reason why there is 2 is because of of leap year
        int[] calender = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] calenderl = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //if year is leap year
        if (calculateLeap2(b4) == true) {
            System.out.println("reee");
            int month = 0;
            int day = 0;
            int calenderspot = 0;
            while (calenderspot < calenderl.length) {
                //if b6 is less than or equal to 31 that means it's in january so don't need many calulations
                if (b6 <= 31) {
                    month = 1;
                    day = b6;
                } else {
                    //b6 is subtracted by elements in calender array 
                    b6 = b6 - calenderl[calenderspot];
                    //if b6 is lower than the int in the next element that means it's in a month
                    if (b6 <= calenderl[calenderspot + 1]) {
                        day = b6;
                        month = calenderspot + 2;
                        calenderspot = 100;
                    } else {
                        calenderspot++;
                    }
                }
            }
            return Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(b4);
            //if year is not a leap year
        } else {
            System.out.println("deee");
            int month1 = 0;
            int day1 = 0;
            int calenderspot = 0;
            while (calenderspot < calender.length) {
                // if b6 is less than 31 than it's in january
                if (b6 <= 31) {
                    month1 = 1;
                    day1 = b6;
                } else {
                    //b6 is subtracted by elements in calender array 
                    b6 = b6 - calender[calenderspot];
                    // once b6 is lower than the int in the next element of calender that means it's in a month
                    if (b6 <= calender[calenderspot + 1]) {
                        day1 = b6;
                        month1 = calenderspot + 2;
                        calenderspot = 100;
                    } else {
                        calenderspot++;
                    }
                }
            }
            return Integer.toString(day1) + "/" + Integer.toString(month1) + "/" + Integer.toString(b4);
        }
    }
  //caluculates only year fullday value, used for testing  
    public int getFullDayValueYear(int y){
        int year =  y*365+(y/4)-(y/100)+(y/400) ;
       return year; 
    }
//copy of date method 
//    public int setFullDay2(String t) {
//        int[] calender = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//        int[] calenderl = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
//        int stop = t.indexOf("/");
//        int stop3 = t.lastIndexOf("/");
//
//        int d = Integer.parseInt(t.substring(0, stop));
//
//        int m = Integer.parseInt(t.substring(stop + 1, stop3));
//
//        int y = Integer.parseInt(t.substring(stop3 + 1, t.length()));
//        //leap year status
//        boolean f=calculateLeap2(y);
//        
//    
//        int fullDayValue=0;
//        if(m==1){
//        int year =  y*365+(y/4)-(y/100)+(y/400) ;
//        int day = d;
//        int month=0; 
//        return fullDayValue=year+month+day;
//        }
//        
//        if(m>1){
//        int year =  y*365+(y/4)-(y/100)+(y/400) ;
//        int day = d;
//        int month=0;
//        if(f==true){
//         for (int i = 0; i < m-1; i++) {
//             //adds elements in the array it reaches it's ending month 
//                month += calenderl[i]; 
//            } 
//         return fullDayValue=year+month+day;    
//        }         
//        else{
//        for (int i = 0; i < m-1; i++) {
//             //adds elements in the array it reaches it's ending month 
//                month += calender[i];  
//            } 
//         return fullDayValue=year+month+day;      
//        }
//    }
//        return fullDayValue;
//    }
}

//01/02/2002 *search from 1 week to the future* *fulldayvalue +7 = b*
