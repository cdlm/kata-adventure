package adventure;

import adventure.commands.Go;
import adventure.commands.Look;
import adventure.commands.Quit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Narrator is the interface between the user/player and the rest of the game. It works as a
 * simple command-like interpreter, waiting for a command to be typed, describing what happens then
 * to the user, and so on.
 * <p/>
 * To the user/player, commands are simple English-like clauses; technically, they are sequences of
 * alphabetic words separated by spaces and terminated by a newline character. The first word of a
 * command is its <em>keyword</em>, and is usually a verb. The keyword is used by the narrator to
 * determine how to interpret the rest of the command.
 * <p/>
 * A freshly created Narrator instance does not recognise any commands; the game initialization code
 * must {@linkplain #registerCommandAlias(String, Command) register} all the commands that the
 * narrator should recognize.
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
     * Registers a {@link Command} to recognize for the given keyword.
     * <p/>
     * If there is a previous command registration with this keyword, it will be overwritten.
     *
     * @param keyword The keyword used for registering the command, possibly one of many; commands
     *                can change behavior depending on which keyword was used to invoke them.
     * @param command A command object which will be able to interpret word sequences beginning with
     *                the given keyword.
     * @return {@code this} for usage fluidity.
     * @see Command#invoke(Adventure, String, String[])
     */
    public Narrator registerCommandAlias(String keyword, Command command) {
        commands.put(keyword, command);
        return this;
    }

    /**
     * Registers all basic commands.
     *
     * @return {@code this} for usage fluidity.
     */
    public Narrator registerBasicCommands() {
        new Quit().registerTo(this);
        new Look().registerTo(this);
        new Go().registerTo(this);
        return this;
    }

    /**
     * React to a command line typed by the player.
     *
     * @param commandLine The full command line as typed by the user.
     * @return Text to display to the user as a result of the command.
     */
    public String react(String commandLine) {
        String[] words = commandLine.toLowerCase().trim().split("[ ]+");
        String keyword = words[0];
        String[] rest = Arrays.copyOfRange(words, 1, words.length);
        Command command = this.recognizeCommand(keyword);
        return command.invoke(adventure, keyword, rest);
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
     * A dummy command, for when the narrator does not recognize the given keyword (Null Object
     * pattern).
     */
    public class Huh implements Command {
        public String invoke(Adventure adventure, String keyword, String[] terms) {
            return "Huh? Your words made no sense.";
        }
    }


    /**
     * A sample Narrator setup, reading commands from the standard input and displaying results to
     * the standard output.
     */
    public void runOnConsole() throws IOException {
        String prompt = " > ";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print(adventure.situation() + prompt);
            String commandLine = in.readLine();
            if (commandLine == null) {
                commandLine = Quit.ABORT;
            }
            System.out.println(this.react(commandLine));
        } while (!adventure.isFinished());
    }
}
