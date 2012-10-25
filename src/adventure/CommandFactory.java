package adventure;

public interface CommandFactory {
    public Command make(String[] words);
}
