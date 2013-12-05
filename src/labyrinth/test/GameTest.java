package labyrinth.test;

import labyrinth.Game;
import labyrinth.games.campus.CampusGame;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertNotNull;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        game = new CampusGame();
    }

    @Test
    public void testHasInitialRoom() {
        assertNotNull(game.currentRoom());
    }


}
