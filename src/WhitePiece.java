public class WhitePiece extends Piece {
    WhitePiece(int xPos, int yPos, boolean hasPieceMoved, Piece[][] gameBoard) {
        super(xPos, yPos, hasPieceMoved, gameBoard);
    }

    @Override
    public boolean checkCanMove(int xPos, int yPos) {
        if (yPos < board.length && xPos < board.length) {
            if (board[yPos][xPos] == null || board[yPos][xPos] instanceof BlackPiece) {
                return true;
            }
        }
        return false;
    }
}
