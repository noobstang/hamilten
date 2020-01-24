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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author noobstang
 */
public class TabMainController implements Initializable {

    @FXML
    private TableColumn<?, ?> mainLsEntry;
    @FXML
    private TableColumn<?, ?> mainLsSubject;
    @FXML
    private TableColumn<?, ?> mainLsDate;
    @FXML
    private TableColumn<?, ?> mainLsAmount;
    @FXML
    private TableColumn<?, ?> mainLsBalance;
    @FXML
    private TableColumn<?, ?> mainLsNotes;
    @FXML
    private TableColumn<?, ?> mainLsFavourite;
    @FXML
    private TableView<?> srTableView;
    @FXML
    private Button srShowOnMainScr;
    @FXML
    private Button srCancel;
    @FXML
    private Button srMakeNewFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mainSearchMasterAction(ActionEvent event) throws IOException {
        
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
        
        
    }


    @FXML
    private void mainExportMasterAction(ActionEvent event) {
        
    }
    
}
