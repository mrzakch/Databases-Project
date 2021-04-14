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
public class EmployeeManagementSubmenu {

    //Builds the scene for New Person
    public static Scene Build(MenuManager main_menu, EmployeeInformation info) {
        //Init main stackpane
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        //Create back button
        Button back_button = new Button();
        back_button.setText("Back");
        back_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = EmployeeLookupSubmenu.Build(main_menu,String.valueOf(info.employee_id));
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Employee Management");
                primary.setScene(callback);
            }
        });

        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10, 10, 10, 10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        
        //position ID HBox
        HBox position_hbox = new HBox(5);

        //position label
        Label position_label = new Label("Position ID: " + String.valueOf(info.position_id));

        //Edit button
        Button edit_position_button = new Button();
        edit_position_button.setText("Change");
        edit_position_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Edit position click");
            }
        });
        //Add to position hbox
        position_hbox.getChildren().addAll(position_label, edit_position_button);
        
        //department ID HBox
        HBox department_hbox = new HBox(5);

        //department label
        Label department_label = new Label("Department ID: " + String.valueOf(info.department_id));

        //Edit button
        Button edit_department_button = new Button();
        edit_department_button.setText("Change");
        edit_department_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Edit department click");
            }
        });
        //Add to department hbox
        department_hbox.getChildren().addAll(department_label, edit_department_button);
        
        Button delete_emp_button = new Button();
        delete_emp_button.setText("Delete Employee");
        delete_emp_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //String sql = "DELETE FROM employee WHERE EmployeeID = "+String.valueOf(info.employee_id));
                System.out.println("Delete employee click");
            }
        });
        
        //salary ID HBox
        HBox salary_hbox = new HBox(5);

        //salary label
        Label salary_label = new Label("Salary ID: " + String.valueOf(info.salary));

        //Edit button
        Button edit_salary_button = new Button();
        edit_salary_button.setText("Change");
        edit_salary_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Edit salary click");
            }
        });
        //Add to salary hbox
        salary_hbox.getChildren().addAll(salary_label, edit_salary_button);
        
        //home_branch ID HBox
        HBox home_branch_hbox = new HBox(5);

        //home_branch label
        Label home_branch_label = new Label("Home Branch ID: " + String.valueOf(info.home_branch_id));

        //Edit button
        Button edit_home_branch_button = new Button();
        edit_home_branch_button.setText("Change");
        edit_home_branch_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Edit home branch click");
            }
        });
        //Add to home_branch hbox
        home_branch_hbox.getChildren().addAll(home_branch_label, edit_home_branch_button);

        //Add to central vbox
        info_entry.getChildren().addAll(position_hbox,department_hbox,salary_hbox,home_branch_hbox,delete_emp_button);
        //Add to root
        pane.getChildren().addAll(info_entry, back_button);
        //Set alignments
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }
}
