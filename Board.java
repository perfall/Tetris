package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * An instance of this class serves the purpose of representing the main structure and logic of game.
 * It keeps track of the current state of the game, in regards to the positions of the falling polys
 * and the ones that have already been integrated into the board. The class consists of getters, setters,
 * and methods that change the position and rotation of the falling Polys, as well as removing filled rows.
 * It also keeps track of collisions between blocks, score, and finally the tick function, which is called
 * upon on each timestep of the running game.
 */
public class Board
{
    private static final int WALL = 2;
    private static final int START_X = 3;
    private static final int START_Y = 0;

    private SquareType[][] squares;
    private int width;
    private int height;
    private Poly falling=null;
    private int fallingX;
    private int fallingY;
    private Collection<BoardListener> boardListeners = new ArrayList<>();
    private boolean gameOver = false;
    private int score = 0;
    private CollisionHandler collisionHandler;// = new DefaultCollisionHandler();

    public Board(final int height, final int width) {
	this.height = height;
	this.width = width;
	this.collisionHandler = new DefaultCollisionHandler();
	this.squares = new SquareType[width + WALL * 2][height + WALL * 2];
	for (int row = 0; row < height + WALL * 2; row++) {
	    for (int col = 0; col < width + WALL * 2; col++) {
		if (row >= WALL && row < height + WALL && col >= WALL && col < width + WALL) {
		    this.squares[col][row] = SquareType.EMPTY;
		} else {
		    this.squares[col][row] = SquareType.OUTSIDE;
		}
	    }
	}
    }

    public void tick() {
	TetrominoMaker factory = new TetrominoMaker();
	Random rand = new Random();
	if (falling == null) {
	    int randomInt = rand.nextInt(SquareType.values().length - 2);
	    falling = factory.getPoly(randomInt);
	    fallingX = START_X;
	    fallingY = START_Y;
	} else {
	    removeFallingOnBoard();
	    fallingY++;
	}
	if (collisionHandler.hasCollision(this)) {
	    if (fallingX == START_X && fallingY == START_Y) {
		gameOver = true;
		falling = null;
	    } else {
		resetPowerUps();
		fallingY--;
		updateFallingOnBoard();
		falling = null;
		removeFilledRows();
	    }
	} else {
	    updateFallingOnBoard();
	    notifyListeners();
	}
    }

    /**
     * This method modifies the position of the fallen Poly so that it moves one square to the right,
     * if this implies a collision, it will not execute the update.
     */
    public Action moveRight = new AbstractAction()
    {
	public void actionPerformed(ActionEvent e) {
	    if (falling != null) {
		removeFallingOnBoard();
		fallingX++;
		if (checkColl()) {
		    fallingX--;
		}
		updateFallingOnBoard();
		notifyListeners();
	    }
	}
    };

    /**
     * This method modifies the position of the fallen Poly so that it moves one square to the left,
     * if this implies a collision, it will not execute the update.
     */
    public Action moveLeft = new AbstractAction()
    {
	public void actionPerformed(ActionEvent e) {
	    if (falling != null) {
	    removeFallingOnBoard();
	    fallingX--;
	    if (checkColl()) {
		fallingX++;
	    }
	    updateFallingOnBoard();
	    notifyListeners();
	}
	}
    };

    /**
     * This method modifies the position of the fallen Poly so that it rotates one step to the right,
     * if this implies a collision, it will not perform the update.
     */
    public Action rotate = new AbstractAction()
    {
	public void actionPerformed(ActionEvent e) {
	    if (falling != null) {
		removeFallingOnBoard();
		falling = falling.rotate(true);
		if (checkColl()) {
		    falling = falling.rotate(false);
		}
		updateFallingOnBoard();
		notifyListeners();
	    }

	}
    };

    /**
     * This method modifies the position of the fallen Poly so that it moves one square down,
     * if this implies a collision, it will not perform the update.
     */
    public Action fastFall = new AbstractAction()
    {
	public void actionPerformed(ActionEvent e) {
	    if (falling != null) {
		removeFallingOnBoard();
		fallingY++;
		if (checkColl()) {
		    fallingY--;
		}
		notifyListeners();
		updateFallingOnBoard();
	    }
	}
    };

    public boolean isGameOver() {
	return gameOver;
    }

    public String currentScoreToString() {
	return "Score: " + Integer.toString(score);
    }

    public CollisionHandler getCollisionHandler() {
	return collisionHandler;
    }

    public void addBoardListener(BoardListener bl) {
	boardListeners.add(bl);
    }

    public void notifyListeners() {
	for (BoardListener b : boardListeners) {
	    b.boardChanged();
	}
    }

    public void cleanBoard() {
	for (int row = 0; row < height + WALL * 2; row++) {
	    for (int col = 0; col < width + WALL * 2; col++) {
		if (row >= WALL && row < height + WALL && col >= WALL && col < width + WALL) {
		    this.squares[col][row] = SquareType.EMPTY;
		} else {
		    this.squares[col][row] = SquareType.OUTSIDE;
		}
	    }
	}
    }

    public void setScore(final int score) {
	this.score = score;
    }

    public void setGameOver(final boolean gameOver) {
	this.gameOver = gameOver;
    }

    public void setSquareType(int x, int y, SquareType type) {squares[x + WALL][y + WALL] = type; }

    public void setFallingY(final int fallingY) {
	this.fallingY = fallingY;
    }

    public int getScore() {
	return score;
    }

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public int getFallingX() {
	return fallingX;
    }

    public int getFallingY() {
	return fallingY;
    }

    public SquareType getSquareType(int x, int y) {
	return squares[x + WALL][y + WALL];
    }

    public Poly getFalling() {
	return falling;
    }

    private boolean checkColl() {
	return collisionHandler.hasCollision(this);
    }

    private void updateScore(int rowsRemoved) {
	switch (rowsRemoved) {
	    case 0:
		break;
	    case 1: //I
		final int scoreLevel1 = 100;
		score += scoreLevel1;
		this.collisionHandler = new Fallthrough();
		break;
	    case 2: //O
		final int scoreLevel2 = 100;
		score += scoreLevel2;
		this.collisionHandler = new Earthquake();
		break;
	    case 3:
		final int scoreLevel3 = 100;
		score += scoreLevel3;
		this.collisionHandler = new Fallthrough();
		break;
	    case 4:
		final int scoreLevel4 = 100;
		score += scoreLevel4;
		this.collisionHandler = new Earthquake();
		break;
	    default:
		System.out.println("Amount of rows removed not valid.");
	}
    }

    private void updateFallingOnBoard() {
	SquareType[][] shape = falling.getPolyArray();
	for (int row = 0; row < shape.length; row++) {
	    for (int col = 0; col < shape.length; col++) {
		if (shape[row][col] != SquareType.EMPTY) {
		    setSquareType(col + fallingX, row + fallingY, falling.getPolyType());
		}
	    }
	}
	notifyListeners();
    }

    private void removeFallingOnBoard() {
	SquareType[][] shape = falling.getPolyArray();
	for (int row = 0; row < shape.length; row++) {
	    for (int col = 0; col < shape.length; col++) {
		if (shape[row][col] != SquareType.EMPTY) {
		    setSquareType(col + fallingX, row + fallingY, SquareType.EMPTY);
		}
	    }
	}
	notifyListeners();
    }

    private void resetPowerUps() {
	this.collisionHandler = new DefaultCollisionHandler();
    }

    private void removeFilledRows() {

	boolean filled = true;
	int removedRows = 0;
	for (int row = 0; row < height; row++) {
	    for (int col = 0; col < width; col++) {
		if (getSquareType(col, row) == SquareType.EMPTY) {
		    filled = false;
		}
	    }
	    if (filled) {
		removeRow(row);
		removedRows++;
	    }
	    filled = true;
	}
	updateScore(removedRows);
    }

    private void removeRow(int rowIndex) {
	for (int row = rowIndex; row > 0; row--) {
	    for (int col = 0; col < width; col++) {
		setSquareType(col, row, getSquareType(col, row - 1));
	    }
	}
	notifyListeners();
    }

}
