package tetris;

/**
 * This interface is implemented by the classes that defines the different outcomes of a collision.
 * Apart from the method hasCollision() that defines the action, a description is also used.
 */
public interface CollisionHandler
{
    boolean hasCollision(Board board);
    String getDescription();
}
