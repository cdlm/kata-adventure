package adventure.tests;

import adventure.Adventure;
import adventure.Location;
import adventure.Narrator;
import adventure.Way;
import adventure.actions.Go;
import adventure.actions.Look;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class NarratorTest {

    protected Adventure adventure;
    protected Narrator narrator;
    protected Location lab;
    protected Location office;

    @Before
    public void setUp() {
        lab = new Location("You're in a test lab.");
        office = new Location("You're in an office.");
        lab.addWay(new Way("door to the north", office));
        adventure = new Adventure(lab);
        narrator = new Narrator(adventure);
        narrator.registerCommand(new Look())
                .registerCommand(new Go());
    }

    @Test
    public void test_initialLocation() {
        assertSame(adventure.getCurrentLocation(), lab);
    }

    @Test
    public void test_incorrectCommand() {
        String output = narrator.react("illegal input");
        assertThat(output, containsString("Huh"));
    }

    @Test
    public void test_look() {
        String output = narrator.react("look");
        assertThat(output, containsString("You look around"));
        assertThat(output, containsString("test lab"));
        assertThat(output, containsString("door to the north"));
    }

    @Test
    public void test_goViaExistingWay() {
        String output = narrator.react("go north");
        assertThat(output, containsString("You go through"));
        assertSame(adventure.getCurrentLocation(), office);
    }

    @Test
    public void test_commandAlias() {
        narrator.registerCommandAlias("walk", narrator.recognizeCommand("go"));
        String output = narrator.react("walk north");
        assertThat(output, containsString("You walk through"));
        assertSame(adventure.getCurrentLocation(), office);
    }
}
