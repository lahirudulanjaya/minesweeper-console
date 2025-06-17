package com.lahiru.controller;

import com.lahiru.model.Board;
import com.lahiru.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SafeRegionRevealerTest {

    /** Helper that returns a fresh 3×3 board with the given mine positions set. */
    private Board boardWithMines(Position... mines) {
        Board b = new Board(3);
        for (Position p : mines) {
            b.setMine(p);
        }
        b.calculateAllAdjacentMineCounts();
        return b;
    }

    @Test
    void revealRegion_whenStartIsZero_revealsZeroRegion() {
        Board board = boardWithMines(new Position(2, 2));
        SafeRegionRevealer revealer = new SafeRegionRevealer();

        revealer.revealRegion(board, new Position(0, 0));

        int revealed = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.getCellAt(new Position(r, c)).isRevealed()) {
                    revealed++;
                }
            }
        }
        assertEquals(8, revealed);           // 9 squares - 1 mine
        assertFalse(board.getCellAt(new Position(2, 2)).isRevealed());
    }

    @Test
    void revealRegion_whenStartHasAdjacentMines_revealsOnlyStart() {
        Board board = boardWithMines(new Position(0, 1));
        SafeRegionRevealer revealer = new SafeRegionRevealer();

        revealer.revealRegion(board, new Position(0, 0));

        assertTrue(board.getCellAt(new Position(0, 0)).isRevealed());
        assertFalse(board.getCellAt(new Position(1, 1)).isRevealed());
    }

    @Test
    void revealRegion_whenStartIsMine_doesNotRevealSafeCells() {
        Board board = boardWithMines(new Position(1, 1));
        SafeRegionRevealer revealer = new SafeRegionRevealer();

        revealer.revealRegion(board, new Position(1, 1));

        assertFalse(board.getCellAt(new Position(0, 0)).isRevealed());
        assertFalse(board.getCellAt(new Position(2, 2)).isRevealed());
    }

    @Test
    void revealRegion_whenStartAlreadyRevealed_noAdditionalReveal() {
        Board board = boardWithMines(); // empty board (all zeros)
        SafeRegionRevealer revealer = new SafeRegionRevealer();

        Position start = new Position(0, 0);
        revealer.revealRegion(board, start);          // first reveal
        int revealedAfterFirst = countRevealed(board);

        revealer.revealRegion(board, start);          // reveal again
        int revealedAfterSecond = countRevealed(board);

        assertEquals(revealedAfterFirst, revealedAfterSecond);
    }

    /** Counts how many cells are currently marked revealed. */
    private int countRevealed(Board b) {
        int count = 0;
        for (int r = 0; r < b.getSize(); r++)
            for (int c = 0; c < b.getSize(); c++)
                if (b.getCellAt(new Position(r, c)).isRevealed())
                    count++;
        return count;
    }
}
