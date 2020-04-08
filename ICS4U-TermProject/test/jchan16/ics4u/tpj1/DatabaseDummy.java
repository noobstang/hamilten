/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jchan16.ics4u.tpj1;

import java.util.ArrayList;
import java.util.Random;
import java.text.DecimalFormat;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jackie
 */
public class DatabaseDummy {

    private ArrayList<Entries> test = new ArrayList<Entries>();
    private int Idcounter;
    public ArrayList<Entries> test2= new ArrayList<Entries>();
// change 21 to any number to increase size of array
    public DatabaseDummy() {
        for(int p=0;p<21;p++){
        test2.add(new Entries(p,coin(),randoSub(),randoDate(),randoAmount(coin()),randoNotes()));
        Entries z= new Entries(test2.get(p).getId(),test2.get(p).getFavorite(),test2.get(p).getSubject()
        ,test2.get(p).date(4),test2.get(p).showPrice2(),test2.get(p).getNotes());
        test.add(z);
        }
        //this one was for database so that the id matches with row number in db
        for(int p=0; p<test2.size();p++){
            test2.get(p).changeId();
            test.get(p).changeId();
        } 
    }
    
    public DatabaseDummy(ResultSet a){
    try{
    while(a.next()){
    int counter=0;
    test.add(new Entries(a.getInt("ID"),a.getBoolean("FAVORTIE"),a.getString("SUBJECT"),a.getString("Date")
    ,a.getString("Amount"),a.getString("NOTES")));
    
    Entries z= new Entries(test2.get(counter).getId(),test2.get(counter).getFavorite(),test2.get(counter).getSubject()
        ,test2.get(counter).date(4),test2.get(counter).showPrice2(),test2.get(counter).getNotes());
        test.add(z);
    counter++;
    }    
    }catch(SQLException e){
        System.out.println(e.getMessage());    
    } 
    }

    public ArrayList getArray(){
        return test;
    }
    public void changeCounter() {
        Idcounter++;
    }

    public int getCounter() {
        return Idcounter;
    }

    public String randoSub() {
        Random rand = new Random();
        int seer = rand.nextInt(11-1);
        switch (seer) {
            case 1:
                return "Arts";

            case 2:
                return "Food";

            case 3:
                return "Technology";

            case 4:
                return "Housing";

            case 5:
                return "Taxes";

            case 6:
                return "Hobbies";

            case 7:
                return "Misc";

            case 8:
                return "Stationary";

            case 9:
                return "Bills";

            case 10:
                return "Alcohol";

            default:
                return "Glue";

        }

    }

    public boolean coin() {
        Random rand = new Random();
        int seer = rand.nextInt(3);
        switch (seer) {
            case 1:
                return false;

            case 2:
                return false;

            default:
                return true;

        }
    }

    public String randoDate() {
        Random rand = new Random();
        int d = rand.nextInt(30)+1;
        //String d2= Integer.toString(d);
        int m = rand.nextInt(11)+1;
        //String m2=Integer.toString(d);
        int y = rand.nextInt(100)+2000;
        //String y2=Integer.toString(d);

        return d + "/" + m + "/" + y;
    }

    public String randoNotes() {
        return "";
    }

    public String randoAmount(boolean a) {
        String b = "";
        DecimalFormat f = new DecimalFormat("##.00");
        Random rand = new Random();
        double c = rand.nextDouble();
        String c2 = f.format(c);
        int d = rand.nextInt(2000);
        
        Double dub = new Double(d);
        Double dub2 = dub + c;
        
        String dub3 = f.format(dub2);

        if (a == true && dub2 < 0) {
            b = "+";
            
        } if(a== false) {
            b = "-";
        }
        
      return b+dub3 ;
    }

    public void getEntry(Entries a){
        System.out.println(a.getId() + " " + a.getFavorite() + " " + a.getSubject() + " " + a.date(4) + " " + a.getPrice().getFull() + " " + a.getNotes());
}
    //for(int j=0; j<dum.test2.size(); j++){
    //con.insertData(dum.test2.get(j).getId(),dum.test2.get(j).getFavorite(),dum.test2.get(j).getSubject(),
   //dum.test2.get(j).date(4),dum.test2.get(j).showPrice2(),dum.test2.get(j).getNotes());
           // }
    
}
