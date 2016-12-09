package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This class initialises the game, it uses a Timer to simulate timesteps, within these timesteps the tick() method
 * in Board is called upon.
 */
public final class GraphicBoardTest
{
    private static final int HEIGHT = 12;
    private static final int WIDTH = 8;
    private static final int TIME_STEP = 400;

    private GraphicBoardTest() {} // Utility class needs a private constructor

    private static void resetBoard(Board board){
	board.cleanBoard();
	board.setGameOver(false);
	board.setScore(0);
	board.notifyListeners();
    }

    public static void main(String[] args) {

	final Board testBoard = new Board(HEIGHT, WIDTH);
	TetrisFrame testFrame = new TetrisFrame("Test1", testBoard);
	testBoard.addBoardListener(testFrame.getComponent());
	final Action doOneStep = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e) {
		if (testBoard.isGameOver()) {
		    testFrame.gameOverWindow();
		    resetBoard(testBoard);
		}
		testBoard.tick();
		testBoard.notifyListeners();
	    }
	};
	final Timer clockTimer = new Timer(TIME_STEP, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }
}
