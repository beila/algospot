import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * http://algospot.com/judge/problem/read/BOARDCOVER
 * Created by hojin.ghim on 3/15/14.
 */
public class BoardCoverTest {
    @Rule
    public Timeout globalTimeout = new Timeout(2000);

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

}
