package adventure;

public abstract class Command {
    protected String[] words;

    public Command(String[] words) { this.words = words; }

    public abstract String narration();

    public void perform() { /* do nothing by default */ }

    public static CommandFactory factory() {
        return new CommandFactory() {
            public Command make(String[] words) { return new Huh(words); }
        };
    }

    public static class Huh extends Command {

        public Huh(String[] words) { super(words); }

        public String narration() {
            return "Huh? Your words made no sense.";
        }
    }
}
