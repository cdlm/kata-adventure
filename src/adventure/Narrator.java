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
 * {@linkplain #registerCommand(String, Command) register} all the commands that the narrator should recognize.
 *
 * @see Command
 * @see Adventure
 */
public class Narrator {
    protected Map<String, Command> commands;
    protected Adventure adventure;

    /**
     * @param adventure The Adventure that will be narrated to the player.
     */
    public Narrator(Adventure adventure) {
        this.adventure = adventure;
        commands = new TreeMap<String, Command>();
        // TODO
    }

    /**
     * Registers a keyword to recognize, associating a {@link Command} with it.
     *
     * @param keyword The command's keyword; if the same keyword is registered again, the new registration overwrites
     *                the previous one.
     * @param command A command object which will be able to recognize subsequent words following the {@code keyword}.
     */
    public void registerCommand(String keyword, Command command) {
        commands.put(keyword, command);
    }

    /**
     * React to a command line typed by the player.
     *
     * @param commandLine The full command line as typed by the user.
     * @return Text to display to the user as a result of the command.
     */
    public String react(String commandLine) {
        String[] words = commandLine.split("[ ]+");
        String keyword = words[0];
        Command command = this.recognizeCommand(keyword);
        return command.invoke(adventure, words);
    }

    /**
     * Find the {@link Command} object registered to handle the given {@code keyword}.
     *
     * @return A {@link Command} instance that matches the keyword typed.
     */
    public Command recognizeCommand(String keyword) {
        if (commands.containsKey(keyword)) {
            return commands.get(keyword);
        } else {
            return new Huh();
        }
    }


    /**
     * A dummy command, for when the narrator does not recognize the given keyword (Null Object pattern).
     */
    public class Huh implements Command {

        public String invoke(Adventure adventure, String[] words) {
            return "Huh? Your words made no sense.";
        }
    }


    /**
     * A sample Narrator setup, reading commands from the standard input and displaying results to the standard output.
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
