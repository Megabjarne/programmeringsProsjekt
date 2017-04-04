/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOPPackage;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class Pilot extends Crew {
    
    private String certificateNumber;
    
    /**
     * Constructor for the pilot-object
     * @param _firstName, first name of the pilot
     * @param _lastName, last name of the pilot
     * @param _emailAddress, email address of the pilot
     * @param _employeeID, employee-ID of the pilot
     * @param _certificateNumber, certificate-number of the pilot
     */
    public Pilot(String _firstName, String _lastName, String _emailAddress, String _employeeID, String _certificateNumber){
        super(_firstName,_lastName,_emailAddress,_employeeID);
        certificateNumber = _certificateNumber;
    }
    
    /**
     * Returns the certificate-number of the pilot
     * @return The certificate-number of the pilot
     */
    public String getCertificateNumber(){
        return certificateNumber;
    }
    
    /**
     * Textual representation of all information describing the object
     * @return Textual representation of the object
     */
    @Override
    public String toString(){
        return super.toString() + " certificate: '" + certificateNumber + "'";
    }
}
