package OOPPackage;


/**
 * Write a description of class seat here.
 * 
 * @author Bendik Delp
 */
public class Seat
{
    // instance variables - replace the example below with your own
    public final SeatLocation position;
    private boolean available;

    /**
     * Creates a new seat belonging to the given flight, position and with the given availability
     * @param position, row and column this seat is in
     * @param isAvailable, whether this seat has been reserved/booked
     */
    public Seat(SeatLocation position, boolean isAvailable)
    {
        // initialise instance variables
        this.position = position;
        available = isAvailable;
    }

    /**
     * Returns the row of the seat-instance
     * @return The row number
     */
    public int getRow()
    {
        return position.row;
    }
    
    /**
     * returns the seat letter of the seat-instance
     * @return The seat letter
     */
    public char getSeatLetter()
    {
        return (char)('A' + position.column - 1);
    }

    /**
     * Wether or not the seat is available
     * @return true if seat is avaliable, false if not
     */
    public boolean isAvailable()
    {
        return available;
    }
    
    /**
     * Reserves the seat
     */
    public void reserve(){
        available = false;
    }
    
    /**
     * Equivalent of a structure, holds an x and y coordinate
     */
    public static class SeatLocation{
        public final int row;
        public final int column;
        public SeatLocation(int row, int column){
            this.row = row;
            this.column = column;
        }
    }
    
    /**
     * returns a textual representation of the instance
     * @return Textual representation of the instance
     */
    @Override
    public String toString(){
        return Integer.toString(position.row) + (char)('A' + position.column - 1);
    }
}
