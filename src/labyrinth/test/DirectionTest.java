package labyrinth.test;

import junit.framework.TestCase;
import labyrinth.Direction;

public class DirectionTest extends TestCase {

    public void testNamed() {
        assertSame(Direction.NORTH, Direction.named("nord"));
        assertSame(Direction.SOUTH, Direction.named("sud"));
        assertSame(Direction.EAST, Direction.named("est"));
        assertSame(Direction.WEST, Direction.named("ouest"));
    }
}
