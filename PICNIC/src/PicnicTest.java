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
    private static final boolean t = true;
    private static final boolean f = false;

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

    @Test
    public void testTenFriendsOutOfSixStudents() throws Exception {
        // 0 1
        // 0 2
        // 1 2
        // 1 3
        // 1 4
        // 2 3
        // 2 4
        // 3 4
        // 3 5
        // 4 5
        Main main = new Main(new boolean[][]{
                {f, t, t, f, f, f}, //0
                {t, f, t, t, t, f}, //1
                {t, t, f, t, t, f}, //2
                {f, t, t, f, t, t}, //3
                {f, t, t, t, f, t}, //4
                {f, f, f, t, t, f}, //5
        });
        Assert.assertEquals(4, main.countPairingCases());
    }

    @Test
    public void testBiggestSingleProblem() throws Exception {
        Main main = new Main(new boolean[][]{
                {t, t, t, t, t, t, t, t, t, t}, //0
                {t, t, t, t, t, t, t, t, t, t}, //1
                {t, t, t, t, t, t, t, t, t, t}, //2
                {t, t, t, t, t, t, t, t, t, t}, //3
                {t, t, t, t, t, t, t, t, t, t}, //4
                {t, t, t, t, t, t, t, t, t, t}, //4
                {t, t, t, t, t, t, t, t, t, t}, //6
                {t, t, t, t, t, t, t, t, t, t}, //7
                {t, t, t, t, t, t, t, t, t, t}, //8
                {t, t, t, t, t, t, t, t, t, t}, //9
        });
        main.countPairingCases();
    }

}
