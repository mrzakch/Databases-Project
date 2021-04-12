/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import java.sql.Connection;
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
public class LoanLookupSubmenu {
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
        
        HBox loan_hbox = new HBox(2);
        
        TextField loan_input = new TextField();
        loan_input.setText(start_id);
        
        Button new_loan = new Button();
        new_loan.setText("New");
        new_loan.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Loan Creation");
                primary.setScene(NewLoanSubmenu.Build(main_menu));
            }
        });
        
        loan_hbox.getChildren().addAll(new Label("Loan ID: "),loan_input,new Label(" or "),new_loan);

        //Create error label.
        Label err = new Label();
        err.setTextFill(Color.RED);
        err.setText("");
        
        Button lookup = new Button();
        lookup.setText("Manage");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //ADD LOOKUP FUNCTIONALITY
                try {
                    int loan_num=Integer.parseInt(loan_input.getText());
                    System.out.println("Looking up "+loan_num);
                    //Add lookup functionality!
                    //testing assumed lookup
                    LoanInformation loan_info = LoanLookup(main_menu, loan_num);
                    Scene callback = LoanManagementSubmenu.Build(main_menu,new LoanInformation(loan_num,loan_info.principle, loan_info.customer_id, loan_info.approver_id, loan_info.interest, loan_info.interest_rate));
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("Loan Management || "+loan_input.getText());
                    primary.setScene(callback);
                } catch (NumberFormatException | SQLException e){
                    System.out.println("Input is NAN");
                    err.setText("Please enter a valid ID");
                }
                
            }
        });
        
        info_entry.getChildren().addAll(loan_hbox,lookup,err);
        
        pane.getChildren().addAll(info_entry,back_button);
        pane.setAlignment(back_button,Pos.TOP_LEFT);
        pane.setAlignment(info_entry,Pos.CENTER);
        Scene scene = new Scene(pane,500,500);
        return scene;
    }
    
    public static LoanInformation LoanLookup(MenuManager main, int loan_num) throws SQLException {
    	//creation of the query in JDBC. Can be moved directly to database if needed
    	Connection reservation = main.connectDatabase();
    	String sql = "SELECT CustomerID, LoanOfficerEmployeeID, Principal, Interest, InterestRate FROM loan WHERE LoanID = "+(String.valueOf(loan_num));
    	Statement statement = reservation.createStatement();
    	ResultSet out = statement.executeQuery(sql);
    	
    	if(out!=null) {
    		LoanInformation loan_info = new LoanInformation(loan_num,  out.getFloat("Principal"), out.getInt("CustomerID"), out.getInt("LoanOfficerEmployeeID"), out.getFloat("Interest"), out.getFloat("InterestRate"));
    		main.closeDatabase();
    		return loan_info;
    	}else {
    		return null;
    	}
    }
    
    public static Scene Build(MenuManager main_menu){
        return LoanLookupSubmenu.Build(main_menu,"Loan ID");
    }
}
