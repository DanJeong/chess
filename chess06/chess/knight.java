import java.util.ArrayList;
import java.util.Arrays;

/**
 * knight class that extends the piece superclass
 * 
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225
 */

public class knight extends piece {

    public knight(String x, boolean y) {
        super(x, y);
    }

    /**
     * returns the name of the piece
     * 
     * @return String
     */
    public String whatAmI() {

        return "i am knight";
    }

    /**
     * checks if the the piece can move to a destination
     * 
     * @param board
     * @param whiteTurn
     * @param origin
     * @param destination
     * @param thirdCommand
     * @return boolean
     */
    public boolean canMoveHere(piece[][] board, boolean whiteTurn, int[] origin, int[] destination,
            String thirdCommand) {
        // if (currPiece.white == whiteTurn) {

        // }//only runs if we're trying to move our current piece
        ArrayList<Integer[]> p_moves = new ArrayList<Integer[]>();
        int rank = origin[0];
        int file = origin[1];
        if (rank + 1 <= 7 && file + 2 <= 7) {
            if (board[rank + 1][file + 2] == null || board[rank + 1][file + 2].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank + 1, file + 2 };
                p_moves.add(new_move);
            }

        }
        rank = origin[0];
        file = origin[1];
        if (rank - 1 >= 0 && file + 2 <= 7) {
            if (board[rank - 1][file + 2] == null || board[rank - 1][file + 2].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank - 1, file + 2 };
                p_moves.add(new_move);
            }
        }
        rank = origin[0];
        file = origin[1];
        if (rank + 2 <= 7 && file + 1 <= 7) {
            if (board[rank + 2][file + 1] == null || board[rank + 2][file + 1].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank + 2, file + 1 };
                p_moves.add(new_move);
            }
        }
        rank = origin[0];
        file = origin[1];
        if (rank - 2 >= 0 && file + 1 <= 7) {
            if (board[rank - 2][file + 1] == null || board[rank - 2][file + 1].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank - 2, file + 1 };
                p_moves.add(new_move);
            }
        }
        rank = origin[0];
        file = origin[1];
        if (rank + 2 <= 7 && file - 1 >= 0) {
            if (board[rank + 2][file - 1] == null || board[rank + 2][file - 1].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank + 2, file - 1 };
                p_moves.add(new_move);
            }
        }
        rank = origin[0];
        file = origin[1];
        if (rank - 2 >= 0 && file - 1 >= 0) {
            if (board[rank - 2][file - 1] == null || board[rank - 2][file - 1].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank - 2, file - 1 };
                p_moves.add(new_move);
            }
        }
        if (rank + 1 <= 7 && file - 2 >= 0) {
            if (board[rank + 1][file - 2] == null || board[rank + 1][file - 2].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank + 1, file - 2 };
                p_moves.add(new_move);
            }
        }
        if (rank - 1 >= 0 && file - 2 >= 0) {
            if (board[rank - 1][file - 2] == null || board[rank - 1][file - 2].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank - 1, file - 2 };
                p_moves.add(new_move);
            }
        }
        for (int i = 0; i < p_moves.size(); i++) {// checks if the inputted move is in the possible list
            if (Arrays.deepEquals(p_moves.get(i), new Integer[] { destination[0], destination[1] })) {
                return true;
            }
        }
        return false;

    }

    /**
     * returns the spaces between a king and another piece
     * 
     * @param board
     * @param whiteTurn
     * @param origin
     * @param kingpos
     * @return ArrayList<Integer[]>
     */
    public ArrayList<Integer[]> sbKing(piece[][] board, boolean whiteTurn, int[] origin, int[] kingpos) {

        ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
        return moves;
    }

}
