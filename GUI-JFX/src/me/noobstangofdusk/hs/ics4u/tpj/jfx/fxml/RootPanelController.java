/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */
package me.noobstangofdusk.hs.ics4u.tpj.jfx.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author noobstang
 */
public class RootPanelController implements Initializable {

    @FXML
    private MenuBar MenuBar;
    @FXML
    private Menu MBFile;
    @FXML
    private MenuItem mbNewFile;
    @FXML
    private MenuItem mbOpenFile;
    @FXML
    private MenuItem mbSaveFile;
    @FXML
    private MenuItem mbSaveFileAs;
    @FXML
    private MenuItem mbQuit;
    @FXML
    private Menu MBEdit;
    @FXML
    private MenuItem mbCopy;
    @FXML
    private MenuItem mbPaste;
    @FXML
    private MenuItem mbUndo;
    @FXML
    private MenuItem mbDelete;
    @FXML
    private Menu MBAccount;
    @FXML
    private MenuItem mbLogin;
    @FXML
    private MenuItem mbLogout;
    @FXML
    private Menu MBHelp;
    @FXML
    private MenuItem mbAbout;
    @FXML
    private TabPane MainTabPane;
    @FXML
    private Tab TabFinance;
    @FXML
    private AnchorPane tabFinanceAPane;
    @FXML
    private Tab TabReport;
    @FXML
    private AnchorPane tabReportAPane;
    @FXML
    private Tab TabAccount;
    @FXML
    private AnchorPane tabAccountAPane;
    @FXML
    private Tab TabAdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FXMLLoader loader = new FXMLLoader();
        String filePath = "./tab/TabMain.fxml";
//        FileInputStream fileStream = new FileInputStream(filePath);
        
        tabFinanceAPane = 
        
    }    

    @FXML
    private void TANewAction(Event event) {
        
    }
    
}
