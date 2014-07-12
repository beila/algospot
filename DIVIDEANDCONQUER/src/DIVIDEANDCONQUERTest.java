import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DivideAndConquerTest {

    @Test
    public void testFastSum() throws Exception {
        assertEquals(55, Main.fastSum(10));
    }

    @Test
    public void testFirstPower() throws Exception {
        Main.SquareMatrix a = new Main.SquareMatrix(new double[][]{{1,2},{3,4}});
        assertEquals(a, Main.SquareMatrix.power(a, 1));
    }

    @Test
    public void testSecondPower() throws Exception {
        Main.SquareMatrix a = new Main.SquareMatrix(new double[][]{{1,2},{3,4}});
        Main.SquareMatrix expected = new Main.SquareMatrix(new double[][]{{7, 10}, {15, 22}});
        assertEquals(expected, Main.SquareMatrix.power(a, 2));
    }

    @Test
    public void testThirdPower() throws Exception {
        Main.SquareMatrix a = new Main.SquareMatrix(new double[][]{{1,2},{3,4}});
        Main.SquareMatrix expected = new Main.SquareMatrix(new double[][]{{37, 54}, {81, 118}});
        assertEquals(expected, Main.SquareMatrix.power(a, 3));
    }

}