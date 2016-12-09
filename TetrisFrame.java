package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.EnumMap;

/**
 * This class extends JFrame and an instance of it builds the frame that consists of every visible element in the game.
 */
public class TetrisFrame extends JFrame
{
    private Board board;
    private TetrisComponent component;
    private EnumMap<SquareType, Color> colorMap = new EnumMap<>(SquareType.class);
    private JFrame graphicArea;

    public TetrisFrame(final String title, final Board board) throws HeadlessException {
	super(title);
	this.board = board;
	setColors();
	setComponentProperties();
	JMenuBar menuBar = createMenus();
	graphicArea = new JFrame();
	graphicArea.getPreferredSize();
	graphicArea.add(component);
	graphicArea.setJMenuBar(menuBar);
	graphicArea.pack();
	graphicArea.setVisible(true);
    }

    public BoardListener getComponent() {
	return component;
    }

    // Parameter e is an essential for ActionEvent and cannot be removed.
    public void exit(ActionEvent e) {
	int answer =
		JOptionPane.showConfirmDialog(component, "Do you really want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
	if (answer == JOptionPane.YES_OPTION) {
	    System.exit(0); // Exits game
	}
    }

    public void gameOverWindow() {
	String name = JOptionPane.showInputDialog(null, "Well done! " + board.currentScoreToString() + "\nWhat is your name?");
	HighScoreList scoreList = HighScoreList.getInstance();
	HighScore currentScore = new HighScore(board.getScore(), name);
	scoreList.addScore(currentScore);
	Collections.sort(scoreList.getHighScoreList(), new ScoreComparator());
	JOptionPane.showMessageDialog(component, "Highscores: \n" + scoreList.highScoresToString());
    }

    private JMenuBar createMenus() {
	final JMenuBar menuBar = new JMenuBar();
	final JMenu file = new JMenu("File");
	final JMenuItem exitButton = new JMenuItem("Exit");
	exitButton.addActionListener(this::exit);
	file.add(exitButton);
	menuBar.add(file);
	return menuBar;
    }

    private void setColors() {
	colorMap.put(SquareType.EMPTY, Color.WHITE);
	colorMap.put(SquareType.I, Color.RED);
	colorMap.put(SquareType.O, Color.GREEN);
	colorMap.put(SquareType.T, Color.YELLOW);
	colorMap.put(SquareType.S, Color.GRAY);
	colorMap.put(SquareType.Z, Color.BLUE);
	colorMap.put(SquareType.J, Color.BLACK);
	colorMap.put(SquareType.L, Color.CYAN);
    }

    private void setComponentProperties(){
	component = new TetrisComponent(board, colorMap);
	component.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
	component.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
	component.getInputMap().put(KeyStroke.getKeyStroke("UP"), "rotate");
	component.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "fastFall");
	component.getActionMap().put("moveRight", board.moveRight);
	component.getActionMap().put("moveLeft", board.moveLeft);
	component.getActionMap().put("rotate", board.rotate);
	component.getActionMap().put("fastFall", board.fastFall);
    }
}


