/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */
package me.noobstangofdusk.hs.ics4u.tpj.jfx.fxml.tab;

import java.net.URL;
import java.util.ResourceBundle;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import com.jchan16.ics4u.tpj5.*;

/**
 * FXML Controller class
 *
 * @author noobstang
 */
public class TabMainController implements Initializable {

    @FXML
    private TableColumn<Entries, Integer> mainLsEntry;
    @FXML
    private TableColumn<Entries, String> mainLsSubject;
    @FXML
    private TableColumn<Entries, Date> mainLsDate;
    @FXML
    private TableColumn<Entries, Amount> mainLsAmount;
    @FXML
    private TableColumn<Entries, Amount> mainLsBalance;
    @FXML
    private TableColumn<Entries, String> mainLsNotes;
    @FXML
    private TableColumn<Entries, Boolean> mainLsFavourite;
    @FXML
    private AnchorPane tabFinance__APane;
    @FXML
    private TableView<Entries> mainTableView;
    @FXML
    private Button mainBtnImport;
    @FXML
    private Button mainBtnExport;
    @FXML
    private Button mainBtnSearch;
    @FXML
    private Button mainBtnAdd;
    @FXML
    private Button mainBtnDelete;
    @FXML
    private Button mainBtnEdit;
    
    //Balance static variable
    private static Amount balance;
    
    public Array2 a2Suite = new Array2();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initTable();
        
        initEntries();
        
        
    }    
    
    //Initialize table columns
    public void initTable() {
        
        //Initialize table columns
        mainLsEntry = new TableColumn<Entries, Integer>("Entry #");
        mainLsEntry.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        mainLsSubject = new TableColumn<Entries, String>("Subject");
        mainLsSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        
        mainLsDate = new TableColumn<Entries, Date>("Date");
        mainLsDate.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        mainLsAmount = new TableColumn<Entries, Amount>("Amount");
        mainLsAmount.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Balance is static, held by MainTable
        mainLsBalance = new TableColumn<Entries, Amount>("Balance");
        mainLsBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        
        mainLsNotes = new TableColumn<Entries, String>("Notes");
        mainLsNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        
        mainLsFavourite = new TableColumn<Entries, Boolean>("Favourite");
        mainLsFavourite.setCellValueFactory(new PropertyValueFactory<>("favorite"));
        
        //Initialize Table View
        mainTableView = new TableView<>();
        mainTableView.getColumns().addAll(mainLsEntry, mainLsSubject, mainLsDate, mainLsAmount, mainLsBalance, mainLsNotes, mainLsFavourite);
        
    }
    
    //Fill table with entries
    @FXML
    public void initEntries() {
        //Get entries from static master array (Array2)
        mainTableView.setItems(getEntries());
        
    }
    
    public ObservableList<Entries> getEntries() {
        
        
        
        //Initialize with dummy database
        DatabaseDummy slacker = new DatabaseDummy();
        
//        Array2.Array2.fill(slacker);
        
        ObservableList<Entries> entriesOL = FXCollections.observableArrayList();
        for (Entries entry: Array2.Array2) {
            entriesOL.add(entry);
        }
        
        return entriesOL;
    }
    
    @FXML
    private void mainSearchMasterAction(ActionEvent event) {
        /*
        
        Stage window = new Stage();
        
        //Initialize FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Define FXML file path
        String filePath = "../windows/WindowSearch.fxml";
        //Create FileInputStream object with filepath
        FileInputStream fileStream = new FileInputStream(filePath);
        //Set root panel
        AnchorPane root = (AnchorPane) loader.load(fileStream);
        
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
        
        */
    }
    
    @FXML
    public void initSearchAlertWindow() {
        
    }


    @FXML
    private void mainExportMasterAction(ActionEvent event) {
        
    }

    @FXML
    private void mainImportMasterAction(ActionEvent event) {
        
    }

    @FXML
    private void mainAddEntryAction(ActionEvent event) {
        
    }

    @FXML
    private void mainDeleteEntryAction(ActionEvent event) {
        
    }

    @FXML
    private void mainEditEntryAction(ActionEvent event) {
    }
    
}
