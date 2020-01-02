/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan16.ics4u.tpj2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Jackie
 */
public class Save {
    
private final String d= "/AccountData";
private File f= new File(d);
private ArrayList<Entries> read = new ArrayList<Entries>();


public Save(){
    //default will not make duplicate
    try{
    f.mkdir();
    new File(d+"/Entries.txt").createNewFile();
    }catch(IOException e){
        System.out.println(e.getMessage());    
    }
}

public String directory(){
    return d;
}

public File parent(){
    return f;
}


public void readEntry(String e){
int start=e.indexOf("|");
int middle=e.indexOf("|",start+1);
int middle2=e.indexOf("|",middle+1); 
int middle3=e.indexOf("|",middle2+1);
int last=e.lastIndexOf("|");

//
int ID=Integer.parseInt(e.substring(0,start));
boolean Fav=Boolean.parseBoolean(e.substring(start,middle));
String sub=e.substring(middle+1,middle2);
String day=e.substring(middle2+1,middle3);
String amount=e.substring(middle3+1,last);
String note= e.substring(last+1,e.length());

read.add(new Entries(ID,Fav,sub,day,amount,note));

}

public void makeLog(int a){
    //will not make duplicate file on it's own
    try{
    new File(d,"log"+a+".txt").createNewFile();
    writeLog1(d+"/log"+a+".txt",Integer.toString(a));
    }catch(IOException e){
        System.out.println(e.getMessage());
    }
}


public void getFileNames(){
    for(int i=0;i<parent().listFiles().length;i++){
        System.out.println(parent().listFiles()[i]);
    }    
}

public int getLogPlace(int a){
   for(int i=1;i<parent().listFiles().length;i++){
   int b=Integer.parseInt(parent().listFiles()[i].getName().substring(3,(parent().listFiles()[i].getName().length()-4)));
        if(b==a){
            return i;
        }
   }
   return -1;
}

public String getFileName(int a){
    try{    
    return parent().listFiles()[getLogPlace(a)].getAbsolutePath();
    }catch(ArrayIndexOutOfBoundsException e){
        return "Does Not Exist";
    }  
//    return "Does not exists";
}

public File getLog(int a){
 
 return parent().listFiles()[a];
}

public void clearLog(int a){
    parent().listFiles()[a].delete();
}

public void writeLog1(String a,String b){
    try{
    FileWriter d= new FileWriter(a,true);
    BufferedWriter write=new BufferedWriter(d);
//    FileReader r= new FileReader(a);
//    BufferedReader read= new BufferedReader(r);
    /*while(read.readLine()!= null){*/
    write.write(b+"\n");    
//    }
    write.close();
    d.close();
//    r.close();
//    read.close();
    }catch(IOException e){
         System.out.println(e.getMessage());
    }
}



public void copyLog(String a,String[] b){   
    for(int i=0;i<b.length;i++){
        writeLog1(a,b[i]);
    }    
}

}
