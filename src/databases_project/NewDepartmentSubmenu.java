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
public class NewDepartmentSubmenu {

    //Builds the scene for New Account
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
                Scene callback = DepartmentLookupSubmenu.Build(main_menu);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Department Information");
                primary.setScene(callback);
            }
        });

        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10, 10, 10, 10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //HBox for address id input
        HBox name_hbox = new HBox(2);
        //Main ID input
        TextField name_input = new TextField();
        name_input.setText(start_id);
        //Create new customer button

        //Add to the main customer HBox
        name_hbox.getChildren().addAll(new Label("Department Name: "), name_input);
        //Manager HBox
        HBox manager_hbox = new HBox(2);
        //Main Interest Rate Input
        TextField manager_input = new TextField();
        //Add to manager HBox
        //manager_hbox.getChildren().addAll(new Label("Manager ID: "),manager_input);

        //Button to create new account
        Button lookup = new Button();
        lookup.setText("Create");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //Create new ID in database
//String sql = "INSERT INTO department(DepartmentName,ManagerEmployeeID) VALUES ("+(name_input.getText())+", "+(manager_input.getText())+");");
                try {
                    String new_dept_id = addDepartment(main_menu, name_input.getText());
                    Scene callback = DepartmentLookupSubmenu.Build(main_menu, new_dept_id);
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("Department Management");
                    primary.setScene(callback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //Add to main VBox
        info_entry.getChildren().addAll(name_hbox, manager_hbox, lookup);
        //Add to root
        pane.getChildren().addAll(info_entry, back_button);
        //Adjust positioning
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }

    //A method for use when we don't have a default ID.
    public static Scene Build(MenuManager main_menu) {
        return NewDepartmentSubmenu.Build(main_menu, "");
    }

    public static String addDepartment(MenuManager mainM, String dept_name) throws SQLException {
        //input of new account info into database
        Connection reservation = mainM.connectDatabase();
        String sql = "INSERT INTO department (DepartmentName) VALUES(?)";
        PreparedStatement statement = reservation.prepareStatement(sql);
        statement.setString(1, dept_name);
        int out = statement.executeUpdate();

        if (out == 1) {
            String get_new_id = "SELECT DepartmentID FROM department WHERE  DepartmentName=\"" + dept_name + "\"";
            PreparedStatement get_statement = reservation.prepareStatement(get_new_id);
            ResultSet to_return = get_statement.executeQuery(get_new_id);
            if (to_return.next()) {
                String new_id = String.valueOf(to_return.getInt("DepartmentID"));
                mainM.closeDatabase();
                return new_id;
            } else {
                mainM.closeDatabase();
                return "Error getting new ID";
            }
        } else {
            mainM.closeDatabase();
            return "Error creating department";
        }
    }
}
