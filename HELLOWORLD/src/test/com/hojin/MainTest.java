package test.com.hojin;

import com.hojin.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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
    private InputStream systemInputStream;
    private PrintStream systemOutputStream;

    @Before
public void before() throws Exception {
        systemInputStream = System.in;
        systemOutputStream = System.out;
        main = new Main();
} 

@After
public void after() throws Exception {
    System.setIn(systemInputStream);
    System.setOut(systemOutputStream);
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception {
    System.setIn(new ByteArrayInputStream("5\n1\n2\n3\n4\n5\n".getBytes()));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    Main.main(new String[]{});
    //TODO assert out has 5 lines
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
