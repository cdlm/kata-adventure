package adventure.commands;

import adventure.Adventure;
import adventure.Command;
import adventure.Way;

/**
 * The "look" command makes the hero look around herself. It has no effect on the adventure, so the player can invoke it
 * as many times as necessary.
 *
 * @see adventure.Location
 * @see Adventure
 */
public class Look implements Command {

    public String defaultKeyword() { return "look"; }

    public String invoke(Adventure adventure, String[] words) {
        StringBuilder text = new StringBuilder("You look around.\n\n");
        text.append(adventure.situation()).append("\n\n");
        for (Way w : adventure.getWays()) {
            text.append("You see a ")
                    .append(w.getDescription())
                    .append(".\n");
        }
        return text.toString();
    }
}
