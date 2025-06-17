package com.lahiru.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BoardTest {

    @Test
    void getAdjacentPositions_whenCorner_returns3Neighbours() {
        Board board = new Board(4);

        int neighbourCount = board.getAdjacentPositions(new Position(0, 0)).size();

        assertEquals(3, neighbourCount, "Top‑left corner should have exactly 3 neighbours");
    }

    @Test
    void setMine_whenCalled_marksCellAsMine() {
        Board board = new Board(3);
        Position minePos = new Position(1, 1);

        board.setMine(minePos);

        assertTrue(board.getCellAt(minePos).isMine());
    }

    @Test
    void calculateAllAdjacentMineCounts_whenSingleMine_countsCorrectly() {
        Board board = new Board(2);
        Position mine = new Position(0, 0);
        board.setMine(mine);

        board.calculateAllAdjacentMineCounts();

        assertEquals(1, board.getCellAt(new Position(0,1)).adjacentMines());
    }
}
