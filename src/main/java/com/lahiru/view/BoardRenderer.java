package com.lahiru.view;


import com.lahiru.model.Board;
import com.lahiru.model.Cell;
import com.lahiru.model.Position;

/**
 * Builds an ASCII representation of the board for the console view.
 */
public final class BoardRenderer {
    private static final String CELL_SEPARATOR = " ";
    private static final String NEWLINE = "\n";
    private static final char UNICODE_A = 'A';

    private BoardRenderer(){
    }

    /**
     * Builds an easy‑to‑read text representation of the current board.
     *
     * <ul>
     *   <li>If {@code showAll} is {@code true} we draw every square:
     *       mines as <code>*</code> and safe squares with their adjacent‑mine number.</li>
     *   <li>If {@code showAll} is {@code false} we keep hidden squares as
     *       an underscore (<code>_</code>), just like the player sees during play.</li>
     * </ul>
     *
     * @param board   the board to display
     * @param showAll whether to reveal everything
     * @return a multi‑line String ready to print to the console
     */
    public static String renderBoard(Board board, boolean showAll){
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int col = 1; col <= board.getSize(); col++) sb.append(col).append(CELL_SEPARATOR);
        sb.append(NEWLINE);
        for (int row = 0; row < board.getSize(); row++) {
            sb.append((char) (UNICODE_A + row)).append(CELL_SEPARATOR);
            for (int col = 0; col < board.getSize(); col++) {
                Cell cell = board.getCellAt(new Position(row, col));
                if (showAll) {
                    sb.append(cell.isMine() ? "*" : cell.adjacentMines()).append(CELL_SEPARATOR);
                } else {
                    sb.append(cell).append(CELL_SEPARATOR);
                }
            }
            sb.append(NEWLINE);
        }
        return sb.toString();
    }
}
