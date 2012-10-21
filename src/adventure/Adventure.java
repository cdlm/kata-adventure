package adventure;

public class Adventure {
    protected Location currentLocation;

    public Adventure(Location startingPoint) {
        currentLocation = startingPoint;
    }

    public String situation() {
        return currentLocation.description();
    }
}
