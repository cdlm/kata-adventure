package adventure;

/**
 * An instance of Adventure represents where the hero is, what she can do, and where she can go from there.
 * It basically contains all information about the state of the game world, from the hero's point of view.
 * <p/>
 * {@linkplain Command Commands} are performed in the context of an Adventure.
 * </p>
 *
 * @see Location
 * @see Command
 */
public class Adventure {
    protected Location currentLocation;

    /** @param startingPoint The initial {@link Location} in this adventure */
    public Adventure(Location startingPoint) {
        currentLocation = startingPoint;
    }

    /** @return a description of the situation the hero is in: place, belongings, etc. */
    public String situation() {
        return currentLocation.description();
    }
}
