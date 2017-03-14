package OOPPackage;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class Ticket {
    int ticketID;
    Seat seat;
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
