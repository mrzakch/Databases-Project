/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Kyle
 */
public class AccountLookupSubmenu {
    public static Scene Build(MenuManager main_menu){
        
        StackPane pane = new StackPane();
        
        Button back_button = new Button();
        back_button.setText("Back");
        back_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                main_menu.returnToMenu();
            }
        });
        
        VBox info_entry = new VBox();
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        
        TextField account_input = new TextField();
        account_input.setText("Account ID");
        
        Label err = new Label();
        err.setTextFill(Color.RED);
        err.setText("");
        
        Button lookup = new Button();
        lookup.setText("Lookup");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    int account_num=Integer.parseInt(account_input.getText());
                    System.out.println("Looking up "+account_num);
                } catch (NumberFormatException e){
                    System.out.println("Input is NAN");
                    err.setText("Input is NAN");
                }
            }
        });
        
        
        
        info_entry.getChildren().addAll(account_input,lookup,err);
        
        pane.getChildren().addAll(info_entry,back_button);
        pane.setAlignment(back_button,Pos.TOP_LEFT);
        pane.setAlignment(info_entry,Pos.CENTER);
        Scene scene = new Scene(pane,500,500);
        return scene;
    }
}
