package adventure.tests;

import adventure.Way;
import adventure.game.Lab;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

public class LocationTest {

    protected Lab lab;

    @Before
    public void setUp() throws Exception {
        lab = new Lab();
    }

    @Test
    public void test_description() {
        assertThat(lab.location.description(), containsString("test lab"));
    }

    @Test
    public void test_way() {
        List<Way> ways = lab.location.findWays(new String[]{"door"});
        assertEquals(ways.size(), 1);
        assertSame(ways.get(0).traverse(), lab.anotherLocation);
    }

    @Test
    public void test_wrongWay() {
        assertTrue(lab.location.findWays(new String[]{"incorrect"}).isEmpty());
    }
}
