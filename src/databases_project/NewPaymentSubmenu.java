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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Kyle
 */
public class NewPaymentSubmenu {

    //Builds the scene for New Account
    public static Scene Build(MenuManager main_menu, LoanInformation info) {
        //Init main stackpane
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        //Create back button
        Button back_button = new Button();
        back_button.setText("Back");
        back_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = LoanManagementSubmenu.Build(main_menu,info);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Loan Management || "+String.valueOf(info.id));
                primary.setScene(callback);
            }
        });

        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10, 10, 10, 10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //HBox for Account input
        HBox account_hbox = new HBox(2);
        //Account input
        TextField account_input = new TextField();
        account_input.setText("");

        //Add to the Account input HBox
        account_hbox.getChildren().addAll(new Label("Account ID: "), account_input);
        //Amount HBox
        HBox amount_hbox = new HBox(2);
        //Main Amount Input
        TextField amount_input = new TextField();
        //Add to city HBox
        amount_hbox.getChildren().addAll(new Label("Amount: "), amount_input);
   
        //Button to create new account
        Button process = new Button();
        process.setText("Process Payment");
        process.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //make call to database to process payment
                Scene callback = LoanManagementSubmenu.Build(main_menu,info);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Loan Management || "+String.valueOf(info.id));
                primary.setScene(callback);
            }
        });
        //Add to main VBox
        info_entry.getChildren().addAll(account_hbox, amount_hbox, process);
        //Add to root
        pane.getChildren().addAll(info_entry, back_button);
        //Adjust positioning
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }
}
