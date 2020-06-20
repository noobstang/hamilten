package jchan16.ics4u.tpj;

import java.util.ArrayList;
import java.util.Random;

import jchan16.ics4u.tpj.Entries;

public class DatabaseDummy2 {

    private ArrayList<Entries> test = new ArrayList<Entries>();
    private int Idcounter;
    public ArrayList<Entries> test2= new ArrayList<Entries>();
    
    public DatabaseDummy2() {
        
    }

    public void changeCounter() {
        Idcounter++;
    }

    public int getCounter() {
        return Idcounter;
    }

    public String randoSub() {
        Random rand = new Random();
        int seer = rand.nextInt(0 - 10);
        switch (seer) {
            case 0:
                return "Arts";
//                break;
            case 1:
                return "Food";
//                break;
            case 2:
                return "Technology";
//                break;
            case 3:
                return "Housing";
//                break;
            case 4:
                return "Taxes";
//                break;
            case 5: 
                return "Hobbies";
//                break;
            case 6:
                return "Misc";
//                break;
            case 7: 
                return "Stationary";
//                break;
            case 8: 
                return "Bills";
//                break;
            case 9:
                return "Alcohol";
//                break;
            default:
                return "Glue";
//                break;
        }
    }
    public boolean coin(){
        Random rand= new Random();
        int seer=rand.nextInt(0-2);
        switch (seer){
            case 0:
                return true;
//                break;
            case 1:
                return false;
//                break;
            default: 
                return false;
//                break;
        }    
    }
    
    public String randoDate(){
    Random rand= new Random();
    int d= rand.nextInt(1-32);
    int m=rand.nextInt(1-13);
    int y=rand.nextInt(1000-2020);
    
    return d+"/"+m+"/"+y;
    }
    
    public String randoNotes(){
        return "";
    }
    
//    public String getDummy(){
//        
//    }
    
}
//>>>>>>> b2351949a61f0b8e66b421f17e01dad50336a7e2
