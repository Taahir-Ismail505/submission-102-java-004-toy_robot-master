package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReplayCommand extends Command {

    public ReplayCommand(String argument){
        super("replay", argument);
    }

    @Override
    public boolean execute(Robot target){
        String stat = "";
        boolean reversed = getArgument().contains("reversed");
        List<String> commands = getCommands(target,reversed);
        int numComm = commands.size();
        for (String comm: commands) {
            Command temp = Command.create(comm);
            target.handleCommand(temp);
            String stats = target.toString();
            System.out.println(stats);
            target.getHistory().removeLast();
            stat = stat + stats + "\n";
        }
        target.setStatus("replayed " +numComm+ " commands.");
        return true;
    }

    private List<String> getCommands(Robot target, boolean reversed){
        int begin = 0;
        int end;
        List<String> commands = new ArrayList<>(target.getHistory().getCommand());
        String arg = getArgument().replace("reversed", "").replaceAll("\\s", "");

        if (arg.contains("-")) {
            String[] args = arg.split("-");
            begin = Integer.parseInt(args[1]);
            end = Integer.parseInt(args[0]);

        }else if (!arg.isBlank()) {
            end = Integer.parseInt(arg);

        }else {
            end = commands.size();
        }

        Collections.reverse(commands);
        try {
            commands = commands.subList(begin,end);

        }catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException();
        }

        if(!reversed) {
            Collections.reverse(commands);
        }

        return commands;
    }


}
