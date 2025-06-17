package com.lahiru.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PreconditionsTest {

    @Test
    void check_conditionTrue_doesNotThrow() {
        assertDoesNotThrow(() -> Preconditions.check(5 > 1, "Should not throw"));
    }

    @Test
    void check_conditionFalse_throwsIllegalArgumentException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                Preconditions.check(false, "Error occurred"));
        assertEquals("Error occurred", ex.getMessage());
    }
}
