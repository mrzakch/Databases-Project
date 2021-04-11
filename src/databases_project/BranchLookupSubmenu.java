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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Kyle
 */

public class BranchLookupSubmenu {   
    //Builds the scene for Account Lookup
    public static Scene Build(MenuManager main_menu, String start_id){
        //Init main stackpane
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10,10,10,10));
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
        //Branch input textbox creation.
        TextField branch_input = new TextField();
        branch_input.setText(start_id);
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
                    int branch_num=Integer.parseInt(branch_input.getText());
                    System.out.println("Looking up "+branch_num);
                    //Add lookup functionality!
                    //testing assumed lookup
                    Scene callback = BranchManagementSubmenu.Build(main_menu,new BranchInformation(branch_num,0,0));
                    Stage primary = main_menu.getPrimary();
                    primary.setTitle("Branch Management || "+branch_input.getText());
                    primary.setScene(callback);
                } catch (NumberFormatException e){
                    System.out.println("Input is NAN");
                    err.setText("Please enter a valid ID");
                }
            }
        });
        
        
        //Add UI elements to VBox.
        info_entry.getChildren().addAll(branch_input,lookup,err);
        //Add UI elements to root.
        pane.getChildren().addAll(info_entry,back_button);
        //Align root UI elements properly.
        pane.setAlignment(back_button,Pos.TOP_LEFT);
        pane.setAlignment(info_entry,Pos.CENTER);
        Scene scene = new Scene(pane,500,500);
        return scene;
    }
    //A method for use when we don't have a default ID.
    public static Scene Build(MenuManager main_menu){
        return BranchLookupSubmenu.Build(main_menu,"Branch ID");
    }
}
