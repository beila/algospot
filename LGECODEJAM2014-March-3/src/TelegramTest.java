import com.beila.testlib.Lib;
import org.junit.Test;

/**
 * http://www.lgecodejam.com/codejam/dashboard
 * http://www.lgecodejam.com/codejam/problem/download/pdf/5/kr
 * Created by hojin.ghim on 3/29/14.
 */
public class TelegramTest {
    @Test
    public void testInit() throws Exception {
        new Main().addMessages("zebra", "bras", "ebbs");
    }

    @Test
    public void testObvious() throws Exception {
        Main main = new Main();
        main.addMessages("zebra");
        Lib.assertDeepEquals(new int[]{1}, main.essentialLength());
    }

}
