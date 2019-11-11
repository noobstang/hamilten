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
public class Amount {
private boolean checker;
private double amount;

public Amount(String t){
if(t.substring(0,1).equals("+")==true){
checker=true;
}
else if(t.substring(0,1).equals("-")==false){
checker=false;  
}
amount= Double.parseDouble(t.substring(1,t.length()));
}

public boolean check(){
    return checker;
}

public double checkAmount(){
    return amount;
}

}
