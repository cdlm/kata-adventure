package adventure;

import java.util.List;

/**
 * An instance of Adventure represents where the hero is, what she can do, and where she can go from
 * there. It basically contains all information about the state of the game world, from the hero's
 * point of view.
 * <p/>
 * {@linkplain Command Commands} are performed in the context of an Adventure.
 *
 * @see Location
 * @see Command
 */
public class Adventure {
    protected Location currentLocation;
    protected boolean finished;

    /**
     * @param startingPoint The initial {@link Location} in this adventure
     */
    public Adventure(Location startingPoint) {
        currentLocation = startingPoint;
    }

    /**
     * @return a short description of the situation the hero is in: place, readily perceptible
     *         things, tools at hand, etc.
     */
    public String situation() {
        return currentLocation.name();
    }

    /**
     * @return the {@link Location} where the hero currently is.
     */
    public Location getCurrentLocation() { return currentLocation; }

    public void setCurrentLocation(Location newLocation) {
        currentLocation = newLocation;
    }

    public List<Artifact> getArtifacts() { return currentLocation.getArtifacts(); }

    public List<Way> getWays() { return currentLocation.getWays(); }

    public void beFinished() { finished = true; }

    public boolean isFinished() { return finished; }
}
