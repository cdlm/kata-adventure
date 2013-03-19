package labyrinth.games.checkpoints;

import labyrinth.Game;
import labyrinth.Room;

public class Exit extends Room {
    protected String finishedMessage;
    protected String unfinishedMessage;

    public Exit(String description, String finishedMessage, String unfinishedMessage) {
        super(description);
        this.finishedMessage = finishedMessage;
        this.unfinishedMessage = unfinishedMessage;
    }

    public boolean enter(Game game) {
        super.enter(game);
        if (game.isOkToFinish()) {
            System.out.println(finishedMessage);
        } else {
            System.out.println(unfinishedMessage);
        }
        return game.isOkToFinish();
    }
}
