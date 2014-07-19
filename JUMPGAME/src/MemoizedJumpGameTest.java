import org.junit.Before;

public class MemoizedJumpGameTest extends JumpGameTest {
    @Before
    public void setUp() throws Exception {
        game = new Main.MemoizedJumpGame();
    }
}
