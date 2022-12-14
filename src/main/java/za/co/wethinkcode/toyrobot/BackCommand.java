package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class BackCommand extends Command {
    public BackCommand(String argument) {
        super("back", argument);
    }

    @Override
    public boolean execute (Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        IWorld.UpdateResponse r = target.getWorld().updatePosition(-nrSteps);
        if (r == IWorld.UpdateResponse.SUCCESS){
            target.setStatus("Moved back by "+nrSteps+" steps.");
        } else if (r == IWorld.UpdateResponse.FAILED_OBSTRUCTED) {
            target.setStatus("Sorry, there is an obstacle in the way.");
        }
        else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }
}
