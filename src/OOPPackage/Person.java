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
public class Person {

    protected String firstName, surName;
    protected String mailAddress;

    /**
     * Constructor for creating a basic person-object, only to be called from
     * subclasses.
     * @param _firstName, the first name of the person
     * @param _lastName, the last name of the person
     * @param _emailAddress, the email address associated with the person
     */
    protected Person(String _firstName, String _lastName, String _emailAddress) {
        firstName = _firstName;
        surName = _lastName;
        mailAddress = _emailAddress;
    }

    /**
     * Returns the first name associated with the person-object
     *
     * @return The first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name associated with the person-object
     *
     * @return The last name of the person
     */
    public String getLastName() {
        return surName;
    }

    /**
     * Returns the email address of the person-object
     *
     * @return The email address of the person
     */
    public String getEmailAddress() {
        return mailAddress;
    }

    /**
     * Default stringification of the object
     *
     * @return String containing last name, first name and email address
     */
    @Override
    public String toString() {
        return surName + ", " + firstName + " - " + mailAddress;
    }
}
