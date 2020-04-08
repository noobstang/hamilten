/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */
package noobstangvb.hs.ics4u.tpj.storageArea.Lite.jfx.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.Parent;
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
        
        initTabFinance();
        initTabReport();
        
        TabAccount.setDisable(true);
        TabAdd.setDisable(true);
    }    

    @FXML
    private void TANewAction(Event event) {
        
    }
    
    private void initTabFinance() {
        Parent loader = null;
        String filePath = "tab/TabMain.fxml";
        
        try{
            loader = FXMLLoader.load(getClass().getResource(filePath));
        } catch (IOException ex) {
            Logger.getLogger(RootPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        tabFinanceAPane.getChildren().setAll(loader);
        tabFinanceAPane.setTopAnchor(loader, 0.0);
        tabFinanceAPane.setBottomAnchor(loader, 0.0);
        tabFinanceAPane.setLeftAnchor(loader, 0.0);
        tabFinanceAPane.setRightAnchor(loader, 0.0);
    }
    
    private void initTabReport() {
        Parent loader = null;
        String filePath = "tab/TabReport.fxml";
        
        try{
            loader = FXMLLoader.load(getClass().getResource(filePath));
        } catch (IOException ex) {
            Logger.getLogger(RootPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        tabReportAPane.getChildren().setAll(loader);
        tabReportAPane.setTopAnchor(loader, 0.0);
        tabReportAPane.setBottomAnchor(loader, 0.0);
        tabReportAPane.setLeftAnchor(loader, 0.0);
        tabReportAPane.setRightAnchor(loader, 0.0);
    }
    
}
