public class Piece {
    protected Piece[][] board;
    protected int x;
    protected int y;
    protected boolean hasMoved;

    Piece(int xPos, int yPos, boolean hasPieceMoved, Piece[][] gameBoard) {
        x = xPos;
        y = yPos;
        board = gameBoard;
        hasMoved = hasPieceMoved;

        board[y][x] = this;
    }

    public Piece() {

    }

    public boolean move(int xPos, int yPos) {
        if (checkCanMove(xPos, yPos)) {
            successfulMove(xPos, yPos);
            hasMoved = true;
            return true;
        }
        return false;
    }

    public boolean checkHasMoved() {
        if (hasMoved) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkCanMove(int xPos, int yPos) {
        if (yPos < board.length && xPos < board[yPos].length) {
            return true;
        }
        return false;
    }

    protected void successfulMove(int xPos, int yPos) {
        board[y][x] = null;
        board[yPos][xPos] = this;
        x = xPos;
        y = yPos;
    }
}
