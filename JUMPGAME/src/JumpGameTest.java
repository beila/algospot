import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JumpGameTest {

    private Main game;

    @Before
    public void setUp() throws Exception {
        game = new Main.RecursiveJumpGame();
    }

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

}