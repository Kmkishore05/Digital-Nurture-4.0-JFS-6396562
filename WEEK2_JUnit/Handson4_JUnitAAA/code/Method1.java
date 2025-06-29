import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Method1 {

    @Before
    public void setUp() {
        System.out.println("Setup before each test");
    }

    @Test
    public void testOne() {
        System.out.println("Running Test 1");
    }

    @After
    public void tearDown() {
        System.out.println("Cleanup after each test");
    }
    public class SampleTest {

    @AfterEach
    void cleanUp() {
        System.out.println("After each test");
    }

    @Test
    void testExample() {
        System.out.println("Test running...");
    }
}
}

import org.junit.AfterClass;

public class SampleTest {

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Cleanup after all tests");
    }
}
