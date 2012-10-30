package adventure.actions;

import adventure.Adventure;
import adventure.Command;
import adventure.Way;

import java.util.Arrays;
import java.util.List;

public class Go implements Command {
    @Override
    public String invoke(Adventure adventure, String[] words) {
        String[] keywords = Arrays.copyOfRange(words, 1, words.length);
        List<Way> ways = adventure.getCurrentLocation().findWays(keywords);
        switch (ways.size()) {
            case 0:
                return "Huh? Which way?";
            case 1:
                Way picked = ways.get(0);
                adventure.setCurrentLocation(picked.traverse());
                return "You go via the " + picked.getDescription();
            default:
                return "I'm not sure which way of several you meant.";
        }
    }
}
