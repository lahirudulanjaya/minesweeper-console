package com.lahiru.controller;


import com.lahiru.model.Board;
import com.lahiru.model.Cell;
import com.lahiru.model.Position;
import com.lahiru.util.Preconditions;


/**
 * Handles the core logic of a Minesweeper game session.
 */
public class GameController {
    private static final String MSG_ALREADY_REVEALED = "Already revealed.";
    private static final String MSG_MINE_DETONATED = "Oh no, you detonated a mine!";
    private static final String MSG_SAFE = "Safe.";
    private static final String MSG_GAME_ALREADY_FINISHED = "Game finished, start a new one";

    public enum State {ONGOING, WON, LOST}

    private final Board board;
    private final SafeRegionRevealer filler = new SafeRegionRevealer();
    private State state = State.ONGOING;

    public GameController(int size, int mines, MinePlacer placer){
        this.board = new Board(size);
        placer.placeMines(board, mines);
    }

    public Board board(){
        return board;
    }

    public State state(){
        return state;
    }

    /**
     * Reveals the cell at the given board position.
     * <p>
     * If the cell is already revealed, returns a message indicating so.
     * If it is a mine, reveals the cell, marks the game as lost, and returns a failure message.
     * If it is safe, reveals a region and checks for a win.
     *
     * @param position the board position to reveal
     * @return feedback message indicating the result of the action
     * @throws IllegalStateException if the game is already finished
     */
    public String revealCell(Position position){
        Preconditions.check(state == State.ONGOING,
                MSG_GAME_ALREADY_FINISHED);
        Cell cell = board.getCellAt(position);
        if (cell.isRevealed()) return MSG_ALREADY_REVEALED;
        if (cell.isMine()) {
            cell.reveal();
            state = State.LOST;
            return MSG_MINE_DETONATED;
        }
        filler.revealRegion(board, position);
        if (checkWin()) state = State.WON;
        return MSG_SAFE;
    }


    private boolean checkWin(){
        for (int r = 0; r < board.getSize(); r++)
            for (int c = 0; c < board.getSize(); c++) {
                Cell cell = board.getCellAt(new Position(r, c));
                if (!cell.isMine() && !cell.isRevealed())
                    return false;
            }
        return true;
    }
}
