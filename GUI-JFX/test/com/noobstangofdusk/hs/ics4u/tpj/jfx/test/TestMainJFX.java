package com.noobstangofdusk.hs.ics4u.tpj.jfx.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static java.lang.System.out;

/**
 * 
 * 
 * @author noobstang
 * @date Nov 11, 2019
 * @version 1.0
 * @see java.lang.System
 */

public class TestMainJFX extends Application implements EventHandler<ActionEvent> {
    
    private static Button button1;
    
    public static void main(String[] args) {
        launch(args);
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Term Project Main GUI");
        button1 = new Button();
        button1.setText("Click Me");
        button1.setOnAction(this);
        
        StackPane layout = new StackPane();
        layout.getChildren().add(button1);
        
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    @Override
    public void handle(ActionEvent event) {
        if(event.getSource()==button1) {
            out.println("Hello I'm lovin' it!");
        }
        
    }
    
}
