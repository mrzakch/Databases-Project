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
public class EmployeeInformation {
    public int employee_id;
    public int position_id;
    public int department_id;
    public float salary;
    public int home_branch_id;
    
    public EmployeeInformation(int employid, int posid, int deptid, float sal, int hbid){
        employee_id=employid;
        position_id=posid;
        department_id=deptid;
        salary=sal;
        home_branch_id=hbid;
    }
}
