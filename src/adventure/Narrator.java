package adventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Narrator {
    protected Map<String, CommandFactory> commands;

    public Narrator(Adventure adventure) {
        commands = new TreeMap<String, CommandFactory>();
        // TODO
    }

    public void registerCommand(String keyword, CommandFactory factory) {
        commands.put(keyword, factory);
    }

    public String react(String commandLine) {
        Command action = this.recognizeAction(commandLine);
        action.perform();
        return action.narration();
    }

    public Command recognizeAction(String commandLine) {
        String[] words = commandLine.split("[ ]+");
        String keyword = words[0];
        if (commands.containsKey(keyword)) {
            return commands.get(keyword).make(words);
        } else {
            return new Command.Huh(words);
        }
    }

    public static void main(String[] args) throws IOException {
        Narrator narrator = new Narrator(new Adventure(new Location("foo")));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String commandLine;
        do {
            commandLine = in.readLine();
            System.out.println(narrator.react(commandLine));
        } while (commandLine != null);
    }
}