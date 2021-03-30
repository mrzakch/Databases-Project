/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

import javafx.scene.*;

/**
 *
 * @author Kyle
 */

//Helper functions

//

public class MenuManager {
    private Scene[] menu_scenes;
    
    private Scene menu;
    
    public MenuManager(Parent root, double x, double y){
        menu=new Scene(root,x,y);
    }
    
    public void addToMenu(Scene to_add, String menu_title){
        //Takes a scene and makes a button for it in the menu's scene.
    }
    
    public Scene getMenuScene(){
        return menu;
    }
            
}
