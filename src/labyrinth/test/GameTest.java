package labyrinth.test;

import junit.framework.TestCase;
import labyrinth.Game;
import labyrinth.games.campus.CampusGame;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameTest extends TestCase {

    private Game game;

    public void setUp() {
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        game = new CampusGame();
    }

    public void testHasInitialRoom() {
        assertNotNull(game.currentRoom());
    }


}
