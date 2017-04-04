package OOPPackage;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class TicketReservationSystem {

    /**
     * Global instance of the TicketReservationSystem, assuming only a single
     * system will run in this process
     */
    static final TicketReservationSystem instance = new TicketReservationSystem();

    /**
     * registers holding all information contained in this system
     */
    private final FlightRegister flightRegister;
    private final TicketRegister ticketRegister;
    private final PersonRegister personRegister;

    /**
     * private default constructor, no TicketReservationSystem can be created
     * outside the global static instance contained within the class
     */
    private TicketReservationSystem() {
        flightRegister = new FlightRegister();
        ticketRegister = new TicketRegister();
        personRegister = new PersonRegister();
    }

    /**
     * Returns the global instance of the TicketReservationSystem-class
     *
     * @return the global TicketReservationSystem-instance
     */
    public static TicketReservationSystem getInstance() {
        return instance;
    }

    /**
     * Registers a flight in the flight-register
     *
     * @param newFlight, The flight to be registered
     */
    public void registerFlight(Flight newFlight) {
        flightRegister.addFlight(newFlight);
    }

    /**
     * Registers a ticket in the ticket-register
     *
     * @param newTicket, The ticket to be registered
     */
    public void registerTicket(Ticket newTicket) {
        ticketRegister.addTicket(newTicket);
    }

    public void registerPerson(Person person) {
        personRegister.add(person);
    }

    /**
     * Returns an iterator for the flight register, allowing iteration through
     * all registered flights
     *
     * @return Iterator for all flights registered within the system
     */
    public Iterator<Flight> getFlightIterator() {
        return flightRegister.iterator();
    }

    /**
     * Returns an iterator for the ticket register, allowing iteration through
     * all registered tickets
     *
     * @return Iterator for all tickets registered within the system
     */
    public Iterator<Ticket> getTicketIterator() {
        return ticketRegister.iterator();
    }

    /**
     * Returns the flight with the given flight-ID, returns null if flight not
     * found
     *
     * @param ID, the flight-ID of the flight to be returned
     * @return The flight with the given flight-ID, null if flight is not found
     */
    public Flight getFlightByFlightID(String ID) {
        return flightRegister.getFlightByFlightID(ID);
    }

    /**
     * Returns an iterator for all objects in the persons register of the given
     * type
     *
     * @param <T>
     * @param wantedClass, instance of the class wanted
     * @return Iterator to a list of objects of the type specified by wanted
     * class
     */
    public <T> Iterator<T> getPersonIterator(Class<T> wantedClass) {
        return personRegister.getIterator(wantedClass);
    }

    /**
     * Returns a list of all objects in the person-register of the given class
     *
     * @param <T>
     * @param wantedClass, class-instance of the class you wish to extract from
     * the person-register
     * @return List of all objects in the person-register being the exact same
     * class as the given wanted classF
     */
    public <T> List<T> getPersonList(Class<T> wantedClass) {
        return personRegister.getList(wantedClass);
    }

    /**
     * Returns an iterator for all objects in the persons register that are
     * instances of the given class
     *
     * @param <T>
     * @param wantedClass, instance of the class/parent wanted
     * @return Iterator to a list of objects that are instances of the given
     * class class
     */
    public <T> Iterator<T> getPersonIteratorStrict(Class<T> wantedClass) {
        return personRegister.getIteratorStrict(wantedClass);
    }

    /**
     * Returns a list of all objects in the person-register that are instances
     * of the given class
     *
     * @param <T>
     * @param wantedClass, instance of the class/parent wanted
     * @return List of all objects in the person-register being the same class
     * or child of the given class
     */
    public <T> List<T> getPersonListStrict(Class<T> wantedClass) {
        return personRegister.getListStrict(wantedClass);
    }
}
