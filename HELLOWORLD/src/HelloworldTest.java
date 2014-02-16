import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/** 
* Main Tester. 
* 
* @author <Authors name> 
* @since <pre>7�� 6, 2013</pre> 
* @version 1.0 
*/ 
public class HelloworldTest {

    private Main main;

    @Before
public void before() throws Exception {
        main = new Main();
} 

@After
public void after() throws Exception {
}

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception {
    final String[] USER_INPUT = new String[] {
            "5",
            "World",
            "Algospot",
            "Illu",
            "Jullu",
            "Kodori",
    };
    final String[] EXPECTED_OUTPUT = new String[] {
            "Hello, World!",
            "Hello, Algospot!",
            "Hello, Illu!",
            "Hello, Jullu!",
            "Hello, Kodori!",
    };

    byte[] buf;
    try (StringWriter stringOutForInput = new StringWriter()) {
        for(String ui: USER_INPUT) {
            stringOutForInput.write(String.format("%s%n", ui));
        }
        buf = stringOutForInput.getBuffer().toString().getBytes();
    }

    try (ByteArrayInputStream byteIn = new ByteArrayInputStream(buf);
         ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
         PrintStream out = new PrintStream(byteOut)) {
        Main.main(new String[]{}, byteIn, out);
        buf = byteOut.toByteArray();
    }

    try (BufferedReader readerForOutput = new BufferedReader(
            new InputStreamReader(new ByteArrayInputStream(buf)))) {
        List<String> results = new ArrayList<>();
        String line = readerForOutput.readLine();
        while (null != line) {
            results.add(line);
            line = readerForOutput.readLine();
        }
        assertEquals(EXPECTED_OUTPUT.length, results.size());
        for (int i = 0; i < EXPECTED_OUTPUT.length; ++i) {
            assertEquals(EXPECTED_OUTPUT[i], results.get(i));
        }
    }
}

/** 
* 
* Method: mainOne(String arg) 
* 
*/ 
@Test
public void testMainOneSample1() throws Exception {
    assertEquals(main.mainOne("World"), "Hello, World!");
}


} 
