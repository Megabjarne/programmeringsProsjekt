package OOPPackage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class FlightRegister {
    //Arraylist holding all registered flights
    ArrayList<Flight> flights = new ArrayList<>();
    
    /**
     * Adds a new flight to the flight register
     * @param flight, the flight-object to be added
     */
    public void addFlight(Flight flight){
        flights.add(flight);
    }
    
    /**
     * Searches the flight register for flight with the given flight ID, returns it if found
     * @param ID
     *      ID of the flight to search for
     * @return flight
     *      The flight belonging to the given ID, null if not found
     */
    public Flight getFlightByFlightID(String ID){
        for (Flight f:flights){
            if (f.flightID.equals(ID)){
                return f;
            }
        }
        return null;
    }
    
    /**
     * accessor to the iterator of the flights-list
     * @return Iterator to all registered flights
     */
    public Iterator<Flight> iterator(){
        return flights.iterator();
    }
}
