package tetris;

/**
 * This enum stores the different kind of types the Polys can have, the names(letters) represent the shapes.
 */
public enum SquareType
    // Complains that the enumerated constant names are to short,
    // however, the instructions declared these names.
{
    /**
     * This is the enum-type for an empty square
     */
    EMPTY,
    /**
     * This is the enum-type for a square that is part of an I-shaped square
     */
    I,
    /**
     * This is the enum-type for a square that is part of an O-shaped square
     */
    O,
    /**
     * This is the enum-type for a square that is part of an T-shaped square
     */
    T,
    /**
     * This is the enum-type for a square that is part of an S-shaped square
     */
    S,
    /**
     * This is the enum-type for a square that is part of an Z-shaped square
     */
    Z,
    /**
     * This is the enum-type for a square that is part of an J-shaped square
     */
    J,
    /**
     * This is the enum-type for a square that is part of an L-shaped square
     */
    L,
    /**
     * This is the enum-type for a square that is outside of the visible board
     */
    OUTSIDE
}
