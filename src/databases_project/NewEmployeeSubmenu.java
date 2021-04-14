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
public class NewEmployeeSubmenu {

    //Builds the scene for New Person
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
                Scene callback = EmployeeLookupSubmenu.Build(main_menu,"");
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
        
        //person ID HBox
        HBox person_hbox = new HBox(5);
        
        //person ID input
        TextField person_input = new TextField();
        person_input.setText(start_id);

        //person ID label
        Label person_label = new Label("Person ID: ");
        
        //new person button
        Button new_person_button = new Button();
        new_person_button.setText("New");
        new_person_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = NewPersonSubmenu.Build(main_menu,"NewEmployeeSubmenu");
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Person");
                primary.setScene(callback);
            }
        });

        //Add to position hbox
        person_hbox.getChildren().addAll(person_label,person_input,new Label(" or "),new_person_button);
        
        //position ID HBox
        HBox position_hbox = new HBox(5);
        
        //position input
        TextField position_input = new TextField();

        //position label
        Label position_label = new Label("Position ID: ");

        //Add to position hbox
        position_hbox.getChildren().addAll(position_label,position_input);
        
        //department ID HBox
        HBox department_hbox = new HBox(5);
        
        //department input
        TextField department_input = new TextField();

        //department label
        Label department_label = new Label("Department ID: ");

        //Add to department hbox
        department_hbox.getChildren().addAll(department_label,department_input);
        
        //salary HBox
        HBox salary_hbox = new HBox(5);
        
        //salary input
        TextField salary_input = new TextField();

        //salary label
        Label salary_label = new Label("Salary: ");

        //Add to salary hbox
        salary_hbox.getChildren().addAll(salary_label, salary_input);
        
        //home_branch ID HBox
        HBox home_branch_hbox = new HBox(5);
        
        //home_branch input
        TextField home_branch_input = new TextField();

        //home_branch label
        Label home_branch_label = new Label("Home Branch ID: ");

        //Add to home_branch hbox
        home_branch_hbox.getChildren().addAll(home_branch_label, home_branch_input);
        
        Button create_emp_button = new Button();
        create_emp_button.setText("Create Employee");
        create_emp_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
//String sql = "INSERT INTO employee(PersonID, PositionID, DepartmentID, Salary, HomeBranchID) VALUES ("+(person_input.getText())+", "+(position_input.getText())+", "+(department_input.getText())+", "+(salary_input.getText())+", "+(home_branch_input.getText())+");");
                System.out.println("Create employee click");
            }
        });

        //Add to central vbox
        info_entry.getChildren().addAll(person_hbox,position_hbox,department_hbox,salary_hbox,home_branch_hbox,create_emp_button);
        //Add to root
        pane.getChildren().addAll(info_entry, back_button);
        //Set alignments
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }
    
    public static Scene Build(MenuManager main_menu){
        return NewEmployeeSubmenu.Build(main_menu,"");
    }
}
