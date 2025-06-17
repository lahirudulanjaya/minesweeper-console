package com.lahiru.controller;

import com.lahiru.model.Board;
import com.lahiru.model.Position;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class RandomMinePlacerTest {

    @Test
    void placeMines_withValidCount_placesExactNumberOfDistinctMines() {
        int boardSize = 5;
        int mineCount = 5;

        Board board = new Board(boardSize);
        RandomMinePlacer placer = new RandomMinePlacer(new Random(42));

        placer.placeMines(board, mineCount);

        int actualMines = countMines(board);
        assertEquals(mineCount, actualMines, "Should place the requested number of mines");

        // ensure uniqueness
        Set<Position> minePositions = collectMinePositions(board);
        assertEquals(mineCount, minePositions.size(), "Mines should be in unique positions");
    }

    @Test
    void placeMines_whenCountExceedsLimit_throwsException() {
        int boardSize = 4;
        Board board = new Board(boardSize);
        RandomMinePlacer placer = new RandomMinePlacer(new Random(1));

        int illegalCount = (int) Math.ceil(boardSize * boardSize * 0.4); // > 35%

        assertThrows(IllegalArgumentException.class,
                () -> placer.placeMines(board, illegalCount));
    }


    private int countMines(Board b) {
        int mines = 0;
        for (int r = 0; r < b.getSize(); r++)
            for (int c = 0; c < b.getSize(); c++)
                if (b.getCellAt(new Position(r, c)).isMine())
                    mines++;
        return mines;
    }

    private Set<Position> collectMinePositions(Board b) {
        Set<Position> set = new HashSet<>();
        for (int r = 0; r < b.getSize(); r++)
            for (int c = 0; c < b.getSize(); c++) {
                Position p = new Position(r, c);
                if (b.getCellAt(p).isMine()) set.add(p);
            }
        return set;
    }
}
