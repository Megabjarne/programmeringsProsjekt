package OOPPackage;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 *
 * @author Thomas Sund Mjåland
 * 
 * 
 * ATTENTION:
 * This is in no way a final draft, it is unstable, prone to crashing and with little to no errorhandling, 
 * it is only meant to demonstrate the possibilities and give a taste of what is to come
 */
public class MainMenu {
    /**
     * Creates a menu and runs it
     */
    static void run() {
        Scanner input = new Scanner(System.in);
        /**
         * The "add flight"-action, takes the user through the procedure of adding a new flight to the flightregister
         */
        Menu addFlight = new Menu.ActionItem("Add flight") {
            @Override
            public void run() {
                //the data we need to collect from the user
                Date departureDate;
                Date arrivalDate;
                String departureCity;
                String arrivalCity;
                String flightID;
                int rows, columns;

                //The date format we use to parse the user's date-input
                SimpleDateFormat df = new SimpleDateFormat("dd/MM HH:mm");
                System.out.println("input departure date [day/month hour:minute]");
                try {
                    departureDate = df.parse(input.nextLine());
                } catch (Exception ex) {
                    return;
                }
                System.out.println("input arrival date [day/month hour:minute]");
                try {
                    arrivalDate = df.parse(input.nextLine());
                } catch (Exception ex) {
                    return;
                }
                System.out.println("input departure city airport-code");
                departureCity = input.nextLine();
                System.out.println("input arrival city airport-code");
                arrivalCity = input.nextLine();
                System.out.println("Input flight ID");
                flightID = input.nextLine();
                System.out.println("dimentions of the seats [columns rows]");
                columns = input.nextInt();
                rows = input.nextInt();
                try {
                    System.in.skip(1000);   //Tries to skip any and all trailing input, such as '\n''s and similar
                } catch (Exception ex) {}   //We do not care to handle any errors occuring during the skip, as it is not strictly necessary, but nice to have

                //Creates the actial flight
                Flight newFlight = new Flight(rows, columns)
                        .setDepartureCity(departureCity)
                        .setArrivalCity(arrivalCity)
                        .setDepartureTime(departureDate.toInstant())
                        .setArrivalTime(arrivalDate.toInstant())
                        .setFlightID(flightID);
                //Stores the new flight in the flightregister in the global TicketReservationSystem-instance
                TicketReservationSystem.getInstance().flightRegister.addFlight(newFlight);
            }
        };
        
        /**
         * The action of listing all flights avaliable in the flightregister
         */
        Menu listFlights = new Menu.ActionItem("List all flights") {
            @Override
            public void run() {
                Iterator<Flight> i = TicketReservationSystem.getInstance().flightRegister.iterator();
                while (i.hasNext()) {
                    System.out.println(i.next());
                }
            }
        };
        
        /**
         * The action of adding a new ticket, takes the user through the process of adding a new ticket
         */
        Menu addTicket = new Menu.ActionItem("Add ticket") {
            @Override
            public void run() {
                //The data we need to collect from the user
                String flightID;
                Flight selectedFlight;
                int row, column;
                Seat selectedSeat;
                Passenger passenger;
                String firstName, lastName, mailAddress;
                int price;

                System.out.println("Input flightID of wanted flight");
                flightID = input.nextLine();
                selectedFlight = TicketReservationSystem.getInstance().flightRegister.getFlightByFlightID(flightID);
                if (selectedFlight == null) {
                    System.out.println("Could not find flight");
                    return;
                }
                System.out.println("This flight has " + Integer.toString(selectedFlight.seats.seats.length) + " rows and " + Integer.toString(selectedFlight.seats.seats[0].length) + " columns of seats");
                System.out.println("Input wanted seat [row column]");
                row = input.nextInt();
                column = input.nextInt();
                input.nextLine();   //remove trailing characters and cr
                selectedSeat = selectedFlight.seats.getSeat(row, column);
                System.out.println("Input the passengers first name");
                firstName = input.nextLine();
                System.out.println("Input the passengers last name");
                lastName = input.nextLine();
                System.out.println("Input the passengers mail address");
                mailAddress = input.nextLine();
                passenger = new Passenger(firstName,lastName,mailAddress);
                System.out.println("lastly, what's the price of the ticket?");
                price = input.nextInt();
                
                Ticket newTicket = new Ticket(selectedSeat, price, flightID, passenger);
                TicketReservationSystem.getInstance().ticketRegister.addTicket(newTicket);
            }
        };
        
        /**
         * The action of listing all registered tickets
         */
        Menu listTickets = new Menu.ActionItem("List all tickets") {
            @Override
            public void run() {
                Iterator<Ticket> i = TicketReservationSystem.getInstance().ticketRegister.iterator();
                while (i.hasNext()){
                    System.out.println(i.next());
                }
            }
        };
        
        /**
         * Debug-menu option that quickly adds a flight of a predetermined route etc.
         */
        Menu addTestFlight = new Menu.ActionItem("Add test flight") {
            @Override
            public void run() {
                Flight newFlight = new Flight(6,10)
                        .setDepartureCity("OSL")
                        .setArrivalCity("TND")
                        .setDepartureTime(Instant.now())                    //Sets the flight to be leaving NOW
                        .setArrivalTime(Instant.now().plusSeconds(3600))    //Sets the flight to be arriving in 3600 seconds (one hour)
                        .setFlightID("SK123");
                TicketReservationSystem.getInstance().flightRegister.addFlight(newFlight);
            }
        };
        
        /**
         * Debug-menu option that reinvents maths and tear a rift in the space-time continuum in order to further expand the capabilities of this already amazing software
         */
        Menu selfDestruct = new Menu.ActionItem("Self-destruct") {
            @Override
            public void run() {
                int i = 0;
                i = 0 / i;
            }
        };
        
        /**
         * The debug-submenu holding all debug actions
         */
        Menu testSubMenu = new Menu.SubMenu("Debug", "Debug menu", addTestFlight, selfDestruct)
                .setExit(true)
                .setFallsThrough(true);
        /**
         * Root menu holding all options and sub-menus
         */
        Menu rootMenu = new Menu.SubMenu("", "Main menu", addFlight, listFlights, addTicket, listTickets,testSubMenu)
                .setExit(true)
                .setFallsThrough(false);
        /**
         * Runs the root-menu
         */
        rootMenu.run();
        //Returns after root-menu finishes
    }
}
