/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class DepartmentLookupSubmenu {

    //Builds the scene for Account Lookup
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
                main_menu.returnToMenu();
            }
        });
        //Create main central VBox
        VBox info_entry = new VBox(5);
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //Department ID HBox
        HBox dept_hbox = new HBox(2);

        //Department input textbox creation.
        TextField department_input = new TextField();
        department_input.setText(start_id);

        Button new_dept = new Button();
        new_dept.setText("New");
        new_dept.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = NewDepartmentSubmenu.Build(main_menu);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Department Creation");
                primary.setScene(callback);
            }
        });

        dept_hbox.getChildren().addAll(department_input, new Label(" or "), new_dept);

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
                    int dept_num = Integer.parseInt(department_input.getText());
                    System.out.println("Looking up " + dept_num);
                    //Add lookup functionality!
                    //String sql = "SELECT * FROM department WHERE DepartmentID = "+(department_input.getText()));
                    try {
                        Connection reservation = main_menu.connectDatabase();
                        String sql = "SELECT * FROM department WHERE DepartmentID=" + department_input.getText();
                        PreparedStatement statement = reservation.prepareStatement(sql);
                        ResultSet out = statement.executeQuery();
                        if (out.next()) {
                            Scene callback = DepartmentManagementSubmenu.Build(main_menu, new DepartmentInformation(out.getInt("DepartmentID"), out.getString("DepartmentName"), out.getInt("ManagerEmployeeID")));
                            Stage primary = main_menu.getPrimary();
                            primary.setTitle("Department Management || " + department_input.getText());
                            primary.setScene(callback);
                        }
                        main_menu.closeDatabase();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input is NAN");
                    err.setText("Please enter a valid ID");
                }
            }
        });

        //Add UI elements to VBox.
        info_entry.getChildren().addAll(dept_hbox, lookup, err);
        //Add UI elements to root.
        pane.getChildren().addAll(info_entry, back_button);
        //Align root UI elements properly.
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }

    //A method for use when we don't have a default ID.
    public static Scene Build(MenuManager main_menu) {
        return DepartmentLookupSubmenu.Build(main_menu, "Department ID");
    }
}
