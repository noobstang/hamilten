/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan.ics4u.tpj4;

/**
 *
 * @author Jackie
 */
public class Amount {
    //true=gains,false=losses
private boolean checker;
//double thats appear after +/- sign
private double amount;
//string version that concantenates the +/- and the double
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
//concantenates a fstring rep of the amount by using the boolean to see wheteher or
//to add +/- sign
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
 //works with edit method in array 2 acts just like setter for the class
 public void editAmount(String a){
  if(a.substring(0,1).equals("+")==true){
checker=true;
}
else if(a.substring(0,1).equals("-")==false){
checker=false;  
}
amount= Double.parseDouble(a.substring(1,a.length()));
setFull(checker,amount);   
 }
}
