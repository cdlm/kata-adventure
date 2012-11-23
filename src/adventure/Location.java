package adventure;

import java.util.LinkedList;
import java.util.List;

/**
 * A place in the game's world.
 *
 * @see Way
 */
public class Location extends Describable {
    private List<Way> ways;
    private List<Artifact> artifacts;

    public Location(String name) {
        super(name);
        ways = new LinkedList<Way>();
        artifacts = new LinkedList<Artifact>();
    }

    public void addWay(Way newWay) {
        newWay.setProvenance(this);
        ways.add(newWay);
    }

    public List<Way> findWays(String[] keywords) {
        List<Way> found = new LinkedList<Way>();
        for (Way w : ways) {
            if (w.matches(keywords)) {
                found.add(w);
            }
        }
        return found;
    }

    public List<Way> getWays() { return ways; }

    public void addArtifact(Artifact newArtifact) {
        artifacts.add(newArtifact);
    }

    public List<Artifact> getArtifacts() { return artifacts; }
}
