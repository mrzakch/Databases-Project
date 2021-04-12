/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import java.sql.*;
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
    //Builds the scene for Account Lookup
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
        //Create main central VBox
        VBox info_entry = new VBox(5);
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //Account input textbox creation.
        TextField account_input = new TextField();
        account_input.setText(start_id);
        //Create error label.
        Label err = new Label();
        err.setTextFill(Color.RED);
        err.setText("");
        //Create main lookup button.
        Button lookup = new Button();
        lookup.setText("Lookup");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    int account_num=Integer.parseInt(account_input.getText());
                    System.out.println("Looking up "+account_num);
                    //Add lookup functionality!
                    //testing assumed lookup
                    AccountInformation account = accountLookup(main_menu, account_num);
                    Scene callback = AccountManagementSubmenu.Build(main_menu,new AccountInformation(account_num, account.balance, account.interest_rate, account.customerid));
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("Account Management || "+account_input.getText());
                    primary.setScene(callback);
                } catch (NumberFormatException | SQLException e){
                    e.printStackTrace();
                }
            }
        });
        
        
        //Add UI elements to VBox.
        info_entry.getChildren().addAll(account_input,lookup,err);
        //Add UI elements to root.
        pane.getChildren().addAll(info_entry,back_button);
        //Align root UI elements properly.
        pane.setAlignment(back_button,Pos.TOP_LEFT);
        pane.setAlignment(info_entry,Pos.CENTER);
        Scene scene = new Scene(pane,500,500);
        return scene;
    }
    
    @SuppressWarnings("null")
	public static AccountInformation accountLookup(MenuManager main, int acct_num) throws SQLException {
    	//creation of the query in JDBC. Can be moved directly to database if needed
    	Connection reservation = main.connectDatabase();
    	String sql = "SELECT Balance, InterestRate, CustomerID FROM account WHERE AccountID = "+(String.valueOf(acct_num));
    	PreparedStatement statement = reservation.prepareStatement(sql);
    	ResultSet out = statement.executeQuery(sql);
    	
    	if(out.next()) {
    		AccountInformation acct = new AccountInformation(acct_num, out.getFloat("Balance"), out.getFloat("InterestRate"), out.getInt("CustomerID"));
    		return acct;
    	}else {
    		return null;
    	}
    }
    
    //A method for use when we don't have a default ID.
    public static Scene Build(MenuManager main_menu){
        return AccountLookupSubmenu.Build(main_menu,"Account ID");
    }
}
