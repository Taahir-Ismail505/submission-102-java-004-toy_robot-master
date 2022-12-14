package za.co.wethinkcode.toyrobot.maze;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomMazeTest {

    @Test
    void getObstacles() {
        Maze maze = new RandomMaze();
        assertTrue(maze.getObstacles().size() >= 0);
    }
}