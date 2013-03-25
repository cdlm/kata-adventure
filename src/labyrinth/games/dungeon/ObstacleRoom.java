package labyrinth.games.dungeon;

import labyrinth.Game;
import labyrinth.Room;

public class ObstacleRoom extends Room {

    protected int difficulty;

    public ObstacleRoom(String description, int difficulty) {
        super(description);
        this.difficulty = difficulty;
    }

    @Override
    public boolean enter(Game game) {
        boolean wantsToQuit = super.enter(game);
        game.affectHealth(-difficulty);
        System.out.println("(-" + difficulty + "HP)");
        return wantsToQuit;
    }

    @Override
    public char characterDescription() {
        return difficulty > 9 ? '9' : Character.forDigit(difficulty, 10);
    }
}
