/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Kyle
 */
public class Databases_Project extends Application {

    @Override
    public void start(Stage primaryStage) {

        MenuManager main_menu = new MenuManager(500, 900, primaryStage);

        main_menu.addToMenu(new Scene(new StackPane(), 500, 500), "Lookup Account");
        main_menu.addToMenu(new Scene(new StackPane(), 500, 500), "New Account");

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(main_menu.getMenuScene());
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
