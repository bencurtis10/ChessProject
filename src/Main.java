import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        Piece[][] board = generateDefaultBoard();

        printBoard(board);

        String userInput;
        boolean finished = false;
        boolean hasValidInput;
        boolean hasSelectedPiece;
        boolean hasValidMovement;
        int yPieceCoordinate = 0;
        int xPieceCoordinate = 0;
        int yNewCoordinate = 0;
        int xNewCoordinate = 0;

        while (!finished) {
            hasSelectedPiece = false;
            System.out.println("Move which piece?");

            while(!hasSelectedPiece) {
                hasValidInput = false;
                while (!hasValidInput) {
                    if (scnr.hasNext()) {
                        userInput = scnr.next().trim().toLowerCase();
                        if (userInput.length() == 2) {
                            yPieceCoordinate = fetchYCoordinate(userInput);
                            xPieceCoordinate = fetchXCoordinate(userInput);
                            if (xPieceCoordinate + yPieceCoordinate < 15) {
                                hasValidInput = true;
                            } else {
                                System.out.println("Error! Invalid input! Try again.");
                            }
                        } else if (userInput.length() == 3) {
                            switch (userInput) {
                                case "cks":
                                    PieceHandler.castleKingSide("white", board);
                                    break;
                                case "end":
                                    finished = true;
                                    break;
                            }
                        } else {
                            System.out.println("Error! Invalid Input! Try again.");
                        }
                    }
                }

                if (board[yPieceCoordinate][xPieceCoordinate] != null) {
                    hasSelectedPiece = true;
                } else {
                    System.out.println("Error! No piece at selected position! Try again.");
                }
            }

            System.out.println("Move piece where?");

            hasValidMovement = false;
            while (!hasValidMovement) {
                hasValidInput = false;
                while (!hasValidInput) {
                    if (scnr.hasNext()) {
                        userInput = scnr.next();
                        if (userInput.length() == 2) {
                            yNewCoordinate = fetchYCoordinate(userInput);
                            xNewCoordinate = fetchXCoordinate(userInput);
                            if (xNewCoordinate + yNewCoordinate < 15) {
                                hasValidInput = true;
                            } else {
                                System.out.println("Error! Invalid input! Try again.");
                            }
                        } else {
                            System.out.println("Error! Invalid Input! Try again.");
                        }
                    }
                }

                if (board[yPieceCoordinate][xPieceCoordinate].move(xNewCoordinate, yNewCoordinate)) {
                    hasValidMovement = true;
                } else {
                    System.out.println("Error! Piece cannot move there! Try again.");
                }
            }

            printBoard(board);
        }

    }



    private static Piece[][] generateDefaultBoard() {
        Piece[][] boardObjects = new Piece[8][8];

        for (int i = 0; i < boardObjects[1].length; ++i) {
            new WhitePawn(i, 1, false, boardObjects);
        }

        for (int i = 0; i < boardObjects[6].length; ++i) {
            new BlackPawn(i, 6, false, boardObjects);
        }

        {
            new WhiteRook(0, 0, false, boardObjects);
            new WhiteKnight(1, 0, false, boardObjects);
            new WhiteBishop(2, 0, false, boardObjects);
            new WhiteQueen(3, 0, false, boardObjects);
            new WhiteKing(4, 0, false, boardObjects);
            new WhiteBishop(5, 0, false, boardObjects);
            new WhiteKnight(6, 0, false, boardObjects);
            new WhiteRook(7, 0, false, boardObjects);
        }

        {
            new BlackRook(0, 7, false, boardObjects);
            new BlackKnight(1, 7, false, boardObjects);
            new BlackBishop(2, 7, false, boardObjects);
            new BlackQueen(3, 7, false, boardObjects);
            new BlackKing(4, 7, false, boardObjects);
            new BlackBishop(5, 7, false, boardObjects);
            new BlackKnight(6, 7, false, boardObjects);
            new BlackRook(7, 7, false, boardObjects);
        }

        return boardObjects;
    }

//testing new commit

//    private static boolean lookForCheckWhite(Piece[][] board) {
//
//        boolean foundWhiteKing = false;
//        int whiteKingX = 0;
//        int whiteKingY = 0;
//        int i = 0;
//        int j = 0;
//        while (!foundWhiteKing || i < board.length) {
//            while (!foundWhiteKing || j < board[i].length) {
//                if (board[i][j] != null && board[i][j] instanceof WhiteKing) {
//                    whiteKingX = j;
//                    whiteKingY = i;
//                    foundWhiteKing = true;
//                }
//                ++j;
//            }
//            ++i;
//        }
//
//        for (i = 0; i < board.length; ++i) {
//            for (j = 0; j < board[i].length; ++j) {
//                if (board[i][j] != null && board[i][j] instanceof BlackPiece) {
//
//                }
//            }
//        }
//
//
//
//        return false;
//    }



    private static void printBoard(Piece[][] board) {
        for (int i = board.length - 1; i >= 0; --i) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == null)
                    System.out.print("-- ");
                else if (board[i][j] instanceof WhitePawn)
                    System.out.print("WP ");
                else if (board[i][j] instanceof BlackPawn)
                    System.out.print("BP ");
                else if (board[i][j] instanceof WhiteRook)
                    System.out.print("WR ");
                else if (board[i][j] instanceof BlackRook)
                    System.out.print("BR ");
                else if (board[i][j] instanceof WhiteKnight)
                    System.out.print("WN ");
                else if (board[i][j] instanceof BlackKnight)
                    System.out.print("BN ");
                else if (board[i][j] instanceof WhiteBishop)
                    System.out.print("WB ");
                else if (board[i][j] instanceof BlackBishop)
                    System.out.print("BB ");
                else if (board[i][j] instanceof WhiteQueen)
                    System.out.print("WQ ");
                else if (board[i][j] instanceof BlackQueen)
                    System.out.print("BQ ");
                else if (board[i][j] instanceof WhiteKing)
                    System.out.print("WK ");
                else if (board[i][j] instanceof BlackKing)
                    System.out.print("BK ");
                else if (board[i][j] instanceof WhitePiece)
                    System.out.print("WX ");
                else if (board[i][j] instanceof BlackPiece)
                    System.out.print("BX ");
                else
                    System.out.print("XX ");

            }
            System.out.println();
        }
        System.out.println("   A  B  C  D  E  F  G  H  ");
    }

    private static int fetchXCoordinate(String position) {
        int x;
        char userChar = position.charAt(0);
        switch (userChar) {
            case 'a':
            case 'A':
                x = 0;
                break;
            case 'b':
            case 'B':
                x = 1;
                break;
            case 'c':
            case 'C':
                x = 2;
                break;
            case 'd':
            case 'D':
                x = 3;
                break;
            case 'e':
            case 'E':
                x = 4;
                break;
            case 'f':
            case 'F':
                x = 5;
                break;
            case 'g':
            case 'G':
                x = 6;
                break;
            case 'h':
            case 'H':
                x = 7;
                break;
            default:
                x = 15;
                break;
        }

        return x;
    }

    public static int fetchYCoordinate(String position) {
        int y;
        int userNum = position.charAt(1);
        if (userNum >= 49 && userNum <= 56) {
            y = userNum - 49;
        } else {
            y = 15;
        }

        return y;
    }
}