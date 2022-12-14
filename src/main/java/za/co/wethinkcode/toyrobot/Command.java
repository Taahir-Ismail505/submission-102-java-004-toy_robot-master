package za.co.wethinkcode.toyrobot;

public abstract class Command {
    private final String name;
    private String argument;

    public abstract boolean execute(Robot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]) {
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                try {
                    return new ForwardCommand(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Unsupported command: " + instruction);
                }
            case "back":
                try {
                    return new BackCommand(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Unsupported command: " + instruction);
                }
            case "right":
                return new RightCommand();
            case "left":
                return new LeftCommand();
            case "sprint":
                try {
                    return new SprintCommand(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Unsupported command: " + instruction);
                }
            case "replay":
                return new ReplayCommand(instruction.toLowerCase().trim()
                        .replace("replay", "").replaceAll("\\s", ""));
            case "mazerun":
                try {
                    return new MazeRunCommand(args[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new MazeRunCommand("top");
                }
            default:
        }       throw new IllegalArgumentException("Unsupported command: " + instruction);
    }
}

