package com.hojin;

import java.io.IOException;

/**
 * http://algospot.com/judge/problem/read/HELLOWORLD
 */
public class Main {

    public static void main(String[] args) throws IOException {
        byte[] input = new byte[10];
        //noinspection ResultOfMethodCallIgnored
        System.in.read(input);
        final int round = parseInt(input);
        for (int i = 0; i < round; ++i) {
            //noinspection ResultOfMethodCallIgnored
            System.in.read(input);
            System.out.println(new Main().mainOne(new String(input)));
        }
    }

    private static int parseInt(byte[] input) {
        return Integer.valueOf(new String(input));
    }

    public String mainOne(String arg) {
        return "Hello, " + arg + "!";
    }
}
