package adventure;

public abstract class Describable {
    protected String name;
    protected String description;

    public Describable(String name) {
        this.name = name;
        this.description = "";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String name() { return name; }

    public String description() { return description; }

    public String fullDescription() {
        StringBuilder desc = new StringBuilder(name);
        if (!description.isEmpty()) {
            desc.append('\n').append(description);
        }
        return desc.toString();
    }

}
