package OOPPackage;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class TicketReservationSystem {
    TicketReservationSystem instance = new TicketReservationSystem();
    
    public FlightRegister flightRegister = new FlightRegister();
    public TicketRegister ticketRegister = new TicketRegister();
    
    public TicketReservationSystem getInstance(){
        return instance;
    }
    
}
