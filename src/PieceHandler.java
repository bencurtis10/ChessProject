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

    public static boolean castleKingSide(String player, Piece[][] board) {
        player = player.toLowerCase().trim();

        if (player.equals("white") && board[0][4] != null && board[0][7] != null) {
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
        } else if (player.equals("black") && board [7][4] != null && board [7][7] != null) {
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

    public static boolean castleQueenSide(String player, Piece[][] board) {
        player = player.trim().toLowerCase();

        if (player.equals("white") && board[0][0] != null && board[0][4] != null) {
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
        } else if (player.equals("black") && board[7][0] != null && board[7][4] != null) {
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

}
