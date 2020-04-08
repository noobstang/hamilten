/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */
package noobstangvb.hs.ics4u.tpj.storageArea.Lite.jfx.fxml.windows;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author noobstang
 */
public class WindowSearchController implements Initializable {

    @FXML
    private AnchorPane searchAnchor;
    @FXML
    private TextField serEntryField;
    @FXML
    private RadioButton serEntryBeforeRadio;
    @FXML
    private RadioButton serEntryAfterRadio;
    @FXML
    private ChoiceBox<?> serSubjectChoiceBox;
    @FXML
    private DatePicker serDatePick1;
    @FXML
    private DatePicker serDatePick2;
    @FXML
    private RadioButton serDateMonthBfRadio;
    @FXML
    private RadioButton serDateWeekBfRadio;
    @FXML
    private RadioButton serDateMonthAftRadio;
    @FXML
    private RadioButton serDateWeekAftRadio;
    @FXML
    private TextField serAmountField;
    @FXML
    private TextField serBalanceField;
    @FXML
    private CheckBox serFavouriteCheck;
    @FXML
    private Button serMainSearch;
    @FXML
    private Button serMainCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
