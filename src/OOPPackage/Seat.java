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
    private final boolean available;

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

    public int getRow()
    {
        return position.row;
    }
    
    public char getSeatLetter()
    {
        return (char)('A' + position.column - 1);
    }

    public boolean isAvailable()
    {
        return available;
    }
    
    public static class SeatLocation{
        public final int row;
        public final int column;
        public SeatLocation(int row, int column){
            this.row = row;
            this.column = column;
        }
    }
    
    @Override
    public String toString(){
        return Integer.toString(position.row) + (char)('A' + position.column - 1);
    }
}
