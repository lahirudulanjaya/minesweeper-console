package com.lahiru.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    void parseInputCoordinate_validCoordinate_returnsCorrectPosition() {
        Position pos = Position.parseInputCoordinate("A1", 4);
        assertEquals(0, pos.row());
        assertEquals(0, pos.col());

        pos = Position.parseInputCoordinate("D4", 4);
        assertEquals(3, pos.row());
        assertEquals(3, pos.col());
    }

    @Test
    void parseInputCoordinate_invalidFormat_throwsException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                Position.parseInputCoordinate("11", 4));
        assertTrue(ex.getMessage().contains("Invalid coordinate format"));
    }

    @Test
    void parseInputCoordinate_outOfBounds_throwsException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                Position.parseInputCoordinate("Z9", 4));
        assertTrue(ex.getMessage().contains("Coordinate outside board"));
    }

}
