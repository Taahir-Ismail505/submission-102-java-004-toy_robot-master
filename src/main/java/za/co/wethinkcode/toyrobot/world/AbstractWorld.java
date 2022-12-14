package za.co.wethinkcode.toyrobot.world;


import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorld implements IWorld {
    public final Position TOP_LEFT = new Position(-100,200);
    public final Position BOTTOM_RIGHT = new Position(100,-200);

    Position position;
    za.co.wethinkcode.toyrobot.world.IWorld.Direction currentDirection ;
    Maze maze;
    List<Obstacle> obstaclesList = new ArrayList<>();


    public AbstractWorld(Maze maze){
        this.currentDirection = za.co.wethinkcode.toyrobot.world.IWorld.Direction.UP;
        this.maze = maze;
        this.position = CENTRE;
    }


    public za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (za.co.wethinkcode.toyrobot.world.IWorld.Direction.UP.equals(this.currentDirection)) {
            newY = newY + nrSteps;

        }else if (za.co.wethinkcode.toyrobot.world.IWorld.Direction.LEFT.equals(this.currentDirection)){
            newX = newX - nrSteps;

        }else if (za.co.wethinkcode.toyrobot.world.IWorld.Direction.RIGHT.equals(this.currentDirection)){
            newX = newX + nrSteps;

        }else if (za.co.wethinkcode.toyrobot.world.IWorld.Direction.DOWN.equals(this.currentDirection)){
            newY = newY - nrSteps;
        }

        Position newPosition = new Position(newX, newY);

        if(getMaze().blocksPath(getPosition(),newPosition)){
            return za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse.FAILED_OBSTRUCTED;
        }

        if (isNewPositionAllowed(newPosition)){
            this.position = newPosition;
            return za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse.SUCCESS;
        }

        return za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse.FAILED_OUTSIDE_WORLD;
    }


    public void updateDirection(boolean turnRight) {
        if(turnRight){
            if (currentDirection == za.co.wethinkcode.toyrobot.world.IWorld.Direction.UP){
                this.currentDirection = za.co.wethinkcode.toyrobot.world.IWorld.Direction.RIGHT;

            }else if (currentDirection == za.co.wethinkcode.toyrobot.world.IWorld.Direction.RIGHT){
                this.currentDirection = za.co.wethinkcode.toyrobot.world.IWorld.Direction.DOWN;

            }else if (currentDirection == za.co.wethinkcode.toyrobot.world.IWorld.Direction.DOWN){
                this.currentDirection = za.co.wethinkcode.toyrobot.world.IWorld.Direction.LEFT;

            }else if (currentDirection == za.co.wethinkcode.toyrobot.world.IWorld.Direction.LEFT){
                this.currentDirection =  za.co.wethinkcode.toyrobot.world.IWorld.Direction.UP;
            }

        }else{
            if (currentDirection == za.co.wethinkcode.toyrobot.world.IWorld.Direction.UP){
                this.currentDirection = za.co.wethinkcode.toyrobot.world.IWorld.Direction.LEFT;

            }else if (currentDirection == za.co.wethinkcode.toyrobot.world.IWorld.Direction.LEFT){
                this.currentDirection = za.co.wethinkcode.toyrobot.world.IWorld.Direction.DOWN;


            }else if (currentDirection == za.co.wethinkcode.toyrobot.world.IWorld.Direction.DOWN){
                this.currentDirection = za.co.wethinkcode.toyrobot.world.IWorld.Direction.RIGHT;


            }else if (currentDirection == za.co.wethinkcode.toyrobot.world.IWorld.Direction.RIGHT){
                this.currentDirection = za.co.wethinkcode.toyrobot.world.IWorld.Direction.UP;

            }
        }
    }


    public Position getPosition() {
        return this.position;
    }

    public za.co.wethinkcode.toyrobot.world.IWorld.Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public boolean isNewPositionAllowed(Position position) {
        return position.isIn(TOP_LEFT,BOTTOM_RIGHT);
    }

    public boolean isAtEdge() {
        return (getPosition().getX() == TOP_LEFT.getX() ||
                getPosition().getX() == BOTTOM_RIGHT.getX() ||
                getPosition().getY() == TOP_LEFT.getY() ||
                getPosition().getY() == BOTTOM_RIGHT.getY());
    }

    public void reset() {
        this.currentDirection = za.co.wethinkcode.toyrobot.world.IWorld.Direction.UP;
        this.position = CENTRE;

    }

    public List<Obstacle> getObstacles() {
        return obstaclesList;
    }

    public void showObstacles() {
        List<Obstacle> obstaclesList = maze.getObstacles();
        if(obstaclesList.size() != 0){
            System.out.println("There are obstacles: ");
            for (Obstacle obstacle : obstaclesList) {
                System.out.println("[" + obstacle.getBottomLeftX() + ","
                        + obstacle.getBottomLeftY() + "] "
                        + "to [" + (obstacle.getBottomLeftX() + 4) + ","
                        + (obstacle.getBottomLeftY() + 4) + "]");

            }

        }

    }

    public Maze getMaze(){
        return maze;
    }
}
