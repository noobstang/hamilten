/*
 *  ICS4U Term Project, Earl of March Secondary School
 * 
 *  Copyright 2019 under the GNU 2.0 License
 *  All rights reserved.
 */
package com.noobstangofdusk.hs.ics4u.tpj.jfxmain;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * stockSh3LL Main Class
 * 
 *   Main class that launches the application.
 * 
 * @author noobstang
 * @date Dec 12, 2019
 * @version 1.0
 * @see java.lang.System
 */
public class Start extends Application {
    private Stage primaryStage;
    private BorderPane mainLayout;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("stockShell Finance Management");
        showMainView();
        
    }
    
    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Start.class.getResource("view/FXMLTest.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
