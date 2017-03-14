package OOPPackage;

import java.util.ArrayList;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class TicketRegister {

    ArrayList<Ticket> ticketList = new ArrayList<>();

    public TicketRegister() {

    }

    /**
     * Adds a ticket to the registry, assigns it an unique ID and returns the
     * ID.
     *
     * @param ticket The ticket to be added
     * @return ID The ID assigned to the added ticket
     */
    public int addTicket(Ticket ticket) {
        int newTicketID = getNewID();
        ticket.setID(newTicketID);
        ticketList.add(ticket);
        return newTicketID;
    }

    /**
     * Finds the highest assigned ID, and returns it incremented by one
     *
     * @return ID an unused ID.
     */
    private int getNewID() {
        int highestID = 0;
        for (Ticket t : ticketList) {
            if (t.getID() > highestID) {
                highestID = t.getID();
            }
        }
        return highestID + 1;
    }

    /**
     *
     * @param ticket The ticket whose ID is to be found
     * @return ID The ID belonging to given ticket, -1 if ticket is not found
     */
    public int getID(Ticket ticket) {
        for (Ticket t : ticketList) {
            if (t == ticket) {
                return t.getID();
            }
        }
        return -1;
    }

    /**
     *
     * @param ID The ID of the ticket to be found
     * @return ticket The ticket belonging to the given ID, null if not found
     */
    public Ticket getTicketFromID(int ID) {
        for (Ticket t : ticketList) {
            if (t.getID() == ID) {
                return t;
            }
        }
        return null;
    }

    /**
     *
     * @param ID The ID of the ticket to be removed
     * @return success True if ticket was found and removed, false if not found
     */
    public boolean removeTicketByID(int ID) {
        for (Ticket t : ticketList) {
            if (t.getID() == ID) {
                ticketList.remove(t);
                return true;
            }
        }
        return false;
    }
}
