package labyrinth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Saisie et représentation des commandes du joueur.
 *
 * Les commandes sont composées d'un verbe et d'un argument optionnel.
 */
public class Command {
    protected static String[] validVerbs = new String[]{"aller", "plan", "quitter", "aide"};

    protected String verb, argument;

    public Command(String verb, String argument) {
        this.verb = verb;
        this.argument = argument;
    }

    /**
     * @return <code>true</code> si cette commande n'est pas correctement formulée.
     */
    public boolean isUnknown() {
        return verb == null || !isValidVerb(verb);
    }

    public String getVerb() { return verb; }

    public String getArgument() { return argument; }

    public boolean hasArgument() { return argument != null; }

    public String toString() {
        return "Command(" + verb + "," + argument + ")";
    }

    /**
     * Affiche l'ensemble des commandes disponibles.
     */
    public static void printAll() {
        for (String s : validVerbs) {
            System.out.print(" " + s);
        }
        System.out.println();
    }

    /**
     * @param verb Un verbe d'action saisi par le joueur.
     * @return <code>true</code> si <code>verb</code> fait partie des commandes disponibles.
     */
    public static boolean isValidVerb(String verb) {
        for (String str : validVerbs) {
            if (verb.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saisie d'une commande.
     *
     * @return une instance représentant la commande saisie par le joueur, avec son argument.
     */
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
        // on ignore le reste de la ligne saisie

        return new Command(verb, argument);
    }
}
