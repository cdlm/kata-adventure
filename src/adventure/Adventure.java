package adventure;

import java.util.LinkedList;
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
    private List<Artifact> inventory;

    /**
     * @param startingPoint The initial {@link Location} in this adventure
     */
    public Adventure(Location startingPoint) {
        currentLocation = startingPoint;
        inventory = new LinkedList<Artifact>();
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
    public Location currentLocation() { return currentLocation; }

    public void changeLocation(Location newLocation) {
        currentLocation = newLocation;
    }

    public List<Way> availableWays() { return currentLocation.getWays(); }

    public List<Artifact> environmentArtifacts() { return currentLocation.availableArtifacts(); }

    public List<Artifact> inventory() { return inventory; }

    public void addToInventory(Artifact picked) { inventory.add(picked); }

    public boolean isArtifactAvailable(Artifact artifact) {
        return inventory.contains(artifact) || environmentArtifacts().contains(artifact);
    }

    public void beFinished() { finished = true; }

    public boolean isFinished() { return finished; }
}
