package adventure;

import java.util.LinkedList;
import java.util.List;

/**
 * A place in the game's world.
 *
 * @see Way
 */
public class Location {
    protected String name;
    protected String description;
    private List<Way> ways;

    public Location(String name) { this(name, ""); }

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        ways = new LinkedList<Way>();
    }

    public String name() { return name; }

    public String description() { return description; }

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
}
