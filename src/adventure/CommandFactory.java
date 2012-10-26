package adventure;

public interface CommandFactory {
    public Command make(Adventure adventure, String[] words);
}
