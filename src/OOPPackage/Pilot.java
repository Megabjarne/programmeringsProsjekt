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
     * @param firstName, first name of the pilot
     * @param lastName, last name of the pilot
     * @param emailAddress, email address of the pilot
     * @param employeeID, employee-ID of the pilot
     * @param certificateNumber, certificate-number of the pilot
     */
    public Pilot(String firstName, String lastName, String emailAddress, String employeeID, String certificateNumber){
        super(firstName,lastName,emailAddress,employeeID);
        this.certificateNumber = certificateNumber;
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
