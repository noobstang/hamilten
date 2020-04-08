/*
 * ICS4U Term Project, Earl of March Secondary School
 * 
 */
package noobstangvb.hs.ics4u.tpj.storageArea.Lite;

import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.System.out;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Project main App class
 * 
 * @author noobstang
 * @date Apr 8, 2020
 * @since Dec 17, 2019
 * @version 0.0.1-SNAPSHOT
 * 
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
        out.println("Test");
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        //Create FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        
        //Path to the FXML file
        
        //String uiRootPath = "/home/noobstang/gan/TermProjectGit/GUI-JFX/src/me/noobstangofdusk/hs/ics4u/tpj/jfxmain/view/UIRoot.fxml";
        String uiRootPath = "/home/noobstang/gan/TermProjectGit/GUI-JFX/src/me/noobstangofdusk/hs/ics4u/tpj/jfx/fxml/RootPanel.fxml";
        
        // Create new FileInputStream Objectfrom uiRootPath
        FileInputStream uiRootStream = new FileInputStream(uiRootPath);
        
        // Create panel and encompassing details
        AnchorPane root = (AnchorPane) loader.load(uiRootStream);
        
        // Create the Scene
        Scene scene = new Scene(root);
        // Set the Scene to the Stage
        stage.setScene(scene);
        // Set the Title to the Stage
        stage.setTitle("St0rageAreaLite Finance Management");
        // Display the Stage
        stage.show();
    }
    
}
