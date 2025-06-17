package com.lahiru.controller;

import com.lahiru.model.Board;
import com.lahiru.model.Cell;
import com.lahiru.model.Position;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Handles revealing all connected cells starting from a given position that have zero adjacent mines.
 */
public class SafeRegionRevealer {
    /**
     * Reveals a region of adjacent non-mine, unrevealed cells starting from the given position.
     * If a cell has zero adjacent mines, its neighbors are recursively revealed.
     *
     * @param board the game board
     * @param start the starting position for the region reveal
     */
    public void revealRegion(Board board, Position start){
        Deque<Position> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            Position p = stack.pop();
            Cell cell = board.getCellAt(p);
            if (cell.isRevealed() || cell.isMine()) continue;
            cell.reveal();
            if (cell.adjacentMines() == 0)
                board.getAdjacentPositions(p).forEach(stack::push);
        }
    }
}
