package adventure.tests;

import adventure.Adventure;
import adventure.Artifact;
import adventure.Location;
import adventure.Narrator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class ArtifactTest {

    protected Narrator narrator;
    protected Adventure adventure;
    protected Location location;
    protected Artifact key;

    @Before
    public void setUp() {
        location = new Location("Somewhere");
        key = new Artifact("golden key");
        location.addArtifact(key);
        adventure = new Adventure(location);
        narrator = new Narrator(adventure).registerBasicCommands();
    }

    @Test
    public void test_addKey() {
        assertThat(location.getArtifacts(), hasItem(key));
    }

    @Test
    public void test_seeKey() {
        String output = narrator.react("look");
        assertThat(output, containsString("golden key"));
    }
}
