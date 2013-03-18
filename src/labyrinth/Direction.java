package labyrinth;

public enum Direction {
    NORTH(0, "nord", 0, -1),
    WEST(1, "ouest", -1, 0),
    SOUTH(2, "sud", 0, 1),
    EAST(3, "est", 1, 0);

    public final int index;
    public final String name;
    public final int dx, dy;

    /* Le constructeur d'un enum est privé */
    private Direction(int index, String name, int dx, int dy) {
        this.index = index;
        this.name = name;
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction named(String name) {
        for (Direction d : values()) {
            if (d.name.equals(name)) {
                return d;
            }
        }
        return null;
    }
}
