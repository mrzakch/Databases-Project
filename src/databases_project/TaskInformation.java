/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases_project;

/**
 *
 * @author Kyle
 */
public class TaskInformation {
    public int task_id;
    String description;
    String title;
    
    public TaskInformation(int temp_task_id, String temp_desc, String temp_title){
        task_id=temp_task_id;
        description=temp_desc;
        title=temp_title;
    }
}
