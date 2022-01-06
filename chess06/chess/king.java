import java.util.ArrayList;
import java.util.Arrays;

/**
 * king class that extends the piece superclass
 * 
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225
 */

public class king extends piece {

    public king(String x, boolean y) {
        super(x, y);
    }

    /**
     * returns the name of the piece
     * 
     * @return String
     */
    public String whatAmI() {
        return "i am king";
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
        ArrayList<Integer[]> p_moves = new ArrayList<Integer[]>();
        ArrayList<Integer[]> c_moves = new ArrayList<Integer[]>();
        // king

        int rank = origin[0];
        int file = origin[1];
        // piece currPiece = board[rank][file];

        if (rank + 1 >= 0 && rank + 1 <= 7) {// if not empty
            if (board[rank + 1][file] == null || board[rank + 1][file].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank + 1, file };
                p_moves.add(new_move);
            }
        }

        if (rank - 1 >= 0 && rank - 1 <= 7) {// if not empty
            if (board[rank - 1][file] == null || board[rank - 1][file].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank - 1, file };
                p_moves.add(new_move);
            }
        }

        if (rank + 1 >= 0 && rank + 1 <= 7 && file + 1 >= 0 && file + 1 <= 7) {// if not empty
            if (board[rank + 1][file + 1] == null || board[rank + 1][file + 1].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank + 1, file + 1 };
                p_moves.add(new_move);
            }
        }

        if (rank - 1 >= 0 && rank - 1 <= 7 && file - 1 >= 0 && file - 1 <= 7) {// if not empty
            if (board[rank - 1][file - 1] == null || board[rank - 1][file - 1].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank - 1, file - 1 };
                p_moves.add(new_move);
            }
        }

        if (file + 1 >= 0 && file + 1 <= 7) {// if not empty
            if (board[rank][file + 1] == null || board[rank][file + 1].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank, file + 1 };
                p_moves.add(new_move);
            }
        }

        if (file - 1 >= 0 && file - 1 <= 7) {// if not empty
            if (board[rank][file - 1] == null || board[rank][file - 1].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank, file - 1 };
                p_moves.add(new_move);
            }
        }

        if (rank + 1 >= 0 && rank + 1 <= 7 && file - 1 >= 0 && file - 1 <= 7) {// if not empty
            if (board[rank + 1][file - 1] == null || board[rank + 1][file - 1].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank + 1, file - 1 };
                p_moves.add(new_move);
            }
        }

        if (rank - 1 >= 0 && rank - 1 <= 7 && file + 1 >= 0 && file + 1 <= 7) {// if not empty
            if (board[rank - 1][file + 1] == null || board[rank - 1][file + 1].white == !whiteTurn) {
                Integer[] new_move = new Integer[] { rank - 1, file + 1 };
                p_moves.add(new_move);
            }
        }
        // right castle

        if ((!moved && board[rank][file + 3] != null && !board[rank][file + 3].moved)
                && (board[rank][file + 1] == null && board[rank][file + 2] == null)) {
            if (!checked) {
                Integer[] new_move = new Integer[] { rank, file + 2 };
                c_moves.add(new_move);
            }

        }
        // left castle
        if ((!moved && board[rank][file - 4] != null && !board[rank][file - 4].moved)
                && (board[rank][file - 1] == null && board[rank][file - 2] == null)
                && (board[rank][file - 3] == null)) {
            if (!checked) {
                Integer[] new_move = new Integer[] { rank, file - 2 };
                c_moves.add(new_move);
            }
        }
        for (int i = 0; i < p_moves.size(); i++) {// checks if the inputted move is in the possible list
            if (Arrays.deepEquals(p_moves.get(i), new Integer[] { destination[0], destination[1] })) {
                return true;
            }
        }
        for (int i = 0; i < c_moves.size(); i++) {// checks if the inputted move is in the possible list
            if (Arrays.deepEquals(c_moves.get(i), new Integer[] { destination[0], destination[1] })) {
                castle = true;
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
