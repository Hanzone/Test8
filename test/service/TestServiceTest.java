package service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Slf4j
public class TestServiceTest extends BaseTest {

    private TestService testService = new TestService();

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

}