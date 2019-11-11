package me.noobstangofdusk.hs.ics4u.tpj.jfxtest;

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
 * @author noobstang
 * @date November 10th, 2019
 * 
 */


public class Main extends Application implements EventHandler<ActionEvent> {
    
    public static Button button1;
    
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
