package adventure.actions;

import adventure.Command;
import adventure.CommandFactory;

public class Look extends Command {
    public Look(String[] words) { super(words); }

    @Override
    public String narration() {
        StringBuilder text = new StringBuilder("You look around.");
//        text.append(adventure.description);
        return text.toString();
    }

    public static CommandFactory factory() {
        return new CommandFactory() {
            public Command make(String[] words) {
                return new Look(words);
            }
        };
    }
}
