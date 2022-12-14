package za.co.wethinkcode.toyrobot;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move back by specified number of steps, e.g. 'BACK 5'\n" +
                "LEFT - turns the robot to the left\n"+
                "RIGHT - turns the robot to the right\n"+
                "SPRINT - moves the robot forward quickly by specified number of steps, e.g. 'SPRINT 10'\n" +
                "REPLAY - repeats all previous movement commands");
        return true;
    }
}
