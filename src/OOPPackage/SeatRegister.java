package OOPPackage;

/*
@author Thomas Sund Mjåland
*/
public class SeatRegister {
    /**
     * Primitive array representing the grid of seats made avaliable in this register
     */
    Seat seats[][];
    
    /**
     * Creates a new register given the number of rows and columns in the plane
     * @param rows, the number of rows in the plane (often given by letters)
     * @param columns, the number of columns in the plane
     */
    public SeatRegister(int rows, int columns){
        seats = new Seat[rows][columns];
        for (int x=0;x<rows;x++){
            for (int y=0;y<columns;y++){
                seats[x][y] = new Seat(x,(char)('A'+y),true);
            }
        }
    }
    
    /**
     * Returns the Seat-object belonging to the given letter and row
     * @param row, the seat's row
     * @param letter, the seat's letter
     * @return the seat-object with the given position
     * @throws IllegalArgumentException, row or letter outside boundaries of the register
     */
    public Seat getSeat(int row, char letter) throws IllegalArgumentException{
        int column = (int)letter-(int)'A';
        int seatRows = seats.length;
        int seatColumns = seats[0].length;
        if (row>=0 && row<seatRows && column>=0 && column<seatColumns){
        return seats[row][column];
        }else{
            throw new IllegalArgumentException("Given row or letter was outside seat boundaries");
        }
    }
    
    /**
     * Returns the Seat-object belonging to the given coordinates
     * @param row, the seat's row
     * @param column, the seat's column
     * @return The Seat-object in the given position
     * @throws IllegalArgumentException, row or column outside the boundaries of the register
     */
    public Seat getSeat(int row, int column) throws IllegalArgumentException{
        int seatRows = seats.length;
        int seatColumns = seats[0].length;
        if (row>=0 && row<seatRows && column>=0 && column<seatColumns){
        return seats[row][column];
        }else{
            throw new IllegalArgumentException("Given row or column was outside seat boundaries");
        }
    }
}
