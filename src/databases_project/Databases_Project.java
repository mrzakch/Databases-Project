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

import java.sql.*;

/**
 *
 * @author Kyle
 * 
Submenus: (For reference)
Account lookup (Designed)
Account management (subsidiary of lookup, need to get proper lookup info first)
Opening of new accounts with new customers. (Designed)
Employee management
Task management
Department management
Loan Processing
Branch Information

 * 
 * 
 */
public class Databases_Project extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        MenuManager main_menu = new MenuManager(500, 500, primaryStage);

        //Temporarily storing these here for easier management, will eventually have dedicated method in main_menu
        main_menu.addToMenu(AccountLookupSubmenu.Build(main_menu), "Lookup Account");
        main_menu.addToMenu(NewAccountSubmenu.Build(main_menu), "New Account");
        main_menu.addToMenu(EmployeeManagementSubmenu.Build(main_menu),"Employee Management");
        
        //Considering making this part of the employee management submenu.
        main_menu.addToMenu(new Scene(new StackPane(), 500, 500),"Task Management");
        
        main_menu.addToMenu(DepartmentLookupSubmenu.Build(main_menu),"Department Management");
        main_menu.addToMenu(new Scene(new StackPane(), 500, 500),"Loan Processing");
        main_menu.addToMenu(new Scene(new StackPane(), 500, 500),"Branch Information");
        
        main_menu.returnToMenu();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
