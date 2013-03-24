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
     * Recherche l'instance de <code>Direction</code> correspondant à l'indication en toutes lettres
     * saisie par le joueur, parmi les quatre constantes de la classe.
     *
     * @param name Le nom de la direction recherchée.
     * @return La direction cardinale correspondante, ou <code>null</code> si le nom fourni est
     *         incorrect.
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
