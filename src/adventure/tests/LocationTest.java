package adventure.tests;

import adventure.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class LocationTest {

    protected Location location;

    @Before
    public void setUp() throws Exception {
        location = new Location("You're in a test lab.");
    }

    @Test
    public void test_description() {
        assertThat(location.description(), containsString("test lab"));
    }
}
