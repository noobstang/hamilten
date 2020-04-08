/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */
package noobstangvb.hs.ics4u.tpj.jfx.fxml.windows;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author noobstang
 */
public class WindowAccountLoginController implements Initializable {

    @FXML
    private TextField accloginUsrField;
    @FXML
    private PasswordField accloginPassField;
    @FXML
    private Button accloginMainLogin;
    @FXML
    private Hyperlink accloginHyprFgtPswd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
