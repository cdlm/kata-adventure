package adventure;

public abstract class Command {
    protected Adventure adventure;
    protected String[] words;

    public Command(Adventure adventure, String[] words) {
        this.adventure = adventure;
        this.words = words;
    }

    public abstract String narration();

    public void perform() { /* do nothing by default */ }

    public static CommandFactory factory() {
        return new CommandFactory() {
            public Command make(Adventure adventure, String[] words) { return new Huh(adventure, words); }
        };
    }

    public static class Huh extends Command {

        public Huh(Adventure adventure, String[] words) { super(adventure, words); }

        public String narration() {
            return "Huh? Your words made no sense.";
        }
    }
}
