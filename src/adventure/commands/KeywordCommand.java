package adventure.commands;

import adventure.Command;
import adventure.Narrator;

import java.util.Set;
import java.util.TreeSet;

/**
 * Superclass for commands that have one or more pre-determined keywords.
 */
public abstract class KeywordCommand implements Command {
    protected Set<String> keywords;

    public KeywordCommand() { keywords = new TreeSet(); }

    public void registerTo(Narrator narrator) {
        for (String keyword : keywords) {
            narrator.registerCommandAlias(keyword, this);
        }
    }
}
