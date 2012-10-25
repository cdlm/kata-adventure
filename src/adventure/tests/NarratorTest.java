package adventure.tests;

import adventure.Adventure;
import adventure.Narrator;
import adventure.actions.Look;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class NarratorTest {

    protected Narrator narrator;
    protected Adventure adventure;

    @Before
    public void setUp() {
        narrator = new Narrator(adventure);
    }

    @Test
    public void test_incorrectCommand() {
        String output = narrator.react("illegal input");
        assertThat(output, containsString("Huh"));
    }

    @Test
    public void test_lookCommand() {
        narrator.registerCommand("look", Look.factory());
        String output = narrator.react("look");
        assertThat(output, containsString("You look around"));
    }
}
