package com.lahiru.model;


import com.lahiru.util.Preconditions;

/**
 * Represents a position on the Minesweeper board using row and column indices.
 */
public record Position(int row, int col) {
    private static final int A_CODE = 'A';
    private static final String COORDINATE_PATTERN = "^[A-Za-z]\\d+$";
    private static final String ERR_INVALID_FORMAT = "Invalid coordinate format. Use e.g. A1";
    private static final String ERR_OUT_OF_BOUNDS = "Coordinate outside board";

    /**
     * Parses a human-readable coordinate (e.g., "B2") into a Position object.
     *
     * @param input the coordinate string (e.g., "A1")
     * @param size  the size of the board to validate bounds
     * @return the corresponding Position
     * @throws IllegalArgumentException if the format is invalid or out of bounds
     */
    public static Position parseInputCoordinate(String input, int size){
        Preconditions.check(input.matches(COORDINATE_PATTERN),
                ERR_INVALID_FORMAT);
        int r = Character.toUpperCase(input.charAt(0)) - A_CODE;
        int c = Integer.parseInt(input.substring(1)) - 1;
        Preconditions.check(r >= 0 && r < size && c >= 0 && c < size,
                ERR_OUT_OF_BOUNDS);
        return new Position(r, c);
    }
}

