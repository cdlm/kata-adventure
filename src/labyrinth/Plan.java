package labyrinth;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Plan {
    protected Room initialRoom;
    protected List<Room> mappedRooms;
    protected int minX, maxX, minY, maxY;

    public Plan(Room initialRoom) {
        this.initialRoom = initialRoom;
        mappedRooms = new LinkedList<Room>();
        mapRoom(initialRoom, 0, 0);
    }

   protected void mapRoom(Room room, int x, int y) {
        mappedRooms.add(room);
        room.setXY(x, y);
        if (x < minX) minX = x;
        if (x > maxX) maxX = x;
        if (y < minY) minY = y;
        if (y > maxY) maxY = y;
        for (Direction d : Direction.values()) {
            Room exit = room.getExit(d);
            if (exit != null && exit.isVisited() && !mappedRooms.contains(exit)) {
                mapRoom(exit, room.getX() + d.dx, room.getY() + d.dy);
            }
        }
    }

    public String toString() {
        Room[][] grid = new Room[maxX][maxY];
        Collections.sort(mappedRooms, new Comparator<Room>() {
            @Override
            public int compare(Room a, Room b) {
                if (a.getY() < b.getY()) return -1;
                if (a.getY() > b.getY()) return 1;
                if (a.getX() < b.getX()) return -1;
                if (a.getX() > b.getX()) return 1;
                return 0;
            }
        });
        StringBuilder result = new StringBuilder();
        int x = minX, y = minY;
        for (Room r : mappedRooms) {
            if (y < r.getY()) {
                x = minX;
                y++;
                result.append("\n");
            }
            while (x < r.getX()) {
                result.append("   ");
                x++;
            }
            result.append(r == initialRoom ? "[i]" : "[ ]");
            x++;
        }
        return result.toString();
    }
}
