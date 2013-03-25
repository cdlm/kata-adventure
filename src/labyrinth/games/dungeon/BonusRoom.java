package labyrinth.games.dungeon;

import labyrinth.Game;
import labyrinth.Room;

public class BonusRoom extends Room {
    protected final String bonusDescription;
    protected int bonus;

    public BonusRoom(String description, String bonusDescription, int bonus) {
        super(description);
        this.bonusDescription = bonusDescription;
        this.bonus = bonus;
    }

    public boolean enter(Game game) {
        boolean newRoom = !isVisited(); /* avant d'entrer */
        boolean wantsToQuit = super.enter(game);
        if (newRoom) {
            System.out.println(bonusDescription + " (+" + bonus + "HP)");
            game.affectHealth(bonus);
        }
        return wantsToQuit;
    }
}
