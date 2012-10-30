package adventure.tests;

import adventure.Adventure;
import adventure.Location;
import adventure.Narrator;
import adventure.actions.Look;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class NarratorTest {

    protected Adventure adventure;
    protected Narrator narrator;

    @Before
    public void setUp() {
        adventure = new Adventure(new Location("You're in a test lab."));
        narrator = new Narrator(adventure);
    }

    @Test
    public void test_incorrectCommand() {
        String output = narrator.react("illegal input");
        assertThat(output, containsString("Huh"));
    }

    @Test
    public void test_lookCommand() {
        narrator.registerCommand("look", new Look());
        String output = narrator.react("look");
        assertThat(output, containsString("You look around"));
        assertThat(output, containsString("test lab"));
    }
}
