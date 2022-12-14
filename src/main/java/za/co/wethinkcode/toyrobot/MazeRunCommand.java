package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.MazeRunner;
import za.co.wethinkcode.toyrobot.maze.SimpleMazeRunner;
import za.co.wethinkcode.toyrobot.world.IWorld;

public class MazeRunCommand extends Command {

    public MazeRunCommand(String argument) { super("mazerun", argument);
    }

    /**
     * Command robot to to mazerun to the given edge.
     *
     * @param target the current robot object
     * @return `true`
     */
    @Override
    public boolean execute(Robot target) {
        IWorld.Direction edge;
        String printEdge;

        switch (getArgument()) {
            case "right":
                edge = IWorld.Direction.RIGHT;
                printEdge = "right";
                break;
            case "bottom":
                edge = IWorld.Direction.DOWN;
                printEdge = "bottom";
                break;
            case "left":
                edge = IWorld.Direction.LEFT;
                printEdge = "left";
                break;
            default:
                edge = IWorld.Direction.UP;
                printEdge = "top";
        }

        target.setStatus("Starting maze run..");
        Play.output(target);

        MazeRunner runner = new SimpleMazeRunner(target);

        if (runner.mazeRun(target, edge)) {
            target.setStatus("I am at the " + printEdge + " edge. (Cost: " + runner.getMazeRunCost() + " steps)");
        } else {
            target.setStatus("I am lost. (Cost: " + runner.getMazeRunCost() + " steps)");
        }

        return true;
    }
}
