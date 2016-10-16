package se.marcusrehn.chickentime;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    ClassLoader classLoader = this.getClass().getClassLoader();
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void readUtkiken() throws Exception {

        URL url = classLoader.getResource("utkiken.html");

        System.out.println(url.getPath());
    }
}