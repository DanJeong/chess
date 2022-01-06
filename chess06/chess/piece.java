
//import java.util.Arrays;
import java.util.ArrayList;

/**
 * piece superclass for all the different piece types (bishop, king, knight,
 * pawn, queen, rook)
 * 
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225
 */

public abstract class piece {
    String name;
    boolean white;
    boolean enpassant = false;
    boolean promotion = false;
    boolean castle = false;
    boolean moved = false;
    boolean checked = false;
    int[] killer;

    public piece(String x, boolean y) {
        name = x;
        white = y;
    }

    /**
     * returns the name of the piece
     * 
     * @return String name of piece
     */
    public String getName() {
        return name;
    }

    abstract String whatAmI();

    abstract boolean canMoveHere(piece[][] board, boolean whiteTurn, int[] origin, int[] destination,
            String thirdCommand);

    abstract ArrayList<Integer[]> sbKing(piece[][] board, boolean whiteTurn, int[] origin, int[] kingpos);
}
