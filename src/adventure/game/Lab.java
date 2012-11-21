package adventure.game;

import adventure.Adventure;
import adventure.Location;
import adventure.Narrator;
import adventure.Way;

import java.io.IOException;

public class Lab {

    protected Location location;
    protected Location anotherLocation;

    public Lab() {
        location = new Location("A test lab", "The place is full of strange experiments.");
        anotherLocation = new Location("An office", "Stacks of papers cover every available horizontal surface.");
        location.addWay(new Way("door to the north", anotherLocation));
        anotherLocation.addWay(new Way("door", location));
        location.addWay(new Way("loophole", location));
    }

    public Adventure makeAdventure() {
        return new Adventure(initialLocation());
    }

    public Location initialLocation() { return location; }

    public Location anotherLocation() { return anotherLocation; }

    public static void main(String[] args) throws IOException {
        new Narrator(new Lab().makeAdventure())
                .registerBasicCommands()
                .runOnConsole();
    }
}
