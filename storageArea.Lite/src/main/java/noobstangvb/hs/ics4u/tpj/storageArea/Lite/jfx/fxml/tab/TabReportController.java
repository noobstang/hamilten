/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */
package noobstangvb.hs.ics4u.tpj.storageArea.Lite.jfx.fxml.tab;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author noobstang
 */
public class TabReportController implements Initializable {

    @FXML
    private TitledPane reportTitleIncome;
    @FXML
    private ListView<?> reportLsIncome;
    @FXML
    private TitledPane reportTitleExpense;
    @FXML
    private ListView<?> reportLsExpense;
    @FXML
    private TitledPane reportTitleMiscellaneous;
    @FXML
    private ListView<?> reportLsMiscellaneous;
    @FXML
    private TitledPane reportTitleOverall;
    @FXML
    private ListView<?> reportLsOverall;
    @FXML
    private TableView<?> reportTableView;
    @FXML
    private TableColumn<?, ?> reportLsEntry;
    @FXML
    private TableColumn<?, ?> reportLsAmount;
    @FXML
    private TableColumn<?, ?> reportLsDate;
    @FXML
    private AnchorPane reportGraphPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
