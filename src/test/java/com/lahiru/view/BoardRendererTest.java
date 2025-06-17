package com.lahiru.view;

import com.lahiru.model.Board;
import com.lahiru.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link BoardRenderer}.
 * Naming: method_condition_expectedResult
 */
class BoardRendererTest {

    @Test
    void renderBoard_whenHidden_showsUnderscores() {
        Board board = new Board(2);
        board.setMine(new Position(0, 0));
        board.calculateAllAdjacentMineCounts();

        String ascii = BoardRenderer.renderBoard(board, false);

        assertTrue(ascii.contains("_ _"), "Hidden mode should show underscores for unrevealed squares");
    }

    @Test
    void renderBoard_whenShowAll_displaysMines() {
        Board board = new Board(2);
        board.setMine(new Position(0, 0));
        board.calculateAllAdjacentMineCounts();

        String ascii = BoardRenderer.renderBoard(board, true);

        assertTrue(ascii.contains("*"), "ShowAll mode should reveal mines");
    }

    @Test
    void renderBoard_whenShowAll_displaysNumbers() {
        Board board = new Board(2);
        board.setMine(new Position(0, 0));
        board.calculateAllAdjacentMineCounts();

        String ascii = BoardRenderer.renderBoard(board, true);

        assertTrue(ascii.contains("1") || ascii.contains("2"),
                "ShowAll mode should reveal adjacent mine numbers");
    }
}
