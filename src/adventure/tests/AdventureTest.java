package adventure.tests;

import adventure.Adventure;
import adventure.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class AdventureTest {

    protected Adventure adventure;
    protected String title = "A strange place";
    protected String description = "Strange indeed; red and green lights flicker in the fog.";

    @Before
    public void setUp() {
        Location strangePlace = new Location(title);
        strangePlace.setDescription(description);

        adventure = new Adventure(strangePlace);
    }

    @Test
    public void test_welcomeMessage() {
        assertThat(adventure.situation(), containsString(title));
    }
}
