package OOPPackage;

import java.time.*;
import java.util.*;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class Flight {

    long departureTime;
    long arrivalTime;
    String departureCity;
    String arrivalCity;
    String flightID;
    SeatRegister seats;
    List<Crew> crew = new ArrayList<>();

    /**
     * Creates a new flight with the given seat-array
     * @param rows, number of seat rows in the plane
     * @param columns, number of seat columns in the plane
     */
    public Flight(int rows, int columns) {
        seats = new SeatRegister(rows, columns);
    }

    /**
     * Sets the departureTime to a new instant
     * @param time, the instant where the flight leaves
     * @return Itself
     */
    public Flight setDepartureTime(Instant time) {
        departureTime = time.getEpochSecond();
        return this;
    }
    
    /**
     * returns the departure-time of the flight
     * @return the departure-instant of the flight
     */
    public Instant getDepartureTime(){
        return Instant.ofEpochSecond(departureTime);
    }

    /**
     * Sets the arrivalTime to a new instant
     * @param time, the instant where the flight arrives
     * @return Itself
     */
    public Flight setArrivalTime(Instant time) {
        arrivalTime = time.getEpochSecond();
        return this;
    }
    
    /**
     * Returns the arrivalTime of the flight
     * @return the arrival-instant of the flight
     */
    public Instant getArrivalTime(){
        return Instant.ofEpochSecond(arrivalTime);
    }

    /**
     * Sets the departureCity to a new city code
     * @param city, the code of the city where the flight is to depart
     * @return Itself
     */
    public Flight setDepartureCity(String city) {
        departureCity = city;
        return this;
    }
    
    /**
     * Returns the code of the city where the flight is to depart
     * @return The code of the city where the flight is to depart
     */
    public String getDepartureCity(){
        return departureCity;
    }

    /**
     * Sets the city code of the city the flight is going to
     * @param city, the city code of the city the flight is set to arrive in
     * @return Itself
     */
    public Flight setArrivalCity(String city) {
        arrivalCity = city;
        return this;
    }
    
    /**
     * Returns the code of the city the flight is set to arrive in
     * @return the code  of the city the flight is set to arrive in
     */
    public String getArrivalCity(){
        return arrivalCity;
    }

    /**
     * Sets the ID of the flight
     * @param _flightID, the new flight ID
     * @return Itself
     */
    public Flight setFlightID(String _flightID) {
        flightID = _flightID;
        return this;
    }
    
    /**
     * Returns the ID of the flight
     * @return the ID of the flight
     */
    public String getFlightID(){
        return flightID;
    }
    
    /**
     * Adds a crew-object to the flight's crew-list if not already there
     * @param newCrew, the crewmember to be added
     */
    public void addCrew(Crew newCrew){
        if (!crew.contains(newCrew)){
            crew.add(newCrew);
        }
    }
    
    /**
     * Textually represents the flight
     * @return String that textually represents the flight
     */
    @Override
    public String toString(){
        return flightID + " " + departureCity + " -> " + arrivalCity + "   " + Instant.ofEpochSecond(departureTime).toString() + " to " + Instant.ofEpochSecond(arrivalTime).toString();
    }
}
