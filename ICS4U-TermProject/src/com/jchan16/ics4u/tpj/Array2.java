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
public class Array {

    private int counter;
    private ArrayList<Entries> Array2 = new ArrayList<Entries>();

    public Array(ResultSet a) {
        fill(a);

    }

    public Array(DatabaseDummy a) {
        fill(a);
    }
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

    public ArrayList<Entries> getArray2() {
        return Array2;
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

    public void sortSubjectAD() {
        for (int j = 1; j < getArray2().size(); j++) {
            int key = j;
            int ender = key - 1;
            String key1 = getArray2().get(j).getSubject();
            System.out.println(key1);
            String ender1 = getArray2().get(ender).getSubject();
            System.out.println(ender1);
            int var1 = key1.compareToIgnoreCase(ender1);
            System.out.println(var1);
            if (key == 1) {
                while (ender == 0 && var1 < 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getArray2().get(key).getId(), getArray2().get(key).getFavorite(),
                            getArray2().get(key).getSubject(), getArray2().get(key).date(4), getArray2().get(key).getPrice().getFull(), getArray2().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getArray2().get(key).changeEntries(getArray2().get(ender).getId(), getArray2().get(ender).getFavorite(),
                            getArray2().get(ender).getSubject(), getArray2().get(ender).date(4), getArray2().get(ender).getPrice().getFull(),
                            getArray2().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getArray2().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && var1 < 0) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(getArray2().get(key).getId(), getArray2().get(key).getFavorite(),
                            getArray2().get(key).getSubject(), getArray2().get(key).date(4), getArray2().get(key).getPrice().getFull(), getArray2().get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    getArray2().get(key).changeEntries(getArray2().get(ender).getId(), getArray2().get(ender).getFavorite(),
                            getArray2().get(ender).getSubject(), getArray2().get(ender).date(4), getArray2().get(ender).getPrice().getFull(),
                            getArray2().get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    getArray2().get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = getArray2().get(key).getSubject();
                    ender1 = getArray2().get(ender).getSubject();
                    var1 = key1.compareToIgnoreCase(ender1);
                }
            }

        }
        //int j = getArray2().size()-1;
     //  do {
        //    int key2 = j;
          //  int ender2 = j - 1;
    //        while (ender2 > 0) {
      //          Entries p = new Entries();
        //        p.changeEntries(getArray2().get(key2).getId(), getArray2().get(key2).getFavorite(),
          //              getArray2().get(key2).getSubject(), getArray2().get(key2).date(4), getArray2().get(key2).getPrice().getFull(), getArray2().get(key2).getNotes());

                //transfers entries[ender] to entries[key]
//                getArray2().get(key2).changeEntries(getArray2().get(ender2).getId(), getArray2().get(ender2).getFavorite(),
//                        getArray2().get(ender2).getSubject(), getArray2().get(ender2).date(4), getArray2().get(ender2).getPrice().getFull(),
//                        getArray2().get(ender2).getNotes());
//                //entries[ender] then takes info from entries p or entries k       
//                getArray2().get(ender2).changeEntries(p.getId(), p.getFavorite(),
//                        p.getSubject(), p.date(4), p.getPrice().getFull(),
//                        p.getNotes());
//                j--;
//                key2--;
//               ender2--;
//            }
//        } while (j > 0);
//        }

        //public void printArray(){
        //for(int j=0; j<getCounter();j++){
        //System.out.println(Array2[j].getId()+" "+Array2[j].getFavorite()
        //+" "+Array2[j].getSubject()+" "+Array2[j].date(4)+" "Array2[j].getPrice().getFull()+" "+Array2[j].getNotes());
        //}
        //}
   // }
}

       }