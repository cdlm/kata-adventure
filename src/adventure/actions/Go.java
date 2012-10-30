package adventure.actions;

import adventure.Adventure;
import adventure.Command;
import adventure.Way;

import java.util.Arrays;
import java.util.List;

public class Go implements Command {

    public String defaultKeyword() { return "go"; }

    public String invoke(Adventure adventure, String[] words) {
        String keywordUsed = words[0];
        String[] wayKeywords = Arrays.copyOfRange(words, 1, words.length);
        List<Way> ways = adventure.getCurrentLocation().findWays(wayKeywords);
        switch (ways.size()) {
            case 0:
                return "Huh? There's no such way.";
            case 1:
                Way picked = ways.get(0);
                adventure.setCurrentLocation(picked.traverse());
                return "You " + keywordUsed + " through the " + picked.getDescription();
            default:
                return "I'm not sure which of several ways you meant.";
        }
    }
}
