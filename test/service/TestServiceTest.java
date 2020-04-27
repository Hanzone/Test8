package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestServiceTest {

    private TestService testService;

    @Test
    public void getHello() {
        String hello = testService.getHello();
        assertEquals("hello", hello);
    }

    @Test
    public void satHi() {
        String hi = testService.satHi();
        assertEquals("Hi", hi);
    }

    @Before
    public void setUp() throws Exception {
        testService = new TestService();
    }

    @After
    public void tearDown() throws Exception {
        testService = null;
    }

}