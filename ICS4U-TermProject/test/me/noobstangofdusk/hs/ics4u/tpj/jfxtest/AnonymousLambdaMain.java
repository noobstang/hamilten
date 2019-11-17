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


public class AnonymousLambdaMain extends Application {
    
    public static Button button1;
    public static Button button2;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Term Project Main GUI");
        
        button1 = new Button();
        button1.setText("Whassup");
        button1.setOnAction(e -> {
            out.println("hey now brown cow");
            out.println("I am a meatball");
        });
        
        button2 = new Button();
        button2.setText("Hey baby");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                out.println("I am an anonymous inner class.");
            }
        });

        
        
        StackPane layout = new StackPane();
        layout.getChildren().add(button1);
        layout.getChildren().add(button2);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    
}
