package com.lahiru.view;

import com.lahiru.controller.GameController;
import com.lahiru.model.Position;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Handles user interaction for the Minesweeper game using the command-line interface.
 * <p>
 * Provides methods to prompt for input and print game-related messages to the console.
 */
public class CLIView {
    private final Scanner scanner;
    private final PrintStream out;
    private static final String MSG_WELCOME = "Welcome to Minesweeper!";
    private static final String MSG_GRID_PROMPT = "Enter grid size (4 => 4x4): ";
    private static final String MSG_MINES_PROMPT = "Enter number of mines (≤35% of squares): ";
    private static final String MSG_PLAY_AGAIN = "Play again? (y/n): ";
    private static final String MSG_SELECT_SQUARE = "Select a square (e.g. B3): ";
    static final String MSG_CONGRATULATIONS = "Congratulations, you won!";
    static final String MSG_GAME_OVER = "Game over.";
    private static final String MSG_INVALID_INT = "Please enter a valid integer.";

    private static final String ANSWER_YES = "yes";
    private static final String ANSWER_NO = "no";
    private static final String SHORT_YES = "y";
    private static final String SHORT_NO = "n";
    private static final String MSG_YES_NO_INVALID = "Please answer y/n.";

    public CLIView(){
        this(System.in, System.out);
        out.println(MSG_WELCOME);
    }

    /**
     * Test‑friendly constructor that accepts custom IO streams.
     */
    CLIView(InputStream in, PrintStream out){
        this.scanner = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints the current state of the game board.
     *
     * @param boardAscii ASCII representation of the board
     */
    public void printBoard(String boardAscii){
        out.println("\n" + boardAscii);
    }

    /**
     * Prompts the user to select a position on the board.
     *
     * @param size the size of the board
     * @return the chosen position
     */
    public Position promptForPosition(int size){
        return askPosition(MSG_SELECT_SQUARE, size);
    }

    /**
     * Prints a general message to the console.
     *
     * @param msg the message to display
     */
    public void printMessage(String msg){
        out.println(msg);
    }

    /**
     * Prints a message indicating the end of the game based on the game state.
     *
     * @param s the game state (WON or LOST)
     */
    public void printEndMessage(GameController.State s){
        out.println(s == GameController.State.WON ? MSG_CONGRATULATIONS : MSG_GAME_OVER);
    }

    /**
     * Prompts the user to enter the grid size.
     *
     * @return the entered grid size
     */
    public int promptGridSize(){
        return askInt(MSG_GRID_PROMPT);
    }

    /**
     * Prompts the user to enter the number of mines.
     *
     * @return the entered mine count
     */
    public int promptMineCount(){
        return askInt(MSG_MINES_PROMPT);
    }

    /**
     * Prompts the user to decide whether to play again.
     *
     * @return true if the user wants to play again, false otherwise
     */
    public boolean promptPlayAgain(){
        return promptYesNo(MSG_PLAY_AGAIN);
    }

    private int askInt(String prompt){
        while (true) {
            out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                out.println(MSG_INVALID_INT);
            }
        }
    }

    private Position askPosition(String prompt, int size){
        while (true) {
            out.print(prompt);
            try {
                return Position.parseInputCoordinate(scanner.nextLine().trim(), size);
            } catch (IllegalArgumentException ex) {
                out.println(ex.getMessage());
            }
        }
    }

    private boolean promptYesNo(String prompt){
        while (true) {
            out.print(prompt);
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals(SHORT_YES) || answer.equals(ANSWER_YES)) return true;
            if (answer.equals(SHORT_NO) || answer.equals(ANSWER_NO)) return false;
            out.println(MSG_YES_NO_INVALID);
        }
    }

}
