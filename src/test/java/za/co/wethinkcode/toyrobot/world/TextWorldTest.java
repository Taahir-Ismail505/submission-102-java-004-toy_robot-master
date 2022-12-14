package za.co.wethinkcode.toyrobot.world;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.maze.SimpleMaze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextWorldTest {

    @Test
    void updatePosition() {
        Robot robot = new Robot("CrashTestDummy");
        robot.setWorld(new TextWorld(new SimpleMaze()));
        robot.getWorld().updatePosition(-10);
        assertEquals(robot.getWorld().getPosition(), new Position(0, -10));
    }

    @Test
    void updateDirection() {
        Robot robot = new Robot("CrashTestDummy");
        robot.setWorld(new TextWorld(new SimpleMaze()));
        robot.getWorld().updateDirection(true);
        assertEquals(robot.getWorld().getCurrentDirection(), IWorld.Direction.RIGHT);
    }

    @Test
    void getObstacles() {
        Robot robot = new Robot("CrashTestDummy");
        robot.setWorld(new TextWorld(new SimpleMaze()));
        assertEquals(robot.getWorld().getObstacles().size(), 0);

    }
}