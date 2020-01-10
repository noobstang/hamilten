/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */

package me.noobstangofdusk.hs.ics4u.tpj;


import java.io.FileInputStream;
import java.io.IOException;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.System.out;

/**
 * Project Main Class
 * 
 * @author noobstang
 * @date Dec 17, 2019
 * @version 1.0
 * @see java.lang.System
 */

public class Main extends Application {
    
    public static void main(String[] args) {
        out.println("Test");
        Application.launch(args);
        
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        //Create FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        
        //Path to the FXML file
        
        //String uiRootPath = "/home/noobstang/gan/TermProjectGit/GUI-JFX/src/me/noobstangofdusk/hs/ics4u/tpj/jfxmain/view/UIRoot.fxml";
        String uiRootPath = "/home/noobstang/gan/TermProjectGit/GUI-JFX/src/me/noobstangofdusk/hs/ics4u/tpj/jfx/fxml/RootPanel.fxml";
        
        FileInputStream uiRootStream = new FileInputStream(uiRootPath);
        
        // Create panel and encompassing details
        AnchorPane root = (AnchorPane) loader.load(uiRootStream);
        
        // Create the Scene
        Scene scene = new Scene(root);
        // Set the Scene to the Stage
        stage.setScene(scene);
        // Set the Title to the Stage
        stage.setTitle("stockSh3LL Finance Management");
        // Display the Stage
        stage.show();
        
    }
    
    
    
}


