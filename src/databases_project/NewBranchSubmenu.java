/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import static databases_project.NewCustomerSubmenu.addCustomer;
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
public class NewBranchSubmenu {
    //Builds the scene for New Account
    public static Scene Build(MenuManager main_menu, String start_id){
        //Init main stackpane
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10,10,10,10));
        //Create back button
        Button back_button = new Button();
        back_button.setText("Back");
        back_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = BranchLookupSubmenu.Build(main_menu);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Branch Information");
                primary.setScene(callback);
            }
        });
        
        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10,10,10,10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //HBox for address id input
        HBox address_hbox = new HBox(2);
        //Main ID input
        TextField address_input = new TextField();
        address_input.setText(start_id);
        //Create new customer button
        Button new_address = new Button();
        new_address.setText("New");
        new_address.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Address");
                primary.setScene(NewBranchAddressSubmenu.Build(main_menu));
            }
        });
        //Add to the main customer HBox
        address_hbox.getChildren().addAll(new Label("Address ID: "),address_input,new Label(" or "),new_address);
        //Manager HBox
        HBox manager_hbox = new HBox(2);
        //Main Interest Rate Input
        TextField manager_input = new TextField();
        //Add to interest rate HBox
        manager_hbox.getChildren().addAll(new Label("Manager ID: "),manager_input);

        //Button to create new account
        Button lookup = new Button();
        lookup.setText("Create");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
//String sql = "INSERT INTO branch(AddressID,ManagerEmployeeID) VALUES ("+(address_input.getText())+", "+(manager_input.getText())+");");
                try {
                    String new_branch_id = addBranch(main_menu, Integer.parseInt(address_input.getText()), Integer.parseInt(manager_input.getText()));
                    Scene callback = BranchLookupSubmenu.Build(main_menu, new_branch_id);
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("Branch Management");
                    primary.setScene(callback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //Add to main VBox
        info_entry.getChildren().addAll(address_hbox,manager_hbox,lookup);
        //Add to root
        pane.getChildren().addAll(info_entry,back_button);
        //Adjust positioning
        pane.setAlignment(back_button,Pos.TOP_LEFT);
        pane.setAlignment(info_entry,Pos.CENTER);
        Scene scene = new Scene(pane,500,500);
        return scene;
    }
    //A method for use when we don't have a default ID.
    public static Scene Build(MenuManager main_menu){
        return NewBranchSubmenu.Build(main_menu,"");
    }
    
    public static String addBranch(MenuManager mainM, int address_id, int manager_id) throws SQLException {
        //input of new account info into database
        Connection reservation = mainM.connectDatabase();
        String sql = "INSERT INTO branch (AddressID, ManagerEmployeeID) VALUES(?, ?)";
        PreparedStatement statement = reservation.prepareStatement(sql);
        statement.setInt(1, address_id);
        statement.setInt(2, manager_id);
        int out = statement.executeUpdate();

        if (out == 1) {
            String get_new_id = "SELECT BranchID FROM branch WHERE  AddressID=" + address_id + " AND ManagerEmployeeID=" + manager_id;
            PreparedStatement get_statement = reservation.prepareStatement(get_new_id);
            ResultSet to_return = get_statement.executeQuery(get_new_id);
            if (to_return.next()) {
                String new_id = String.valueOf(to_return.getInt("BranchID"));
                mainM.closeDatabase();
                return new_id;
            } else {
                mainM.closeDatabase();
                return "Error getting new ID";
            }
        } else {
            mainM.closeDatabase();
            return "Error creating branch";
        }
    }
}
