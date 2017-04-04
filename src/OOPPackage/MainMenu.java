package OOPPackage;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Thomas Sund Mjåland
 *
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
                Date departureDate = null;
                Date arrivalDate = null;
                String departureCity = null;
                String arrivalCity = null;
                String flightID = null;
                int rows = 0, columns = 0;

                //The date format we use to parse the user's date-input
                SimpleDateFormat df = new SimpleDateFormat("dd/MM HH:mm yyyy zzzz");
                boolean done = false; //Used to repeat the inputting until valid input has been given
                String s = ""; //Used to hold given input before being processed

                //System.out.println(Calendar.getInstance().getTimeZone().);
                done = false;
                while (!done) {
                    System.out.println("Input the departure date [day/month hour:minute]");
                    try {
                        s = input.nextLine();
                        if (s.toLowerCase().equals("exit")) {    //if user wishes to exit at this point
                            return;
                        }
                        System.out.println(s + " " + Integer.toString(Calendar.getInstance().get(Calendar.YEAR)) + " " + "UTC");
                        departureDate = df.parse(s + " " + Integer.toString(Calendar.getInstance().get(Calendar.YEAR)) + " " + "UTC");
                        done = true;
                    } catch (Exception ex) {
                        System.out.println("Sorry, but '" + s + "' is not a valid date or in the wrong format");
                    }
                }

                System.out.println(departureDate.toInstant().atZone(ZoneId.of("Z")));

                done = false;
                while (!done) {
                    System.out.println("Input the arrival date [day/month hour:minute]");
                    try {
                        s = input.nextLine();
                        if (s.toLowerCase().equals("exit")) {    //If user wishes to exit at this point
                            return;
                        }
                        arrivalDate = df.parse(s + " " + Integer.toString(Calendar.getInstance().get(Calendar.YEAR)) + " +0000");
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
                    if (Verifications.FLIGHTID.test(s)) {   //Attempts to match the given input to the '[A-Z]{1,2}\\d{3,4}'-regex pattern, can be expanded for more leeway
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
                        done = true;
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
                
                
                TicketReservationSystem sys=TicketReservationSystem.getInstance();
                List<Crew> allCrew = sys.getPersonList(Crew.class);             //Get all instances of crew
                List<Crew> crewList = sys.getPersonListStrict(Crew.class);      //Get all pure-crew objects
                List<Pilot> pilotList = sys.getPersonListStrict(Pilot.class);   //Get all pure-pilot objects
                done=false;
                while (!done){
                    System.out.println("Please type in the employee ID (the eID) of the crewmember that is participating in this flight, when done adding crew, type 'done'");
                    for (Crew c:allCrew){
                        System.out.println(c);
                    }
                    System.out.println("Pilots:");
                    for (Pilot c:pilotList){
                        System.out.println(c);
                    }
                    System.out.println("\nCabin-crew:");
                    for (Crew c:crewList){
                        System.out.println(c);
                    }
                    System.out.println();
                    s = input.nextLine();
                    if (Verifications.DONE.test(s)){
                        done=true;
                    }else{
                        if (Verifications.EMPLOYEEID.test(s)){
                            final String searchString = s;
                            Optional<Crew> selectedCrew = 
                                    allCrew.stream()
                                    .filter(c->c.employeeID.equals(searchString))
                                    .findFirst();
                            if (selectedCrew.isPresent()){
                                newFlight.addCrew(selectedCrew.get());
                            }else{
                                System.out.println("No crew found by that ID");
                            }
                        }else{
                            System.out.println("That is sadly not a valid employee-ID, please try again.");
                        }
                    }
                }
                

                done = false;
                while (!done) {
                    //Gets confirmation from user that all the flight-details are correct
                    System.out.println("Please confirm that all the details shown here are correct:\n\n" + newFlight.toString() + "\n\nIs everything correct? [Y/N]");
                    s = input.nextLine();
                    if (s.toLowerCase().equals("y")) {
                        System.out.println("Registering flight");
                        done = true;
                    } else if (s.toLowerCase().equals("n")) {
                        System.out.println("Cancelling flight registering");
                        return;
                    } else {
                        System.out.println("Please confirm that the details are correct [Y/N]");
                    }
                }

                //Stores the new flight in the flightregister in the global TicketReservationSystem-instance
                TicketReservationSystem.getInstance().registerFlight(newFlight);
                System.out.println("Flight registered");
            }
        };

        /**
         * The action of listing all flights avaliable in the flight-register
         */
        Menu listFlights = new Menu.ActionItem("List all flights") {
            @Override
            public void run() {
                Iterator<Flight> i = TicketReservationSystem.getInstance().getFlightIterator();
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
                String flightID = null;
                Flight selectedFlight = null;
                int row = 0, column = 0;
                Seat selectedSeat = null;
                Passenger passenger = null;
                String firstName = null, lastName = null, mailAddress = null;
                int price = 0;

                boolean done; //Holds wether or not the current step is done
                String s;

                done = false;
                while (!done) {
                    System.out.println("Input flightID of wanted flight");
                    s = input.nextLine().toUpperCase();
                    if (s.toLowerCase().equals("exit")) {    //If the user wishes to exit
                        return;
                    }
                    flightID = s;
                    selectedFlight = TicketReservationSystem.getInstance().getFlightByFlightID(flightID);
                    if (selectedFlight == null) {
                        System.out.println("Could not find flight '" + s + "'");
                    } else {
                        done = true;
                    }
                }

                done = false;
                while (!done) {
                    System.out.println("This flight has " + Integer.toString(selectedFlight.seats.seats.length) + " rows and " + Integer.toString(selectedFlight.seats.seats[0].length) + " columns of seats");
                    System.out.println("Input wanted seat, for example: 13B");
                    s = input.nextLine().toUpperCase();
                    if (s.toLowerCase().equals("exit")) {    //If user wishes to exit menu at this point
                        return;
                    }
                    if (Verifications.SEAT.test(s)) { //tests if the input matches the regex pattern '\d+[A-Z]'
                        Matcher m = Pattern.compile("(\\d+)([A-Z])").matcher(s);
                        m.find();
                        row = Integer.parseInt(m.group(1));
                        column = m.group(2).charAt(0) - 'A' + 1;
                        selectedSeat = selectedFlight.seats.getSeat(row, column);
                        if (selectedSeat.isAvailable()) {
                            done = true;
                        } else {
                            System.out.println("Sadly, the seat " + s + " is not avaliable, please try another seat");
                        }
                    } else {
                        System.out.println("Did not recognice input as valid seat, remember to input the seat as a number followed by a character, such as 15C");
                    }
                }
                done = false;
                while (!done) {
                    System.out.println("Input the passengers first name");
                    s = input.nextLine();
                    if (Verifications.NAME.test(s)) {
                        firstName = s;
                        done = true;
                    } else {
                        System.out.println("Please input only your first name, note that only a-z and '-' are allowed characters");
                    }
                }
                done = false;
                while (!done) {
                    System.out.println("Input the passengers last name");
                    s = input.nextLine();
                    if (Verifications.NAME.test(s)) {
                        lastName = s;
                        done = true;
                    } else {
                        System.out.println("Please input only your last name, note that only a-z and '-' are allowed characters");
                    }
                }
                done = false;
                while (!done) {
                    System.out.println("Input the passengers mail address");
                    s = input.nextLine();
                    if (s.toLowerCase().equals("exit")) {    //if the user wishes to exit at this point
                        return;
                    }
                    if (Verifications.MAILADDRESS.test(s)) {
                        mailAddress = s;
                        done = true;
                    } else {
                        System.out.println(s + " is not recognized as a valid mail-address");
                    }
                }
                passenger = new Passenger(firstName, lastName, mailAddress);
                done = false;
                while (!done) {
                    System.out.println("lastly, what's the price of the ticket?");
                    s = input.nextLine();
                    if (s.toLowerCase().equals("exit")) {
                        return;
                    }
                    if (Verifications.NUMERIC.test(s)) {
                        price = Integer.parseInt(s);
                        done = true;
                    } else {
                        System.out.println(s + " is not a valid number");
                    }
                }

                Ticket newTicket = new Ticket(selectedSeat, price, flightID, passenger);

                done = false;
                while (!done) {
                    System.out.println("Please confirm that the following details are correct:\n"
                            + newTicket
                            + "\n[Y/N]");
                    s = input.nextLine();
                    if (s.toLowerCase().equals("y")) {
                        done = true;
                    } else if (s.toLowerCase().equals("n") || s.toLowerCase().equals("exit")) {
                        System.out.println("Ticket not registered");
                        return;
                    } else {
                        System.out.println("Please enter a valid response");
                    }
                }

                System.out.println("Registering ticket with the system");
                TicketReservationSystem.getInstance().registerTicket(newTicket);
                System.out.println("Ticket registered, have a nice flight!");
            }
        };

        Menu addCrew = new Menu.ActionItem("Hire crew") {
            @Override
            public void run() {
                String firstName = null;
                String lastName = null;
                String emailAddress = null;
                String employeeID = null;
                boolean isPilot = false;
                String certificateNumber = null;

                String s;
                boolean done;

                done = false;
                while (!done) {
                    System.out.println("What is their first name?");
                    s=input.nextLine();
                    if (Verifications.NAME.test(s)){
                        firstName = s;
                        done=true;
                    }else{
                        System.out.println("'" + s + "' is not a valid first name, please stick to alphabetical characters and '-' only");
                    }
                }
                
                done = false;
                while (!done) {
                    System.out.println("What is their last name?");
                    s=input.nextLine();
                    if (Verifications.NAME.test(s)){
                        lastName = s;
                        done=true;
                    }else{
                        System.out.println("'" + s + "' is not a valid last name, please stick to alphabetical characters and '-' only");
                    }
                }
                
                done = false;
                while (!done) {
                    System.out.println("What is their mail address?");
                    s=input.nextLine();
                    if (Verifications.MAILADDRESS.test(s)){
                        emailAddress = s;
                        done=true;
                    }else{
                        System.out.println("'" + s + "' is not recognized as a valid mail address");
                    }
                }
                
                done = false;
                while (!done) {
                    System.out.println("What is their employee-ID?");
                    s=input.nextLine();
                    if (Verifications.EMPLOYEEID.test(s)){
                        employeeID = s;
                        done=true;
                    }else{
                        System.out.println("'" + s + "' is not a valid employee-ID, please stick to the format 0dddd, (d-numeric character)");
                    }
                }
                
                done = false;
                while (!done) {
                    System.out.println("Is the employee a pilot? [Y/N]");
                    s=input.nextLine();
                    if (Verifications.YESNO.test(s)){
                        isPilot = Verifications.YES.test(s);
                        done=true;
                    }else{
                        System.out.println("Please answer Y for yes or N for no");
                    }
                }
                
                done = !isPilot;
                while (!done) {
                    System.out.println("What is the pilot's certificate-number?");
                    s=input.nextLine();
                    if (Verifications.CERTIFICATENUMBER.test(s)){
                        certificateNumber = s;
                        done=true;
                    }else{
                        System.out.println("'" + s + "' is not a valid certificate-number, please answer in the format '0dddddd', (d = numeric character)");
                    }
                }
                
                Crew newCrew;
                
                System.out.println("Please verify that this information is correct:");
                if (isPilot){
                    newCrew = new Pilot(firstName,lastName,emailAddress,employeeID,certificateNumber);
                    System.out.println((Pilot)newCrew);
                }else{
                    newCrew = new Crew(firstName, lastName, emailAddress, employeeID);
                    System.out.println(newCrew);
                }
                System.out.println("Is everything correct? [Y/N]");
                done=false;
                while (!done){
                    s=input.nextLine();
                    if (Verifications.YES.test(s)){
                        TicketReservationSystem.getInstance().registerPerson(newCrew);
                        System.out.println(lastName + ", " + firstName + " is now hired and registered");
                        done=true;
                    }else if (Verifications.NO.test(s)){
                        //If someone sees this, what are you doing here?! go do something productive instead!
                        System.out.println("Initializing 'Halt and catch fire' protocol,\nAll recollection of this hiring is now gone");
                        done=true;
                    }else{
                        System.out.println("Please answer yes or no [Y/N],\nIt's really not that hard, like, seriously...");
                    }
                }
            }
        };

        /**
         * The action of listing all registered tickets
         */
        Menu listTickets = new Menu.ActionItem("List all tickets") {
            @Override
            public void run() {
                Iterator<Ticket> i = TicketReservationSystem.getInstance().getTicketIterator();
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
                TicketReservationSystem.getInstance().registerFlight(newFlight);
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
        Menu rootMenu = new Menu.SubMenu("", "Main menu", addFlight, listFlights, addTicket, listTickets, addCrew, testSubMenu)
                .setExit(true)
                .setFallsThrough(false);

        /**
         * Runs the root-menu
         */
        rootMenu.run();
        //Returns after root-menu finishes
    }
}
