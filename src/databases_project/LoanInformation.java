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
public class LoanInformation {
    public int id;
    public float principle;
    public int customer_id;
    public int approver_id;
    public float interest;
    public float interest_rate;
    
    public LoanInformation(int temp_id, float temp_principle, int temp_cid, int temp_aid, float temp_int, float temp_intr){
        id=temp_id;
        principle = temp_principle;
        customer_id = temp_cid;
        approver_id = temp_aid;
        interest = temp_int;
        interest_rate = temp_intr;
    }
}
