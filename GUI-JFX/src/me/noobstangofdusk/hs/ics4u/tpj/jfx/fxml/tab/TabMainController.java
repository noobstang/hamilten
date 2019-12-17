/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */
package me.noobstangofdusk.hs.ics4u.tpj.jfx.fxml.tab;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author noobstang
 */
public class TabMainController implements Initializable {

    @FXML
    private TableView<?> mainTableView;
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
    private Button mainBtnSearch;
    @FXML
    private Button mainBtnImport;
    @FXML
    private Button mainBtnExport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mainSearchMasterAction(ActionEvent event) {
    }

    @FXML
    private void mainImportMasterAction(ActionEvent event) {
    }

    @FXML
    private void mainExportMasterAction(ActionEvent event) {
    }
    
}