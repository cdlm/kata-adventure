package labyrinth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Command {
    protected static String[] validCommands = new String[]{"aller", "plan", "quitter", "aide"};

    public static void printAll() {
        for (String s : validCommands) {
            System.out.print(" " + s);
        }
        System.out.println();
    }

    public static boolean isValidVerb(String verb) {
        for (String str : validCommands) {
            if (verb.equals(str)) {
                return true;
            }
        }
        return false;
    }

    protected static Command readCommand() {
        System.out.print("> "); // affiche l'invite

        String inputLine = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();
        } catch (IOException exc) {
            System.out.println("Il y a eu une erreur en cours de lecture :"
                    + exc.getMessage());
        }

        StringTokenizer tokenizer = new StringTokenizer(inputLine);
        String verb = null;
        String argument = null;

        if (tokenizer.hasMoreTokens()) {
            verb = tokenizer.nextToken();
        }
        if (tokenizer.hasMoreTokens()) {
            argument = tokenizer.nextToken();
        }
        // on ignore le reste de la ligne entrée

        return new Command(verb, argument);
    }


    protected String verb, argument;

    public Command(String verb, String argument) {
        this.verb = verb;
        this.argument = argument;
    }

    public boolean isUnknown() {
        return verb == null || !isValidVerb(verb);
    }

    public String getVerb() { return verb; }
    public String getArgument() { return argument; }
    public boolean hasArgument() { return argument != null; }

    public String toString() {
        return "Command(" + verb + "," + argument + ")";
    }
}
