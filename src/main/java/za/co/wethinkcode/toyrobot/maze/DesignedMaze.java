package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.world.SquareObstacle;


public class DesignedMaze extends AbstractMaze {

    public DesignedMaze() {
        draw_x();
        draw_y();
    }


    public void draw_x() {
        for (int i = 0; i < 55; i += 5) {
            addToObstacleList(new SquareObstacle(-50 + i, 51));
        }
        for (int i = 0; i < 120; i += 5) {
            addToObstacleList(new SquareObstacle(-50 + i, -150));
        }
        for (int i = 0; i < 160; i += 5) {
            addToObstacleList(new SquareObstacle(-90 + i, 115));
        }
        for (int i = 0; i < 180; i += 5) {
            addToObstacleList(new SquareObstacle(-90 + i, -180));
        }
        for (int i = 0; i < 195; i += 5) {
            addToObstacleList(new SquareObstacle(-100 + i, 170));
        }

    }


    public void draw_y() {
        for (int i = 0; i < 150; i += 5) {
            addToObstacleList(new SquareObstacle(1, -100 + i));
        }
        for (int i = 0; i < 205; i += 5) {
            addToObstacleList(new SquareObstacle(-50, -150 + i));
        }
        for (int i = 0; i < 270; i += 5) {
            addToObstacleList(new SquareObstacle(70, -150 + i));
        }
        for (int i = 0; i < 300; i += 5) {
            addToObstacleList(new SquareObstacle(-90, -180 + i));
        }
        for (int i = 0; i < 350; i += 5) {
            addToObstacleList(new SquareObstacle(90, -180 + i));
        }
    }
}