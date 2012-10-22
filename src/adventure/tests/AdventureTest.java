package adventure.tests;

import adventure.Adventure;
import adventure.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdventureTest {

    protected Adventure adventure;

    @Before
    public void setUp() {
        Location strangePlace = new Location("You've landed in a strange place...");
        adventure = new Adventure(strangePlace);
    }

    @Test
    public void test_welcomeMessage() {
        assertEquals("You've landed in a strange place...", adventure.situation());
    }
}
