import com.beila.testlib.Lib;
import com.beila.testlib.StringArrayInputOutput;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoggleTest {

    @Rule
    public Timeout globalTimeout = new Timeout(10000);

    @SuppressWarnings("SpellCheckingInspection")
    static String FIRST_BOARD = Lib.getLinedString(
            "URLPM",
            "XPRET",
            "GIAET",
            "XTNZY",
            "XOQRS"
    );

    @SuppressWarnings("SpellCheckingInspection")
    private final String[] FIRST_WORDS = new String[] {
            "PRETTY",
            "GIRL",
            "REPEAT",
            "KARA",
            "PANDORA",
            "GIAZAPX"
    };

    @SuppressWarnings("SpellCheckingInspection")
    private final String[] FIRST_OUTPUT = new String[] {
            "PRETTY YES",
            "GIRL YES",
            "REPEAT YES",
            "KARA NO",
            "PANDORA NO",
            "GIAZAPX YES"
    };

    @Test
    public void testFindOneCharacter() throws Exception {
        Main main = new Main(FIRST_BOARD);
        assertEquals(true, main.findString("P"));
    }

    @Test
    public void testDoNotFindOneCharacter() throws Exception {
        Main main = new Main(FIRST_BOARD);
        assertEquals(false, main.findString("B"));
    }

    @Test
    public void testFindTwoCharacter() throws Exception {
        Main main = new Main(FIRST_BOARD);
        assertEquals(true, main.findString("PR"));
    }

    @Test
    public void testFindThreeCharacter() throws Exception {
        Main main = new Main(FIRST_BOARD);
        assertEquals(true, main.findString("PRE"));
    }

    @Test
    public void testFindThreeCharacter2() throws Exception {
        Main main = new Main(FIRST_BOARD);
        assertEquals(true, main.findString("PRU"));
    }

    @Test
    public void testDoNotFindThreeCharacter() throws Exception {
        Main main = new Main(FIRST_BOARD);
        assertEquals(false, main.findString("ABC"));
    }

    @Test
    public void testFindPretty() throws Exception {
        Main main = new Main(FIRST_BOARD);
        assertEquals(true, main.findString("PRETTY"));
    }

    @Test
    public void testFirstExample() throws Exception {

        String USER_INPUT = Lib.getLinedString(
                "1"
        ) + FIRST_BOARD + Lib.getLinedString(
                "6"
        ) + Lib.getLinedString(
                FIRST_WORDS
        );

        assertMain(new String[]{USER_INPUT}, FIRST_OUTPUT);
    }

    @Test
    public void testSimpleSample() throws Exception {
        //noinspection SpellCheckingInspection
        assertMain(new String[]{
                "1",
                "NNNNS",
                "NEEEN",
                "NEYEN",
                "NEEEN",
                "NNNNN",
                "2",
                "YES",
                "NO"
        }, new String[] {"YES YES", "NO NO"});
    }

    @Test
    public void testSimpleSample2() throws Exception {
        //noinspection SpellCheckingInspection
        assertMain(new String[]{
                "1",
                "NNNNN",
                "NEEEN",
                "NEYEN",
                "NEEEN",
                "NSNNN",
                "2",
                "YES",
                "NO"
        }, new String[] {"YES YES", "NO NO"});
    }

    @Test
    public void testManyBoards() throws Exception {
        final int reps = 50;
        StringBuilder USER_INPUT = new StringBuilder();
        USER_INPUT.append(Lib.getLinedString(String.valueOf(reps)));
        for (int i = 0; i < reps; i++) {
            USER_INPUT.append(FIRST_BOARD);
        }
        USER_INPUT.append(Lib.getLinedString(String.valueOf(FIRST_WORDS.length)));
        USER_INPUT.append(Lib.getLinedString(FIRST_WORDS));

        String[] OUTPUT = new String[FIRST_OUTPUT.length* reps];
        for (int i = 0; i < reps; i++) {
            System.arraycopy(FIRST_OUTPUT, 0, OUTPUT,
                    i * FIRST_OUTPUT.length, FIRST_OUTPUT.length);
        }

        assertMain(new String[]{USER_INPUT.toString()}, OUTPUT);
    }

    @Test
    public void testManyWords() throws Exception {
        final int reps = 10;
        StringBuilder input = new StringBuilder();
        input.append(Lib.getLinedString("1"));
        input.append(FIRST_BOARD);
        input.append(Lib.getLinedString(String.valueOf(reps*FIRST_WORDS.length)));
        for (int i = 0; i < reps; i++) {
            input.append(Lib.getLinedString(FIRST_WORDS));
        }

        String[] output = new String[reps*FIRST_OUTPUT.length];
        for (int i = 0; i < reps; i++) {
            System.arraycopy(FIRST_OUTPUT, 0, output,
                    i * FIRST_OUTPUT.length, FIRST_OUTPUT.length);
        }

        assertMain(new String[]{input.toString()}, output);
    }

    @Test
    public void testNothingFound() throws Exception {
        @SuppressWarnings("SpellCheckingInspection")
        String[] input = new String[] {"1\n" +
                "AAAAA\n" +
                "AAAAA\n" +
                "AAAAA\n" +
                "AAAAA\n" +
                "AAAAA\n" +
                "1\n" +
                "AAAAAAAAAB"};
        @SuppressWarnings("SpellCheckingInspection")
        String[] output = new String[] {"AAAAAAAAAB" + " NO"};
        assertMain(input, output);
    }

    @Test
    public void testNothingFound2() throws Exception {
        @SuppressWarnings("SpellCheckingInspection")
        String[] input = new String[] {"1\n" +
                "AAAAA\n" +
                "AAAAA\n" +
                "AAAAA\n" +
                "AAACC\n" +
                "AAACB\n" +
                "1\n" +
                "AAAAAAAAAB"};
        @SuppressWarnings("SpellCheckingInspection")
        String[] output = new String[] {"AAAAAAAAAB" + " NO"};
        assertMain(input, output);
    }

    @Test
    public void testBiggestProblem() throws Exception {
        final int repBoards = 50;
        List<String> inputList = new ArrayList<>();
        inputList.add(String.valueOf(repBoards));
        for (int i = 0; i < repBoards; i++) {
            for (int j = 0; j < 5; j++) {
                //noinspection SpellCheckingInspection
                inputList.add("AAAAA");
            }
        }

        final int repWords = 10;
        inputList.add(String.valueOf(repWords));
        for (int i = 0; i < repWords; i++) {
            //noinspection SpellCheckingInspection
            inputList.add("AAAAAAAAAB");
        }

        String[] output = new String[repBoards*repWords];
        //noinspection SpellCheckingInspection
        Arrays.fill(output, "AAAAAAAAAB NO");

        assertMain(inputList.toArray(new String[inputList.size()]), output);
    }

    public static void assertMain(String[] input, String[] output) throws IOException {
        try (StringArrayInputOutput io = new StringArrayInputOutput(input)) {
            Main.main(new String[]{}, io.getInputStream(), io.getPrintStream());
            Lib.assertDeepEquals(output, io.toStringArray());
        }
    }
}
