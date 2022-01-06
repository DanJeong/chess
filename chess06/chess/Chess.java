
//import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Fully functional 2p chess game.
 * <p>
 * Format for a move is as such: "e2 e4". This example will move the piece in e2
 * to e4. Players may also forfeit or request a draw.
 *
 * @author Daniel Jeong - dsj58
 * @author Kevin Zhang - kz225
 */

public class Chess {

    /**
     * Builds the chessboard and runs the game. This is the main method of the
     * program.
     * 
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {

        piece[][] board = new piece[8][8];
        build_board(board);

        print_board(board);

        boolean isWhite = true;
        Scanner scanner = new Scanner(System.in);
        // main while loop to play the game. Loop runs until a player wins or
        // draws/forfeits.
        while (true) {
            String turn = "White's move: ";
            if (isWhite) {
                turn = "White's move: ";
            } else {
                turn = "Black's move: ";
            }
            System.out.print(turn);
            String[] commands = scanner.nextLine().split(" ");

            if (commands.length == 1) {
                if (commands[0].equals("resign")) {
                    if (isWhite) {
                        System.out.println("Black wins");
                    } else {
                        System.out.println("White wins");
                    }
                    break;
                }
            }

            int[] origin = new int[2];
            int[] dest = new int[2];
            origin[1] = file_toInt(commands[0].charAt(0));
            origin[0] = Integer.parseInt(commands[0].substring(1)) - 1;
            dest[1] = file_toInt(commands[1].charAt(0));
            dest[0] = Integer.parseInt(commands[1].substring(1)) - 1;

            piece currPiece = board[origin[0]][origin[1]];
            if (commands.length == 3) {
                if (commands[2].equals("draw?")) {
                    System.out.println("draw");
                    break;
                }
                if (currPiece != null) {
                    if (currPiece.canMoveHere(board, isWhite, origin, dest, commands[2])) {
                        // put outselves in check
                        piece[][] t_board = new piece[8][8];

                        for (int i = 0; i < board.length; i++)
                            t_board[i] = board[i].clone();

                        piece t_moved_piece = t_board[origin[0]][origin[1]];
                        t_board[origin[0]][origin[1]] = null;
                        t_board[dest[0]][dest[1]] = t_moved_piece;

                        if (!isCheck(t_board, isWhite)) {
                            if (currPiece instanceof pawn && currPiece.enpassant) {

                                if (dest[1] > origin[1]) {
                                    board[origin[0]][origin[1] + 1] = null;
                                } else {
                                    board[origin[0]][origin[1] - 1] = null;
                                }

                                currPiece.enpassant = false;
                                piece moved_piece = board[origin[0]][origin[1]];
                                board[origin[0]][origin[1]] = null;
                                board[dest[0]][dest[1]] = moved_piece;
                                print_board(board);

                            }
                            if (currPiece instanceof pawn && currPiece.promotion) {
                                board[origin[0]][origin[1]] = null;
                                piece promo_piece = null;
                                char promo_color = 'b';
                                if (isWhite) {
                                    promo_color = 'w';
                                }
                                if (commands[2].equals("B")) {
                                    promo_piece = new bishop(promo_color + "B", isWhite);
                                }
                                if (commands[2].equals("N")) {
                                    promo_piece = new knight(promo_color + "N", isWhite);
                                }
                                if (commands[2].equals("R")) {
                                    promo_piece = new rook(promo_color + "R", isWhite);
                                }
                                if (commands[2].equals("Q")) {
                                    promo_piece = new queen(promo_color + "Q", isWhite);
                                }
                                board[dest[0]][dest[1]] = promo_piece;
                                print_board(board);

                            }
                            isWhite = !isWhite;
                            currPiece.moved = true;
                            currPiece.checked = false;

                            if (isCheckmate(board, isWhite)) {
                                break;
                            } else if (isCheck(board, isWhite)) {
                                System.out.println("Check");
                                int[] kingposs = findKing(board, isWhite);
                                board[kingposs[0]][kingposs[1]].checked = true;
                            }
                        } else {
                            System.out.println("Illegal move, try again");
                        }

                    } else {
                        System.out.println("Illegal move, try again");
                    }
                } else {
                    System.out.println("Illegal move, try again");
                }

            }

            else {// 2 args
                if (currPiece != null) {
                    if (currPiece.canMoveHere(board, isWhite, origin, dest, "NoPro")) {

                        // put outselves in check
                        piece[][] t_board = new piece[8][8];

                        for (int i = 0; i < board.length; i++)
                            t_board[i] = board[i].clone();

                        piece t_moved_piece = t_board[origin[0]][origin[1]];
                        t_board[origin[0]][origin[1]] = null;
                        t_board[dest[0]][dest[1]] = t_moved_piece;
                        t_board[dest[0]][dest[1]].moved = true;

                        if (!isCheck(t_board, isWhite)) {
                            if (currPiece instanceof pawn && currPiece.enpassant) {

                                if (dest[1] > origin[1]) {
                                    board[origin[0]][origin[1] + 1] = null;
                                } else {
                                    board[origin[0]][origin[1] - 1] = null;
                                }

                                currPiece.enpassant = false;
                                piece moved_piece = board[origin[0]][origin[1]];
                                board[origin[0]][origin[1]] = null;
                                board[dest[0]][dest[1]] = moved_piece;
                                print_board(board);
                                isWhite = !isWhite;
                            }

                            else if (currPiece.castle && currPiece instanceof king) {
                                int king_movement;
                                int rook_movement;
                                if (dest[1] > origin[1]) {
                                    king_movement = 3;
                                    rook_movement = 1;
                                } else {
                                    king_movement = -4;
                                    rook_movement = -1;
                                }

                                currPiece.castle = false;
                                piece moved_piece = board[origin[0]][origin[1]];
                                board[origin[0]][origin[1]] = null;
                                board[dest[0]][dest[1]] = moved_piece;

                                piece moved_rook = board[origin[0]][origin[1] + king_movement];
                                board[origin[0]][origin[1] + king_movement] = null;
                                board[origin[0]][origin[1] + rook_movement] = moved_rook;
                                print_board(board);
                                isWhite = !isWhite;
                            } else {
                                piece moved_piece = board[origin[0]][origin[1]];
                                board[origin[0]][origin[1]] = null;
                                board[dest[0]][dest[1]] = moved_piece;
                                print_board(board);
                                isWhite = !isWhite;
                            }
                            currPiece.moved = true;
                            currPiece.checked = false;

                            if (isCheckmate(board, isWhite)) {

                                break;
                            } else if (isCheck(board, isWhite)) {
                                System.out.println("Check");
                                int[] kingposs = findKing(board, isWhite);
                                board[kingposs[0]][kingposs[1]].checked = true;

                            }
                        } else {
                            System.out.println("Illegal move, try again");
                        }

                    } else {
                        System.out.println("Illegal move, try again");
                    }
                } else {
                    System.out.println("Illegal move, try again");
                }
            }

        }
        scanner.close();

    }

    /***
     * 
     *
     * @param newBoard A 2d array of pieces to be filled with objects - knight/king
     *                 etc.
     * 
     */
    public static void build_board(piece[][] newBoard) {
        // pawns

        for (int i = 0; i < newBoard.length; i++) {
            piece x = new pawn("wp", true);
            newBoard[1][i] = x;
            piece y = new pawn("bp", false);
            newBoard[6][i] = y;
        }

        // rooks
        piece x = new rook("wR", true);
        piece y = new rook("wR", true);
        newBoard[0][0] = x;
        newBoard[0][7] = y;
        piece a = new rook("bR", false);
        piece b = new rook("bR", false);
        newBoard[7][0] = a;
        newBoard[7][7] = b;
        // knights
        piece c = new knight("wN", true);
        piece d = new knight("wN", true);
        newBoard[0][1] = c;
        newBoard[0][6] = d;
        piece e = new knight("bN", false);
        piece f = new knight("bN", false);
        newBoard[7][1] = e;
        newBoard[7][6] = f;
        // bishops
        piece g = new bishop("wB", true);
        piece h = new bishop("wB", true);
        newBoard[0][2] = g;
        newBoard[0][5] = h;
        piece i = new bishop("bB", false);
        piece j = new bishop("bB", false);
        newBoard[7][2] = i;
        newBoard[7][5] = j;
        // queens
        piece k = new queen("wQ", true);
        piece l = new queen("bQ", false);
        newBoard[0][3] = k;
        newBoard[7][3] = l;
        // kings
        piece m = new king("wK", true);
        piece n = new king("bK", false);
        newBoard[0][4] = m;
        newBoard[7][4] = n;
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                if (newBoard[rank][file] == null) {

                }
            }
        }

    }

    /**
     * @param newBoard a 2d array made of pieces to be printed out
     */
    public static void print_board(piece[][] newBoard) {
        boolean line_switch = false;
        System.out.println();
        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                if ((newBoard[rank][file] != null)) {
                    System.out.print(newBoard[rank][file].getName() + " ");

                } else if (line_switch) {
                    System.out.print("## ");

                } else {
                    System.out.print("   ");
                }
                line_switch = !line_switch;

            }
            System.out.println(rank + 1);
            line_switch = !line_switch;

        }
        System.out.print(" ");
        for (int i = 97; i <= 104; i++) {
            System.out.print((char) i + "  ");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * @param x takes in an alphabetic character
     * @return An int that represents the character's file
     */
    public static int file_toInt(char x) {
        int file = (int) x - 97;
        return file;
    }

    /**
     * @param board A 2d piece array that contains our pieces
     * @param white A boolean indicating if it is white's turn
     * @return boolean
     */
    public static boolean isCheckmate(piece[][] board, boolean white) {
        int moves[][] = new int[][] { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
                { -1, 1 }, { 0, 0 } };
        int[] king_pos = findKing(board, white);
        int[] t_king_pos = king_pos.clone();

        piece t_moved_piece;
        for (int i = 0; i < moves.length; i++) {
            piece[][] t_board = new piece[8][8];
            for (int j = 0; j < board.length; j++)
                t_board[j] = board[j].clone();

            t_king_pos[0] += moves[i][0];
            t_king_pos[1] += moves[i][1];

            if ((t_king_pos[0] <= 7 && t_king_pos[0] >= 0) && (t_king_pos[1] <= 7 && t_king_pos[1] >= 0)) {
                t_moved_piece = t_board[king_pos[0]][king_pos[1]];
                t_board[king_pos[0]][king_pos[1]] = null;
                t_board[t_king_pos[0]][t_king_pos[1]] = t_moved_piece;
                // if spot is in the board range
                if (board[t_king_pos[0]][t_king_pos[1]] != null) {// if spot is not empty

                    if (board[t_king_pos[0]][t_king_pos[1]].white == !white) {
                        if (!isCheck(t_board, white)) {
                            return false;
                        }
                    } else if (king_pos[0] == t_king_pos[0] && king_pos[1] == t_king_pos[1]) {

                        if (!isCheck(t_board, white)) {
                            return false;
                        }
                    }
                } else {// spot is null
                    if (!isCheck(t_board, white)) {
                        return false;
                    }
                }

            }

            t_king_pos = king_pos.clone();

        }

        if (board[king_pos[0]][king_pos[1]].killer != null) {// if king is in danger
            // check if we can take piece endangering our king
            if (inDanger(board, board[king_pos[0]][king_pos[1]].killer, !white)) {

                // check if killer's killer is king
                // if so, then move king into killer's spot and run isCheck with tempboard
                // if isCheck is false, then return false\
                int killerX = board[king_pos[0]][king_pos[1]].killer[0];
                int killerY = board[king_pos[0]][king_pos[1]].killer[1];
                piece kingKiller = getPiece(board, board[king_pos[0]][king_pos[1]].killer);

                if (getPiece(board, kingKiller.killer) instanceof king) {
                    piece[][] t_board = new piece[8][8];

                    for (int i = 0; i < board.length; i++)
                        t_board[i] = board[i].clone();

                    t_moved_piece = t_board[king_pos[0]][king_pos[1]];
                    t_board[king_pos[0]][king_pos[1]] = null;
                    t_board[killerX][killerY] = t_moved_piece;
                    if (isCheck(t_board, white)) {
                        System.out.println("Checkmate");
                        if (white) {
                            System.out.println("Black wins");
                        } else {
                            System.out.println("White wins");
                        }

                        return true;
                    }
                }
                board[king_pos[0]][king_pos[1]].killer = null;

                return false;
            }
            // check if we can block path between king and killer
            // get spaces between king and killer
            ArrayList<Integer[]> spaces_between = getPiece(board, board[king_pos[0]][king_pos[1]].killer).sbKing(board,
                    !white, board[king_pos[0]][king_pos[1]].killer, new int[] { king_pos[0], king_pos[1] });
            // run canMoveHere on all of our pieces and see if they can move into the spaces
            if (!(getPiece(board, board[king_pos[0]][king_pos[1]].killer) instanceof knight)) {// we cant block a
                                                                                               // knight's path
                ArrayList<Integer[]> p_moves = spaces_between;
                for (int a = 0; a < p_moves.size(); a++) {
                    for (int i = 0; i <= 7; i++) {
                        for (int j = 0; j <= 7; j++) {
                            if (board[i][j] != null && board[i][j].white == white && !(board[i][j] instanceof king)
                                    && board[i][j].canMoveHere(board, white, new int[] { i, j },
                                            new int[] { p_moves.get(a)[0], p_moves.get(a)[1] }, "noPro")) {

                                board[king_pos[0]][king_pos[1]].killer = null;
                                return false;
                            }

                        }
                    }

                }
            }

        }

        System.out.println("Checkmate");
        if (white) {
            System.out.println("Black wins");
        } else {
            System.out.println("White wins");
        }

        return true;

    }

    /**
     * @param board A 2d piece array that contains our pieces
     * @param white A boolean indicating whos king the method returns
     * @return int[] Returns an int array that contains the rank and file of our
     *         king
     */
    public static int[] findKing(piece[][] board, boolean white) {

        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                if (board[rank][file] instanceof king && board[rank][file].white == white) {

                    return new int[] { rank, file };
                }
            }
        }
        return new int[] { 9, 9 };// this function should never get here

    }

    /**
     * @param board A 2d piece array that contains our pieces
     * @param pos   An int array that contains the rank and file to be checked
     * @param white A boolean indicating whos turn it is
     * @return boolean Returns a boolean indicating whether the given pos can be
     *         attacked by the opponents piece
     */
    public static boolean inDanger(piece[][] board, int[] pos, boolean white) {
        for (int r = 0; r < 8; r++) {
            for (int f = 0; f < 8; f++) {
                if (board[r][f] != null && board[r][f].white == !white) {
                    if (board[r][f].canMoveHere(board, !white, new int[] { r, f }, pos, "zzzzz")) {
                        board[pos[0]][pos[1]].killer = new int[] { r, f };
                        return true;
                    }
                }
            }
        }
        return false;

    }

    /**
     * @param board A 2d piece array that contains our pieces
     * @param white A boolean indicating whos turn it is
     * @return boolean Returns a boolean indicating if the player is in check
     */
    public static boolean isCheck(piece[][] board, boolean white) {
        int[] king_pos = findKing(board, white);
        return inDanger(board, king_pos, white);
    }

    /**
     * @param board A 2d piece array that contains our pieces
     * @param cords An int array containing coordinates of a piece in our board
     * @return piece Returns the piece object found at the given cords in our board
     */
    public static piece getPiece(piece[][] board, int[] cords) {
        return board[cords[0]][cords[1]];
    }
}
