package tetris;

/**
 * This class can be viewed as the "factory" for the Poly's, based on what type of Poly it is it returns
 * the two-dimensional array representation of it.
 */
public class TetrominoMaker
{
    public Poly getPoly(int n) {
	switch (n) {
	    case 0: //I
		return new Poly(createShapeI());//), SquareType.I);
	    case 1: //O
		return new Poly(createShapeO());//), SquareType.I);, SquareType.O);
	    case 2: //T
		return new Poly(createShapeT());//), SquareType.I);, SquareType.T);
	    case 3: //S
		return new Poly(createShapeS());//), SquareType.I);, SquareType.S);
	    case 4: //Z
		return new Poly(createShapeZ());//), SquareType.I);, SquareType.Z);
	    case 5: //J
		return new Poly(createShapeJ());//), SquareType.I);, SquareType.J);
	    case 6: //L
		return new Poly(createShapeL());//), SquareType.I);, SquareType.L);
	    default:
		throw new IllegalArgumentException("Invalid index: " + n);
	}
    }

    public SquareType[][] createEmptyShell(int dim){
	// Create dim x dim array
	SquareType[][] polyShell = new SquareType[dim][dim];

	// Fill with EMPTY
	for (int row = 0; row < dim; row++) {
	    for (int col = 0; col < dim; col++) {
		polyShell[col][row] = SquareType.EMPTY;
	    }
	}
	return polyShell;
    }

    public SquareType[][] createShapeI(){
	SquareType[][] shape = createEmptyShell(4);
	shape[1][0] = SquareType.I;
	shape[1][1] = SquareType.I;
	shape[1][2] = SquareType.I;
	shape[1][3] = SquareType.I;
	return shape;
    }

    public SquareType[][] createShapeO(){
	SquareType[][] shape = createEmptyShell(2);
	shape[0][0] = SquareType.O;
	shape[0][1] = SquareType.O;
	shape[1][0] = SquareType.O;
	shape[1][1] = SquareType.O;
	return shape;
    }

    public SquareType[][] createShapeT() {
	SquareType[][] shape = createEmptyShell(3);
	shape[0][1] = SquareType.T;
	shape[1][0] = SquareType.T;
	shape[1][1] = SquareType.T;
	shape[1][2] = SquareType.T;
	return shape;
    }

    public SquareType[][] createShapeS() {
	SquareType[][] shape = createEmptyShell(3);
	shape[0][1] = SquareType.S;
	shape[0][2] = SquareType.S;
	shape[1][0] = SquareType.S;
	shape[1][1] = SquareType.S;
	return shape;
    }

    public SquareType[][] createShapeZ() {
	SquareType[][] shape = createEmptyShell(3);
	shape[0][0] = SquareType.Z;
	shape[0][1] = SquareType.Z;
	shape[1][1] = SquareType.Z;
	shape[1][2] = SquareType.Z;
	return shape;
    }

    public SquareType[][] createShapeJ() {
	SquareType[][] shape = createEmptyShell(3);
	shape[0][0] = SquareType.J;
	shape[1][0] = SquareType.J;
	shape[1][1] = SquareType.J;
	shape[1][2] = SquareType.J;
	return shape;
    }

    public SquareType[][] createShapeL() {
	SquareType[][] shape = createEmptyShell(3);
	shape[0][2] = SquareType.L;
	shape[1][0] = SquareType.L;
	shape[1][1] = SquareType.L;
	shape[1][2] = SquareType.L;
	return shape;
    }
}