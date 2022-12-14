package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.List;
import java.util.Random;

public class RandomMaze extends AbstractMaze {
    private final Random random = new Random();
    private final int obstacleCount;

    public RandomMaze() {
        this.obstacleCount = randomNumber(1, 100);
        generateObstacles();
    }

    public int getObstacleCount() {
        return obstacleCount;
    }

    public int randomNumber(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public void generateObstacles() {
        for (int i = 0; i < getObstacleCount(); i++) {
            Obstacle obstacle = new SquareObstacle(randomNumber(-100, 97), randomNumber(-200, 197));
            while (obstacleBlocked(obstacle)) {
                obstacle = new SquareObstacle(randomNumber(-100, 97), randomNumber(-200, 197));
            }
            this.getObstacles().add(obstacle);

        }
    }

    public boolean obstacleBlocked(Obstacle obstacle) {
        for (Obstacle obs : getObstacles()) {
            if (obs.blocksPosition(new Position(obstacle.getBottomLeftX(), obstacle.getBottomLeftY())) ||
                    obs.blocksPosition(new Position(obstacle.getBottomLeftX() + 4, obstacle.getBottomLeftY())) ||
                    obs.blocksPosition(new Position(obstacle.getBottomLeftX(), obstacle.getBottomLeftY() + 4)) ||
                    obs.blocksPosition(new Position(obstacle.getBottomLeftX() + 4, obstacle.getBottomLeftY() + 4)) ||
                    obs.blocksPosition(new Position(0, 0))) {
                return true;
            }
        }
        return false;
    }
}