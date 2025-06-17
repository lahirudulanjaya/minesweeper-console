package com.lahiru.controller;


import com.lahiru.model.Board;
import com.lahiru.util.Preconditions;

/**
 * Strategy interface for placing mines on a Minesweeper board.
 * <p>
 * Provides validation logic and a template method for safely placing a limited number of mines.
 */
public interface MinePlacer {
    double DEFAULT_MAX_PERCENT = 0.35;

    String MSG_MINE_COUNT_RANGE = "Mine count must be between 1 and %d";

    /**
     * Template method that validates input before delegating to the specific placement strategy.
     *
     * @param board     the game board to place mines on
     * @param mineCount the number of mines to place
     * @throws IllegalArgumentException if mine count exceeds the allowed maximum
     */
    default void placeMines(Board board, int mineCount){
        validate(board, mineCount);
        doPlaceMines(board, mineCount);
    }

    /**
     * Strategy-specific logic to place the given number of mines on the board.
     *
     * @param board     the game board
     * @param mineCount number of mines to place
     */
    void doPlaceMines(Board board, int mineCount);

    /**
     * Validates that the requested mine count is within the acceptable range.
     *
     * @param board the game board
     * @param count the requested number of mines
     * @throws IllegalArgumentException if count is out of range
     */
    default void validate(Board board, int count){
        int max = (int) Math.floor(board.getSize() * board.getSize() * DEFAULT_MAX_PERCENT);
        Preconditions.check(count > 0 && count <= max,
                MSG_MINE_COUNT_RANGE.formatted(max));
    }
}
