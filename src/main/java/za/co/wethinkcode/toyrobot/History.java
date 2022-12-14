package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<String> command ;

    public History(){
        command = new ArrayList<>();
    }

    public List<String> getCommand(){
        return this.command;
    }

    public void append(Command command){
        switch (command.getName()){
            case "forward":
            case "back":
            case "sprint":
                this.command.add(command.getName() + " " +command.getArgument());
                break;

            case "left":
            case "right":
                this.command.add(command.getName());
                break;
        }
    }

    public void removeLast(){
        int i = this.command.size() -1;
        command.remove(i);
    }

}
