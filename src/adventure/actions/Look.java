package adventure.actions;

import adventure.Adventure;
import adventure.Command;
import adventure.CommandFactory;

/**
 * The Look command makes the hero look around herself. It has no effect on the adventure, so the player can invoke it
 * as many times as necessary.
 */
public class Look extends Command {
    public Look(Adventure adventure, String[] words) { super(adventure, words); }

    @Override
    public String narration() {
        StringBuilder text = new StringBuilder("You look around.");
        text.append('\n');
        text.append(adventure.situation());
        return text.toString();
    }

    public static CommandFactory factory() {
        return new CommandFactory() {
            public Command make(Adventure adventure, String[] words) {
                return new Look(adventure, words);
            }
        };
    }
}
