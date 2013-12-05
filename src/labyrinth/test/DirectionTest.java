package labyrinth.test;

import labyrinth.Direction;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class DirectionTest {

    @Test
    public void testNamed() {
        assertSame(Direction.NORTH, Direction.named("nord"));
        assertSame(Direction.SOUTH, Direction.named("sud"));
        assertSame(Direction.EAST, Direction.named("est"));
        assertSame(Direction.WEST, Direction.named("ouest"));
    }
}
