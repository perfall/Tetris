package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;

/**
 * This class purpose is to implement the graphic aspects of the game, which is done in regards to the
 * Board instance and and the color map.
 */
public class TetrisComponent extends JComponent implements BoardListener
{
    private Board board;
    private AbstractMap<SquareType, Color> colorMap;

    public TetrisComponent(final Board board, final AbstractMap<SquareType, Color> colorMap) {
	this.board = board;
	this.colorMap = colorMap;
    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	final int rigthSectionX = 420;
	final int rightSectionY1 = 50;
	g2d.drawString(board.currentScoreToString(), rigthSectionX, rightSectionY1);
	final int rightSectionY2 = 150;
	g2d.drawString(board.getCollisionHandler().getDescription(), rigthSectionX, rightSectionY2);
	for (int row = 0; row < board.getHeight(); row++) {
	    for (int col = 0; col < board.getWidth(); col++) {
		SquareType currentType = board.getSquareType(col, row);
		g2d.setColor(colorMap.get(currentType));
		final int squareSize = 50;
		g2d.fillRect(col * squareSize + col, row * squareSize + row, squareSize, squareSize);
	    }
	}
    }

    public Dimension getPreferredSize() {
	return new Dimension(640, 640);
    }

    public void boardChanged() {
	repaint();
    }
}
