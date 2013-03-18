package labyrinth;

public class Room {
    protected final String description;
    protected final Room[] exits = new Room[Direction.values().length];
    protected boolean visited;
    protected int mapX, mapY;

    public Room(String description) {
        this.description = description;
    }

    public String fullDescription() {
        return "Vous êtes " + this.description + ". (sorties :" + this.exitDirections() + ")";
    }

    public String exitDirections() {
        String result = "";
        for (Direction d : Direction.values()) {
            if (exits[d.index] != null) {
                result += " " + d.name;
            }
        }
        return result;
    }

    public void setExit(Direction direction, Room adjacent) {
        exits[direction.index] = adjacent;
    }

    public Room getExit(Direction direction) {
        return exits[direction.index];
    }

    public boolean enter(Game game) {
        System.out.println(fullDescription());
        beVisited();
        return false;
    }

    public void beVisited() { visited = true; }

    public boolean isVisited() { return visited; }

    public void setXY(int x, int y) {
        mapX = x;
        mapY = y;
    }

    public int getX() { return mapX; }

    public int getY() { return mapY; }
}
