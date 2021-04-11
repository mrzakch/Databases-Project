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
public class BranchInformation {
    public int id;
    public int address_id;
    public float manager_id;
    
    public BranchInformation(int temp_id, int temp_address_id, int temp_manager_id){
        id=temp_id;
        manager_id=temp_manager_id;
        address_id=temp_address_id;
    }
}
