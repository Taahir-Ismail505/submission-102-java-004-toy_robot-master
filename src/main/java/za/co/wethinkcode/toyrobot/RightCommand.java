package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.AbstractWorld;

public class RightCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        target.getWorld().updateDirection(true);
        target.setStatus("Turned right.");
        return true;
    }

    public RightCommand(){
        super("right");
    }
}
