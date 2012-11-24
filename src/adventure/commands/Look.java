package adventure.commands;

import adventure.Adventure;
import adventure.Artifact;
import adventure.Way;

/**
 * The "look" command makes the hero look around herself. It has no effect on the adventure, so the
 * player can invoke it as many times as necessary.
 *
 * @see adventure.Location
 * @see Adventure
 */
public class Look extends KeywordCommand {

    public Look() {
        super();
        keywords.add("look");
    }

    public String invoke(Adventure adventure, String[] words) {
        StringBuilder text = new StringBuilder();
        text.append(adventure.currentLocation().description())
                .append("\n\n");
        describeArtifacts(adventure, text);
        describeWays(adventure, text);
        return text.toString();
    }

    protected void describeArtifacts(Adventure adventure, StringBuilder text) {
        for (Artifact a : adventure.environmentArtifacts()) {
            text.append("You see a ")
                    .append(a.name())
                    .append(".\n");
        }
    }

    protected void describeWays(Adventure adventure, StringBuilder text) {
        for (Way w : adventure.availableWays()) {
            text.append("You see a ")
                    .append(w.name())
                    .append(".\n");
        }
    }
}
