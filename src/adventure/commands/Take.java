package adventure.commands;

import adventure.Adventure;
import adventure.Artifact;

import java.util.List;

public class Take extends KeywordCommand {

    public Take() {
        super();
        keywords.add("take");
        keywords.add("grab");
        keywords.add("get");
    }

    public String invoke(Adventure adventure, String keyword, String[] terms) {
        List<Artifact> artifacts = adventure.currentLocation().findArtifacts(terms);
        switch (artifacts.size()) {
            case 0:
                return "Huh? There's no such thing at hand.\n";
            case 1:
                Artifact picked = artifacts.get(0);
                adventure.addToInventory(picked);
                adventure.currentLocation().removeArtifact(picked);
                return "You take the " + picked.name() + " with you.\n";
            default:
                return "I'm not sure which one you meant.\n";
        }
    }
}
