package tetris;

/**
 * This class can be used to create an instance of a highscore that consists of a name and the corresponding score.
 * These are later used when vizualizing the highscorelist.
 */
public class HighScore
{
    private int score;
    private String name;

    public HighScore(final int score, final String name) {
	this.score = score;
	this.name = name;
    }

    public int getScore() {
	return score;
    }

    @Override public String toString() {
	return name +" - " + score;
    }

}
