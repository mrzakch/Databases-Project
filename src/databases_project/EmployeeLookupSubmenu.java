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
public class EmployeeLookupSubmenu {
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
        
        HBox employee_hbox = new HBox(2);
        
        TextField employee_input = new TextField();
        employee_input.setText(start_id);
        
        Button new_employee = new Button();
        new_employee.setText("New");
        new_employee.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = NewEmployeeSubmenu.Build(main_menu);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Employee");
                primary.setScene(callback);
            }
        });
        
        employee_hbox.getChildren().addAll(new Label("Employee ID: "),employee_input,new Label(" or "),new_employee);

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
                    int emp_num=Integer.parseInt(employee_input.getText());
                    System.out.println("Looking up "+emp_num);
                    //Add lookup functionality!
                    //String sql = "SELECT * FROM employee WHERE DepartmentID = "+(info.id));
                    //testing assumed lookup
                    Scene callback = EmployeeManagementSubmenu.Build(main_menu,new EmployeeInformation(emp_num,0,0,0,0));
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("Employee Management || "+employee_input.getText());
                    primary.setScene(callback);
                } catch (NumberFormatException e){
                    System.out.println("Input is NAN");
                    err.setText("Please enter a valid ID");
                }
                
            }
        });
        
        info_entry.getChildren().addAll(employee_hbox,lookup,err);
        
        pane.getChildren().addAll(info_entry,back_button);
        pane.setAlignment(back_button,Pos.TOP_LEFT);
        pane.setAlignment(info_entry,Pos.CENTER);
        Scene scene = new Scene(pane,500,500);
        return scene;
    }
    
    public static Scene Build(MenuManager main_menu){
        return EmployeeLookupSubmenu.Build(main_menu,"");
    }
}
