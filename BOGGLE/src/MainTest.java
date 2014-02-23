import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    static String FIRST_BOARD = TestLib.getString(
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

}
