package tetris;

/**
 * This class modified the collision so that it simulates an earthquake on the board, the earthquake removes the lowest
 * row and pushes the rest of the blocks down one square.
 */
public class Earthquake implements CollisionHandler
{
    public boolean hasCollision(Board board) {
	SquareType[][] shape = board.getFalling().getPolyArray();
	for (int row = 0; row < shape.length; row++) {
	    for (int col = 0; col < shape.length; col++) {
		if (shape[col][row] != SquareType.EMPTY) {
		    if (board.getSquareType(row + board.getFallingX(), col + board.getFallingY()) != SquareType.EMPTY) {
			if (board.getSquareType(row + board.getFallingX(), col + board.getFallingY()) != SquareType.OUTSIDE) {
			    removeRow(board);
			}
			return true;
		    }
		}
	    }
	}
	return false;
    }

    public void removeRow(Board board) {
	for (int row = board.getHeight(); row > 0; row--) {
	    for (int col = 0; col < board.getWidth(); col++) {
		board.setSquareType(col, row, board.getSquareType(col, row - 1));
	    }
	}
	board.setFallingY(board.getFallingY() + 1);
	board.notifyListeners();
    }

    public String getDescription() {
	return "Earthquake powerup active!";
    }
}