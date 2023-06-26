public class BlackPiece extends Piece {
    BlackPiece(int xPos, int yPos, boolean hasPieceMoved, Piece[][] gameBoard) {
        super(xPos, yPos, hasPieceMoved, gameBoard);
    }

    @Override
    public boolean checkCanMove(int xPos, int yPos) {
        if (yPos < board.length && xPos < board.length) {
            if (board[yPos][xPos] == null || board[yPos][xPos] instanceof WhitePiece) {
                return true;
            }
        }
        return false;
    }

}
