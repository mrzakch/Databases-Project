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
public class AccountDepositSubmenu {

    //Builds the scene for Account Lookup
    public static Scene Build(MenuManager main_menu, AccountInformation info) {
        //Init main stackpane
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        //Create back button
        Button back_button = new Button();
        back_button.setText("Back");
        back_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = AccountManagementSubmenu.Build(main_menu, info);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Account Mangagement || " + String.valueOf(info.accountid));
                primary.setScene(callback);
            }
        });
        //Create main central VBox
        VBox info_entry = new VBox(5);
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //New value input textbox creation.
        TextField new_val_input = new TextField();
        //Create error label.
        Label err = new Label();
        err.setTextFill(Color.RED);
        err.setText("");
        //Create main lookup button.
        Button lookup = new Button();
        lookup.setText("Submit");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //handle update push to database
                 //String sql = "UPDATE account SET balance = "+(CALCULATE NEW BALANCE)+" WHERE AccountID = "+String.valueOf(info.account_id));
                Scene callback = AccountManagementSubmenu.Build(main_menu, info);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Account Management || " + String.valueOf(info.accountid));
                primary.setScene(callback);
            }
        });

        //Add UI elements to VBox.
        info_entry.getChildren().addAll(new Label("Depositing: "), new_val_input, lookup, err);
        //Add UI elements to root.
        pane.getChildren().addAll(info_entry, back_button);
        //Align root UI elements properly.
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }
}
