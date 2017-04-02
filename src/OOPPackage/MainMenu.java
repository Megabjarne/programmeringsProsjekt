package OOPPackage;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;

/**
 *
 * @author Thomas Sund Mjåland
 *
 *
 * ATTENTION: This is in no way a final draft, it is unstable, prone to crashing
 * and with little to no errorhandling, it is only meant to demonstrate the
 * possibilities and give a taste of what is to come
 */
public class MainMenu {

    /**
     * Creates a menu and runs it
     */
    static void run() {
        Scanner input = new Scanner(System.in);
        /**
         * The "add flight"-action, takes the user through the procedure of
         * adding a new flight to the flight-register
         */
        Menu addFlight = new Menu.ActionItem("Add flight") {
            @Override
            public void run() {
                //the data we need to collect from the user
                Date departureDate=null;
                Date arrivalDate=null;
                String departureCity=null;
                String arrivalCity=null;
                String flightID=null;
                int rows=0, columns=0;

                //The date format we use to parse the user's date-input
                SimpleDateFormat df = new SimpleDateFormat("dd/MM HH:mm");
                boolean done = false; //Used to repeat the inputting until valid input has been given
                String s = ""; //Used to hold given input before being processed

                done = false;
                while (!done) {
                    System.out.println("Input the departure date [day/month hour:minute]");
                    try {
                        s = input.nextLine();
                        if (s.toLowerCase().equals("exit")) {    //if user wishes to exit at this point
                            return;
                        }
                        departureDate = df.parse(s);
                        done = true;
                    } catch (Exception ex) {
                        System.out.println("Sorry, but '" + s + "' is not a valid date or in the wrong format");
                    }
                }

                done = false;
                while (!done) {
                    System.out.println("Input the arrival date [day/month hour:minute]");
                    try {
                        s = input.nextLine();
                        if (s.toLowerCase().equals("exit")) {    //If user wishes to exit at this point
                            return;
                        }
                        arrivalDate = df.parse(s);
                        done = true;
                    } catch (Exception ex) {
                        System.out.println("Sorry, but '" + s + "' is not a valid date or in the wrong format");
                    }
                }

                done = false;
                while (!done) {
                    System.out.println("input departure city airport-code");
                    s = input.nextLine().toUpperCase();
                    if (s.toLowerCase().equals("exit")) {    //If the user wishes to exit at this point
                        return;
                    }
                    if (s.length() == 3) {
                        departureCity = s;
                        done = true;
                    } else {
                        System.out.println("Sorry, but '" + s + "' is not a valid city-code, a city code must consist of three letters");
                    }
                }
                done = false;
                while (!done) {
                    System.out.println("input arrival city airport-code");
                    s = input.nextLine().toUpperCase();
                    if (s.toLowerCase().equals("exit")) {    //If the user wishes to exit at this point
                        return;
                    }
                    if (s.length() == 3) {
                        arrivalCity = s;
                        done = true;
                    } else {
                        System.out.println("Sorry, but '" + s + "' is not a valid city-code, a city code must consist of three letters");
                    }
                }
                done = false;
                while (!done) {
                    System.out.println("Input flight ID");
                    s = input.nextLine().toUpperCase();
                    if (s.toLowerCase().equals("exit")) {    //if the user wishes to exit at this stage
                        return;
                    }
                    if (Pattern.compile("[A-Z]{1,2}\\d{3,4}").matcher(s).matches()) {   //Attempts to match the given input to the '[A-Z]{1,2}\\d{3,4}'-regex pattern, can be expanded for more leeway
                        flightID = s;
                        done = true;
                    } else {
                        System.out.println("Sorry, the given flight number is not considered valid,\nPlease conform to the standard LL(L)nnn(n) pattern (L-letter, n-number, (L)-optional letter)");
                    }
                }
                done = false;
                while (!done) {
                    System.out.println("How many seats wide? either in seat-count or by highest letter (D = 4 seats wide)");
                    if (input.hasNextInt()) {
                        columns = input.nextInt();
                        done = true;
                        input.nextLine(); //Skips to after next null/carry-char
                    } else {
                        s = input.nextLine().toUpperCase();
                        if (s.toLowerCase().equals("exit")) {    //if the user wishes to exit at this point
                            return;
                        }
                        if (s.length() == 1) {
                            char c = s.charAt(0);
                            if (c >= 'A' && c <= 'Z') {
                                columns = c - 'A' + 1;
                                done = true;
                            } else {
                                System.out.println("letter was outside of bounds, it has to be between the letter a and z");
                            }
                        } else {
                            System.out.println("You either have to input a single letter, or a number");
                        }
                    }
                }
                done = false;
                while (!done) {
                    System.out.println("How many rows of seats does the plane have?");
                    if (input.hasNextInt()) {
                        rows = input.nextInt();
                        input.nextLine();   //skips the trailing newline/carry character
                    } else if (input.hasNextLine()) {
                        s = input.nextLine();
                        if (s.toLowerCase().equals("exit")) {    //if the user wishes to exit at this stage
                            return;
                        } else {
                            System.out.println("You need to input a number");
                        }
                    }
                }

                //Creates the actial flight
                Flight newFlight = new Flight(rows, columns)
                        .setDepartureCity(departureCity)
                        .setArrivalCity(arrivalCity)
                        .setDepartureTime(departureDate.toInstant())
                        .setArrivalTime(arrivalDate.toInstant())
                        .setFlightID(flightID);

                done = false;
                while (!done) {
                    //Gets confirmation from user that all the flight-details are correct
                    System.out.println("Please confirm that all the details shown here are correct:\n\n" + newFlight.toString() + "\n\nIs everything correct? [Y/N]");
                    s = input.nextLine();
                    if (s.toLowerCase().equals("y")){
                        System.out.println("Registering flight");
                        done=true;
                    }else if (s.toLowerCase().equals("n")){
                        System.out.println("Cancelling flight registering");
                        return;
                    }else{
                        System.out.println("Please confirm that the details are correct [Y/N]");
                    }
                }

                //Stores the new flight in the flightregister in the global TicketReservationSystem-instance
                TicketReservationSystem.getInstance().flightRegister.addFlight(newFlight);
                System.out.println("Flight registered");
            }
        };

        /**
         * The action of listing all flights avaliable in the flight-register
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
         * The action of adding a new ticket, takes the user through the process
         * of adding a new ticket
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
                passenger = new Passenger(firstName, lastName, mailAddress);
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
                while (i.hasNext()) {
                    System.out.println(i.next());
                }
            }
        };

        /**
         * Debug-menu option that quickly adds a flight of a predetermined route
         * etc.
         */
        Menu addTestFlight = new Menu.ActionItem("Add test flight") {
            @Override
            public void run() {
                Flight newFlight = new Flight(6, 10)
                        .setDepartureCity("OSL")
                        .setArrivalCity("TND")
                        .setDepartureTime(Instant.now()) //Sets the flight to be leaving NOW
                        .setArrivalTime(Instant.now().plusSeconds(3600)) //Sets the flight to be arriving in 3600 seconds (one hour)
                        .setFlightID("SK123");
                TicketReservationSystem.getInstance().flightRegister.addFlight(newFlight);
            }
        };

        /**
         * Debug-menu option that reinvents maths and tear a rift in the
         * space-time continuum in order to further expand the capabilities of
         * this already amazing software
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
        Menu rootMenu = new Menu.SubMenu("", "Main menu", addFlight, listFlights, addTicket, listTickets, testSubMenu)
                .setExit(true)
                .setFallsThrough(false);

        /**
         * Runs the root-menu
         */
        rootMenu.run();
        //Returns after root-menu finishes
    }
}
