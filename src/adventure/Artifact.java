package adventure;

public class Artifact extends Describable {
    public Artifact(String name) {
        super(name);
    }

    public boolean isTransportable() { return true; }
    public boolean isOpenable() { return false; }
}
