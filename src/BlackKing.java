public class BlackKing extends BlackPiece {

    BlackKing(int xPos, int yPos, boolean hasPieceMoved, Piece[][] gameBoard) {
        super(xPos, yPos, hasPieceMoved, gameBoard);
    }

    @Override
    public boolean checkCanMove(int xPos, int yPos) {
        if (yPos < board.length && xPos < board[yPos].length) {
            if (board[yPos][xPos] == null || board[yPos][xPos] instanceof WhitePiece) {
                if (Math.abs(xPos - x) <= 1 && Math.abs(yPos - y) <= 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
