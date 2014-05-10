import com.beila.testlib.Lib;
import com.beila.testlib.StringArrayInputOutput;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;

public class Hanoi4Test {
    @Rule public Timeout globalTimeout = new Timeout(5000);

    public static void assertMain(String[] input, String[] output) throws IOException {
        try (StringArrayInputOutput io = new StringArrayInputOutput(input)) {
            Main.main(io.getInputStream(), io.getPrintStream());
            Lib.assertDeepEquals(output, io.toStringArray());
        }
    }

    @Test
    public void testFirstExample() throws Exception {
        String[] input = new String[] {
                "1",
                "5",
                "1 1",
                "1 3",
                "2 5 4",
                "1 2",
        };
        String[] output = new String[] {
                "10",
        };
        assertMain(input, output);
    }

}