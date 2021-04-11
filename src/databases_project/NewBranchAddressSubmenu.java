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
public class NewBranchAddressSubmenu {

    //Builds the scene for New Account
    public static Scene Build(MenuManager main_menu) {
        //Init main stackpane
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        //Create back button
        Button back_button = new Button();
        back_button.setText("Back");
        back_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = NewBranchSubmenu.Build(main_menu);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Branch");
                primary.setScene(callback);
            }
        });

        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10, 10, 10, 10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //HBox for Street input
        HBox street_hbox = new HBox(2);
        //Street input
        TextField street_input = new TextField();
        street_input.setText("");

        //Add to the street input HBox
        street_hbox.getChildren().addAll(new Label("Street: "), street_input);
        //City HBox
        HBox city_hbox = new HBox(2);
        //Main City Input
        TextField city_input = new TextField();
        //Add to city HBox
        city_hbox.getChildren().addAll(new Label("City: "), city_input);
        //Main state HBox
        HBox state_hbox = new HBox(2);
        //Initial state input
        TextField state_input = new TextField();
        //Add to state HBox
        state_hbox.getChildren().addAll(new Label("State: "), state_input);
        //Main zip HBox
        HBox zip_hbox = new HBox(2);
        //Initial zip input
        TextField zip_input = new TextField();
        //Add to zip HBox
        zip_hbox.getChildren().addAll(new Label("ZIP Code: "), zip_input);
        //Button to create new account
        Button create = new Button();
        create.setText("Create");
        create.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //make call to database to get new id
                Scene callback = NewBranchSubmenu.Build(main_menu, "3451");
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Branch");
                primary.setScene(callback);
            }
        });
        //Add to main VBox
        info_entry.getChildren().addAll(street_hbox, city_hbox, state_hbox, zip_hbox, create);
        //Add to root
        pane.getChildren().addAll(info_entry, back_button);
        //Adjust positioning
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }
}
