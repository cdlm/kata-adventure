package adventure.tests;

import adventure.Adventure;
import adventure.Location;
import adventure.Narrator;
import adventure.Way;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

public class NarratorTest {

    protected Adventure adventure;
    protected Narrator narrator;
    protected Location lab;
    protected Location office;

    @Before
    public void setUp() {
        lab = new Location("A test lab");
        office = new Location("An office");
        lab.addWay(new Way("door to the north", office));
        adventure = new Adventure(lab);
        narrator = new Narrator(adventure).registerBasicCommands();
    }

    @Test
    public void test_initialLocation() {
        assertSame(adventure.currentLocation(), lab);
    }

    @Test
    public void test_incorrectCommand() {
        String output = narrator.react("illegal input");
        assertThat(output, containsString("Huh"));
    }

    @Test
    public void test_startsWithSpaces() {
        String output = narrator.react("  go      north");
        assertThat(output, containsString("door"));
    }

    @Test
    public void test_capitalization() {
        String output = narrator.react("Go NORTH");
        assertThat(output, containsString("door"));
    }

    @Test
    public void test_look() {
        String output = narrator.react("look");
        assertThat(output, containsString("door to the north"));
    }

    @Test
    public void test_goViaExistingWay() {
        String output = narrator.react("go north");
        assertThat(output, containsString("You go through"));
        assertSame(adventure.currentLocation(), office);
    }

    @Test
    public void test_commandAlias() {
        narrator.registerCommandAlias("tiptoe", narrator.recognizeCommand("go"));
        String output = narrator.react("tiptoe north");
        assertThat(output, containsString("You tiptoe through"));
        assertSame(adventure.currentLocation(), office);
    }

    @Test
    public void test_quit() {
        String output = narrator.react("quit");
        assertThat(output, containsString("bye"));
        assertTrue(adventure.isFinished());
    }
}
