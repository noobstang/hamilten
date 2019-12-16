/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.noobstangofdusk.hs.ics4u.tpj.jfx.test;

import java.io.IOException;

import javafx.application.Application;
//import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static java.lang.System.out;
/**
 * FXML Template Application Loader
 * 
 * @author noobstang
 * @date Nov 27, 2019
 * @version 1.0
 */

public class TestMainFXMLApp {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    //@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("storageArea Finance Management");
        
        initRootLayout();
        
        //showEntryOverview();
    }
    
    
    /**
     * Initializes the root layout
     */
    
    public void initRootLayout() {
        try {
            // Load loot layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TestMainFXMLApp.class.getResource("view/FXMLTest.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showEntryOverview() {
        try {
            // Load entry overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TestMainFXMLApp.class.getResource("view/EntryOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set entry overview into root layout
            rootLayout.setCenter(personOverview);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
