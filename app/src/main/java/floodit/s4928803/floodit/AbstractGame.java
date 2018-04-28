package floodit.s4928803.floodit;

import java.util.Random;

/**
 * Base class for the Flood It game. You can either extend this class (and keep everything clean) or
 * adapt it to no longer be abstract. It provides a framework for your implementation of the
 * assignment. You are able to change this class as you see fit, but remember that it is intended to
 * just contain game logic, not any Android related code.
 */

public abstract class AbstractGame {

    /**
     * The default amount of columns in the game (horizontal "pixel" amount).
     */
    static int DEFAULT_COLUMNS = 15;

    /**
     * The default amount of rows in the game (vertical "pixel" amount)
     */
    static int DEFAULT_ROWS = 15;

    /**
     * The actual amount of columns in the game. This means there are mColumns*mRows cells.
     */
    private final int mColumns;

    /**
     * The actual amount of rows in the game. This means there are mColumns*mRows cells.
     */
    private final int mRows;

    final int[][] mData;

    /**
     * Creates an array of codes to be assigned to colours for the grid
     */
    Random rand = new Random();
    int[] colCode = {0, 1, 2, 3, 4, 5};


    AbstractGame(final int width, final int height) {
        mColumns = width;
        mRows = height;
        mData = new int[width][height];
    }

    /**
     * @param column The column
     * @param row The row
     * @return the column and row of the mData array
     */
    int GridCoord(int column, int row) {
        return mData[column][row];
    }

    /**
     * The amount of columns in the game.
     *
     * @return the column count.
     */
    int getWidth() {
        return mColumns;
    }

    /**
     * The amount of rows in the game.
     *
     * @return The row count.
     */
    int getHeight() {
        return mRows;
    }


    /**
     * Implement this function to return the current game round (starting with 1, every flood
     * operation updates the round.
     *
     * @return The current round
     */
    public abstract int getRound();

    /**
     * Set the colour at position (x,y) to the colour identified by the colour parameter
     *
     * @param x      The column to change
     * @param y      The row to change
     * @param colour The new colour.
     */
    protected abstract void setColor(int x, int y, int colour);

    abstract void playColour(int colour);

    public abstract int getColor(int x, int y);

    /**
     * Determine whether the game has been won.
     *
     * @return <code>true</code> if won, <code>false</code> if the game has not yet been won.
     */
    public abstract boolean isWon();
}