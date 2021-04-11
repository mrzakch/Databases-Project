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
public class AccountInformation {
    public int accountid;
    public float balance;
    public float interest_rate;
    public int customerid;
    
    public AccountInformation(int accid, float bal, float ir, int cusid){
        accountid=accid;
        customerid=cusid;
        interest_rate=ir;
        balance=bal;
    }
}
