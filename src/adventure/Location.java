package adventure;

/**
 * A place in the game's world.
 */
public class Location {
    protected String description;

    public Location(String description) {
        this.description = description;
    }

    public String description() { return description; }
}
