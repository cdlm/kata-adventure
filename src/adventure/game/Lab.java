package adventure.game;

import adventure.Adventure;
import adventure.Location;
import adventure.Narrator;
import adventure.Way;

import java.io.IOException;

public class Lab {

    public Location location;
    public Location anotherLocation;

    public Lab() {
        location = new Location("You're in a test lab.");
        anotherLocation = new Location("You're in an office.");
        location.addWay(new Way("door to the north", anotherLocation));
        anotherLocation.addWay(new Way("door", location));
        location.addWay(new Way("loophole", location));
    }

    public Adventure makeAdventure() {
        return new Adventure(initialLocation());
    }

    private Location initialLocation() {
        return location;
    }

    public static void main(String[] args) throws IOException {
        new Narrator(new Lab().makeAdventure())
                .registerBasicCommands()
                .runOnConsole();
    }
}
