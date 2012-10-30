package adventure;

public class Way {
    protected String description;
    protected Location provenance;
    protected Location destination;

    public Way(String description, Location destination) {
        this.description = description;
        this.destination = destination;
    }

    public void setProvenance(Location provenance) {
        this.provenance = provenance;
    }

    public Location traverse() {
        return destination;
    }

    public boolean matches(String desc) {
        return description.contains(desc);
    }

    public String getDescription() { return description; }
}
