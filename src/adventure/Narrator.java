package adventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Narrator {
    public Narrator(Adventure adventure) {
        // TODO
    }

    public String react(String commandLine) {
        Command action = this.recognizeAction(commandLine);
        action.perform();
        return action.narration();
    }

    public Command recognizeAction(String commandLine) {
        String[] words = commandLine.split("[ ]+");
        return new Huh(words);
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

    public static class Huh implements Command {
        protected String[] words;

        public Huh(String[] words) { this.words = words; }

        public void perform() { /* do nothing */ }

        public String narration() {
            return "Huh? Your words made no sense.";
        }
    }
}
