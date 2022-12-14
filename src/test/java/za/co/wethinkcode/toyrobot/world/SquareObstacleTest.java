package za.co.wethinkcode.toyrobot.world;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Position;

import static org.junit.jupiter.api.Assertions.*;

class SquareObstacleTest {

    @Test
    void getBottomLeftX() {
        Obstacle obstacle = new SquareObstacle(1, 1);
        assertEquals(obstacle.getBottomLeftX(), 1);
    }

    @Test
    void getBottomLeftY() {
        Obstacle obstacle = new SquareObstacle(1, 1);
        assertEquals(obstacle.getBottomLeftY(), 1);
    }

    @Test
    void getSize() {
        Obstacle obstacle = new SquareObstacle(1, 1);
        assertEquals(obstacle.getSize(), 5);
    }

    @Test
    void blocksPosition() {
        Obstacle obstacle = new SquareObstacle(1, 1);
        assertTrue(obstacle.blocksPosition(new Position(1, 1)));
        assertTrue(obstacle.blocksPosition(new Position(5, 1)));
        assertTrue(obstacle.blocksPosition(new Position(1, 5)));
        assertTrue(obstacle.blocksPosition(new Position(5, 5)));
        assertFalse(obstacle.blocksPosition(new Position(0, 0)));
        assertFalse(obstacle.blocksPosition(new Position(4, 7)));
        assertFalse(obstacle.blocksPosition(new Position(100, 3)));
    }

    @Test
    void blocksPath() {
        Obstacle obstacle = new SquareObstacle(1, 1);
        assertFalse(obstacle.blocksPath(new Position(0, 0), new Position(0, 100)));
        assertTrue(obstacle.blocksPath(new Position(1, 1), new Position(-1, 1)));
        assertTrue(obstacle.blocksPath(new Position(3, -10), new Position(3, 25)));
        assertTrue(obstacle.blocksPath(new Position(-50, 3), new Position(25, 3)));
        assertFalse(obstacle.blocksPath(new Position(-20, -20), new Position(-20, 40)));
    }
}