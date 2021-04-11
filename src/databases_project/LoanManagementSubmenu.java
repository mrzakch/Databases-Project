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
public class LoanManagementSubmenu {

    //Builds the scene for New Person
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
                Scene callback = LoanLookupSubmenu.Build(main_menu,String.valueOf(info.id));
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Loan Processing");
                primary.setScene(callback);
            }
        });

        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10, 10, 10, 10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        
        //customer ID HBox
        HBox customer_hbox = new HBox(5);

        //customer label
        Label customer_label = new Label("Customer ID: " + String.valueOf(info.customer_id));
        
        //add to customer hbox
        customer_hbox.getChildren().addAll(customer_label);
        
        //Authorizer ID HBox
        HBox auth_hbox = new HBox(5);

        //Authorizer label
        Label auth_label = new Label("Authorizer ID: " + String.valueOf(info.approver_id));
        

        //Add to Authorizer hbox
        auth_hbox.getChildren().addAll(auth_label);
        
        //principle HBox
        HBox principle_hbox = new HBox(5);

        //principle label
        Label principle_label = new Label("Principle: " + String.valueOf(info.principle));

        //Add to principle hbox
        principle_hbox.getChildren().addAll(principle_label);
        
        //interest HBox
        HBox interest_hbox = new HBox(5);

        //interest label
        Label interest_label= new Label("Interest: " + String.valueOf(info.interest));

        //Add to interest hbox
        interest_hbox.getChildren().addAll(interest_label);
        
        //Interest rate HBox
        HBox interest_rate_hbox = new HBox(5);

        //Interest rate label
        Label interest_rate_label = new Label("Interest Rate: " + String.valueOf(info.interest_rate));

        //Edit button
        Button edit_interest_rate_button = new Button();
        edit_interest_rate_button.setText("Edit");
        edit_interest_rate_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = LoanEditingSubmenu.Build(main_menu, "InterestRate" ,info);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Editing Interest Rate");
                primary.setScene(callback);
            }
        });
        //Add to home_branch hbox
        interest_rate_hbox.getChildren().addAll(interest_rate_label, edit_interest_rate_button);
        
        //Creating button array
        HBox manage_button_hbox = new HBox(10);
        
        Button pay_button = new Button();
        pay_button.setText("Make Payment");
        pay_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Payment click");
            }
        });
        
        
        manage_button_hbox.getChildren().addAll(pay_button);

        //Add to central vbox
        info_entry.getChildren().addAll(customer_hbox,auth_hbox, principle_hbox, interest_hbox, interest_rate_hbox,manage_button_hbox);
        //Add to root
        pane.getChildren().addAll(info_entry, back_button);
        //Set alignments
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }
}
