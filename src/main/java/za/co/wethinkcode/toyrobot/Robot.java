package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.AbstractWorld;
import za.co.wethinkcode.toyrobot.world.IWorld;

import java.util.Arrays;
import java.util.List;

public class Robot {

    public static final Position CENTRE = new Position(0,0);

    private Position position;
    private Direction currentDirection;
    private String status;
    private String name;
    private History history;
    private AbstractWorld world;

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.position = CENTRE;
        this.history = new History();
        this.currentDirection = Direction.UP;

    }

    public String getStatus() {
        return this.status;
    }

    public AbstractWorld getWorld() {
        return world;
    }

    public void setWorld(AbstractWorld world) {
        this.world = world;
    }

    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public History getHistory(){
        return this.history;
    }

    public boolean handleCommand(Command command) {
        history.append(command);
        return command.execute(this);
    }


    @Override
    public String toString() {
        return "[" + this.world.getPosition().getX() + "," + this.world.getPosition().getY() + "] "
                + this.name + "> " + this.status;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void addCommand(String s) {
    }
}