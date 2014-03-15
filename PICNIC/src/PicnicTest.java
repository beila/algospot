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
    public void testAPairOfTwoFriends() throws Exception {
        Main main = new Main(new boolean[][]{{true, true}, {true, true}});
        Assert.assertEquals(1, main.countPairingCases());
    }

    @Test
    public void testAPairOfNonFriends() throws Exception {
        Main main = new Main(new boolean[][]{{false, false}, {false, false}});
        Assert.assertEquals(0, main.countPairingCases());
    }

    @Test
    public void testSixFriendsOutOfFourStudents() throws Exception {
        // 0 1
        // 1 2
        // 2 3
        // 3 0
        // 0 2
        // 1 3
        Main main = new Main(new boolean[][]{
                {false, true, true, true},
                {true, false, true, true},
                {true, true, false, true},
                {true, true, true, false},
        });
        Assert.assertEquals(3, main.countPairingCases());
    }

}
