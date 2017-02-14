
/**
 * Write a description of class seat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class seat
{
    // instance variables - replace the example below with your own
    private int row;
    private char letter;
    private boolean available;

    /**
     * Constructor for objects of class seat
     */
    public seat(int seatRow, char seatLetter, boolean isAvailable)
    {
        // initialise instance variables
        row = seatRow;
        letter = seatLetter;
        available = isAvailable;
    }

    public int getRow()
    {
        return row;
    }
    
    public char getSeatLetter()
    {
        return letter;
    }

    public boolean isAvailable()
    {
        return available;
    }
}
