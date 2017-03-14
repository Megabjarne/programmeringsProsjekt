package OOPPackage;

/**
 *
 * @author Bendik Matheussen Delp
 */
public class Ticket {
    int ticketID;
    int seatID;
    Passenger passenger;
    int price;
    String flightID;
    
    public Ticket(int seatId, int _price, String flight, Passenger _passenger)
    {
        seatID = seatId;
        price = _price;
        flightID = flight;
        passenger = _passenger;
    }
    public int getID(){
        return ticketID;
    }
    public void setID(int _ID){
        ticketID=_ID;
    }
    public int getPrice()
    {
        return price;
    }
    public String getFlightID()
    {
       return flightID; 
    }
}
