public class PieceHandler {
    public static boolean promote(int xPos, int yPos, String promotionPiece, Piece[][] board) {
        promotionPiece = promotionPiece.toLowerCase().trim();
        if (board[yPos][xPos] != null) {

            if (board[yPos][xPos] instanceof WhitePiece) {
                switch (promotionPiece) {
                    case "knight":
                        board[yPos][xPos] = new WhiteKnight(xPos, yPos, true, board);
                        break;
                    case "rook":
                        board[yPos][xPos] = new WhiteRook(xPos, yPos, true, board);
                        break;
                    case "bishop":
                        board[yPos][xPos] = new WhiteBishop(xPos, yPos, true, board);
                        break;
                    case "queen":
                    default:
                        board[yPos][xPos] = new WhiteQueen(xPos, yPos, true, board);
                        break;
                }
                return true;
            } else if (board[yPos][xPos] instanceof BlackPiece) {
                switch (promotionPiece) {
                    case "knight":
                        board[yPos][xPos] = new BlackKnight(xPos, yPos, true, board);
                        break;
                    case "rook":
                        board[yPos][xPos] = new BlackRook(xPos, yPos, true, board);
                        break;
                    case "bishop":
                        board[yPos][xPos] = new BlackBishop(xPos, yPos, true, board);
                        break;
                    case "queen":
                    default:
                        board[yPos][xPos] = new BlackQueen(xPos, yPos, true, board);
                        break;
                }
                return true;
            }
        }
        return false;
    }

    public static boolean castleKingSide(boolean isWhiteTurn, Piece[][] board) {

        if (isWhiteTurn && board[0][4] != null && board[0][7] != null) {
            if ((board[0][4] instanceof WhiteKing) && (board[0][7] instanceof WhiteRook)
                    && (board[0][5] == null) && (board[0][6] == null)) {
                if (!board[0][4].checkHasMoved() && !board[0][7].checkHasMoved()) {
                    board[0][4] = null;
                    new WhiteRook(5, 0, true, board);
                    new WhiteKing(6, 0, true, board);
                    board[0][7] = null;

                    return true;
                }
            }
        } else if (!isWhiteTurn && board [7][4] != null && board [7][7] != null) {
            if ((board[7][4] instanceof BlackKing) && (board[7][7] instanceof BlackRook)
                    && (board[7][5] == null) && (board[7][6] == null)) {

                if (!board[7][4].checkHasMoved() && !board[7][7].checkHasMoved()) {
                    board[7][4] = null;
                    new BlackRook(5, 7, true, board);
                    new BlackKing(6, 7, true, board);
                    board[7][7] = null;

                    return true;
                }
            }
        }

        return false;
    }

    public static boolean castleQueenSide(boolean isWhiteTurn, Piece[][] board) {

        if (isWhiteTurn && board[0][0] != null && board[0][4] != null) {
            if ((board[0][4] instanceof WhiteKing) && (board[0][0] instanceof WhiteRook) &&
                    (board[0][1] == null) && (board[0][2] == null) && (board[0][3] == null)) {

                if (!board[0][0].checkHasMoved() && !board[0][4].checkHasMoved()) {
                    board[0][0] = null;
                    board[0][1] = null;
                    new WhiteKing(2, 0, true, board);
                    new WhiteRook(3, 0, true, board);
                    board[0][4] = null;

                    return true;
                }
            }
        } else if (isWhiteTurn && board[7][0] != null && board[7][4] != null) {
            if ((board[7][4] instanceof BlackKing) && (board[7][0] instanceof BlackRook) &&
                    (board[7][1] == null) && (board[7][2] == null) && (board[7][3] == null)) {

                if (!board[7][0].checkHasMoved() && !board[7][4].checkHasMoved()) {
                    board[7][0] = null;
                    board[7][1] = null;
                    new BlackKing(2, 7, true, board);
                    new BlackRook(3, 7, true, board);
                    board[7][4] = null;

                    return true;
                }
            }
        }

        return false;
    }

    public static boolean lookForCheckWhite(Piece[][] board) {
        boolean foundWhiteKing = false;
        int whiteKingX = 0;
        int whiteKingY = 0;
        int i = 0;
        int j = 0;
        while (!foundWhiteKing || i < board.length) {
            while (!foundWhiteKing || j < board[i].length) {
                if (board[i][j] != null && board[i][j] instanceof WhiteKing) {
                    whiteKingX = j;
                    whiteKingY = i;
                    foundWhiteKing = true;
                }
                ++j;
            }
            ++i;
        }

        for (i = 0; i < board.length; ++i) {
            for (j = 0; j < board[i].length; ++j) {
                if (board[i][j] != null && board[i][j] instanceof BlackPiece &&
                            board[i][j].checkCanMove(whiteKingX, whiteKingY)) {
                        return true;

                }
            }
        }

        return false;
    }

    public static boolean lookForCheckBlack(Piece[][] board) {
        boolean foundBlackKing = false;
        int blackKingX = 0;
        int blackKingY = 0;
        int i = 0;
        int j = 0;
        while (!foundBlackKing || i < board.length) {
            while (!foundBlackKing || j < board[i].length) {
                if (board[i][j] != null && board[i][j] instanceof BlackKing) {
                    blackKingX = j;
                    blackKingY = i;
                    foundBlackKing = true;
                }
                ++j;
            }
            ++i;
        }

        for (i = 0; i < board.length; ++i) {
            for (j = 0; j < board[i].length; ++j) {
                if (board[i][j] != null && board[i][j] instanceof WhitePiece &&
                        board[i][j].checkCanMove(blackKingX, blackKingY)) {
                    return true;

                }
            }
        }

        return false;
    }

    public static Piece[][] boardCopier(Piece[][] board) {

        Piece[][] boardCopy = new Piece[board.length][board[0].length];

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] != null) {
                    if (board[i][j] instanceof WhitePiece) {
                        if (board[i][j] instanceof WhitePawn) {
                            if (board[i][j].checkHasMoved()) {
                                new WhitePawn(j, i, true, boardCopy);
                            } else {
                                new WhitePawn(j, i, false, boardCopy);
                            }
                        } else if (board[i][j] instanceof WhiteRook) {
                            if (board[i][j].checkHasMoved()) {
                                new WhiteRook(j, i, true, boardCopy);
                            } else {
                                new WhiteRook(j, i, false, boardCopy);
                            }
                        } else if (board[i][j] instanceof WhiteKnight) {
                            if (board[i][j].checkHasMoved()) {
                                new WhiteKnight(j, i, true, boardCopy);
                            } else {
                                new WhiteKnight(j, i, false, boardCopy);
                            }
                        } else if (board[i][j] instanceof WhiteBishop) {
                            if (board[i][j].checkHasMoved()) {
                                new WhiteBishop(j, i, true, boardCopy);
                            } else {
                                new WhiteBishop(j, i, false, boardCopy);
                            }
                        } else if (board[i][j] instanceof WhiteQueen) {
                            if (board[i][j].checkHasMoved()) {
                                new WhiteQueen(j, i, true, boardCopy);
                            } else {
                                new WhiteQueen(j, i, false, boardCopy);
                            }
                        } else if (board[i][j] instanceof WhiteKing) {
                            if (board[i][j].checkHasMoved()) {
                                new WhiteKing(j, i, true, boardCopy);
                            } else {
                                new WhiteKing(j, i, false, boardCopy);
                            }
                        } else {
                            if (board[i][j].checkHasMoved()) {
                                new WhitePiece(j, i, true, boardCopy);
                            } else {
                                new WhitePiece(j, i, false, boardCopy);
                            }
                        }
                    } else if (board[i][j] instanceof BlackPiece) {
                        if (board[i][j] instanceof BlackPawn) {
                            if (board[i][j].checkHasMoved()) {
                                new BlackPawn(j, i, true, boardCopy);
                            } else {
                                new BlackPawn(j, i, false, boardCopy);
                            }
                        } else if (board[i][j] instanceof BlackRook) {
                            if (board[i][j].checkHasMoved()) {
                                new BlackRook(j, i, true, boardCopy);
                            } else {
                                new BlackRook(j, i, false, boardCopy);
                            }
                        } else if (board[i][j] instanceof BlackKnight) {
                            if (board[i][j].checkHasMoved()) {
                                new BlackKnight(j, i, true, boardCopy);
                            } else {
                                new BlackKnight(j, i, false, boardCopy);
                            }
                        } else if (board[i][j] instanceof BlackBishop) {
                            if (board[i][j].checkHasMoved()) {
                                new BlackBishop(j, i, true, boardCopy);
                            } else {
                                new BlackBishop(j, i, false, boardCopy);
                            }
                        } else if (board[i][j] instanceof BlackQueen) {
                            if (board[i][j].checkHasMoved()) {
                                new BlackQueen(j, i, true, boardCopy);
                            } else {
                                new BlackQueen(j, i, false, boardCopy);
                            }
                        } else if (board[i][j] instanceof WhiteKing) {
                            if (board[i][j].checkHasMoved()) {
                                new BlackKing(j, i, true, boardCopy);
                            } else {
                                new BlackKing(j, i, false, boardCopy);
                            }
                        } else {
                            if (board[i][j].checkHasMoved()) {
                                new BlackPiece(j, i, true, boardCopy);
                            } else {
                                new BlackPiece(j, i, false, boardCopy);
                            }
                        }
                    } else {
                        if (board[i][j].checkHasMoved()) {
                            new Piece(j, i, true, boardCopy);
                        } else {
                            new Piece(j, i, false, boardCopy);
                        }
                    }
                }
            }
        }

        return boardCopy;
    }

    public static boolean lookForCheckmateWhite(Piece[][] board) {



        return false;
    }



}
