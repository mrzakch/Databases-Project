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
public class AccountManagementSubmenu {

    //Builds the scene for New Person
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
                Scene callback = AccountLookupSubmenu.Build(main_menu,String.valueOf(info.accountid));
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Lookup Account");
                primary.setScene(callback);
            }
        });

        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10, 10, 10, 10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //Main balance hbox
        HBox balance_hbox = new HBox(5);
        //Balance Label
        Label balance_label = new Label(String.valueOf(info.balance));
     
        //Add to balance hbox
        balance_hbox.getChildren().addAll(new Label("Balance: "), balance_label);
        
        //Main interest rate hbox
        HBox interest_rate_hbox = new HBox(5);
        //Interest rate Label
        Label interest_rate_label = new Label(String.valueOf(info.interest_rate));
      
        //Add to interest rate hbox
        interest_rate_hbox.getChildren().addAll(new Label("Interest Rate: "), interest_rate_label);
        
        //Customer ID HBox
        HBox customer_id_hbox = new HBox(2);

        //Add to customer id hbox
        customer_id_hbox.getChildren().addAll(new Label("CustomerID: "+String.valueOf(info.customerid)));
        
        //Creating button array
        HBox manage_button_hbox = new HBox(10);
        
        //Create new deposit button
        Button deposit_button = new Button();
        deposit_button.setText("Deposit");
        deposit_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = AccountDepositSubmenu.Build(main_menu, info);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Deposit");
                primary.setScene(callback);
            }
        });
        
        Button withdraw_button = new Button();
        withdraw_button.setText("Withdraw");
        withdraw_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = AccountWithdrawSubmenu.Build(main_menu, info);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Withdrawal");
                primary.setScene(callback);
            }
        });
        
        Button transfer_button = new Button();
        transfer_button.setText("Transfer");
        transfer_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Transfer click");
            }
        });
        
        Button edit_rate_button = new Button();
        edit_rate_button.setText("Edit Interest Rate");
        edit_rate_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Edit IR click");
            }
        });
        
        
        manage_button_hbox.getChildren().addAll(deposit_button,withdraw_button,transfer_button,edit_rate_button);
        
        Button delete_acc_button = new Button();
        delete_acc_button.setText("Delete Account");
        delete_acc_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //String sql = "DELETE FROM customeraccount WHERE AccountID = "+(String.valueOf(info.accountid));
                System.out.println("Delete account click");
            }
        });

        //Add to central vbox
        info_entry.getChildren().addAll(customer_id_hbox, balance_hbox, interest_rate_hbox, manage_button_hbox,delete_acc_button);
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
        return AccountManagementSubmenu.Build(main_menu, new AccountInformation(0,(float) 0.00,(float) 0.00,0));
    }
}
