import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DynamicProgrammingTest {
    private Main binomial;

    @Before
    public void setUp() throws Exception {
        binomial = new Main.RecursiveBinomial();
//        binomial = new Main.MemoizedBinomial();
    }

    @Test
    public void testRecursiveBino() throws Exception {
        assertEquals(1, binomial.bino(1, 1));
    }

    @Test
    public void testRecursiveBino2_1() throws Exception {
        assertEquals(2, binomial.bino(2, 1));
    }

    @Test
    public void testRecursiveBino3_1() throws Exception {
        assertEquals(3, binomial.bino(3, 1));
    }

    @Test
    public void testRecursiveBino5_2() throws Exception {
        assertEquals(10, binomial.bino(5, 2));
    }

    @Test
    public void testRecursiveBino5_3() throws Exception {
        assertEquals(10, binomial.bino(5, 3));
    }

    @Test
    public void testRecursiveBino6_3() throws Exception {
        assertEquals(20, binomial.bino(6, 3));
    }

    @Test
    public void testRecursiveBino29_15() throws Exception {
        assertEquals(77558760, new Main.RecursiveBinomial().bino(29, 15));
    }

    @Test
    public void testMemoizedBino29_15() throws Exception {
        assertEquals(77558760, new Main.MemoizedBinomial().bino(29, 15));
    }

}