public class BlackQueen extends BlackPiece {

    BlackQueen(int xPos, int yPos, boolean hasPieceMoved, Piece[][] gameBoard) {
        super(xPos, yPos, hasPieceMoved, gameBoard);
    }

//    @Override
//    public boolean move(int xPos, int yPos) {
//        if (yPos < board.length && xPos < board[yPos].length) {
//            if (board[yPos][xPos] == null || board[yPos][xPos] instanceof WhitePiece) {
//                if (yPos == y && xPos != x && checkClearPathHorizontal(x, xPos, y)) {
//                    successfulMove(xPos, yPos);
//                    return true;
//                } else if (yPos != y && xPos == x && checkClearPathVertical(y, yPos, x)) {
//                    successfulMove(xPos, yPos);
//                    return true;
//                } else if (((xPos - x) == (yPos - y)) && checkClearDiagonalAscending(x, y, xPos, yPos)) {
//                    successfulMove(xPos, yPos);
//                    return true;
//                } else if ((xPos - x) == (-1 * (yPos - y)) && checkClearDiagonalDescending(x, y, xPos, yPos)) {
//                    successfulMove(xPos, yPos);
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    @Override
    public boolean checkCanMove(int xPos, int yPos) {
        if (yPos < board.length && xPos < board[yPos].length) {
            if (board[yPos][xPos] == null || board[yPos][xPos] instanceof WhitePiece) {
                if (yPos == y && xPos != x && checkClearPathHorizontal(x, xPos, y)) {
                    return true;
                } else if (yPos != y && xPos == x && checkClearPathVertical(y, yPos, x)) {
                    return true;
                } else if (((xPos - x) == (yPos - y)) && checkClearDiagonalAscending(x, y, xPos, yPos)) {
                    return true;
                } else if ((xPos - x) == (-1 * (yPos - y)) && checkClearDiagonalDescending(x, y, xPos, yPos)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkClearPathHorizontal(int x1, int x2, int y) {
        int greaterX;
        int lesserX;

        if (x1 > x2) {
            greaterX = x1;
            lesserX = x2;
        } else {
            greaterX = x2;
            lesserX = x1;
        }

        for (int i = lesserX + 1; i <= greaterX - 1; ++i) {
            if (board[y][i] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean checkClearPathVertical(int y1, int y2, int x) {
        int greaterY;
        int lesserY;

        if (y1 > y2) {
            greaterY = y1;
            lesserY = y2;
        } else {
            greaterY = y2;
            lesserY = y1;
        }

        for (int i = lesserY + 1; i <= greaterY - 1; ++i) {
            if (board[i][x] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean checkClearDiagonalAscending(int startX, int startY, int endX, int endY) {
        int lesserX;
        int lesserY;
        if (startX < endX) {
            lesserX = startX;
        } else {
            lesserX = endX;
        }
        if (startY < endY) {
            lesserY = startY;
        } else {
            lesserY = endY;
        }

        for (int i = 1; i < (Math.abs(startX - endX)); ++i) {
            if (board[lesserY + i][lesserX + i] != null) {
                return false;
            }
        }

        return true;
    }

    private boolean checkClearDiagonalDescending(int startX, int startY, int endX, int endY) {
        int lesserX;
        int greaterY;
        if (startX < endX) {
            lesserX = startX;
        } else {
            lesserX = endX;
        }
        if (startY > endY) {
            greaterY = startY;
        } else {
            greaterY = endY;
        }

        for (int i = 1; i < Math.abs(startX - endX); ++i) {
            if (board[greaterY - i][lesserX + i] != null) {
                return false;
            }
        }

        return true;
    }


}