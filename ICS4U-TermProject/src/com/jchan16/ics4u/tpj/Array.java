/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ics4u;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jackie
 */
public class Array {

    private int counter;
    private Entries Array2[];
    
    public Array(ResultSet a){
        counter=setCounter(a);
        Entries Array1[]= new Entries[getCounter()];
        
        
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
    
    public int getCounter(){
        return counter;
    }
    

}
