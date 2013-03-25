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

    private boolean hasKey(Room keyRoom) {
        return keyRoom == null || keyRoom.isVisited();
    }
}
