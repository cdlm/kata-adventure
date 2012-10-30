package adventure.tests;

import adventure.Location;
import adventure.Way;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

public class LocationTest {

    protected Location location;
    protected Location anotherLocation;

    @Before
    public void setUp() throws Exception {
        location = new Location("You're in a test lab");
        anotherLocation = new Location("You're in an office");
        location.addWay(new Way("a door to the north", anotherLocation));
    }

    @Test
    public void test_description() {
        assertThat(location.description(), containsString("test lab"));
    }

    @Test
    public void test_way() {
        List<Way> ways = location.findWays(new String[]{"door"});
        assertEquals(ways.size(), 1);
        assertSame(ways.get(0).traverse(), anotherLocation);
    }

    @Test
    public void test_wrongWay() {
        assertTrue(location.findWays(new String[]{"incorrect"}).isEmpty());
    }
}
