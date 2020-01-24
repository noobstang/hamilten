/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jchan.ics4u.tpj4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jackie
 */
public class Save extends Array2 {

    private final String d = "/AccountData";
    private File f = new File(d);
    private File e = new File(d + "/Entries.txt");
    private ArrayList<Entries> savelog = new ArrayList<Entries>();
//private FileReader reader;

    private String currentFile;
// if directory does not exist it will create the directory and the entry text file, if the directory does exist
//and does not have any logs, array2 will copy the empty save log. If the directory exists and there are 1 or more logs
//then the program should print out getFilenames()for the user, the user will then choose the log they want to read from
    //the program will get the file path and execute method readFromFile(String a)

    public Save() {
        super();
        //default will not make duplicate
        try {
            if (f.exists() == false) {
                f.mkdir();
                f.setExecutable(true);
                f.setReadable(true);
                f.setWritable(true);
//    System.out.print(f.canExecute());
//    System.out.print(f.canRead());
//    System.out.println(f.canWrite());
                e.createNewFile();
                e.setExecutable(true);
                e.setReadable(true);
                e.setWritable(true);
//    System.out.print(e.canExecute());
//    System.out.print(e.canRead());
//    System.out.println(e.canWrite());
                getEntryTotal(f);
                fill(savelog);
            } else {
                getEntryTotal(f);
//;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//changes currentFile to the string inputted,string should be file path

    public void changeFileReading(String b) {
        currentFile = b;
    }
//if file is put in , current file is changed to the file path of the file

    public void changeFileReading(File b) {
        currentFile = b.getAbsolutePath();
    }

    //resets currentFile to nothing,mostlikely used if they close the program after edits have been preformed
    public void clearFileReading() {
        currentFile = null;
    }
//
//    public void switchCurrentFile(String a) {
//        currentFile = a;
//    }
//
//    public void switchCurrentFile(File a) {
//        currentFile = a.getAbsolutePath();
//    }
//returns the directory in string form, not used that much at the moment

    public String directory() {
        return d;
    }
//returns file directory , used in some methods in tandom with the file/filepath of the read from/ write to log the user chooses

    public File parent() {
        return f;
    }
//returns entry file, used for quick access

    public File EntryCounter() {
        return e;
    }
//returns ArrayList<String> save log, used for quick Access

    public ArrayList<Entries> getSave() {
        return savelog;
    }
//returns the file path that the program has read from previously

    public String getCurrentFile() {
        return currentFile;
    }

//public FileReader FReader(){
//    return reader;
//}
    //Substring textfile format of storing entries to then intialize the strings into an entry creater
    public void readEntry(String e) {
        int start = e.indexOf("|");
        int middle = e.indexOf("|", start + 1);
        int middle2 = e.indexOf("|", middle + 1);
        int middle3 = e.indexOf("|", middle2 + 1);
        int last = e.lastIndexOf("|");

//
        int ID = Integer.parseInt(e.substring(1, start));
        boolean Fav = Boolean.parseBoolean(e.substring(start + 1, middle));
        String sub = e.substring(middle + 1, middle2);
        String day = e.substring(middle2 + 1, middle3);
        String amount = e.substring(middle3 + 1, last);
        String note = e.substring(last + 1, e.length());

        savelog.add(new Entries(ID, Fav, sub, day, amount, note));

    }

//Write file will not make duplicates on it's own
    public void makeLog(int a) {
        //will not make duplicate file on it's own
        try {
            File h = new File(d, "log" + a + ".txt");
            h.createNewFile();
            h.setExecutable(true);
            h.setReadable(true);
            h.setWritable(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
   //write file with name after string 
    public void makeLog(String a){
      try {
            File h = new File(d,a + ".txt");
            h.createNewFile();
            h.setExecutable(true);
            h.setReadable(true);
            h.setWritable(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }  
    }

//Prints out all files under Parent() directory, used so user see what file they want to choose from , mostly used for testing too
    public void getFileNames() {
        for (int i = 0; i < parent().listFiles().length; i++) {
            System.out.println(parent().listFiles()[i].getAbsolutePath().substring(15, parent().listFiles()[i].getAbsolutePath().lastIndexOf(".")));
        }
    }
//USed in tandom with get File Names, the int just returns the position in listFiles of wanted file.Not that useful right now

    public int getLogPlace(int a) {
        for (int i = 1; i < parent().listFiles().length; i++) {
            int b = Integer.parseInt(parent().listFiles()[i].getName().substring(3, (parent().listFiles()[i].getName().length() - 4)));
            if (b == a) {
                return i;
            }
        }
        return -1;
    }
//int put in will trigger getLogPlace() to find the file ab=nd the return the file path

    public String getFileName(int a) {
        try {
            return parent().listFiles()[getLogPlace(a)].getAbsolutePath();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Does Not Exist";
        }
//    return "Does not exists";
    }
//Int is just element in listFiles, returns the file. Not that much use.

    public File getLog(int a) {

        return parent().listFiles()[a];
    }
//reads a log and prints out its content in console, ised mainly for testing

    public void getLogContent(int a) {
        try {
            BufferedReader f = new BufferedReader(new FileReader(parent().listFiles()[a]));
            String g = f.readLine();
            while (g != null) {
                System.out.println(g);
                g = f.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//Don't use, clears all files in directory, at the moment has no purpose. 

    public void clearLog(int a) {
        parent().listFiles()[a].delete();
    }
//Both methods will delete a log

    public void deleteLog(File a) {
        a.delete();
    }

    public void deleteLog(int a) {
        getLog(a).delete();
    }

    //String a is the file path, String b is the string you want to add onto the text file without replacing what's already in the file
    //Used in adding entries
    public void writeLogAppend(String a, String b) {
        try {
            FileWriter d = new FileWriter(a, true);
            BufferedWriter write = new BufferedWriter(d);
//    FileReader r= new FileReader(a);
//    BufferedReader read= new BufferedReader(r);
            /*while(read.readLine()!= null){*/
            write.write(b + "\n");
//    }
            write.close();
            d.close();
//    r.close();
//    read.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//String a is filepath String b is you want to replace the already existening text in text file with

    public void writeLogReplace(String a, String b) {
        try {
            FileWriter d = new FileWriter(a, false);
            BufferedWriter write = new BufferedWriter(d);
//    FileReader r= new FileReader(a);
//    BufferedReader read= new BufferedReader(r);
            /*while(read.readLine()!= null){*/
            write.write(b + "\n");
//    }
            write.close();
            d.close();
//    r.close();
//    read.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//int a is used with get log to get the file you want to write to List,Strings> is actually apart of how edit work which I'll explain later
    //This form of the method is used with removing entries

    public void writeLogReplace(int a, List<String> b) {
        try {
            FileWriter d = new FileWriter(getLog(a), false);
            BufferedWriter write = new BufferedWriter(d);
//    FileReader r= new FileReader(a);
//    BufferedReader read= new BufferedReader(r);
            for (int i = 0; i < b.size(); i++) {
                write.write(b.get(i) + "\n");
            }
            write.close();
            d.close();
//    r.close();
//    read.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//String a is file path to the file you want to write to, List <String> is a part of edits, method used with removal of entries from a text file

    public void writeLogReplace(String a, List<String> b) {
        try {
            FileWriter d = new FileWriter(a, false);
            BufferedWriter write = new BufferedWriter(d);
//    FileReader r= new FileReader(a);
//    BufferedReader read= new BufferedReader(r);
            for (int i = 0; i < b.size(); i++) {
                write.write(b.get(i) + "\n");
            }
            write.close();
            d.close();
//    r.close();
//    read.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//String a is file path of wanted file to write to, arrayList<String> was databasedummy, method is used for testing by creating a log and filling it with databasedummy entries

    public void writeLogReplace(String a, ArrayList<String> b) {
        try {
            FileWriter d = new FileWriter(a, false);
            BufferedWriter write = new BufferedWriter(d);
//    FileReader r= new FileReader(a);
//    BufferedReader read= new BufferedReader(r);
            for (int i = 0; i < b.size(); i++) {
                write.write(b.get(i) + "\n");
            }
            write.close();
            d.close();
//    r.close();
//    read.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

//
//String a is desired file path, arraylist<String> is string you want to copy, forgot why I have this
    public void copyLog(String a, ArrayList<String> b) {
        for (int i = 0; i < b.size(); i++) {
            writeLogReplace(a, b.get(i));
        }
    }
//G is file you want to get entry count from, entry count is stored as an int, as long as the line is not null it adds 1 to entry count
//Once loop hits a null, method writeLogReplace(EntryCount(),entryCount) will replace text in entry file to match the entry count of read file

    public void getEntryTotal(File g) {
        int count = 0;
        int total = 0;
        if (g.listFiles().length == 1) {
//            System.out.println("reee");
            writeLogReplace(EntryCounter().getAbsolutePath(), Integer.toString(0));
        } else if (g.listFiles().length > 1) {
            try {
                BufferedReader v = new BufferedReader(new FileReader(getFileName(g.listFiles().length - 1)));
                String f = v.readLine();
                while (f != null) {
                    if (f.equalsIgnoreCase("null") == false) {
                        count++;
                        f = v.readLine();
                    } else {
                        count--;
                    }
                }
                writeLogReplace(EntryCounter().getAbsolutePath(), Integer.toString(count));
                v.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
//File g should be file directory/parent always, File a is whatever log you want to read from, works the same as getEntryTotal(file a) with 1 file

    public void getEntryTotal(File g, File a) {
        int count = 0;
        int total = 0;
        if (g.listFiles().length == 1) {
//            System.out.println("reee");
            writeLogReplace(EntryCounter().getAbsolutePath(), Integer.toString(0));
        } else if (g.listFiles().length > 1) {
            try {
                BufferedReader v = new BufferedReader(new FileReader(a));
                String f = v.readLine();
                while (f != null) {
                    if (f.equalsIgnoreCase("null") == false) {
                        count++;
                        f = v.readLine();
                    } else {
                        count--;
                    }
                }

                writeLogReplace(EntryCounter().getAbsolutePath(), Integer.toString(count));
                v.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
//File g is always file directory or parent() String is a file path in directory you want to read from

    public void getEntryTotal(File g, String a) {
        int count = 0;
        int total = 0;
        if (g.listFiles().length == 1) {
//            System.out.println("reee");
            writeLogReplace(EntryCounter().getAbsolutePath(), Integer.toString(0));
        } else if (g.listFiles().length > 1) {
            try {
                BufferedReader v = new BufferedReader(new FileReader(a));
                String f = v.readLine();
                while (f != null) {
                    if (f.equalsIgnoreCase("null") == false) {
                        count++;
                        f = v.readLine();
                    } else {
                        count--;
                    }
                }

                writeLogReplace(EntryCounter().getAbsolutePath(), Integer.toString(count));
                v.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
//File a is desired file you want entries from, it will go through text file untill it reaches null, each line read 
// is passed through method readEntry() which will then be added to savelog array of entries, save log will then be copied by array2 for the program

    public void readLogToRead(File a) {
        try {
            currentFile = a.getAbsolutePath();
            BufferedReader v = new BufferedReader(new FileReader(a));
            String line = v.readLine();
            while (line != null) {
                if (line.substring(0, 1).equals("-")) {
                    readEntry(line);
                    line = v.readLine();
                } else {
                    line = v.readLine();
                }
            }

            v.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//int a  passed through getLog to get the file you want to read from, it will go through text file untill it reaches null, each line read 
// is passed through method readEntry() which will then be added to savelog array of entries, save log will then be copied by array2 for the program

    public void readLogToRead(int a) {
        try {
            currentFile = getLog(a).getAbsolutePath();
            BufferedReader v = new BufferedReader(new FileReader(getLog(a)));
            String line = v.readLine();
            while (line != null) {
                if (line.substring(0, 1).equals("-")) {
                    readEntry(line);
                    line = v.readLine();
                } else {
                    line = v.readLine();
                }
            }

            v.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//String a is the file path from the file you want to read from, it will go through text file untill it reaches null, each line read 
// is passed through method readEntry() which will then be added to savelog array of entries, save log will then be copied by array2 for the program

    public void readLogToRead(String a) {
        try {
            currentFile = a;
            BufferedReader v = new BufferedReader(new FileReader(a));
            String line = v.readLine();
            while (line != null) {
                if (line.substring(0, 1).equals("-")) {
                    readEntry(line);
                    line = v.readLine();
                } else {
                    line = v.readLine();
                }
            }

            v.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//Prints out save log to console, used for testing

    public void showSaveLog() {
        for (int i = 0; i < getSave().size(); i++) {
            getSave().get(i).getEntry();
            System.out.println("\n");
        }
    }
//Obsolete method, if we were to use multiple textfiles/directories it would have automatically found the file to edit/add entry to

    public int calculateIDFileLocation(int a) {
        int round = a / 50;
        if (a % 50 == 0) {
            return round;
        } else {
            return round + 1;
        }
    }
//true mean read from bottom down while false means read bottom up
//public boolean readOperation(int a){
//    if(a>1){
//    int starter =((calculateIDFileLocation(a)-1)*50)+1;
//    int end=starter+49;
//    int middle=(end+starter)/2;
//        if(a<middle && a==middle){
//            return true;
//        }else if(a>middle){
//            return false;
//        }
//    }
//    else if(a==1){
//    int starter =calculateIDFileLocation(a)*50-1;
//    int end=starter+49;
//    int middle=(end+starter)/2;
//        if(a<middle && a==middle){
//            return true;
//        }else if(a>middle){
//            return false;
//        }    
//    }
//    
//    return true;
//    
//}
    ///a is equal to id edited

//    public String getUneditedString(int a) {
//        try {
//            BufferedReader read = new BufferedReader(new FileReader(getLog(calculateIDFileLocation(a))));
//            String f = read.readLine();
//            while (f != null) {
//                if (Integer.parseInt(f.substring(1, 2)) == a) {
//                    return f;
//                } else {
//                    f = read.readLine();
//                }
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return "doot";
//    }
    //int a is id number,Uses getCurrentFile() to read from that file, stores each line as a string, substring text so that id is comapred to a, if yes returns the string of entry based off it's id
    public String getUneditedString(int a) {
        try {
            BufferedReader read = new BufferedReader(new FileReader(getCurrentFile()));
            String f = read.readLine();
            while (f != null) {
                if (Integer.parseInt(f.substring(1, f.indexOf("|"))) == a) {
                    return f;
                } else {
                    f = read.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "doot";
    }

    public String getUneditedString(int a, ArrayList<String> b) {
        String t="";
        for (int i = 0; i < b.size(); i++) {
            if (Integer.parseInt(b.get(i).substring(1, b.get(i).indexOf("|"))) == a) {
                t= b.get(i);
            }
        }
        
        return t;
    }

//a is the the ID that need to edited, old string is always(getUneditedString(a) and New string is read from edits2 arrays in array class
    public void performEdit(int a, String oldString, String newString) {
        try {
            List<String> temp = new ArrayList<String>();
            int place = 0;
            BufferedReader read = new BufferedReader(new FileReader(getCurrentFile()));
            String f = read.readLine();
            while (f != null) {
                temp.add(f);
                f = read.readLine();
            }
            place = temp.indexOf(oldString);
            temp.set(place, newString);
            writeLogReplace(a, temp);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//reads entries file  after choosing what file to read from, turns the string to an int, method should be used to set entry count variable in array class

    public int getCountInEntry() {
        try {
            BufferedReader f = new BufferedReader(new FileReader(EntryCounter()));
            String p = f.readLine();
            if (p == null || p.equalsIgnoreCase("null")) {
                return 0;
            } else {
                try {
                    return Integer.parseInt(p);
                } catch (NumberFormatException e) {
                    writeLogReplace(EntryCounter().getAbsolutePath(), "0");
                    return -1;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
// file is file where edit should happen,usually currentFile()m int is the entry id that should be removed.Stores the lines into an array of string
//ususes getUneditedStringto find where int

    public void removeEdit(File a, int a2) {
        try {
            List<String> temp = new ArrayList<String>();
            int place = 0;
            BufferedReader read = new BufferedReader(new FileReader(a));
            String f = read.readLine();
            while (f != null) {
                temp.add(f);
                f = read.readLine();
            }
            place = temp.indexOf(getUneditedString(a2));
            if (place == -1) {
                System.out.println("Entry does not exist");
            } else {
                temp.remove(place);
                read.close();
                //            for(int i=0; i<temp.size();i++){
                //                System.out.println(temp.get(i));
                //            }
                for (int i = temp.size() - 1; i >= place; i--) {
                    ArrayList<String> o = new ArrayList<String>();

                    int new1 = Integer.parseInt(temp.get(i).substring(1, temp.get(i).indexOf("|")));
                    String new112 = temp.get(i).substring(0, temp.get(i).indexOf("|") + 1);
                    int new12 = new1 - 1;
                    String new13 = "-" + new12 + "|";
                    String new3 = temp.get(i).replace(new112, new13);
                    o.add(new3);

                    int elementofTemp = 0;
                    temp.set(i, o.get(elementofTemp));
                    elementofTemp++;
                }
                writeLogReplace(a.getAbsolutePath(), temp);
                substractCount();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//a should be current file, a2 in the id of entry you want to remove, reads file, stores lines as element s in a list
//calls upon getUneditedString(a2) to find entry you want to remove, the it reads from the last elment of list to element where entry was delted
//replacing the id's with the correct ones or subtracting their id's by once    
    public void removeEdit(String a, int a2) {
        try {
            List<String> temp = new ArrayList<String>();
            int place = 0;
            BufferedReader read = new BufferedReader(new FileReader(a));
            String f = read.readLine();
            while (f != null) {
                temp.add(f);
                f = read.readLine();
            }
            place = temp.indexOf(getUneditedString(a2));
            if (place == -1) {
                System.out.println("Entry does not exist");
            } else {
                temp.remove(place);
                read.close();
                //            for(int i=0; i<temp.size();i++){
                //                System.out.println(temp.get(i));
                //            }
                for (int i = temp.size() - 1; i >= place; i--) {
                    ArrayList<String> o = new ArrayList<String>();

                    int new1 = Integer.parseInt(temp.get(i).substring(1, temp.get(i).indexOf("|")));
                    String new112 = temp.get(i).substring(0, temp.get(i).indexOf("|") + 1);
                    int new12 = new1 - 1;
                    String new13 = "-" + new12 + "|";
                    String new3 = temp.get(i).replace(new112, new13);
                    o.add(new3);

                    int elementofTemp = 0;
                    temp.set(i, o.get(elementofTemp));
                    elementofTemp++;
                }
                writeLogReplace(a, temp);
                substractCount();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//These three methods, the String is filepath,the int uses getLog so returns a file and File is the file,a2 should be based off of entries text file or 
    //Entrycount of array class,b is the new entry in correct text file format
    public void addEntry(File a, int a2, String b) {
        writeLogAppend(a.getAbsolutePath(), b);
        addCount();
    }

    public void addEntry(String a, int a2, String b) {
        writeLogAppend(a, b);
        addCount();
    }

    public void addEntry(int a, int a2, String b) {
        writeLogAppend(getLog(a).getAbsolutePath(), b);
        addCount();
    }
///ArrayList b is edits array while ArrayList c is the weited string array, a is currentFile()(Should be)
    //So will go through b in order, substrings elements in b to get edited entry number and type of edit
    //Switch case the decides what method to do for edits for removing and adding entries it uses remove and addentry method
    //For the rest it just ues perfromEdit() method

    public void runThroughEdits(String a, ArrayList<String> b, ArrayList<String> c) {
        if (b.isEmpty()) {
            System.out.println("No edits need to be made");
            ;
        } else {
            System.out.println("Almost there");
            for (int i = 0; i < b.size(); i++) {
                int b2 = Integer.parseInt(b.get(i).substring(0, b.get(i).length() - 1));
                char b3 = b.get(i).charAt(b.get(i).length() - 1);
                int placeholder = 0;
                switch (b3) {
                    case 'a':
                        performEdit(b2, getUneditedString(b2), c.get(placeholder));
                        placeholder++;
                        break;
                    case 'd':
                        performEdit(b2, getUneditedString(b2), c.get(placeholder));
                        placeholder++;
                        break;
                    case 's':
                        performEdit(b2, getUneditedString(b2), c.get(placeholder));
                        placeholder++;
                        break;
                    case 'f':
                        performEdit(b2, getUneditedString(b2), c.get(placeholder));
                        placeholder++;
                        break;
                    case 'n':
                        performEdit(b2, getUneditedString(b2), c.get(placeholder));
                        placeholder++;
                        break;
                    case 'r':
                        removeEdit(a, b2);
                        break;
                    case 'e':
                        addEntry(a, b2, c.get(placeholder));
                        placeholder++;
                        break;

                    default:
                        System.out.println("Can't make edits error");
                        break;
                }
            }
            getEntryTotal(parent(), a);
        }
    }
//After edit is done, will replace text in entries with an int 1 higher than it was,already in addEntry at the end of the method
    public void addCount() {
        int e = getCountInEntry();
        int f = e + 1;
        if (e == -1) {
            System.out.println("error");
        } else if (e >= 0) {
            writeLogReplace(EntryCounter().getAbsolutePath(), Integer.toString(f));
        }
    }
//After edit is done, will replace text in entries with an int 1 lower than it was, already in removeEntry method near the end
    public void substractCount() {
        int e = getCountInEntry();
        int f = e - 1;
        if (e == -1) {
            System.out.println("error");
        } else if (e >= 0) {
            writeLogReplace(EntryCounter().getAbsolutePath(), Integer.toString(f));
        }
    }
    
    //a is file path, sets current File as file path, sets entries text file to entries in file, sets entrycount in array2 with setCount/getCountEntry, and fills in array 2 by coping savelog
    public void readFromFile(String a) {
        currentFile = a;
        getEntryTotal(parent(), a);
        setCount(getCountInEntry());
        readLogToRead(a);
        fill(savelog);
    }
//when user tries to exit program and there are edits will be asked to commit or not , if yes it will use method run through edits, if not the text files are not changed
    //The boolean comes from a method in array2 that checks for size of edits array, if greater than 0 then this method should pop up
    public void executeEdits(boolean a) {
        if (a == true) {
            System.out.println("Plan is a go");
            runThroughEdits(getCurrentFile(), getEdits(), getEditedStringArray());
        } else {
            System.out.println("I disobey my master");
            ;
        }
    }

}
