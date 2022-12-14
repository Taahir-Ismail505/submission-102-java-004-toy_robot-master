package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.*;
import za.co.wethinkcode.toyrobot.world.AbstractWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

import java.util.Scanner;

public class Play {
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        AbstractWorld world;
        Maze maze;
        String useMaze= "RandomMaze";


        if(args.length >= 1 && (args[0].toLowerCase().trim().equalsIgnoreCase("text") ||
                args[0].toLowerCase().trim().equalsIgnoreCase("turtle"))) {
            if (args.length == 1) {
                maze = new RandomMaze();
            }else{
                switch (args[1].toLowerCase().trim()){
                    case "designedmaze":
                        maze = new DesignedMaze();
                        useMaze = "DesignedMaze";
                        break;

                    case "emptymaze":
                        maze = new EmptyMaze();
                        useMaze = "EmptyMaze";
                        break;

                    case "simplemaze":
                        maze = new SimpleMaze();
                        useMaze = "SimpleMaze";
                        break;
                    default:
                        maze = new RandomMaze();

                }

            }
            world = ((args[0].equalsIgnoreCase("turtle")) ?
                    new TurtleWorld(maze) : new TextWorld(maze));

        }else{
            world = new TextWorld(new RandomMaze());
        }


        Robot robot;

        String name = getInput("What do you want to name your robot?");
        robot = new Robot(name);
        System.out.println("Hello Kiddo!");
        System.out.println("Loaded " +useMaze+ ".");
        robot.setWorld(world);
        world.showObstacles();

        System.out.println(robot);


        Command command;
        boolean shouldContinue = true;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);
                shouldContinue = robot.handleCommand(command);
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
            }
            System.out.println(robot);
        } while (shouldContinue);

    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
    public static void output(Robot output){
        System.out.println(output);

    }
}
