/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import java.lang.reflect.Method;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class NewPersonSubmenu {

    //Builds the scene for New Person
    public static Scene Build(MenuManager main_menu, String calltype, String start_id) {
        //Init main stackpane
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        //Create back button
        Button back_button = new Button();
        back_button.setText("Back");
        back_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (calltype.equals("NewCustomerSubmenu")) {
                    Scene callback = NewCustomerSubmenu.Build(main_menu, "");
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("New Customer");
                    primary.setScene(callback);
                }  else if (calltype.equals("NewEmployeeSubmenu")){
                    Scene callback = NewEmployeeSubmenu.Build(main_menu, "");
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("New Employee");
                    primary.setScene(callback);
                }
            }
        });

        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10, 10, 10, 10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //Main address hbox
        HBox address_hbox = new HBox(2);
        //Address ID input
        TextField address_input = new TextField();
        address_input.setText(start_id);
        //Create new address button
        Button new_address = new Button();
        new_address.setText("New");
        new_address.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = NewAddressSubmenu.Build(main_menu, calltype, start_id);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Address");
                primary.setScene(callback);
            } 
        });
        //Add to address hbox
        address_hbox.getChildren().addAll(new Label("Address ID: "), address_input, new Label(" or "), new_address);
        //Person name HBox
        HBox person_name_hbox = new HBox(2);

        //Create person name input
        TextField name_input = new TextField();

        //Add to person name hbox
        person_name_hbox.getChildren().addAll(new Label("Person's Name: "), name_input);

        //Person SSN HBox
        HBox person_ssn_hbox = new HBox(2);

        //Create person name input
        TextField ssn_input = new TextField();

        //Add to person name hbox
        person_ssn_hbox.getChildren().addAll(new Label("Person's SSN: "), ssn_input);

        //Create new person button
        Button lookup = new Button();
        lookup.setText("Create");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //implement actual new person id info here
//String sql = "INSERT INTO person(PersonName, AddressID, SSN) VALUES ("+(name_input.getText())+", "+(address_input.getText())+", "+(ssn_input.getText())+");");
                Class[] args = {MenuManager.class, String.class};
                if (calltype.equals("NewCustomerSubmenu")) {
                    Scene callback = NewCustomerSubmenu.Build(main_menu, "NewPersonID!");
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("New Customer");
                    primary.setScene(callback);
                } else if (calltype.equals("NewEmployeeSubmenu")){
                    Scene callback = NewEmployeeSubmenu.Build(main_menu, "NewPersonID!");
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("New Employee");
                    primary.setScene(callback);
                }
            }
        });
        //Add to central vbox
        info_entry.getChildren().addAll(person_name_hbox, address_hbox, person_ssn_hbox, lookup);
        //Add to root
        pane.getChildren().addAll(info_entry, back_button);
        //Set alignments
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }

    //A method for use when we don't have a default ID.
    public static Scene Build(MenuManager main_menu, String calltype) {
        return NewPersonSubmenu.Build(main_menu, calltype, "");
    }
}
