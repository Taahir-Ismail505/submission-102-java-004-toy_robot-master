package za.co.wethinkcode.toyrobot.maze;


import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class AbstractMaze implements Maze {
    private final List<Obstacle> obstacles = new ArrayList<>();


    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
        for (Obstacle obs: this.getObstacles()) {
            if (obs.blocksPath(a,b)){
                return true;
            }
        }
        return false;
    }

    public void addToObstacleList(Obstacle obstacle) {
        this.obstacles.add(obstacle);
    }

}
