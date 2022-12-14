package za.co.wethinkcode.toyrobot.maze;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmptyMazeTest {

    @Test
    void getObstacles() {
        Maze maze = new EmptyMaze();
        assertEquals(maze.getObstacles().size(), 0);
    }
}