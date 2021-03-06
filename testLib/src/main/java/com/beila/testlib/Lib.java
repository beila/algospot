package com.beila.testlib;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class Lib {

    static public String getLinedString(String... input) {
        try {
            try (StringWriter stringOutForInput = new StringWriter()) {
                for(String ui: input) {
                    stringOutForInput.write(String.format("%s%n", ui));
                }
                return stringOutForInput.getBuffer().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> void assertDeepEquals(T[] expected, T[] actual) {
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], actual[i]);
        }
    }

    public static void assertDeepEquals(int[] expected, int[] actual) {
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], actual[i]);
        }
    }

}
