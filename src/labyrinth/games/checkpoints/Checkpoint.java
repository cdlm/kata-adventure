package labyrinth.games.checkpoints;

import labyrinth.Game;
import labyrinth.Room;

public class Checkpoint extends Room {
    protected int checkinsAvailable;
    protected final String checkinMessage;

    public Checkpoint(String description, String checkinMessage) { this(description, checkinMessage, 1); }

    public Checkpoint(String description, String checkinMessage, int checkinsAvailable) {
        super(description);
        this.checkinMessage = checkinMessage;
        this.checkinsAvailable = checkinsAvailable;
    }

    public boolean enter(Game game) {
        boolean validatesGame = super.enter(game);
        if (checkinsAvailable > 0) {
            checkinsAvailable--;
            System.out.println(checkinMessage);
            if (game instanceof CheckpointGame) {
                ((CheckpointGame) game).visitCheckpoint();
            }
        }
        return validatesGame;
    }
}
