package adventure.commands;

import adventure.Adventure;
import adventure.Way;

import java.util.Arrays;
import java.util.List;

/**
 * The "go" command is to make the hero take a {@link Way} out of the current {@link
 * adventure.Location}, to another one.
 * <p/>
 * Since each location can have several ways departing from it, the player needs to select which one
 * to take by giving terms after the command keyword. Only locations whose description contains all
 * the terms will be considered. If there is only one way out, then no terms need be given.
 *
 * @see adventure.Location
 * @see Way
 */
public class Go extends KeywordCommand {

    public Go() {
        super();
        keywords.add("go");
    }

    public String invoke(Adventure adventure, String[] words) {
        String keywordUsed = words[0];
        String[] terms = Arrays.copyOfRange(words, 1, words.length);
        List<Way> ways = adventure.getCurrentLocation().findWays(terms);
        switch (ways.size()) {
            case 0:
                return "Huh? There's no such way.\n";
            case 1:
                Way picked = ways.get(0);
                adventure.setCurrentLocation(picked.traverse());
                return "You " + keywordUsed + " through the " + picked.name() + "...\n";
            default:
                return "I'm not sure which of several ways you meant.\n";
        }
    }
}
