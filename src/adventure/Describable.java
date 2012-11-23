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

    /**
     * Checks if the name and description match all of the given keywords.
     *
     * @param keywords Keywords to match.
     * @return {@code true} if the way matches.
     */
    public boolean matches(String[] keywords) {
        for (String kwd : keywords) {
            if (!(name.contains(kwd) || description.contains(kwd))) {
                return false;
            }
        }
        return true;
    }
}
