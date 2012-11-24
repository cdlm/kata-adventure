package adventure.tests;

import adventure.Adventure;
import adventure.Artifact;
import adventure.Narrator;
import adventure.game.Lab;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class ArtifactTest {

    protected Lab lab;
    protected Artifact key, cabinet;
    protected Adventure adventure;
    protected Narrator narrator;

    @Before
    public void setUp() {
        lab = new Lab();

        key = new Artifact("golden key");
        lab.initialLocation().addArtifact(key);

        cabinet = new Artifact("cabinet");
        cabinet.setDescription("Made of sheet steel, beige, with a lock.");
        lab.anotherLocation().addArtifact(cabinet);

        adventure = lab.makeAdventure();
        narrator = new Narrator(adventure).registerBasicCommands();
    }

    @Test
    public void test_artifactsPresent() {
        assertThat(adventure.inventory(), not(hasItem(key)));

        assertTrue(adventure.isArtifactAvailable(key));
        assertFalse(adventure.isArtifactAvailable(cabinet));

        assertThat(lab.anotherLocation().availableArtifacts(), hasItem(cabinet));
        assertThat(lab.anotherLocation().availableArtifacts(), not(hasItem(key)));
    }

    @Test
    public void test_matchesKey() {
        List<Artifact> artifacts = lab.initialLocation().findArtifacts(new String[]{"key"});
        assertThat(artifacts, hasItem(key));
        assertThat(artifacts, not(hasItem(cabinet)));
    }

    @Test
    public void test_takeKey() {
        narrator.react("take key");
        assertThat(adventure.environmentArtifacts(), not(hasItem(key)));
        assertThat(adventure.inventory(), hasItem(key));
    }

    @Test
    public void test_transportKey() {
        narrator.react("take key");
        narrator.react("go door");
        assertTrue(adventure.isArtifactAvailable(key));
        assertTrue(adventure.isArtifactAvailable(cabinet));
    }
}
