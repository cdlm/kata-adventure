package adventure.commands;

import adventure.Adventure;

public class Quit extends KeywordCommand {
    /* a special keyword, sent only by main loop if the input stream closes */
    public final static String ABORT = "<abort>";

    public Quit() {
        super();
        keywords.add("quit");
        keywords.add(ABORT);
    }

    @Override
    public String invoke(Adventure adventure, String keyword, String[] terms) {
        adventure.beFinished();
        if (keyword.equals(ABORT)) {
            return "";
        } else {
            return "OK, bye. See you soon!";
        }
    }
}
