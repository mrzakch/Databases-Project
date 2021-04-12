/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Kyle
 */
//Helper functions
//
public class MenuManager {

    private Scene[] menu_scenes;
    private Button[] buttons;
    private StackPane root;
    private Scene menu;
    private Connection reservation;
    
    private Stage primary;

    //Self explainatory constructor.
    public MenuManager(double x, double y, Stage primaryStage) {
        root = new StackPane();
        menu = new Scene(root, x, y);
        menu_scenes = new Scene[0];
        buttons = new Button[0];
        primary = primaryStage;
    }

    //Adds a scene to the menu, automatically creating the button and storing the Scene.
    public void addToMenu(Scene to_add, String menu_title) {
        //Takes a scene and makes a button for it in the menu's scene.
        Scene[] extend_scenes = new Scene[menu_scenes.length + 1];
        for (int i = 0; i < menu_scenes.length; i++) {
            extend_scenes[i] = menu_scenes[i];
        }
        extend_scenes[menu_scenes.length] = to_add;

        //Make new button
        Button new_button = new Button();
        new_button.setText(menu_title);
        new_button.setTranslateY((-30 * (buttons.length + 1) / 2) + 30 * buttons.length);

        Button[] extend_buttons = new Button[buttons.length + 1];

        for (int i = 0; i < buttons.length; i++) {
            extend_buttons[i] = buttons[i];
            buttons[i].setTranslateY((-30 * (buttons.length + 1) / 2) + 30 * i);
        }

        extend_buttons[buttons.length] = new_button;
        
        //Sets up button action
        new_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Clicked " + menu_title);
                primary.setScene(to_add);
                primary.setTitle(menu_title);
                primary.show();
            }
        });
        //Adds button to main root.
        root.getChildren().add(new_button);
        
        //Updates variables to reflect the current state of the obj.
        buttons=extend_buttons;
        menu_scenes = extend_scenes;

    }
    
    public Connection connectDatabase() {
    	//connection to local hosting of database
    	//root is unnamed account and password is database password
    	String url = "jdbc:mysql://localhost:3306/Project";
    	String username = "root";
   		String password = "password";
   		
   		try {
   			reservation = DriverManager.getConnection(url, username, password);
    		return reservation;	
    			
    	}catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}

   	}
    
    public void closeDatabase() throws SQLException {
    	reservation.close();
    }
    
    //Takes the application back to the main menu
    public void returnToMenu(){
        primary.setScene(menu);
        primary.setTitle("Main Menu");
        primary.show();
    }

    //Getter used for situations where returnToMenu is not sufficient.
    public Scene getMenuScene() {
        return menu;
    }
    
    //Getter for primary stage, used in submenus to transition correctly.
    public Stage getPrimary(){
        return primary;
    }

}
