package tetris;

/**
 * This class represents a Poly based on a two-dimensional array. It has a get-method for which SquareType the current Poly is
 * as well as methods for right and left rotations.
 */
public class Poly
{
    private SquareType[][] polyArray;

    public Poly(final SquareType[][] polyArray)
    {
	this.polyArray = polyArray;
    }

    public Poly rotate(boolean rot) {
	if (rot) {return rotateRight();} else {return rotateLeft();}
    }

    public Poly rotateRight() {
	Poly newPoly = new Poly(new SquareType[polyArray.length][polyArray.length]);

	for (int r = 0; r < polyArray.length; r++) {
	    for (int c = 0; c < polyArray.length; c++) {
		newPoly.polyArray[c][polyArray.length - 1 - r] = polyArray[r][c];
	    }
	}
	return newPoly;
    }

    public Poly rotateLeft() {
	Poly newPoly = new Poly(new SquareType[polyArray.length][polyArray.length]);

	for (int r = 0; r < polyArray.length; r++) {
	    for (int c = 0; c < polyArray.length; c++) {
		newPoly.polyArray[r][c] = polyArray[c][polyArray.length - 1 - r];
	    }
	}
	return newPoly;
    }

    public SquareType[][] getPolyArray() {
	return polyArray;
    }

    public SquareType getPolyType() {
	for (int row = 0; row < polyArray.length; row++) {
	    for (final SquareType[] aPolyArray : polyArray) {
		if (aPolyArray[row] != SquareType.EMPTY) {
		    return aPolyArray[row];
		}
	    }
	}
	return null;
    }
}
