import com.beila.testlib.Lib;
import com.beila.testlib.StringArrayInputOutput;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;

/**
 * http://algospot.com/judge/problem/read/BOARDCOVER
 * Created by hojin.ghim on 3/15/14.
 */
public class BoardCoverTest {
    @Rule public Timeout globalTimeout = new Timeout(2000);

/*
    static final private boolean t = true;
    static final private boolean f = false;

    @Test
    public void testInit() throws Exception {
        new Main(new boolean[][]{{f, t}, {t, t}});
    }

    @Test
    public void testSimplest() throws Exception {
        Main main = new Main(new boolean[][]{{f, t}, {t, t}});
        Assert.assertEquals(1, main.countLayoutCases());
    }

    @Test
    public void testFourSlots() throws Exception {
        Main main = new Main(new boolean[][]{{t, t}, {t, t}});
        Assert.assertEquals(0, main.countLayoutCases());
    }

    @Test
    public void testFourWalls() throws Exception {
        Main main = new Main(new boolean[][]{{f, f}, {f, f}});
        Assert.assertEquals(0, main.countLayoutCases());
    }

    @Test
    public void testSixSlots() throws Exception {
        Main main = new Main(new boolean[][]{{t, t, t}, {t, t, t}});
        Assert.assertEquals(2, main.countLayoutCases());
    }
*/

    public static void assertMain(String[] input, String[] output) throws IOException {
        try (StringArrayInputOutput io = new StringArrayInputOutput(input)) {
            Main.main(io.getInputStream(), io.getPrintStream());
            Lib.assertDeepEquals(output, io.toStringArray());
        }
    }

    @Test
    public void testMainFirstExample() throws Exception {
        String[] input = new String[] {
                "1",
                "3 7",
                "#.....#",
                "#.....#",
                "##...##",
        };
        String[] output = new String[] {
                "0",
        };
        assertMain(input, output);
    }

    @Test
    public void testMainSecondExample() throws Exception {
        String[] input = new String[] {
                "1",
                "3 7",
                "#.....#",
                "#.....#",
                "##..###",
        };
        String[] output = new String[] {
                "2",
        };
        assertMain(input, output);
    }

    @Test
    public void testMainThirdExample() throws Exception {
        String[] input = new String[] {
                "1",
                "8 10",
                "##########",
                "#........#",
                "#........#",
                "#........#",
                "#........#",
                "#........#",
                "#........#",
                "##########"
        };
        String[] output = new String[] {
                "1514",
        };
        Main.debug = false;
        assertMain(input, output);
    }

    @Test
    public void testMainLargestSingleExample() throws Exception {
        String[] input = new String[] {
                "1",
                "20 20",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
                "#..................#",
        };
        String[] output = new String[] {
                "1514",
        };
        Main.debug = false;
        assertMain(input, output);
    }

}
