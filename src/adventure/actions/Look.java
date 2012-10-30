package adventure.actions;

import adventure.Adventure;
import adventure.Command;
import adventure.Way;

/**
 * The Look command makes the hero look around herself. It has no effect on the adventure, so the player can invoke it
 * as many times as necessary.
 */
public class Look implements Command {

    public String invoke(Adventure adventure, String[] words) {
        StringBuilder text = new StringBuilder("You look around.\n\n");
        text.append(adventure.situation());
        text.append("\n\n");
        for (Way w : adventure.getWays()) {
            text.append("You see " + w.getDescription() + ".\n");
        }
        return text.toString();
    }
}
