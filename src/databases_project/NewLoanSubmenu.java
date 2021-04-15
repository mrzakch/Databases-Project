/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import java.lang.reflect.Method;
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
public class NewLoanSubmenu {

    //Builds the scene for New Person
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
                Scene callback = LoanLookupSubmenu.Build(main_menu, "");
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
        Label customer_label = new Label("Customer ID: ");

        //customer input
        TextField customer_input = new TextField();

        //add to customer hbox
        customer_hbox.getChildren().addAll(customer_label, customer_input);

        //Authorizer ID HBox
        HBox auth_hbox = new HBox(5);

        //Authorizer label
        Label auth_label = new Label("Authorizer ID: ");

        //auth input
        TextField auth_input = new TextField();

        //Add to Authorizer hbox
        auth_hbox.getChildren().addAll(auth_label, auth_input);

        //principle HBox
        HBox principle_hbox = new HBox(5);

        //principle input
        TextField principle_input = new TextField();

        //principle label
        Label principle_label = new Label("Principle: ");

        //Add to principle hbox
        principle_hbox.getChildren().addAll(principle_label, principle_input);

        //interest HBox
        HBox interest_hbox = new HBox(5);

        //interest label
        Label interest_label = new Label("Interest: 0");

        //Add to interest hbox
        interest_hbox.getChildren().addAll(interest_label);

        //Interest rate HBox
        HBox interest_rate_hbox = new HBox(5);

        //Interest rate label
        Label interest_rate_label = new Label("Interest Rate: ");

        //interest_rate input
        TextField interest_rate_input = new TextField();

        //Add to home_branch hbox
        interest_rate_hbox.getChildren().addAll(interest_rate_label, interest_rate_input);

        //Creating button array
        HBox manage_button_hbox = new HBox(10);

        Button make_button = new Button();
        make_button.setText("Create");
        make_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //handle adding to database
//String sql = "INSERT INTO loan(CustomerID, LoanOfficerEmployeeID, Principal, Interest, InterestRate) VALUES ("+(customer_input.getText())+", "+(auth_input.getText())+", "+(principle_input.getText())+",0, "+(interest_rate_input.getText())+");");=
                try {
                    String new_loan_id = addLoan(main_menu, Integer.parseInt(customer_input.getText()), Integer.parseInt(auth_input.getText()), Float.parseFloat(principle_input.getText()), Float.parseFloat(interest_rate_input.getText()));
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("Loan Processing");
                    primary.setScene(LoanLookupSubmenu.Build(main_menu, new_loan_id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        manage_button_hbox.getChildren().addAll(make_button);

        //Add to central vbox
        info_entry.getChildren().addAll(customer_hbox, auth_hbox, principle_hbox, interest_hbox, interest_rate_hbox, manage_button_hbox);
        //Add to root
        pane.getChildren().addAll(info_entry, back_button);
        //Set alignments
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }

    public static String addLoan(MenuManager mainM, int cust_id, int auth_id, float principal, float int_rate) throws SQLException {
        //input of new account info into database
        Connection reservation = mainM.connectDatabase();
        String sql = "INSERT INTO loan (CustomerID, LoanOfficerEmployeeID, Principal, InterestRate) VALUES(?, ?, ?, ?)";
        PreparedStatement statement = reservation.prepareStatement(sql);
        statement.setInt(1, cust_id);
        statement.setInt(2, auth_id);
        statement.setFloat(3, principal);
        statement.setFloat(4, int_rate);
        int out = statement.executeUpdate();

        if (out == 1) {
            String get_new_id = "SELECT LoanID FROM loan WHERE  CustomerID=" + cust_id + " AND LoanOfficerEmployeeID=" + auth_id + " AND Principal=" + principal;
            PreparedStatement get_statement = reservation.prepareStatement(get_new_id);
            ResultSet to_return = get_statement.executeQuery(get_new_id);
            if (to_return.next()) {
                String new_id = String.valueOf(to_return.getInt("LoanID"));
                mainM.closeDatabase();
                return new_id;
            } else {
                mainM.closeDatabase();
                return "Error getting new ID";
            }
        } else {
            mainM.closeDatabase();
            return "Error creating loan";
        }
    }
}
