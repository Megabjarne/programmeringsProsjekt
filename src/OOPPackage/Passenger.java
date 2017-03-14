package OOPPackage;


/**
 * Write a description of class Passenger here.
 * 
 * @author Bendik Delp
 */
public class Passenger
{
    // instance variables - replace the example below with your own
    private String firstName;
    private String surName;
    private String mailAddress;

    /**
     * Constructor for objects of class Passenger
     */
    public Passenger(String lastName, String personName, String emailAddress)
    {
        firstName = personName;
        surName = lastName;
        mailAddress = emailAddress;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String lastName()
    {
        return surName;
    }
    

    public String emailAddress()
    {
        return mailAddress;
    }

    @Override
    public String toString(){
        return surName + ", " + firstName + " " + mailAddress;
    }
}
