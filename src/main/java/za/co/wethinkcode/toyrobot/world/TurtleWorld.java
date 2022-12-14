package za.co.wethinkcode.toyrobot.world;

import org.turtle.StdDraw;
import org.turtle.Turtle;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.AbstractMaze;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.awt.*;
import java.util.List;

public class TurtleWorld extends AbstractWorld{
    private Turtle turtle;


    public TurtleWorld(Maze maze) {
        super(maze);
        StdDraw.setCanvasSize(1000, 1000);
        drawTurtle();
        drawBorder();
        startingValues();
    }

    private void startingValues() {
        turtle.setAngle(90.0);
        turtle.setColor(Color.BLACK);
    }

    private void drawBorder() {
        turtle.setColor(Color.RED);
        turtle.setPosition(-101.0, -201.0);
        turtle.setAngle(0.0);
        turtle.forward(202);
        turtle.left(90.0);
        turtle.forward(402);
        turtle.left(90.0);
        turtle.forward(202);
        turtle.left(90.0);
        turtle.forward(402);

    }


    private void drawTurtle(){
        double x = 0.0;
        double y = 0.0;
        double a = 0.0;
        turtle = new Turtle(x, y, a);
        StdDraw.setXscale(-250, 250);
        StdDraw.setYscale(-250, 250);

    }

    public void showObstacles() {
        List<Obstacle> obstaclesList = maze.getObstacles();
        if(obstaclesList.size() != 0){
            System.out.println("There are obstacles: ");
            for (int i = 0; i < obstaclesList.size(); i++) {
                System.out.println("[" + obstaclesList.get(i).getBottomLeftX() + ","
                        + obstaclesList.get(i).getBottomLeftY() + "] "
                        + "to [" + (obstaclesList.get(i).getBottomLeftX() + 4) + ","
                        + (obstaclesList.get(i).getBottomLeftY() + 4) + "]");

            }
            for (Obstacle s : obstaclesList) {

                turtle.setColor(Color.BLUE);
                turtle.setPosition(s.getBottomLeftX(), s.getBottomLeftY());
                turtle.forward(4);
                turtle.right(90);
                turtle.forward(4);
                turtle.right(90);
                turtle.forward(4);
                turtle.right(90);
                turtle.forward(4);
                turtle.right(90);
                turtle.forward(4);
            }
            turtle.setPosition(0,0);
            turtle.setColor(Color.BLACK);

        }

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
            turtle.forward(nrSteps);
            return za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse.SUCCESS;
        }

        return za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse.FAILED_OUTSIDE_WORLD;
    }

    public void updateDirection(boolean turnRight) {
        if(turnRight){
            turtle.right(90);
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
            turtle.left(90);
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

}
