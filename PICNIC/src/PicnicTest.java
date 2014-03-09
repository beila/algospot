import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * http://algospot.com/judge/problem/read/PICNIC
 * Created by hojin.ghim on 3/9/14.
 */
public class PicnicTest {
    @Rule
    public Timeout globalTimeout = new Timeout(1000);

    @Test
    public void testSuccess() throws Exception {
        Main main = new Main(new boolean[][]{{true, true}, {true, true}});
        Assert.assertEquals(1, main.countPairingCases());
    }

}
