/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan16.ics4u.tpj.u2;

/**
 *
 * @author Jackie
 */
public class Amount {
private boolean checker;
private double amount;
private String fullAmount;

public Amount(String t){
if(t.substring(0,1).equals("+")==true){
checker=true;
}
else if(t.substring(0,1).equals("-")==false){
checker=false;  
}
amount= Double.parseDouble(t.substring(1,t.length()));
setFull(checker,amount);
}

public boolean check(){
    return checker;
}

public double checkAmount(){
    return amount;
}
 public void setFull(boolean a, double b){
   if(a==true){
   fullAmount="+"+amount;
   }else{
       fullAmount="-"+amount;
   }
 }
 
 public String getFull(){
     return fullAmount;
 }
}
