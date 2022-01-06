import java.util.ArrayList;
import java.util.Arrays;

/**
 * bishop class that extends the piece superclass
 * 
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225
 */

public class bishop extends piece {

    public bishop(String x, boolean y) {
        super(x, y);
    }

    /**
     * returns the name of the piece
     * 
     * @return String
     */
    public String whatAmI() {
        return "i am bishop";
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

        // bishop

        // up right diagnol
        int rank = origin[0];
        int file = origin[1];
        piece currPiece = board[rank][file];

        if (currPiece.white == whiteTurn) {// only runs if we're trying to move our current piece
            rank++;
            file++;
            while (rank <= 7 && file <= 7) {
                currPiece = board[rank][file];
                if (currPiece != null) {// if not empty
                    if (currPiece.white == whiteTurn) {// our piece
                        break;
                    } else {// enemy piece
                        Integer[] new_move = new Integer[] { rank, file };
                        p_moves.add(new_move);
                        break;
                    }
                } else {// empty
                    Integer[] new_move = new Integer[] { rank, file };
                    p_moves.add(new_move);
                }
                rank++;
                file++;
            }
            // up right
            rank = origin[0];
            file = origin[1];

            rank++;
            file--;
            while (rank <= 7 && file >= 0) {
                currPiece = board[rank][file];
                if (currPiece != null) {// if not empty
                    if (currPiece.white == whiteTurn) {// our piece
                        break;
                    } else {// enemy piece
                        Integer[] new_move = new Integer[] { rank, file };
                        p_moves.add(new_move);
                        break;
                    }
                } else {// empty
                    Integer[] new_move = new Integer[] { rank, file };
                    p_moves.add(new_move);
                }
                rank++;
                file--;
            }
            // down left
            rank = origin[0];
            file = origin[1];
            rank--;
            file--;
            while (rank >= 0 && file >= 0) {
                currPiece = board[rank][file];
                if (currPiece != null) {// if not empty
                    if (currPiece.white == whiteTurn) {// our piece
                        break;
                    } else {// enemy piece
                        Integer[] new_move = new Integer[] { rank, file };
                        p_moves.add(new_move);
                        break;
                    }
                } else {// empty
                    Integer[] new_move = new Integer[] { rank, file };
                    p_moves.add(new_move);
                }
                rank--;
                file--;
            }
            // down right
            rank = origin[0];
            file = origin[1];
            rank--;
            file++;
            while (rank >= 0 && file <= 7) {
                currPiece = board[rank][file];
                if (currPiece != null) {// if not empty
                    if (currPiece.white == whiteTurn) {// our piece
                        break;
                    } else {// enemy piece
                        Integer[] new_move = new Integer[] { rank, file };
                        p_moves.add(new_move);
                        break;
                    }
                } else {// empty
                    Integer[] new_move = new Integer[] { rank, file };
                    p_moves.add(new_move);
                }
                rank--;
                file++;
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
        // for loop
        // for each diagonal, make temporary list of moves
        // if it hits the king, add list to moves and return moves
        // if not, nothing
        int rank = origin[0];
        int file = origin[1];
        piece currPiece = board[rank][file];

        if (currPiece.white == whiteTurn) {// only runs if we're trying to move our current piece
            rank++;
            file++;
            ArrayList<Integer[]> p_moves = new ArrayList<Integer[]>();
            while (rank <= 7 && file <= 7) {

                currPiece = board[rank][file];
                if (currPiece != null) {// if not empty
                    if (currPiece.white == whiteTurn) {// our piece
                        break;
                    } else {// enemy piece

                        if (currPiece instanceof king) {
                            return p_moves;
                        }

                        break;
                    }
                } else {// empty
                    Integer[] new_move = new Integer[] { rank, file };
                    p_moves.add(new_move);
                }
                rank++;
                file++;
            }
            // up right
            rank = origin[0];
            file = origin[1];

            rank++;
            file--;
            p_moves = new ArrayList<Integer[]>();
            while (rank <= 7 && file >= 0) {

                currPiece = board[rank][file];
                if (currPiece != null) {// if not empty
                    if (currPiece.white == whiteTurn) {// our piece
                        break;
                    } else {// enemy piece

                        if (currPiece instanceof king) {
                            return p_moves;
                        }

                        break;
                    }
                } else {// empty
                    Integer[] new_move = new Integer[] { rank, file };
                    p_moves.add(new_move);
                }
                rank++;
                file--;
            }
            // down left
            rank = origin[0];
            file = origin[1];
            rank--;
            file--;
            p_moves = new ArrayList<Integer[]>();
            while (rank >= 0 && file >= 0) {

                currPiece = board[rank][file];
                if (currPiece != null) {// if not empty
                    if (currPiece.white == whiteTurn) {// our piece
                        break;
                    } else {// enemy piece

                        if (currPiece instanceof king) {
                            return p_moves;
                        }
                    }
                } else {// empty
                    Integer[] new_move = new Integer[] { rank, file };
                    p_moves.add(new_move);
                }
                rank--;
                file--;
            }
            // down right
            rank = origin[0];
            file = origin[1];
            rank--;
            file++;
            p_moves = new ArrayList<Integer[]>();
            while (rank >= 0 && file <= 7) {

                currPiece = board[rank][file];
                if (currPiece != null) {// if not empty
                    if (currPiece.white == whiteTurn) {// our piece
                        break;
                    } else {// enemy piece

                        if (currPiece instanceof king) {
                            return p_moves;
                        }
                    }
                } else {// empty
                    Integer[] new_move = new Integer[] { rank, file };
                    p_moves.add(new_move);
                }
                rank--;
                file++;
            }
        }

        return moves;
    }

}
