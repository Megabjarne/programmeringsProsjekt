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
    
    /**
     * Creates a new instance of ticket
     * @param seat, The seat-object this ticket is associated with
     * @param price, The price at which this ticket was sold at
     * @param flight, The ID of the flight this ticket is for
     * @param passenger, The passenger/person this ticket is bought by
     */
    public Ticket(Seat seat, int price, String flight, Passenger passenger)
    {
        this.seat = seat;
        this.price = price;
        this.flightID = flight;
        this.passenger = passenger;
        seat.reserve();
    }
    
    /**
     * Gets the unique ID of this ticket
     * @return ticketID, The ID of this ticket
     */
    public int getID(){
        return ticketID;
    }
    
    /**
     * Sets the ID of the ticket
     * @param ticketID, The new ID
     */
    public void setID(int ticketID){
        this.ticketID=ticketID;
    }
    
    /**
     * Returns the price at which the ticket was sold
     * @return price, the price at which the ticket was sold
     */
    public int getPrice()
    {
        return price;
    }
    
    /**
     * Returns the flightID of the flight associated with the ticket
     * @return flightID, the flightID of the flight associated with the ticket
     */
    public String getFlightID()
    {
       return flightID; 
    }
    
    /**
     * Returns a textual representation of the object
     * @return Textual representation of the object
     */
    @Override
    public String toString(){
        return  flightID + " " + passenger + " " + seat + " " + Integer.toString(price) + ",-";
    }
}
