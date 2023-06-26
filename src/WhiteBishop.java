public class WhiteBishop extends WhitePiece {

    WhiteBishop(int xPos, int yPos, boolean hasPieceMoved, Piece[][] gameBoard) {
        super(xPos, yPos, hasPieceMoved, gameBoard);
    }

//    @Override
//    public boolean move(int xPos, int yPos) {
//        if (yPos < board.length && xPos < board[yPos].length) {
//            if (board[yPos][xPos] == null || board[yPos][xPos] instanceof BlackPiece) {
//                if (Math.abs(xPos - x) == Math.abs(yPos - y)) {
//                    if (((xPos - x) == (yPos - y)) && checkClearPathAscending(x, y, xPos, yPos)) {
//                        successfulMove(xPos, yPos);
//                        return true;
//                    } else if ((xPos - x) == (-1 * (yPos - y)) && checkClearPathDescending(x, y, xPos, yPos)) {
//                        successfulMove(xPos, yPos);
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }

    @Override
    public boolean checkCanMove(int xPos, int yPos) {
        if (yPos < board.length && xPos < board[yPos].length) {
            if (board[yPos][xPos] == null || board[yPos][xPos] instanceof BlackPiece) {
                if (Math.abs(xPos - x) == Math.abs(yPos - y)) {
                    if (((xPos - x) == (yPos - y)) && checkClearPathAscending(x, y, xPos, yPos)) {
                        return true;
                    } else if ((xPos - x) == (-1 * (yPos - y)) && checkClearPathDescending(x, y, xPos, yPos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkClearPathAscending(int startX, int startY, int endX, int endY) {
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

    private boolean checkClearPathDescending(int startX, int startY, int endX, int endY) {
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