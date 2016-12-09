package tetris;

import java.util.Comparator;

/**
 * This class creates a type of comparator, that is used to sort instances of HighScore.
 */
public class ScoreComparator implements Comparator<HighScore>
{
    public int compare(HighScore o1, HighScore o2) {
        if(o1.getScore() < o2.getScore()){
            return 1;
        }
        else if(o1.getScore() > o2.getScore()){
            return -1;
        }
        else{
            return 0;
        }
    }
}