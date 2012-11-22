package adventure;

/**
 * Command and its subclasses are responsible for reacting to user commands, modifying the game
 * world, and providing a description of the results. Each command interprets the words the user
 * typed as needed, and is responsible for correctly handling cases where the user input is not
 * meaningful.
 * <p/>
 * Commands are performed from the point of view of the hero, i.e. in the context of an {@link
 * Adventure} instance, accessing its current location, etc. Performing a command can (but doesn't
 * have to) change the state of the game world.
 * <p/>
 * For a command to be available to the player, it must be {@link Narrator#registerCommandAlias(String, Command)
 * registered to the narrator} with at least one keyword.
 *
 * @see Narrator
 */
public interface Command {

    /**
     * Change the world/game state according to the command's specification, returning a narration
     * of what happens as a result.
     * <p/>
     * All commands should provide consistent narrations, beginning by a concise statement
     * describing what action the hero takes, followed by a more elaborate description of what
     * happens as a result. For the sake of style, narrations should be written in second-person
     * form, as if the Narrator is relating events to the player/hero. .
     *
     * @param adventure The context in which to invoke the command.
     * @param words     Keyword and following words, to be interpreted by each implementor.
     * @return The text that the Narrator will display to the user.
     */
    public String invoke(Adventure adventure, String[] words);

}
