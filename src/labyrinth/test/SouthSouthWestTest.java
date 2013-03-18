package labyrinth.test;

import junit.framework.TestCase;
import labyrinth.Direction;
import labyrinth.Room;

public class SouthSouthWestTest extends TestCase {

    Room[] rooms = new Room[4];

    public void setUp() {
        rooms[0] = new Room("zero");
        rooms[1] = new Room("one");
        rooms[2] = new Room("two");
        rooms[3] = new Room("three");

        rooms[0].setExit(Direction.SOUTH, rooms[1]);

        rooms[1].setExit(Direction.NORTH, rooms[0]);
        rooms[1].setExit(Direction.SOUTH, rooms[2]);

        rooms[2].setExit(Direction.NORTH, rooms[1]);
        rooms[2].setExit(Direction.WEST, rooms[3]);

        rooms[3].setExit(Direction.EAST, rooms[2]);
    }

    public void testZeroExits() {
        assertEquals(" sud", rooms[0].exitDirections());
        assertSame(rooms[1], rooms[0].getExit(Direction.SOUTH));
    }

    public void testTakeExits() {
        Room finalRoom = rooms[0]
                .getExit(Direction.SOUTH)
                .getExit(Direction.SOUTH)
                .getExit(Direction.WEST);
        assertSame(rooms[3], finalRoom);
    }
}
