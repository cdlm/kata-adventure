package adventure;

/**
 * A one-way passage between two locations. To make a passage that can be {@link #traverse()
 * traversed} in both directions, add a {@code Way} forth and another one back.
 *
 * @see Location
 */
public class Way extends Describable {
    protected Location provenance;
    protected Location destination;

    /**
     * @param name        A concise description of the passage, e.g. "a door to the north"
     * @param destination The {@link Location} to which the way leads.
     */
    public Way(String name, Location destination) {
        super(name);
        this.destination = destination;
    }

    /**
     * Initialization only.
     *
     * @param provenance The {@link Location} from which the way originates.
     */
    public void setProvenance(Location provenance) {
        this.provenance = provenance;
    }

    /**
     * Traverse the way.
     *
     * @return The destination location.
     */
    public Location traverse() {
        return destination;
    }

    /**
     * Checks if the way's description matches each of the given keywords.
     *
     * @param keywords Keywords to match.
     * @return {@code true} if the way matches.
     */
    public boolean matches(String[] keywords) {
        boolean matches = true;
        for (String kwd : keywords) {
            matches = matches && (name.contains(kwd) || description.contains(kwd));
        }
        return matches;
    }
}
