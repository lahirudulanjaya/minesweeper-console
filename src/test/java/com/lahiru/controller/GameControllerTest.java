package com.lahiru.controller;

import com.lahiru.model.Board;
import com.lahiru.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link GameController}.
 */
class GameControllerTest {

    /** Deterministic placer for repeatable tests: puts a mine at (0,0). */
    private static final class FixedMinePlacer implements MinePlacer {
        @Override
        public void doPlaceMines(Board board, int mineCount) {
            // Always place exactly one mine in the top‑left corner.
            board.setMine(new Position(0, 0));
            board.calculateAllAdjacentMineCounts();
        }
    }

    private GameController controller;

    @BeforeEach
    void setUp() {
        controller = new GameController(2, 1, new FixedMinePlacer());
    }

    @Test
    void reveal_WhenMine_EndsGame() {
        assertEquals(GameController.State.ONGOING, controller.state());

        String feedback = controller.revealCell(new Position(0, 0));

        assertEquals(GameController.State.LOST, controller.state());
        assertTrue(feedback.contains("detonated") || feedback.contains("mine"));
    }

    @Test
    void reveal_WhenAllSafeSquaresRevealed_WinsGame() {
        // Remove the mine first square (0,0) = mine; safe squares are (0,1), (1,0), (1,1)
        controller.revealCell(new Position(0, 1));
        controller.revealCell(new Position(1, 0));
        controller.revealCell(new Position(1, 1));

        assertEquals(GameController.State.WON, controller.state());
    }

    @Test
    void reveal_WhenSquareAlreadyRevealed_ReturnsMessage() {
        Position safe = new Position(0, 1);

        controller.revealCell(safe);
        String again = controller.revealCell(safe);

        assertTrue(again.toLowerCase().contains("already"));
        assertEquals(GameController.State.ONGOING, controller.state());
    }
}
