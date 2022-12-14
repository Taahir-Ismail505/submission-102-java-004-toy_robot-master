package za.co.wethinkcode.toyrobot.maze;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.ForwardCommand;
import za.co.wethinkcode.toyrobot.world.TextWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DesignedMazeTest {

    @Test
    void getObstacles() {
        Maze maze = new DesignedMaze();
        assertEquals(maze.getObstacles().size(), 397);
    }

    @Test
    void runIntoObstacle() {
        Robot robot = new Robot("CrashTestDummy");
        robot.setWorld(new TextWorld(new DesignedMaze()));
        robot.handleCommand(new ForwardCommand("150"));
        assertEquals("[0,0] CrashTestDummy> There is an obstacle in the way.", robot.toString());
    }
}