/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOPPackage;

/**
 * @author Thomas Sund Mjåland
 */
public class Crew extends Person {
    
    protected String employeeID;
    
    /**
     * Creates a crew-object with the following information
     * @param firstName, first name
     * @param lastName, last name
     * @param emailAddress, mail address
     * @param employeeID, the employee's ID
     */
    public Crew(String firstName, String lastName, String emailAddress, String employeeID){
        super(firstName, lastName, emailAddress);
        this.employeeID = employeeID;
    }
    
    /**
     * Returns the employee-ID of the employee
     * @return the employee-ID of the employee
     */
    public String getEmployeeID(){
        return employeeID;
    }
    
    /**
     * Textual representation of all information describing the object
     * @return Textual representation of the object
     */
    @Override
    public String toString(){
        return super.toString() + " eID: '" + employeeID + "'";
    }
}
