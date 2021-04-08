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
    public static Scene Build(MenuManager main_menu, String start_id){
        
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10,10,10,10));
        
        Button back_button = new Button();
        back_button.setText("Back");
        back_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //main_menu.returnToMenu();
            }
        });
        
        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10,10,10,10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        
        HBox customer_hbox = new HBox(2);
        
        TextField person_input = new TextField();
        person_input.setText("");
        
        Button new_person = new Button();
        new_person.setText("New");
        new_person.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("New person click");
            }
        });
        
        customer_hbox.getChildren().addAll(new Label("Person ID: "),person_input,new Label(" or "),new_person);
        
        HBox interest_rate_hbox = new HBox(2);
        
        ComboBox<String> customer_type = new ComboBox<String>();
        customer_type.getItems().addAll("Person","Corporate","Municipal");
        
        interest_rate_hbox.getChildren().addAll(new Label("Customer Type: "),customer_type);
        
        HBox balance_hbox = new HBox(2);
        
        TextField init_balance_input = new TextField();
        
        balance_hbox.getChildren().addAll(new Label("Initial Balance: "),init_balance_input);
        
        Button lookup = new Button();
        lookup.setText("Create");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = AccountLookupSubmenu.Build(main_menu,"NewPersonID!");
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Account");
                primary.setScene(callback);
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
        return NewCustomerSubmenu.Build(main_menu,"");
    }
}
