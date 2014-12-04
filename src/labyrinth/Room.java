package labyrinth;

/**
 * Une salle dans le monde virtuel du jeu.
 *
 * Malgré le nom de la classe, il peut s'agir d'un lieu quelconque, en intérieur ou extérieur; pour
 * le joueur, seule la description importe.
 */
public class Room {
    protected final String description;
    protected final Room[] exits = new Room[Direction.values().length];
    protected boolean visited;
    protected int coordX, coordY;

    /**
     * @param description Une description de la salle, sous forme d'un complément de lieu utilisable
     *                    directement après "vous êtes…", et sans point final.
     */
    public Room(String description) {
        this.description = description;
    }

    /**
     * Description de la salle donnée au cours du jeu.
     *
     * @return Une phrase en français correct, suivi de la liste des sorties disponibles.
     */
    public String fullDescription() {
        return "Vous êtes " + this.description + "\n" +
                "(directions possibles : " + this.exitDirections() + ")";
    }

    /**
     * Noms des directions que le joueur peut emprunter depuis cette salle.
     *
     * @return Les noms en français des directions possibles, séparées par des virgules.
     */
    public String exitDirections() {
        String result = "";
        for (Direction d : Direction.values()) {
            if (exits[d.index] != null) {
                String separator = result.isEmpty() ? "" : ", ";
                result += separator;
                result += d.name;
            }
        }
        return result;
    }

    /**
     * Connecte une salle adjacente dans la direction donnée.
     *
     * Le passage ainsi établi est à sens unique, donc il ne faut pas oublier d'établir le passage
     * retour. Contrainte: la commande <tt>plan</tt> suppose que les salles occupent les cases d'une
     * grille orthogonale plane, deux salles adjacentes occupant des cases adjacentes de la grille.
     * Techniquement il est possible de former des connexion arbitraires (ponts, raccourcis,
     * connexions non orthogonales) mais l'affichage du plan sera incorrect.
     *
     * @param direction Direction vers laquelle connecter la salle.
     * @param adjacent La salle à connecter
     */
    public void setExit(Direction direction, Room adjacent) {
        exits[direction.index] = adjacent;
    }

    /**
     * @param direction Direction de déplacement souhaitée.
     * @return La salle adjacente dans la direction donnée, ou <code>null</code> s'il n'y en a pas.
     */
    public Room getExit(Direction direction) {
        return exits[direction.index];
    }

    /**
     * Réaction du jeu à l'arrivée du joueur dans une salle.
     *
     * Affiche la description de la salle, et marque la salle comme connue du joueur.
     *
     * @param game Le jeu en cours de déroulement.
     * @return <code>true</code> si entrer dans cette salle met fin au jeu.
     */
    public boolean enter(Game game) {
        System.out.println(fullDescription());
        beVisited();
        return false;
    }

    /**
     * Marque la salle comme connue du joueur.
     */
    public void beVisited() { visited = true; }

    /**
     * @return <code>true</code> si le joueur est déjà passé dans cette salle.
     */
    public boolean isVisited() { return visited; }

    /**
     * Description iconique de la salle dans le plan.
     *
     * @return La représentation par défaut est un blanc.
     * @see Plan
     */
    public char characterDescription() { return ' '; }

    /**
     * Établit les coordonnées de la salle dans le plan.
     *
     * @param x Longitude de la salle.
     * @param y Latitude de la salle.
     * @see Plan
     */
    public void setXY(int x, int y) {
        coordX = x;
        coordY = y;
    }

    /**
     * @return Longitude de la salle dans le plan.
     */
    public int getX() { return coordX; }

    /**
     * @return Latitude de la salle dans le plan.
     */
    public int getY() { return coordY; }
}
