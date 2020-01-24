/*
 * 
 */

package me.noobstangofdusk.cb.jfxtu1337.ab22MenuBarHandling;

import me.noobstangofdusk.cb.jfxtu1337.ab21MenuBar.*;
import me.noobstangofdusk.cb.jfxtu1337.aa19EditableTableView.*;
import me.noobstangofdusk.cb.jfxtu1337.aa18TableViewSimple.*;
import me.noobstangofdusk.cb.jfxtu1337.aa17TableViewIntro.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
/**
 * 
 * 
 * @author noobstang
 * @date Jan 24, 2020
 * @version 1.0
 * @see java.lang.System
 */
public class MainMenu extends Application {
    
    Stage window;
    BorderPane layout;
    
    public static void main(String[] args) {
        launch(args);
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("noobstangofdusk - JavaFX");
        
        //File menu
        Menu fileMenu = new Menu("File");
        
        //Menu items
        MenuItem newFile = new MenuItem("New...");
        newFile.setOnAction(e -> System.out.println("Create a new file..."));
        fileMenu.getItems().add(newFile);
        
//        fileMenu.getItems().add(new MenuItem("New..."));
        fileMenu.getItems().add(new MenuItem("Open..."));
        fileMenu.getItems().add(new MenuItem("Save..."));
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(new MenuItem("Settings..."));
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(new MenuItem("Exit..."));
        
        //Edit menu
        Menu editMenu = new Menu("_Edit");
        editMenu.getItems().add(new MenuItem("Cut"));
        editMenu.getItems().add(new MenuItem("Copy"));
        
        MenuItem paste = new MenuItem("Paste");
        paste.setOnAction(e -> System.out.println("Pasting some crap."));
        editMenu.getItems().add(paste);
        paste.setDisable(true);
        
        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu);
        
        
        layout = new BorderPane();
        layout.setTop(menuBar);
        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.show();
    }
    
}
