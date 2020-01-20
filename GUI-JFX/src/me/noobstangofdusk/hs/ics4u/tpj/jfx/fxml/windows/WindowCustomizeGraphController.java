/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */
package me.noobstangofdusk.hs.ics4u.tpj.jfx.fxml.windows;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author noobstang
 */
public class WindowCustomizeGraphController implements Initializable {

    @FXML
    private TextField custographEntryEditField;
    @FXML
    private ChoiceBox<?> custographSubjectChoice;
    @FXML
    private TextField custographDateField;
    @FXML
    private DatePicker custographEditDateChooser1;
    @FXML
    private DatePicker custographEditDateChooser2;
    @FXML
    private TextField custographAmountField;
    @FXML
    private TextField custographEditAmountField1;
    @FXML
    private TextField custographEditAmountField2;
    @FXML
    private TextField custographBalanceField;
    @FXML
    private TextField custographEditBalanceField1;
    @FXML
    private TextField custographEditBalanceField2;
    @FXML
    private CheckBox custographFavouriteCheck;
    @FXML
    private Button custographUpdateChart;
    @FXML
    private Button custographChartAddFromFile;
    @FXML
    private AnchorPane custographChartAnchor;
    @FXML
    private ChoiceBox<?> custographTypeChoice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void custoGraphUpdateChartAction(ActionEvent event) {
        
    }

    @FXML
    private void custographChartAddFromFileAction(ActionEvent event) {
        
    }
    
}
