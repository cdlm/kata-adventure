package adventure;

import java.util.LinkedList;
import java.util.List;

/**
 * A place in the game's world.
 *
 * @see Way
 */
public class Location {
    protected String description;
    private List<Way> ways;

    public Location(String description) {
        this.description = description;
        ways = new LinkedList<Way>();
    }

    public String description() { return description; }

    public void addWay(Way newWay) {
        newWay.setProvenance(this);
        ways.add(newWay);
    }

    public List<Way> findWays(String[] keywords) {
        List<Way> found = new LinkedList<Way>();
        for (Way w : ways) {
            boolean matches = true;
            for (String kwd : keywords) {
                matches = matches && w.matches(kwd);
            }
            if (matches) {
                found.add(w);
            }
        }
        return found;
    }

    public List<Way> getWays() { return ways; }
}
