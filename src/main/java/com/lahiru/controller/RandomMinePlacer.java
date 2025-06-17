package com.lahiru.controller;

import com.lahiru.model.Board;
import com.lahiru.model.Position;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * A mine placement strategy that randomly places mines using a provided {@link Random} generator.
 */
public class RandomMinePlacer implements MinePlacer {
    private final Random rng;

    /**
     * Constructs a RandomMinePlacer using a cryptographically strong random number generator.
     */
    public RandomMinePlacer(){
        this(new SecureRandom());
    }

    /**
     * Constructs a RandomMinePlacer using the given random number generator.
     *
     * @param rng the random number generator to use for mine placement
     */
    public RandomMinePlacer(Random rng){
        this.rng = rng;
    }

    /**
     * Performs mine placement on the board.
     * Called internally after validation by {@link #placeMines(Board, int)}.
     *
     * @param board     the game board
     * @param mineCount number of mines to place
     */
    @Override
    public void doPlaceMines(Board board, int mineCount){
        Set<Position> mines = new HashSet<>();
        int size = board.getSize();
        while (mines.size() < mineCount) {
            Position p = new Position(rng.nextInt(size), rng.nextInt(size));
            mines.add(p);
        }
        mines.forEach(board::setMine);
        board.calculateAllAdjacentMineCounts();
    }

}
