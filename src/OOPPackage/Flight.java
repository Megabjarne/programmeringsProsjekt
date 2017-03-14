package OOPPackage;

import java.time.*;
import java.util.ArrayList;

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
    //TODO: When generic crew-class is implemented, add crew-support here.
    ArrayList<Object> crew;

    public Flight(int rows, int columns) {
        seats = new SeatRegister(rows, columns);
    }

    public Flight setDepartureTime(Instant time) {
        departureTime = time.getEpochSecond();
        return this;
    }
    
    public Instant getDepartureTime(){
        return Instant.ofEpochSecond(departureTime);
    }

    public Flight setArrivalTime(Instant time) {
        arrivalTime = time.getEpochSecond();
        return this;
    }
    
    public Instant getArrivalTime(){
        return Instant.ofEpochSecond(arrivalTime);
    }

    public Flight setDepartureCity(String city) {
        departureCity = city;
        return this;
    }
    
    public String getDepartureCity(){
        return departureCity;
    }

    public Flight setArrivalCity(String city) {
        arrivalCity = city;
        return this;
    }
    
    public String getArrivalCity(){
        return arrivalCity;
    }

    public Flight setFlightID(String _flightID) {
        flightID = _flightID;
        return this;
    }
    
    public String getFlightID(){
        return flightID;
    }
}
