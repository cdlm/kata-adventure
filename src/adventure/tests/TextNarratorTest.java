package adventure.tests;

import adventure.Adventure;
import adventure.Narrator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class TextNarratorTest {

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
}
