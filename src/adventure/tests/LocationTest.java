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
        assertThat(lab.initialLocation().name(), containsString("test lab"));
    }

    @Test
    public void test_way() {
        List<Way> ways = lab.initialLocation().findWays(new String[]{"door"});
        assertTrue("there is at least one way out", ways.size() >= 1);
        assertSame(ways.get(0).traverse(), lab.anotherLocation());
    }

    @Test
    public void test_wrongWay() {
        assertTrue(lab.initialLocation().findWays(new String[]{"incorrect"}).isEmpty());
    }
}
