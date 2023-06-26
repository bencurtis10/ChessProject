public class WhitePawn extends WhitePiece {

    WhitePawn(int xPos, int yPos, boolean hasPieceMoved, Piece[][] gameBoard) {
        super(xPos, yPos, hasPieceMoved, gameBoard);
    }

    @Override
    public boolean checkCanMove(int xPos, int yPos) {
        if (yPos < board.length && xPos < board[yPos].length) {
            if ((xPos == x + 1 || xPos == x - 1) && y + 1 == yPos) {
                if (board[yPos][xPos] instanceof BlackPiece) {
                    return true;
                }
            } else if (xPos == x && yPos == y + 2 && !hasMoved) {
                if (board[yPos][xPos] == null) {
                    return true;
                }
            } else if (xPos == x && yPos == y + 1) {
                if (board[yPos][xPos] == null) {
                    return true;
                }
            }
        }
        return false;
    }

}
