package adventure;

import adventure.commands.Go;
import adventure.commands.Look;

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
 * {@linkplain #registerCommand(Command) register} all the commands that the narrator should recognize.
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
     * Registers a {@link Command} to be recognized from its default keyword.
     * <p/>
     * If there is a previous command registration with this keyword, it will be overwritten.
     *
     * @param command The command object which will be able to interpret word sequences beginning with its {@linkplain
     *                Command#defaultKeyword() default keyword}.
     * @return {@code this} for usage fluidity.
     */
    public Narrator registerCommand(Command command) {
        commands.put(command.defaultKeyword(), command);
        return this;
    }

    /**
     * Registers a {@link Command} to recognize, specifying an arbitrary keyword.
     * <p/>
     * If there is a previous command registration with this keyword, it will be overwritten.
     *
     * @param keyword The keyword used for registering the command, possibly different from the command's {@link
     *                Command#defaultKeyword() default one}; commands can change behavior depending on which keyword was
     *                used to invoke them.
     * @param command A command object which will be able to interpret word sequences beginning with the given keyword.
     * @return {@code this} for usage fluidity.
     */
    public Narrator registerCommandAlias(String keyword, Command command) {
        commands.put(keyword, command);
        return this;
    }

    /**
     * Registers all basic commands with predefined keywords.
     *
     * @return {@code this} for usage fluidity.
     */
    public Narrator registerBasicCommands() {
        registerCommand(new Look());
        registerCommand(new Go());
        return this;
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

        public String defaultKeyword() {
            return null; // Huh is a bit special
        }

        public String invoke(Adventure adventure, String[] words) {
            return "Huh? Your words made no sense.";
        }
    }


    /**
     * A sample Narrator setup, reading commands from the standard input and displaying results to the standard output.
     */
    public void runOnConsole() throws IOException {
        String prompt = " > ";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String commandLine;
        do {
            System.out.print(adventure.situation() + prompt);
            commandLine = in.readLine();
            System.out.println(this.react(commandLine));
        } while (commandLine != null);
    }
}
