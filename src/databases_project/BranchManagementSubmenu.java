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
public class BranchManagementSubmenu {

    //Builds the scene for New Person
    public static Scene Build(MenuManager main_menu, BranchInformation info) {
        //Init main stackpane
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        //Create back button
        Button back_button = new Button();
        back_button.setText("Back");
        back_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scene callback = BranchLookupSubmenu.Build(main_menu, String.valueOf(info.id));
                Stage primary = main_menu.getPrimary();
                primary.setTitle("Branch Information");
                primary.setScene(callback);
            }
        });

        //Center VBox
        VBox info_entry = new VBox(5);
        info_entry.setPadding(new Insets(10, 10, 10, 10));
        info_entry.setFillWidth(false);
        info_entry.setAlignment(Pos.CENTER);
        //Main balance hbox
        HBox address_hbox = new HBox(5);
        //Address Label
        Label address_label = new Label(String.valueOf(info.address_id));

        //Add to name hbox
        address_hbox.getChildren().addAll(new Label("Address ID: "), address_label);

        //Manager ID HBox
        HBox manager_hbox = new HBox(5);

        //Manager label
        Label manager_label = new Label("Manager ID: " + String.valueOf(info.manager_id));

        //Edit button
        Button edit_manager_button = new Button();
        edit_manager_button.setText("Change");
        edit_manager_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Edit manager click");
            }
        });
        //Add to manager hbox
        manager_hbox.getChildren().addAll(manager_label, edit_manager_button);

        //Delete button
        Button delete_dept_button = new Button();
        delete_dept_button.setText("Delete Branch");
        delete_dept_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //String sql = "DELETE FROM branch WHERE BranchID = "+(info.id));
                System.out.println("Delete branch click");
            }
        });
        //Add to central vbox
        info_entry.getChildren().addAll(address_hbox, manager_hbox, delete_dept_button);
        //Add to root
        pane.getChildren().addAll(info_entry, back_button);
        //Set alignments
        pane.setAlignment(back_button, Pos.TOP_LEFT);
        pane.setAlignment(info_entry, Pos.CENTER);
        Scene scene = new Scene(pane, 500, 500);
        return scene;
    }

    //A method for use when we don't have a default ID.
    public static Scene Build(MenuManager main_menu) {
        return BranchManagementSubmenu.Build(main_menu, new BranchInformation(0,0,0));
    }
}
