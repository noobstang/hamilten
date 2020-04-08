/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jchan16.ics4u.tpj4;
import java.util.ArrayList;
/**
 *
 * @author Jackie
 */
public class Month_Day {
private ArrayList[][] table= new ArrayList[12][1];

public Month_Day(){
table[0][0]=new ArrayList<>();
table[1][0]= new ArrayList<>();
table[2][0]=new ArrayList<>();
table[3][0]=new ArrayList<>();
table[4][0]= new ArrayList<>();
table[5][0]=new ArrayList<>();
table[6][0]=new ArrayList<>();
table[7][0]= new ArrayList<>();
table[8][0]=new ArrayList<>();
table[9][0]=new ArrayList<>();
table[10][0]= new ArrayList<>();
table[11][0]=new ArrayList<>();
}

public ArrayList<Entries> getJan(){
 return table[0][0];   
}

public ArrayList<Entries> getFeb(){
 return table[1][0];   
}

public ArrayList<Entries> getMar(){
 return table[2][0];   
}

public ArrayList<Entries> getApr(){
 return table[3][0];   
}

public ArrayList<Entries> getMay(){
 return table[4][0];   
}

public ArrayList<Entries> getJun(){
 return table[5][0];   
}

public ArrayList<Entries> getJul(){
 return table[6][0];   
}

public ArrayList<Entries> getAug(){
 return table[7][0];   
}

public ArrayList<Entries> getSep(){
 return table[8][0];   
}

public ArrayList<Entries> getOct(){
 return table[9][0];   
}

public ArrayList<Entries> getNov(){
 return table[10][0];   
}

public ArrayList<Entries> getDec(){
 return table[11][0];   
}
//How this would have worked, this worked along side year array in array2
//each element in year array would get this class
//the method makeCalnderList would have read the month and day of an entry
//and then send it to the array it belonged to ex if it was january it would go to
//table[0][0]
//then table table[0][0] entries would be sorted by day values lowest to highest
//method dateL would have been printing out all the 2d arrays of each year in year array
//from lowest to highest
//but david found the fulldayvalue solution
//so now this is a dead class
public void sortMonthByDayAD(ArrayList<Entries> a){
   for (int j = 1; j < a.size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(a.get(key).date(1));
            System.out.println(key1);
            int ender1 = Integer.parseInt(a.get(ender).date(1));
            System.out.println(ender1);


            if (key == 1) {
                while (ender == 0 && key1 < ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(a.get(key).getId(), a.get(key).getFavorite(),
                            a.get(key).getSubject(), a.get(key).date(4), a.get(key).getPrice().getFull(), a.get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    a.get(key).changeEntries(a.get(ender).getId(), a.get(ender).getFavorite(),
                            a.get(ender).getSubject(), a.get(ender).date(4), a.get(ender).getPrice().getFull(),
                            a.get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    a.get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 < ender1) {

                   //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(a.get(key).getId(), a.get(key).getFavorite(),
                            a.get(key).getSubject(), a.get(key).date(4), a.get(key).getPrice().getFull(), a.get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    a.get(key).changeEntries(a.get(ender).getId(), a.get(ender).getFavorite(),
                            a.get(ender).getSubject(), a.get(ender).date(4), a.get(ender).getPrice().getFull(),
                            a.get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    a.get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(a.get(key).date(1));
                    ender1 = Integer.parseInt(a.get(ender).date(1));
                }

            }
        }
    }
//How this would have worked, this worked along side year array in array2
//each element in year array would get this class
//the method makeCalnderList would have read the month and day of an entry
//and then send it to the array it belonged to ex if it was january it would go to
//table[0][0]
//then table table[0][0] entries would be sorted by day values lowest to highest
//method dateL would have been printing out all the 2d arrays of each year in year array
//from lowest to highest
//but david found the fulldayvalue solution
//so now this is a dead class
public void sortMonthByDayUP(ArrayList<Entries> a){
   for (int j = 1; j < a.size(); j++) {
            int key = j;
            int ender = key - 1;
            int key1 = Integer.parseInt(a.get(key).date(1));
            System.out.println(key1);
            int ender1 = Integer.parseInt(a.get(ender).date(1));
            System.out.println(ender1);


            if (key == 1) {
                while (ender == 0 && key1 > ender1) {

                    //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(a.get(key).getId(), a.get(key).getFavorite(),
                            a.get(key).getSubject(), a.get(key).date(4), a.get(key).getPrice().getFull(), a.get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    a.get(key).changeEntries(a.get(ender).getId(), a.get(ender).getFavorite(),
                            a.get(ender).getSubject(), a.get(ender).date(4), a.get(ender).getPrice().getFull(),
                            a.get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    a.get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                }
            }
            if (key > 1) {
                while (ender > 0 && key1 > ender1) {

                   //Dummy entries, copies  entries [key]
                    Entries p = new Entries();
                    p.changeEntries(a.get(key).getId(), a.get(key).getFavorite(),
                            a.get(key).getSubject(), a.get(key).date(4), a.get(key).getPrice().getFull(), a.get(key).getNotes());

                    ///transfers entries[ender] to entries[key]
                    a.get(key).changeEntries(a.get(ender).getId(), a.get(ender).getFavorite(),
                            a.get(ender).getSubject(), a.get(ender).date(4), a.get(ender).getPrice().getFull(),
                            a.get(ender).getNotes());
                    //entries[ender] then takes info from entries p or entries k       
                    a.get(ender).changeEntries(p.getId(), p.getFavorite(),
                            p.getSubject(), p.date(4), p.getPrice().getFull(),
                            p.getNotes());

                    ender--;
                    key--;
                    key1 = Integer.parseInt(a.get(key).date(1));
                    ender1 = Integer.parseInt(a.get(ender).date(1));
                }

            }
        }
    }
//prints out each entry in each 2d array cell
public void displayMonth_DayAD(){
    for(int j=0; j<12; j++){
      switch(j){
          case 0:
              for(int i=0; i<getJan().size();i++){
                  System.out.println(getJan().get(i));
              }
              
           case 1:
              for(int i=0; i<getFeb().size();i++){
                  System.out.println(getFeb().get(i));
              }
              
              case 2:
              for(int i=0; i<getMar().size();i++){
                  System.out.println(getMar().get(i));
              }
              
              case 3:
              for(int i=0; i<getApr().size();i++){
                  System.out.println(getApr().get(i));
              }
              
              case 4:
              for(int i=0; i<getMay().size();i++){
                  System.out.println(getMay().get(i));
              }
              
              case 5:
              for(int i=0; i<getJun().size();i++){
                  System.out.println(getJun().get(i));
              }
              
              case 6:
              for(int i=0; i<getJul().size();i++){
                  System.out.println(getJul().get(i));
              }
              
              case 7:
              for(int i=0; i<getAug().size();i++){
                  System.out.println(getAug().get(i));
              }
              
              case 8:
              for(int i=0; i<getSep().size();i++){
                  System.out.println(getSep().get(i));
              }
              
              case 9:
              for(int i=0; i<getOct().size();i++){
                  System.out.println(getOct().get(i));
              }
              
              case 10:
              for(int i=0; i<getNov().size();i++){
                  System.out.println(getNov().get(i));
              }
              
              case 11:
              for(int i=0; i<getDec().size();i++){
                  System.out.println(getDec().get(i));
              }
              break;
              
              default:
                  break;
      }
    }
}
}
