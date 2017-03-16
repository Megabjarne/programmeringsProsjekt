package OOPPackage;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class TicketReservationSystem {
    /**
     * Global instance of the TicketReservationSystem, assuming only a single system will run in this process
     */
    static final TicketReservationSystem instance = new TicketReservationSystem();
    
    public FlightRegister flightRegister = new FlightRegister();
    public TicketRegister ticketRegister = new TicketRegister();
    
    /**
     * Returns the global instance of the TicketReservationSystem-class
     * @return the global TicketReservationSystem-instance
     */
    public static TicketReservationSystem getInstance(){
        return instance;
    }
    
}
