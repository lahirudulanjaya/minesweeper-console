
package com.lahiru.util;


/**
 * Utility class for checking preconditions and throwing exceptions when violated.
 */
public final class Preconditions {
    private Preconditions(){
    }

    /**
     * Checks that the specified condition is true.
     * <p>
     * If the condition is false, throws an IllegalArgumentException with the given message.
     *
     * @param condition the condition to check
     * @param msg       the message for the exception if the condition is false
     * @throws IllegalArgumentException if the condition is false
     */
    public static void check(boolean condition, String msg){
        if (!condition) throw new IllegalArgumentException(msg);
    }
}

