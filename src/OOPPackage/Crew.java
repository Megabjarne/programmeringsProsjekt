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
    
    public Crew(String _firstName, String _lastName, String _emailAddress, String _employeeID){
        super(_firstName, _lastName, _emailAddress);
        employeeID = _employeeID;
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
