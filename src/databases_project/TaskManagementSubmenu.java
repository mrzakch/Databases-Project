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
import javafx.scene.control.ScrollPane;
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

public class TaskManagementSubmenu {   
    //Builds the lines for each returned result
    private static HBox makeLine(int id){
        HBox container = new HBox(3);
        
        Button remove = new Button();
        remove.setText("Remove");
        remove.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //remove from task
            }
        });

        container.getChildren().addAll(new Label(String.valueOf(id)),remove);
        
        return container;
    };
    
    //Builds the scene for Account Lookup
    public static Scene Build(MenuManager main_menu, TaskInformation info){
        //Init main stackpane
        VBox pane = new VBox(5);
        pane.setPadding(new Insets(10,10,10,10));
        //Create back button
        Button back_button = new Button();
        back_button.setText("Back");
        back_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = TaskLookupSubmenu.Build(main_menu);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Task Management");
                primary.setScene(callback);
            }
        });
        
        
        Button delete_button = new Button();
        delete_button.setText("Delete Task");
        delete_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //String sql = "DELETE FROM task WHERE TaskID = "+(info.id));
            }
        });

        //Add UI elements to root.
        pane.getChildren().addAll(back_button,new Label("Task Title: "+info.title),new Label("Task Description: "+info.description),delete_button,new Label("Employees With Task:"));
        //Align root UI elements properly.
        
        //FOR LOOP ADD ALL MATCHING ACCOUNTS
        int[] ids = {3,5};
        for (int i = 0; i<ids.length;i++){
            pane.getChildren().add(makeLine(ids[i]));
        }
        
        HBox add_employee_hbox = new HBox(3);
        
        TextField employee_input = new TextField();
        employee_input.setText("New Employee ID");
        
        Button add_button = new Button();
        add_button.setText("Add");
        add_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    int account_num=Integer.parseInt(employee_input.getText());
                    System.out.println("Adding "+account_num);
                    //ADD HERE
                } catch (NumberFormatException e){
                    System.out.println("Input is NAN");
                }
            }
        });
        
        add_employee_hbox.getChildren().addAll(employee_input,add_button);
        
        pane.getChildren().add(add_employee_hbox);
        
        ScrollPane parent = new ScrollPane();
        parent.setContent(pane);
        
        Scene scene = new Scene(parent,500,500);
        return scene;
    }
    //A method for use when we don't have a default ID.
    public static Scene Build(MenuManager main_menu){
        return TaskManagementSubmenu.Build(main_menu,new TaskInformation(3,"TestTask","TestDesc"));
    }
}
