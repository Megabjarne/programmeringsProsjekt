package OOPPackage;

/*
@author Thomas Sund Mjåland
*/
public class SeatRegister {
    Seat seats[][];
    
    public SeatRegister(int rows, int columns){
        seats = new Seat[rows][columns];
    }
    
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
}
