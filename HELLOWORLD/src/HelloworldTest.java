import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

    try (StringArrayInputOutput io = new StringArrayInputOutput(USER_INPUT)) {
        Main.main(new String[]{}, io.getInputStream(), io.getPrintStream());
        Lib.assertDeepEquals(EXPECTED_OUTPUT, io.toStringArray());
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
