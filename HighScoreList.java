package tetris;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a singleton that adds and stores instances of Highscores in a list. It also consists of a method
 * that return the Highscores as a String.
 */

final public class HighScoreList
{
    private List<HighScore> highScoreList = new ArrayList<>();
    private static final HighScoreList INSTANCE = new HighScoreList();
    private HighScoreList() {
    }

    public static HighScoreList getInstance() {
        return INSTANCE;
    }

    public void addScore(HighScore score){
        highScoreList.add(score);
    }

    public List<HighScore> getHighScoreList(){
        return highScoreList;
    }

    public String highScoresToString(){
        if(highScoreList == null){return "-";}
        StringBuilder scores = new StringBuilder();
        for (HighScore score:highScoreList
             ) {
            scores.append(score);
            scores.append("\n");
        }
        return scores.toString();
    }
}