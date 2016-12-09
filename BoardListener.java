package tetris;

/**
 * This interface defines the method boardChanged() which is later implemented in classes that shall be modified
 * when the board is modified.
 */
public interface BoardListener
{
    void boardChanged();
}
