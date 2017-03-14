package OOPPackage;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class TicketReservationSystem {
    static final TicketReservationSystem instance = new TicketReservationSystem();
    
    public FlightRegister flightRegister = new FlightRegister();
    public TicketRegister ticketRegister = new TicketRegister();
    
    public static TicketReservationSystem getInstance(){
        return instance;
    }
    
}
