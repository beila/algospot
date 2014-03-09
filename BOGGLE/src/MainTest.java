import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @SuppressWarnings("SpellCheckingInspection")
    static String FIRST_BOARD = Lib.getLinedString(
            "URLPM",
            "XPRET",
            "GIAET",
            "XTNZY",
            "XOQRS"
    );

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

    @SuppressWarnings("SpellCheckingInspection")
    static String WORDS = Lib.getLinedString(
            "PRETTY",
            "GIRL",
            "REPEAT",
            "KARA",
            "PANDORA",
            "GIAZAPX"
    );

    static String USER_INPUT = Lib.getLinedString(
            "1"
    ) + FIRST_BOARD + Lib.getLinedString(
            "6"
    ) + WORDS;

    @SuppressWarnings("SpellCheckingInspection")
    static String[] EXPECTED_OUTPUT = new String[] {
            "PRETTY YES",
            "GIRL YES",
            "REPEAT YES",
            "KARA NO",
            "PANDORA NO",
            "GIAZAPX YES"
    };

    @Test
    public void testFirstExample() throws Exception {
        try (StringArrayInputOutput io = new StringArrayInputOutput(USER_INPUT)) {
            Main.main(new String[]{}, io.getInputStream(), io.getPrintStream());
            Lib.assertDeepEquals(EXPECTED_OUTPUT, io.toStringArray());
        }
    }

    @Test
    public void testSimpleSample() throws Exception {
        //noinspection SpellCheckingInspection
        try (StringArrayInputOutput io = new StringArrayInputOutput(Lib.getLinedString(
                "1", "NNNNS", "NEEEN", "NEYEN", "NEEEN", "NNNNN", "2", "YES", "NO"
        ))) {
            Main.main(new String[]{}, io.getInputStream(), io.getPrintStream());
            Lib.assertDeepEquals(new String[] {"YES YES", "NO NO"}, io.toStringArray());
        }
    }

}
