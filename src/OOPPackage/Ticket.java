package OOPPackage;

/**
 *
 * @author Bendik Matheussen Delp
 */
public class Ticket {
    int ticketID;
    Seat seat;
    Passenger passenger;
    int price;
    String flightID;
    
    public Ticket(Seat seat, int price, String flight, Passenger passenger)
    {
        this.seat = seat;
        this.price = price;
        this.flightID = flight;
        this.passenger = passenger;
    }
    public int getID(){
        return ticketID;
    }
    public void setID(int ticketID){
        this.ticketID=ticketID;
    }
    public int getPrice()
    {
        return price;
    }
    public String getFlightID()
    {
       return flightID; 
    }
    
    @Override
    public String toString(){
        return "tID: " + Integer.toString(ticketID) + " | " + seat + " - " + passenger + " on flight " + flightID;
    }
}
