/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jchan16.ics4u.tpj3;

import java.io.File;
import java.io.IOException;
/**
 *
 * @author Jackie
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //creates directory, creates a starting log with array provided by dummy then fills array2
//        DatabaseDummy a= new DatabaseDummy();
//        Save e= new Save();
//        e.makeLog(1);
//        e.writeLogReplace(e.getFileName(1), a.getCopy());
//        e.readFromFile(e.getFileName(1));
//        e.printArray2();
//        
        
        //if already have a log that program can read from in this case log1 so int is 1, 
        Save e= new Save();
//        e.readFromFile(e.getFileName(1));
//        e.printArray2();
//        System.out.println(e.getCount2());
//        System.out.println("\n");
        
        //testing sorts
        //(top to bottom sorts)
//        e.sortAmount();
//        e.sortAmountGhost();
//        e.sortDateL();
//        e.sortDateLGhost();
//        e.sortDay();
//        e.sortDayGhost();
//        e.sortFavorite();
//        e.sortFavoriteGhost();
//        e.sortID();
//        e.sortIDGhost();
//        e.sortMonth();
//        e.sortMonthGhost();
//        e.sortSubject();
//        e.sortSubjectGhost();
//        e.sortYear();
//        e.sortYearGhost();
//        e.sortGainsGhost();
//        e.sortLossesGhost():

        
        //(Bottom to top sorts
//            e.RsortAmount();
//            e.RsortAmountGhost();
//            e.RsortDateL();
//            e.RsortDateLGhost();
//            e.RsortDay();
//            e.RsortDayGhost();
//            e.RsortID();
//            e.RsortIDGhost();
//            e.RsortMonth();
//            e.RsortMonthGhost();
//            e.RsortSubject();
//            e.RsortSubjectGhost();
//            e.RsortYear();
//            e.RsortYearGhost();
        
//        e.printResults();


        //Search Sorts
//        e.searchAmountSort();
//        e.searchAmountSortGhost();
//        e.searchDateSort();
//        e.searchDateSortGhost();
//        e.searchFavoriteSort();
//        e.searchFavoriteSortGhost();
//        e.searchGainsSort();
//        e.searchGainsSortGhost();
//        e.searchIDSort();
//        e.searchIDSortGhost();
//        e.searchLossesSort();
//        e.searchLossesSortGhost();
//        e.searchSubjectSort();
//        e.searchSubjectSortGhost();
        
//        e.printSearch();

        //Searches
        //nothing in results search
//        e.searchAmount(">", 1000);
//        e.searchAmount(0, 1000);
//        e.searchDate(">", e.getFullDayValue(2004));
//        e.searchDate(e.getFullDayValue(2000), e.getFullDayValue(2006));
//        e.searchFavorite(true);
//        e.searchGains();
//        e.searchLosses();
//        e.searchID(12);
//        e.searchID(">=", 10);
//        e.searchID(5, 12);
//        e.searchSubject("Taxes");
        
        //Searches from result Array
        e.fill2(e.getArray2());
        e.searchAmountGhost(">", 1000);
        e.searchAmountGhost(0, 1000);
        e.searchDateGhost(">", e.getFullDayValue(2004));
        e.searchDateGhost(e.getFullDayValue(2000), e.getFullDayValue(2006));
        e.searchFavoriteGhost(true);
        e.searchGainsGhost();
        e.searchLossesGhost();
        e.searchIDGhost(12);
        e.searchIDGhost(">=", 10);
        e.searchIDGhost(5, 12);
        e.searchSubjectGhost("Taxes");
        
//        e.printResults();

        //MasterSearch Componenets
        //ID Only
//        e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, false, true, "", 0, 0);
//        e.MasterSearch("", 1, 0, false, true, "", "", 0, 0, false, true, "", 0, 0);
//        e.MasterSearch("", 1, 5, false, true, "", "", 0, 0, false, true, "", 0, 0);
//        e.MasterSearch(">", 0, 3, false, true, "", "", 0, 0, false, true, "", 0, 0);
//        e.MasterSearch("<", 1, 5, false, true, "", "", 0, 0, false, true, "", 0, 0);
//        e.MasterSearch("", 10, 5, false, true, "", "", 0, 0, false, true, "", 0, 0);
//        
        //Favorite Only
//       e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, false, true, "", 0, 0);
//        e.MasterSearch("", 0, 0, true, true, "", "", 0, 0, false, true, "", 0, 0);
//        e.MasterSearch("", 0, 0, true, false, "", "", 0, 0, false, true, "", 0, 0);
        //Subject Only
//        e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, false, true, "", 0, 0);
//        e.MasterSearch("", 0, 0, false, true, "Taxes", "", 0, 0, false, true, "", 0, 0);
        //Date Only
//        e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, false, true, "", 0, 0);
//        e.MasterSearch("", 0, 0, false, true, "", "", e.getFullDayValue(2000), e.getFullDayValue(2005), false, true, "", 0, 0);
//        e.MasterSearch("", 0, 0, false, true, "", ">", e.getFullDayValue(2000), 0, false, true, "", 0, 0);
//        e.MasterSearch("", 0, 0, false, true, "", "<", e.getFullDayValue(2000), e.getFullDayValue(2005), false, true, "", 0, 0);
//        e.MasterSearch("", 0, 0, false, true, "", "", e.getFullDayValue(2005),e.getFullDayValue(2000), false, true, "", 0, 0);
        //Gains/Losses Only
//        e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, false, true, "", 0, 0);
//       e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, true, true, "", 0, 0);
//        e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, true, false, "", 0, 0);
        //Amount Only
//        e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, false, true, "", 0, 0);
//        e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, false, true, "", 0.00, 1000.00);
//        e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, false, true, "<", 1000.00, 0);
//        e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, false, true, ">", 1000, 1500);
//        e.MasterSearch("", 0, 0, false, true, "", "", 0, 0, false, true, "", 1500.00, 1000.00);
        //1 combination(entries 1-15, only favourite, only gains)
//        e.MasterSearch("", 1, 15, true, true, "", "", 0, 0, true, true, "", 0, 0);

        //Math+search
//        System.out.println(e.addGains());
//        System.out.println(e.addLosses());
//        System.out.println(e.getTotal());
        
//        System.out.println(e.showSearchStatus());
//        System.out.println(e.showResultStatus());
//        e.printResults();

        //Save Class methods
        //getters
        e.parent();/*returns directory file where all logs will be held*/
        e.EntryCounter();/*return entries file*/
        e.getSave();/*Returns arraylist string save log*/
        e.getCurrentFile();/*Returns string of currentfile that is being read from*/
        e.getLogPlace(0);/*Obsolete now, used to get postion of log in file array so the element of file array can be referenced in other metods as a file*/
        e.getFileNames();/*Prints out all substringed from absolute path files names in directory where all logs are saved or parent()*/
        e.getFileName(0);/*Uses int to get absolute path of file array element might be obsolete*/
        e.getLog(0);/*Returns file,int is the elment in file array,only use if with get file names since you see which element the file would be in*/
        e.getLogContent(0);/*reads a file and prints into console what was in the file. Uses int to find the file by searching for the file array element with int*/
        
        
    }
    
}
        
        
       
        
    
    
        
    
    
    
    
    
    
    

    
