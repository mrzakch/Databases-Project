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
import java.sql.Statement;

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
public class NewAccountSubmenu {
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
                main_menu.returnToMenu();
            }
        });
        
        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10,10,10,10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //HBox for customer ID input
        HBox customer_hbox = new HBox(2);
        //Main ID input
        TextField customer_input = new TextField();
        customer_input.setText(start_id);
        //Create new customer button
        Button new_customer = new Button();
        new_customer.setText("New");
        new_customer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Customer Creation");
                primary.setScene(NewCustomerSubmenu.Build(main_menu));
            }
        });
        //Add to the main customer HBox
        customer_hbox.getChildren().addAll(new Label("Customer ID: "),customer_input,new Label(" or "),new_customer);
        //Interest rate HBox
        HBox interest_rate_hbox = new HBox(2);
        //Main Interest Rate Input
        TextField interest_rate_input = new TextField();
        //Add to interest rate HBox
        interest_rate_hbox.getChildren().addAll(new Label("Interest Rate: "),interest_rate_input);
        //Main Balance HBox
        HBox balance_hbox = new HBox(2);
        //Initial balance input
        TextField init_balance_input = new TextField();
        //Add to balance HBox
        balance_hbox.getChildren().addAll(new Label("Initial Balance: "),init_balance_input);
        
        Label new_id = new Label("");
        
        //Button to create new account
        Button lookup = new Button();
        lookup.setText("Create");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //Put a label with the new ID
//String sql = "INSERT INTO customeraccount(CustomerID, Balance, InterestRate) VALUES ("+(customer_input.getText())+", "+(init_balance_input.getText())+", "+(interest_rate_input.getText())+");");
                try {
                    String create_id = addAccountDatabase(main_menu,Integer.parseInt(customer_input.getText()),Float.parseFloat(interest_rate_input.getText()),Float.parseFloat(init_balance_input.getText()));
                    new_id.setText("New Account created with ID: "+create_id);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        //Add to main VBox
        info_entry.getChildren().addAll(customer_hbox,interest_rate_hbox,balance_hbox,lookup,new_id);
        //Add to root
        pane.getChildren().addAll(info_entry,back_button);
        //Adjust positioning
        pane.setAlignment(back_button,Pos.TOP_LEFT);
        pane.setAlignment(info_entry,Pos.CENTER);
        Scene scene = new Scene(pane,500,500);
        return scene;
    }
    
  //creation of the query in JDBC. Can be moved directly to database if needed
    public static String addAccountDatabase(MenuManager mainM, int custID, float interestRate, float initialBalance) throws SQLException {
    	//input of new account info into database
    	Connection reservation = mainM.connectDatabase();
    	String sql = "INSERT INTO customeraccount (Balance, InterestRate, CustomerID) VALUES(?, ?, ?)";
    	PreparedStatement statement = reservation.prepareStatement(sql);
    	statement.setInt(3, custID);
    	statement.setFloat(2, interestRate);
    	statement.setFloat(1, initialBalance);
        statement.executeUpdate();
        
        int out=statement.executeUpdate();
        
        if (out==1){
            String get_new_id = "SELECT AccountID FROM customeraccount WHERE  Balance=" +initialBalance+" AND CustomerID="+custID;
            PreparedStatement get_statement = reservation.prepareStatement(get_new_id);
            ResultSet to_return = get_statement.executeQuery(get_new_id);
            if (to_return.next()){
                String new_id = String.valueOf(to_return.getInt("AccountID"));
                mainM.closeDatabase();
                return new_id;
            } else {
                mainM.closeDatabase();
                return "Error getting new ID";
            }
        } else {
            mainM.closeDatabase();
            return "Error creating new account";
        }
    }
    
    //A method for use when we don't have a default ID.
    public static Scene Build(MenuManager main_menu){
        return NewAccountSubmenu.Build(main_menu,"");
    }
}
