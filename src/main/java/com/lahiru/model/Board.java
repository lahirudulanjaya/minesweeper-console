package com.lahiru.model;

import com.lahiru.util.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Minesweeper game board.
 */
public class Board {
    private final int size;
    private final Cell[][] grid;

    public Board(int size){
        // Row labels use single letters A-Z, so the board cannot exceed 26×26
        Preconditions.check(size >= 2 && size <= 26,
                "Size must be between 2 and 26");
        this.size = size;
        this.grid = new Cell[size][size];
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                grid[r][c] = new Cell();
    }

    public int getSize(){
        return size;
    }

    public Cell getCellAt(Position p){
        return grid[p.row()][p.col()];
    }

    /**
     * Returns every valid neighbour of the given {@code center} square.
     * The returned list excludes the centre itself and never contains
     * off-board coordinates.
     */
    public List<Position> getAdjacentPositions(Position center){
        List<Position> neighbours = new ArrayList<>();

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {

                if (rowOffset == 0 && colOffset == 0) {
                    continue;
                }

                int newRow = center.row() + rowOffset;
                int newCol = center.col() + colOffset;

                boolean insideBoard =
                        newRow >= 0 && newRow < size &&
                                newCol >= 0 && newCol < size;

                if (insideBoard) {
                    neighbours.add(new Position(newRow, newCol));
                }
            }
        }
        return neighbours;
    }

    public void setMine(Position p){
        getCellAt(p).setMine(true);
    }

    /**
     * Pre-computes and stores the number of adjacent mines for every
     * non-mine cell on the board. Should be called once after all mines
     * have been placed.
     */
    public void calculateAllAdjacentMineCounts(){
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Position current = new Position(row, col);

                if (getCellAt(current).isMine()) {
                    continue;
                }

                int adjacentMines = countAdjacentMines(current);
                getCellAt(current).setAdjacentMines(adjacentMines);
            }
        }
    }


    private int countAdjacentMines(Position pos){
        int mines = 0;
        for (Position neighbour : getAdjacentPositions(pos)) {
            if (getCellAt(neighbour).isMine()) {
                mines++;
            }
        }
        return mines;
    }
}

