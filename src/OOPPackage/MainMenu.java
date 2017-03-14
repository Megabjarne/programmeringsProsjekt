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

    static void run() {
        Scanner input = new Scanner(System.in);
        Menu addFlight = new Menu.ActionItem("add flight") {
            @Override
            public void run() {
                Date departureDate;
                Date arrivalDate;
                String departureCity;
                String arrivalCity;
                String flightID;
                int rows, columns;

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
                    System.in.skip(1000);
                } catch (Exception ex) {
                }

                Flight newFlight = new Flight(rows, columns)
                        .setDepartureCity(departureCity)
                        .setArrivalCity(arrivalCity)
                        .setDepartureTime(departureDate.toInstant())
                        .setArrivalTime(arrivalDate.toInstant())
                        .setFlightID(flightID);
                TicketReservationSystem.getInstance().flightRegister.addFlight(newFlight);
            }
        };
        Menu listFlights = new Menu.ActionItem("List all flights") {
            @Override
            public void run() {
                Iterator<Flight> i = TicketReservationSystem.getInstance().flightRegister.iterator();
                while (i.hasNext()) {
                    System.out.println(i.next());
                }
            }
        };
        Menu addTicket = new Menu.ActionItem("add ticket") {
            @Override
            public void run() {
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
        Menu listTickets = new Menu.ActionItem("List all tickets") {
            @Override
            public void run() {
                Iterator<Ticket> i = TicketReservationSystem.getInstance().ticketRegister.iterator();
                while (i.hasNext()){
                    System.out.println(i.next());
                }
            }
        };
        Menu addTestFlight = new Menu.ActionItem("add test flight") {
            @Override
            public void run() {
                Flight newFlight = new Flight(6,10)
                        .setDepartureCity("OSL")
                        .setArrivalCity("TND")
                        .setDepartureTime(Instant.now())
                        .setArrivalTime(Instant.now().plusSeconds(3600))
                        .setFlightID("SK123");
                TicketReservationSystem.getInstance().flightRegister.addFlight(newFlight);
            }
        };
        Menu selfDestruct = new Menu.ActionItem("initiate self-destruct") {
            @Override
            public void run() {
                int i = 0;
                i = 0 / i;
            }
        };
        Menu testSubMenu = new Menu.SubMenu("test options", "Test menu", addTestFlight, selfDestruct)
                .setExit(true)
                .setFallsThrough(true);
        Menu rootMenu = new Menu.SubMenu("", "Main menu", addFlight, listFlights, addTicket, listTickets,testSubMenu)
                .setExit(true)
                .setFallsThrough(false);
        rootMenu.run();
    }
}
