/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

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
    
    private Stage primary;

    public MenuManager(double x, double y, Stage primaryStage) {
        root = new StackPane();
        menu = new Scene(root, x, y);
        menu_scenes = new Scene[0];
        buttons = new Button[0];
        primary = primaryStage;
    }

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
        

        new_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Clicked " + menu_title);
                primary.setScene(to_add);
                primary.setTitle(menu_title);
                primary.show();
            }
        });

        root.getChildren().add(new_button);
        
        buttons=extend_buttons;
        menu_scenes = extend_scenes;

    }
    
    public void returnToMenu(){
        primary.setScene(menu);
        primary.setTitle("Main Menu");
        primary.show();
    }

    public Scene getMenuScene() {
        return menu;
    }
    
    public Stage getPrimary(){
        return primary;
    }

}
