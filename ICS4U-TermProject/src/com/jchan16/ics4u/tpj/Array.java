/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan16.ics4u.tpj;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jackie
 */
public class Array {

    private int counter;
    private Entries[] Array2;

    public Array(ResultSet a) {
        counter = setCounter(a);
        Array2 = new Entries[getCounter()];

    }

    public int setCounter(ResultSet a) {
        int i = 0;
        try {
            while (a.next()) {
                i++;
            }
            counter = i;
            return counter;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    //new
    public void fillArray(ResultSet a) {
        try {
            while (a.next()) {
                int fill=0;
                int b = a.getInt("Entries");
                boolean c = a.getBoolean("Favorite");
                String d = a.getString("Subject");
                String e = a.getString("Date");
                String fo="";
                if (a.getString("amount") != null) {
                    fo = a.getString("Amount");
                } else if (a.getString("AMOUNT") == null) {
                    fo = "0.00";
                }
                String go="";
                if (a.getString("notes") != null) {
                    go = a.getString("notes");
                } else if (a.getString("notes") == null) {
                    go = "";
                }
               Array2[fill]= new Entries(b,c,d,e,fo,go);
            }
        } catch (SQLException e) {

        }
    }
    

    public int getCounter() {
        return counter;
    }

}
