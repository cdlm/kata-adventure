package adventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Narrator is the interface between the user/player and the rest of the game. It works as a simple command-like
 * interpreter, waiting for a command to be typed, describing what happens then to the user, and so on.
 * <p/>
 * To the user/player, commands are simple English-like clauses; technically, they are sequences of alphabetic words
 * separated by spaces and terminated by a newline character. The first word of a command is its <em>keyword</em>, and
 * is usually a verb. The keyword is used by the narrator to determine how to interpret the rest of the command.
 * <p/>
 * A freshly created Narrator instance does will not recognise any commands; the game initialization code must
 * {@linkplain #registerCommand(String, CommandFactory) register} all the commands that the narrator should recognize.
 *
 * @see Command
 * @see Adventure
 */
public class Narrator {
    protected Map<String, CommandFactory> commands;
    protected Adventure adventure;

    /**
     * @param adventure The Adventure in which to interpret the commands.
     */
    public Narrator(Adventure adventure) {
        this.adventure = adventure;
        commands = new TreeMap<String, CommandFactory>();
        // TODO
    }

    /**
     * Registers a keyword to recognize, and associates a Command with it.
     *
     * @param keyword The command's keyword; if the same keyword is registered again, the new registration overwrites
     *                the previous one.
     * @param factory
     */
    public void registerCommand(String keyword, CommandFactory factory) {
        commands.put(keyword, factory);
    }

    /**
     * React to a command line typed by the user.
     *
     * @param commandLine The full command line as typed by the user.
     * @return Text to display to the user as a result of the command.
     */
    public String react(String commandLine) {
        Command action = this.recognizeAction(commandLine);
        action.perform();
        return action.narration();
    }

    /**
     * Analyze a command line and find the Command object to act upon it.
     *
     * @param commandLine The command line, as typed by the user, in a single String.
     * @return A {@link Command} instance that matches the keyword typed.
     */
    public Command recognizeAction(String commandLine) {
        String[] words = commandLine.split("[ ]+");
        String keyword = words[0];
        if (commands.containsKey(keyword)) {
            return commands.get(keyword).make(adventure, words);
        } else {
            return new Command.Huh(adventure, words);
        }
    }

    /**
     * A sample Narrator setup, reading commands from the standard input and displaying results to the standard output.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Narrator narrator = new Narrator(new Adventure(new Location("A nondescript place.")));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String commandLine;
        do {
            commandLine = in.readLine();
            System.out.println(narrator.react(commandLine));
        } while (commandLine != null);
    }
}
