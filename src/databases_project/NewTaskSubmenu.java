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
public class NewTaskSubmenu {

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
                Scene callback = TaskLookupSubmenu.Build(main_menu);
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Task Management");
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
        name_hbox.getChildren().addAll(new Label("Task Name: "), name_input);
        //Manager HBox
        HBox desc_hbox = new HBox(2);
        //Main Interest Rate Input
        TextField desc_input = new TextField();
        //Add to interest rate HBox
        desc_hbox.getChildren().addAll(new Label("Task Description: "), desc_input);

        //Button to create new account
        Button lookup = new Button();
        lookup.setText("Create");
        lookup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //Create new ID in database
//String sql = "INSERT INTO task(Title, TaskDescription) VALUES ("+()+", "+()+");");
                try {
                    String new_id = newTask(main_menu, name_input.getText(), desc_input.getText());
                    Scene callback = TaskLookupSubmenu.Build(main_menu, new_id);
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("Task Management");
                    primary.setScene(callback);
                } catch (Exception e) {
                    e.printStackTrace();
                };
            }
        });
        //Add to main VBox
        info_entry.getChildren().addAll(name_hbox, desc_hbox, lookup);
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
        return NewTaskSubmenu.Build(main_menu, "");
    }

    @SuppressWarnings("null")
    private static String newTask(MenuManager main, String title, String desc) throws SQLException {
        //creation of the query in JDBC. Can be moved directly to database if needed
        Connection reservation = main.connectDatabase();
        String sql = "INSERT INTO task(Title, TaskDescription) VALUES (\"" +title+"\", \""+desc+"\")";
        System.out.println(sql);
        PreparedStatement statement = reservation.prepareStatement(sql);
        int out = statement.executeUpdate(sql);
        
        if (out==1){
            String get_new_id = "SELECT TaskID FROM task WHERE  Title=\"" +title+"\" AND TaskDescription=\""+desc+"\"";
            PreparedStatement get_statement = reservation.prepareStatement(get_new_id);
            ResultSet to_return = get_statement.executeQuery(get_new_id);
            if (to_return.next()){
                String new_id = String.valueOf(to_return.getInt("TaskID"));
                main.closeDatabase();
                return new_id;
            } else {
                main.closeDatabase();
                return "Error getting new ID";
            }
        } else {
            main.closeDatabase();
            return "Error creating new task";
        }
    }
}
