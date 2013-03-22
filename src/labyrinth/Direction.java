package labyrinth;

/**
 * Représentation des quatre directions cardinales.
 */
public enum Direction {
    NORTH(0, "nord", 0, -1),
    WEST(1, "ouest", -1, 0),
    SOUTH(2, "sud", 0, 1),
    EAST(3, "est", 1, 0);

    /**
     * Indice dans le tableau d'adjacence des salles.
     *
     * @see Room
     */
    public final int index;

    /**
     * Nom en français utilisé dans les commandes et descriptions.
     *
     * @see Command
     * @see Game
     */
    public final String name;

    /**
     * Incrément horizontal ou vertical dans les coordonnées du plan.
     *
     * @see Plan
     */
    public final int dx, dy;

    /* Le constructeur d'un enum est toujours privé */
    private Direction(int index, String name, int dx, int dy) {
        this.index = index;
        this.name = name;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Recherche par nom français d'une des quatre instances.
     *
     * @return La direction s'appelant <code>name</code>, ou <code>null</code>.
     */
    public static Direction named(String name) {
        for (Direction d : values()) {
            if (d.name.equals(name)) {
                return d;
            }
        }
        return null;
    }
}
