package com.lahiru.view;

import com.lahiru.controller.GameController;
import com.lahiru.model.Position;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.lahiru.view.CLIView.MSG_CONGRATULATIONS;
import static com.lahiru.view.CLIView.MSG_GAME_OVER;
import static org.junit.jupiter.api.Assertions.*;

class CLIViewTest {

    @Test
    void printBoard_printsBoardWithNewline() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CLIView view = new CLIView(new ByteArrayInputStream(new byte[0]), new PrintStream(out));
        view.printBoard("Board Text");
        assertTrue(out.toString().contains("\nBoard Text"));
    }

    @Test
    void printMessage_printsGivenMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CLIView view = new CLIView(new ByteArrayInputStream(new byte[0]), new PrintStream(out));
        view.printMessage("Test message");
        assertTrue(out.toString().contains("Test message"));
    }

    @Test
    void printEndMessage_printsWinMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CLIView view = new CLIView(new ByteArrayInputStream(new byte[0]), new PrintStream(out));
        view.printEndMessage(GameController.State.WON);
        assertTrue(out.toString().contains(MSG_CONGRATULATIONS));
    }

    @Test
    void printEndMessage_printsGameOverMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CLIView view = new CLIView(new ByteArrayInputStream(new byte[0]), new PrintStream(out));
        view.printEndMessage(GameController.State.LOST);
        assertTrue(out.toString().contains(MSG_GAME_OVER));
    }

    @Test
    void promptGridSize_readsValidInteger() {
        String input = "5\n";
        CLIView view = new CLIView(new ByteArrayInputStream(input.getBytes()), new PrintStream(new ByteArrayOutputStream()));
        assertEquals(5, view.promptGridSize());
    }

    @Test
    void promptMineCount_readsValidInteger() {
        String input = "3\n";
        CLIView view = new CLIView(new ByteArrayInputStream(input.getBytes()), new PrintStream(new ByteArrayOutputStream()));
        assertEquals(3, view.promptMineCount());
    }

    @Test
    void promptPlayAgain_yesInput_returnsTrue() {
        String input = "y\n";
        CLIView view = new CLIView(new ByteArrayInputStream(input.getBytes()), new PrintStream(new ByteArrayOutputStream()));
        assertTrue(view.promptPlayAgain());
    }

    @Test
    void promptPlayAgain_noInput_returnsFalse() {
        String input = "n\n";
        CLIView view = new CLIView(new ByteArrayInputStream(input.getBytes()), new PrintStream(new ByteArrayOutputStream()));
        assertFalse(view.promptPlayAgain());
    }

    @Test
    void promptForPosition_validInput_returnsCorrectPosition() {
        String input = "B2\n";
        CLIView view = new CLIView(new ByteArrayInputStream(input.getBytes()), new PrintStream(new ByteArrayOutputStream()));
        Position pos = view.promptForPosition(4);
        assertEquals(1, pos.row());
        assertEquals(1, pos.col());
    }
}

