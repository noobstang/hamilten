/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */
package noobstangvb.hs.ics4u.tpj.storageArea.Lite.jfx.mainView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author noobstang
 * @date Dec 16, 2019
 */
public class FXMLTestController implements Initializable {

    @FXML
    private Button btnMainSearch;
    @FXML
    private Button btnMainImport;
    @FXML
    private Button btnMainExport;
    @FXML
    private BarChart<?, ?> rptBarChart;
    @FXML
    private Button btnReportGraphCustomize;
    @FXML
    private Button btnAccountLogin;
    @FXML
    private Hyperlink hyplnAccountForgot;
    @FXML
    private ListView<?> lsvReportTotIncome;
    @FXML
    private ListView<?> lsvReportTotExpense;
    @FXML
    private ListView<?> lsvReportTotOverall;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //lsvReportTotIncome.getItems().add();
        
    }    

    @FXML
    private void exitAction(ActionEvent event) {
        
        
    }

    @FXML
    private void btnMainSearchAction(ActionEvent event) {
        
    }

    @FXML
    private void btnMainImportAction(ActionEvent event) {
        
    }

    @FXML
    private void btnMainExportAction(ActionEvent event) {
        
    }

    @FXML
    private void btnReportGraphCustomizeAction(ActionEvent event) {
        
    }

    @FXML
    private void btnAccointLoginAction(ActionEvent event) {
        
    }

    @FXML
    private void hyplnAccountForgotAction(ActionEvent event) {
        
    }
    
}
