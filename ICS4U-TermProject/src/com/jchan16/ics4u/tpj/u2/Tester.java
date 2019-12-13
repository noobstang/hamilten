/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan16.ics4u.tpj;

/**
 *
 * @author Jackie
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DatabaseDummy dum= new DatabaseDummy();
        Array2 arr = new Array2(dum);
        
        arr.printArray2();
 
        
        arr.sortAmount();
        arr.sortAmountGhost();
        System.out.println("\n");
        arr.printResults();
        System.out.println("\n");
        arr.sortLosesGhost();
        arr.printResults();
        
        
//        
//        
//        System.out.println(arr.getArray2().get(0).getDate().showLeap());
//        System.out.println(arr.getArray2().get(0).getDate().showFullDay());
        
    }
    
}
        
        
       
        
    
    
        
    
    
    
    
    
    
    

    
