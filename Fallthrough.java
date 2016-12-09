package tetris;

/**
 * This class modified the collision so that a Poly does not stop until it hits a wall, it removes all the blocks
 * in its way on the way down.
 */
public class Fallthrough implements CollisionHandler
{
    public boolean hasCollision(Board board) {
	SquareType[][] shape = board.getFalling().getPolyArray();
	for (int row = 0; row < shape.length; row++) {
	    for (int col = 0; col < shape.length; col++) {
		if (shape[col][row] != SquareType.EMPTY) {
		    if (board.getSquareType(row + board.getFallingX(), col + board.getFallingY()) == SquareType.OUTSIDE) {
			return true;
		    }
		}
	    }
	}
	return false;
    }

    public String getDescription() {
	return "Fallthrough powerup active!";
    }
}
