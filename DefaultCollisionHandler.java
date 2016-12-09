package tetris;

/**
 * This class, as its name implies, is responsible for the main way of handling collisions, which
 * basically is to not do anything, in other words modify the Poly so that it does not perform the update.
 */
public class DefaultCollisionHandler implements CollisionHandler
{
    public boolean hasCollision(Board board) {
	SquareType[][] shape = board.getFalling().getPolyArray();
	for (int row = 0; row < shape.length; row++) {
	    for (int col = 0; col < shape.length; col++) {
		if (shape[col][row] != SquareType.EMPTY) {
		    if (board.getSquareType(row + board.getFallingX(), col + board.getFallingY()) != SquareType.EMPTY) {
			return true;
		    }
		}
	    }
	}
	return false;
    }
    public String getDescription() {
	return "No powerups active.";
    }
}
