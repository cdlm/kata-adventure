package adventure;

/**
 * Command and its subclasses are responsible for reacting to user commands, modifying the game world, and providing a
 * description of the results. Each command interprets the words the user typed as needed, and is responsible for
 * correctly handling cases where the user input is not meaningful.
 * <p/>
 * Commands are performed from the point of view of the hero, i.e. in the context of an {@Link Adventure} instance,
 * accessing its current location, etc. Performing a command can (but doesn't have to) change the state of the game
 * world.
 *
 * @see Narrator
 */
public abstract class Command {
    protected Adventure adventure;
    protected String[] words;

    public Command(Adventure adventure, String[] words) {
        this.adventure = adventure;
        this.words = words;
    }

    /**
     * Provide a brief description of the results of the command.
     * <p/>
     * All commands should provide consistent narrations, beginning by a concise statement describing what action the
     * hero takes, followed by a more elaborate description of what happens as a result. For the sake of style,
     * narrations should be written in second-person form, as if the Narrator is relating events to the player/hero.
     *
     * @return The text that the Narrator will display to the user.
     */
    public abstract String narration();

    /**
     * Change the world/game state according to the command's specification.
     * <p/>
     * This method provides a default no-op implementation to avoid having to define it in subclasses that don't change
     * the world.
     */
    public void perform() { /* do nothing by default */ }

    public static CommandFactory factory() {
        return new CommandFactory() {
            public Command make(Adventure adventure, String[] words) { return new Huh(adventure, words); }
        };
    }

    /**
     * A dummy command, to use when no other commands match the given keyword (Null Object pattern).
     */
    public static class Huh extends Command {

        public Huh(Adventure adventure, String[] words) { super(adventure, words); }

        public String narration() {
            return "Huh? Your words made no sense.";
        }
    }
}
