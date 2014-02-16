package test.com.hojin;

import com.hojin.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertEquals;

/** 
* Main Tester. 
* 
* @author <Authors name> 
* @since <pre>7�� 6, 2013</pre> 
* @version 1.0 
*/ 
public class MainTest {

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
    byte[] buf;
    try (StringWriter stringOutForInput = new StringWriter()) {
        stringOutForInput.write(String.format("%s%n", "5"));
        stringOutForInput.write(String.format("%s%n", "1"));
        stringOutForInput.write(String.format("%s%n", "2"));
        stringOutForInput.write(String.format("%s%n", "3"));
        stringOutForInput.write(String.format("%s%n", "4"));
        stringOutForInput.write(String.format("%s%n", "5"));
        buf = stringOutForInput.getBuffer().toString().getBytes();
    }

    try (ByteArrayInputStream byteIn = new ByteArrayInputStream(buf);
         ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
         PrintStream out = new PrintStream(byteOut)) {
        Main.main(new String[]{}, byteIn, out);
        buf = byteOut.toByteArray();
    }

    try (LineNumberReader inForOutput = new LineNumberReader(
            new InputStreamReader(new ByteArrayInputStream(buf)))) {
        String s;
        int numLines = -1;
        do {
            numLines++;
            s = inForOutput.readLine();
        } while (null != s);
        assertEquals(5, numLines);
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
