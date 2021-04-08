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
public class NewAccountSubmenu {
    public static Scene Build(MenuManager main_menu, String start_id){
        
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10,10,10,10));
        
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
        
        HBox customer_hbox = new HBox(2);
        
        TextField customer_input = new TextField();
        customer_input.setText(start_id);
        
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
        
        customer_hbox.getChildren().addAll(new Label("Customer ID: "),customer_input,new Label(" or "),new_customer);
        
        HBox interest_rate_hbox = new HBox(2);
        
        TextField interest_rate_input = new TextField();
        
        interest_rate_hbox.getChildren().addAll(new Label("Interest Rate: "),interest_rate_input);
        
        HBox balance_hbox = new HBox(2);
        
        TextField init_balance_input = new TextField();
        
        balance_hbox.getChildren().addAll(new Label("Initial Balance: "),init_balance_input);
        
        Button lookup = new Button();
        lookup.setText("Create");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Creating new acc");
            }
        });
        
        info_entry.getChildren().addAll(customer_hbox,interest_rate_hbox,balance_hbox,lookup);
        
        pane.getChildren().addAll(info_entry,back_button);
        pane.setAlignment(back_button,Pos.TOP_LEFT);
        pane.setAlignment(info_entry,Pos.CENTER);
        Scene scene = new Scene(pane,500,500);
        return scene;
    }
    
    public static Scene Build(MenuManager main_menu){
        return NewAccountSubmenu.Build(main_menu,"");
    }
}
