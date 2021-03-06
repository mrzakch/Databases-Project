/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class NewCustomerSubmenu {

    //Builds the scene for New Customer
    public static Scene Build(MenuManager main_menu, String start_id) {
        //Init main stackpane
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        //Create back button
        Button back_button = new Button();
        back_button.setText("Back");
        back_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = NewAccountSubmenu.Build(main_menu, "");
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Account");
                primary.setScene(callback);
            }
        });

        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10, 10, 10, 10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //Main person hbox
        HBox person_hbox = new HBox(2);
        //Person ID input
        TextField person_input = new TextField();
        person_input.setText(start_id);
        //Create new person button
        Button new_person = new Button();
        new_person.setText("New");
        new_person.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = NewPersonSubmenu.Build(main_menu, "NewCustomerSubmenu");
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Person");
                primary.setScene(callback);
            }
        });
        //Add to person hbox
        person_hbox.getChildren().addAll(new Label("Person ID: "), person_input, new Label(" or "), new_person);
        //Customer type HBox
        HBox customer_type_hbox = new HBox(2);
        //Dropdown for type
        ComboBox<String> customer_type = new ComboBox<String>();
        //Add to customer type hbox
        customer_type.getItems().addAll("Person", "Corporate", "Municipal");
        //Add to customer type hbox
        customer_type_hbox.getChildren().addAll(new Label("Customer Type: "), customer_type);

        //Create new customer button
        Button lookup = new Button();
        lookup.setText("Create");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //implement actual new person id info here
//String sql = "INSERT INTO customer(PersonID, CustomerType) VALUES ("+(person_input.getText())+", "+(customer_type.getValue())+");");
                try {
                    String new_cust_id = addCustomer(main_menu, Integer.parseInt(person_input.getText()), customer_type.getValue());
                    Scene callback = NewAccountSubmenu.Build(main_menu, new_cust_id);
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("New Account");
                    primary.setScene(callback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //Add to central vbox
        info_entry.getChildren().addAll(person_hbox, customer_type_hbox, lookup);
        //Add to root
        pane.getChildren().addAll(info_entry, back_button);
        //Set alignments
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }

    //A method for use when we don't have a default ID.
    public static Scene Build(MenuManager main_menu) {
        return NewCustomerSubmenu.Build(main_menu, "");
    }

    public static String addCustomer(MenuManager mainM, int person_id, String customer_type) throws SQLException {
        //input of new account info into database
        Connection reservation = mainM.connectDatabase();
        String sql = "INSERT INTO customer (PersonID, CustomerType) VALUES(?, ?)";
        PreparedStatement statement = reservation.prepareStatement(sql);
        statement.setInt(1, person_id);
        statement.setString(2, customer_type);
        int out = statement.executeUpdate();

        if (out == 1) {
            String get_new_id = "SELECT CustomerID FROM customer WHERE  CustomerType=\"" + customer_type + "\" AND PersonID=" + person_id;
            PreparedStatement get_statement = reservation.prepareStatement(get_new_id);
            ResultSet to_return = get_statement.executeQuery(get_new_id);
            if (to_return.next()) {
                String new_id = String.valueOf(to_return.getInt("CustomerID"));
                mainM.closeDatabase();
                return new_id;
            } else {
                mainM.closeDatabase();
                return "Error getting new ID";
            }
        } else {
            mainM.closeDatabase();
            return "Error creating customer";
        }
    }
}
