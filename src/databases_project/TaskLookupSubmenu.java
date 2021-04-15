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
public class TaskLookupSubmenu {
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
        
        HBox task_hbox = new HBox(2);
        
        TextField task_input = new TextField();
        task_input.setText(start_id);
        
        Button new_task = new Button();
        new_task.setText("New");
        new_task.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = NewTaskSubmenu.Build(main_menu);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("New Task");
                primary.setScene(callback);
            }
        });
        
        task_hbox.getChildren().addAll(new Label("Task ID: "),task_input,new Label(" or "),new_task);

        //Create error label.
        Label err = new Label();
        err.setTextFill(Color.RED);
        err.setText("");
        
        Button lookup = new Button();
        lookup.setText("Manage");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //String sql = "SELECT * FROM task WHERE TaskID = "+(task_input.getText()));
                try {
                    int task_num=Integer.parseInt(task_input.getText());
                    System.out.println("Looking up "+task_num);
                    //Add lookup functionality!
                    try {
                        Connection reservation = main_menu.connectDatabase();
                        String sql = "SELECT * FROM task WHERE TaskID=" + task_input.getText();
                        PreparedStatement statement = reservation.prepareStatement(sql);
                        ResultSet out = statement.executeQuery();
                        if (out.next()) {
                            Scene callback = TaskManagementSubmenu.Build(main_menu, new TaskInformation(out.getInt("TaskID"), out.getString("TaskDescription"), out.getString("Title")));
                            Stage primary = main_menu.getPrimary();
                            primary.setTitle("Task Management || " + task_input.getText());
                            primary.setScene(callback);
                        }
                        main_menu.closeDatabase();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (NumberFormatException e){
                    System.out.println("Input is NAN");
                    err.setText("Please enter a valid ID");
                }
                
            }
        });
        
        info_entry.getChildren().addAll(task_hbox,lookup,err);
        
        pane.getChildren().addAll(info_entry,back_button);
        pane.setAlignment(back_button,Pos.TOP_LEFT);
        pane.setAlignment(info_entry,Pos.CENTER);
        Scene scene = new Scene(pane,500,500);
        return scene;
    }
    
    public static Scene Build(MenuManager main_menu){
        return TaskLookupSubmenu.Build(main_menu,"");
    }
}
