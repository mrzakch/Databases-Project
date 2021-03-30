/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Kyle
 */
public class Databases_Project extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button acc_lookup_select = new Button();
        acc_lookup_select.setText("Lookup Event");
        acc_lookup_select.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Lookup Account");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(acc_lookup_select);

        Scene scene = new Scene(root, 500, 700);
        scene.setFill(Color.BLUE);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
