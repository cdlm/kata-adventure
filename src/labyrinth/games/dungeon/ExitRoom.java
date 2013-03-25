package labyrinth.games.dungeon;

import labyrinth.Game;
import labyrinth.Room;

/**
 * Une salle de sortie (victorieuse, à priori) du jeu.
 */
public class ExitRoom extends Room {

    protected final String exitDescription;
    protected Room key1, key2;

    public ExitRoom(String description, String exitDescription) {
        this(description, exitDescription, null);
    }

    public ExitRoom(String description, String exitDescription, Room key) {
        this(description, exitDescription, key, null);
    }

    /**
     * @param description     Une description de la salle, comme pour {@link Room#description}.
     * @param exitDescription Description supplémentaire quand le jeu termine.
     */
    public ExitRoom(String description, String exitDescription, Room key1, Room key2) {
        super(description);
        this.exitDescription = exitDescription;
        this.key1 = key1;
        this.key2 = key2;
    }

    public boolean enter(Game game) {
        boolean wantsToQuit = super.enter(game) || hasKey(key1) && hasKey(key2);
        if (wantsToQuit) {
            System.out.println(exitDescription);
        }
        return wantsToQuit;
    }

    private boolean needsKey(Room keyRoom) { return keyRoom != null; }

    private boolean hasKey(Room keyRoom) {
        return !needsKey(keyRoom) || keyRoom.isVisited();
    }

    @Override
    public char characterDescription() {
        int neededKeys = 0;
        if (needsKey(key1)) neededKeys++;
        if (needsKey(key2)) neededKeys++;

        if (hasKey(key1) && hasKey(key2)) return super.characterDescription();
        else if (neededKeys == 2 && hasKey(key1) || hasKey(key2)) return '=';
        else {
            // la sortie est verrouillée,
            // mais on ne sait pas combien de clés sont nécessaires...
            // un peu bête, mais c'est l'énoncé...
            return '#';
        }
    }
}
