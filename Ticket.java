/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmeringsProsjekt;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class Ticket {
    int ticketID;
    int seatID;
    Passenger passenger;
    int price;
    String flightID;
    
    public int getID(){
        return ticketID;
    }
    public void setID(int _ID){
        ticketID=_ID;
    }
}
