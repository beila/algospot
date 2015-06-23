import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class WordCountRegionTest {
    Main main = new Main();

    @Before
    public void setUp() throws Exception {
        main = new Main();
    }

    @Test
    public void testFirstExample() throws Exception {
        assertEquals(6, main.solve(new int[]{1, 2, 1, 1, 1, 3, 1, 1, 3, 2}, new int[]{3, 5, 6, 7, 8, 10, 18, 19, 25, 30}, new int[]{0, 2, 1, 1}));
    }

    @Test
    public void testSecondExample() throws Exception {
        assertEquals(-1, main.solve(new int[]{1, 2, 2}, new int[]{1, 3, 5}, new int[]{0, 2, 1}));
    }

    @Test
    public void testFirstSmallInput() throws Exception {
        Scanner problemScanner = new Scanner(new File("wordCountRegion/data/p1-s01.in"));
        Scanner solutionScanner = new Scanner(new File("wordCountRegion/data/p1-s01.out"));
        int cases = problemScanner.nextInt();

        while (--cases > 0) {
            main.inputOneCase(problemScanner);
            assertEquals(solutionScanner.nextInt(), main.solve());
        }
    }

    @Test
    public void testSecondSmallInput() throws Exception {
        Scanner problemScanner = new Scanner(new File("wordCountRegion/data/p1-s02.in"));
        Scanner solutionScanner = new Scanner(new File("wordCountRegion/data/p1-s02.out"));
        int cases = problemScanner.nextInt();

        while (--cases > 0) {
            main.inputOneCase(problemScanner);
            assertEquals(solutionScanner.nextInt(), main.solve());
        }
    }

    @Test
    public void testThirdSmallInput() throws Exception {
        Scanner problemScanner = new Scanner(new File("wordCountRegion/data/p1-s03.in"));
        Scanner solutionScanner = new Scanner(new File("wordCountRegion/data/p1-s03.out"));
        int cases = problemScanner.nextInt();

        while (--cases > 0) {
            main.inputOneCase(problemScanner);
            assertEquals(solutionScanner.nextInt(), main.solve());
        }
    }
}