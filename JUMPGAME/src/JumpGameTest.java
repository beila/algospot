import com.beila.testlib.Lib;
import com.beila.testlib.StringArrayInputOutput;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public abstract class JumpGameTest {
    @Rule
    public Timeout globalTimeout = new Timeout(3000);

    Main game;

    @Test
    public void testJumpGameFirstGivenExample() throws Exception {
        int[][] input = new int[][] {
                {2, 5, 1, 6, 1, 4, 1},
                {6, 1, 1, 2, 2, 9, 3},
                {7, 2, 3, 2, 1, 3, 1},
                {1, 1, 3, 1, 7, 1, 2},
                {4, 1, 2, 3, 4, 1, 2},
                {3, 3, 1, 2, 3, 4, 1},
                {1, 5, 2, 9, 4, 7, 0}
        };
        assertEquals(true, game.jumpGame(input));
    }

    @Test
    public void testJumpGameSecondGivenExample() throws Exception {
        int[][] input = new int[][] {
                {2, 5, 1, 6, 1, 4, 1},
                {6, 1, 1, 2, 2, 9, 3},
                {7, 2, 3, 2, 1, 3, 1},
                {1, 1, 3, 1, 7, 1, 2},
                {4, 1, 2, 3, 4, 1, 3},
                {3, 3, 1, 2, 3, 4, 1},
                {1, 5, 2, 9, 4, 7, 0}
        };
        assertEquals(false, game.jumpGame(input));
    }

    @Test
    public void testJumpGameAllOneInARow() throws Exception {
        int[][] input = new int[][] {
                {1, 1, 1, 1, 1, 1, 0},
        };
        assertEquals(true, game.jumpGame(input));
    }

    @Test
    public void testJumpGameAllTwo() throws Exception {
        int[][] input = new int[][] {
                {2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 0},
        };
        assertEquals(true, game.jumpGame(input));
    }

    @Test
    public void testJumpGameAllTwoOddly() throws Exception {
        int[][] input = new int[][] {
                {2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 0},
        };
        assertEquals(false, game.jumpGame(input));
    }

    @Test
    public void testJumpGameLongTaking() throws Exception {
        int[][] input = new int[100][100];
        Arrays.stream(input).forEach(row -> Arrays.fill(row, 1));
        input[99][99] = 0;
        input[98][99] = 2;
        input[99][98] = 2;
        for (int i = 0; i < 50; ++i)
            assertEquals(false, game.jumpGame(input));
    }

    @Test
    public void testInputExample() throws Exception {
        String[] input = new String[] {
                "2",
                "7",
                "2 5 1 6 1 4 1",
                "6 1 1 2 2 9 3",
                "7 2 3 2 1 3 1",
                "1 1 3 1 7 1 2",
                "4 1 2 3 4 1 2",
                "3 3 1 2 3 4 1",
                "1 5 2 9 4 7 0",
                "7",
                "2 5 1 6 1 4 1",
                "6 1 1 2 2 9 3",
                "7 2 3 2 1 3 1",
                "1 1 3 1 7 1 2",
                "4 1 2 3 4 1 3",
                "3 3 1 2 3 4 1",
                "1 5 2 9 4 7 0"
        };
        String[] output = new String[] {
                "YES",
                "NO"
        };
        assertMain(input, output);
    }

    void assertMain(String[] input, String[] output) throws IOException {
        try (StringArrayInputOutput io = new StringArrayInputOutput(input)) {
            Main.main(game, io.getInputStream(), io.getPrintStream());
            Lib.assertDeepEquals(output, io.toStringArray());
        }
    }

}