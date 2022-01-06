import java.util.ArrayList;
import java.util.Arrays;

/**
 * pawn class that extends the piece superclass
 * 
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225
 */

public class pawn extends piece {

    public pawn(String x, boolean y) {
        super(x, y);
    }

    /**
     * returns the name of the piece
     * 
     * @return String
     */
    public String whatAmI() {
        return "i am pawn";
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
        ArrayList<Integer[]> e_moves = new ArrayList<Integer[]>();

        int down_up;
        int p_start;
        // boolean illegal = false;
        // String[][] t_board = new String[8][8];

        // for (int i = 0; i < board.length; i++)
        // t_board[i] = board[i].clone();
        int rank = origin[0];
        int file = origin[1];
        piece currPiece = board[rank][file];

        if (white) {

            p_start = 1;
            down_up = 1;
        } else {

            p_start = 6;
            down_up = -1;
        }

        // System.out.println("origin color : " +
        // board[origin[0]][origin[1]].charAt(0));
        if (currPiece.white == whiteTurn) {

            // pawn

            // moving forward
            // System.out.println("detected is pawn");
            if (board[origin[0] + down_up][origin[1]] == null) {
                // System.out.println("detected can move up/down");
                Integer[] new_move = new Integer[] { origin[0] + down_up, origin[1] };
                p_moves.add(new_move);
            }
            // move two spaces at beginning
            if (origin[0] == p_start) {
                Integer[] new_move = new Integer[] { origin[0] + down_up * 2, origin[1] };
                p_moves.add(new_move);
            }
            // take piece
            // right diagnal(white perspective)
            if (origin[1] + down_up <= 7 && origin[1] + down_up >= 0) {
                if (board[origin[0] + down_up][origin[1] + down_up] != null
                        && board[origin[0] + down_up][origin[1] + down_up].white == !whiteTurn) {
                    Integer[] new_move = new Integer[] { origin[0] + down_up, origin[1] + down_up };
                    p_moves.add(new_move);
                }
            }
            // left diagnal
            if (origin[1] - down_up <= 7 && origin[1] - down_up >= 0) {
                if (board[origin[0] + down_up][origin[1] - down_up] != null
                        && board[origin[0] + down_up][origin[1] - down_up].white == !whiteTurn) {
                    Integer[] new_move = new Integer[] { origin[0] + down_up, origin[1] - down_up };
                    p_moves.add(new_move);
                }
            }

            //
            // en passant right diagonal
            if (origin[1] + down_up <= 7 && origin[1] + down_up >= 0) {
                if (board[origin[0]][origin[1] + down_up] != null
                        && board[origin[0]][origin[1] + down_up].white == !white) {

                    if (board[origin[0] + down_up][origin[1] + down_up] == null
                            && board[origin[0]][origin[1] + down_up].white == !whiteTurn
                            && board[origin[0]][origin[1] + down_up] instanceof pawn) {
                        Integer[] new_move = new Integer[] { origin[0] + down_up, origin[1] + down_up };
                        e_moves.add(new_move);

                    }
                }
            }
            // en passant left diagonal
            if (origin[1] - down_up <= 7 && origin[1] - down_up >= 0) {
                if (board[origin[0]][origin[1] - down_up] != null
                        && board[origin[0]][origin[1] - down_up].white == !white) {

                    if (board[origin[0] + down_up][origin[1] - down_up] == null
                            && board[origin[0]][origin[1] - down_up].white == !whiteTurn
                            && board[origin[0]][origin[1] - down_up] instanceof pawn) {
                        Integer[] new_move = new Integer[] { origin[0] + down_up, origin[1] - down_up };
                        e_moves.add(new_move);

                    }
                }
            }

            // promotion

        }

        for (int i = 0; i < p_moves.size(); i++) {// checks if the inputted move is in the possible list
            if (Arrays.deepEquals(p_moves.get(i), new Integer[] { destination[0], destination[1] })) {
                int other_side = 0;
                if (white) {
                    other_side = 7;
                }

                if (destination[0] == other_side) {
                    promotion = true;
                }
                return true;
            }
        }
        for (int i = 0; i < e_moves.size(); i++) {// checks if the inputted move is in the possible list
            if (Arrays.deepEquals(e_moves.get(i), new Integer[] { destination[0], destination[1] })) {
                int other_side = 0;
                if (white) {
                    other_side = 7;
                }

                if (destination[0] == other_side) {
                    promotion = true;
                }
                enpassant = true;
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
