import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordCountRegionTest {
    Main main = new Main();

    @Before
    public void setUp() throws Exception {
        main = new Main();
    }

    @Test
    public void testFirstExample() throws Exception {
        assertEquals(6, main.solve(new int[]{1, 2, 1, 1, 1, 3, 1, 1, 3, 2}, new int[]{3, 5, 6, 7, 8, 10, 18, 19, 25, 30}, new int[]{0, 2,1,1}));
    }

    @Test
    public void testSecondExample() throws Exception {
        assertEquals(-1, main.solve(new int[]{1, 2, 2}, new int[]{1, 3, 5}, new int[]{0, 2, 1}));
    }
}