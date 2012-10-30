package adventure.actions;

import adventure.Adventure;
import adventure.Command;

/**
 * The Look command makes the hero look around herself. It has no effect on the adventure, so the player can invoke it
 * as many times as necessary.
 */
public class Look extends Command {

    @Override
    public String perform(Adventure adventure, String[] words) {
        StringBuilder text = new StringBuilder("You look around.");
        text.append('\n');
        text.append(adventure.situation());
        return text.toString();
    }
}
