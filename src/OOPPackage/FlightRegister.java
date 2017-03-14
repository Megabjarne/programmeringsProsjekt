package OOPPackage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class FlightRegister {
    ArrayList<Flight> flights = new ArrayList<>();
    
    public void addFlight(Flight flight){
        flights.add(flight);
    }
    
    /**
     * 
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
    
    public Iterator<Flight> iterator(){
        return flights.iterator();
    }
}
