import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DynamicProgrammingTest {

    @Test
    public void testRecursiveBino() throws Exception {
        assertEquals(1, Main.bino(1, 1));
    }

    @Test
    public void testRecursiveBino2_1() throws Exception {
        assertEquals(2, Main.bino(2, 1));
    }

    @Test
    public void testRecursiveBino3_1() throws Exception {
        assertEquals(3, Main.bino(3, 1));
    }

    @Test
    public void testRecursiveBino5_2() throws Exception {
        assertEquals(10, Main.bino(5, 2));
    }

    @Test
    public void testRecursiveBino5_3() throws Exception {
        assertEquals(10, Main.bino(5, 3));
    }

    @Test
    public void testRecursiveBino6_3() throws Exception {
        assertEquals(20, Main.bino(6, 3));
    }

}