package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {


    @Test
    void getBackName() {
        BackCommand test = new BackCommand("5");
        assertEquals("back", test.getName());
        assertEquals("5", test.getArgument());
    }

    @Test
    void executeBack() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nback 5\noff\n".getBytes());
        System.setIn(mockedInput);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Play.main(new String[]{"text", "emptymaze"});
        String actualOutput = outputStreamCaptor.toString();
        String expectedOutput = "What do you want to name your robot?\n" +
                "Hello Kiddo!\n" +
                "Loaded EmptyMaze.\n" +
                "[0,0] HAL> Ready\n" +
                "HAL> What must I do next?\n" +
                "[0,-5] HAL> Moved back by 5 steps.\n" +
                "HAL> What must I do next?\n" +
                "[0,-5] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void tooFarBack() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nback 500\noff\n".getBytes());
        System.setIn(mockedInput);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Play.main(new String[]{"text", "emptymaze"});
        String actualOutput = outputStreamCaptor.toString();
        String expectedOutput = "What do you want to name your robot?\n" +
                "Hello Kiddo!\n" +
                "Loaded EmptyMaze.\n" +
                "[0,0] HAL> Ready\n" +
                "HAL> What must I do next?\n" +
                "[0,0] HAL> Sorry, I cannot go outside my safe zone.\n" +
                "HAL> What must I do next?\n" +
                "[0,0] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void getShutdownName() {
        Command test = new ShutdownCommand();
        assertEquals("off", test.getName());
    }

    @Test
    void executeShutdown() {

        Robot robot = new Robot("CrashTestDummy");
        Command shutdown = Command.create("shutdown");
        assertFalse(shutdown.execute(robot));
        assertEquals("Shutting down...", robot.getStatus());
    }

    @Test
    void getForwardName() {
        ForwardCommand test = new ForwardCommand("5");
        assertEquals("forward", test.getName());
        assertEquals("5", test.getArgument());
    }

    @Test
    void executeForward() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 5\noff\n".getBytes());
        System.setIn(mockedInput);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Play.main(new String[]{"text", "emptymaze"});
        String actualOutput = outputStreamCaptor.toString();
        String expectedOutput = "What do you want to name your robot?\n" +
                "Hello Kiddo!\n" +
                "Loaded EmptyMaze.\n" +
                "[0,0] HAL> Ready\n" +
                "HAL> What must I do next?\n" +
                "[0,5] HAL> Moved forward by 5 steps.\n" +
                "HAL> What must I do next?\n" +
                "[0,5] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void tooFarForward() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 500\noff\n".getBytes());
        System.setIn(mockedInput);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Play.main(new String[]{"text", "emptymaze"});
        String actualOutput = outputStreamCaptor.toString();
        String expectedOutput = "What do you want to name your robot?\n" +
                "Hello Kiddo!\n" +
                "Loaded EmptyMaze.\n" +
                "[0,0] HAL> Ready\n" +
                "HAL> What must I do next?\n" +
                "[0,0] HAL> Sorry, I cannot go outside my safe zone.\n" +
                "HAL> What must I do next?\n" +
                "[0,0] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void getHelpName() {
        Command test = new HelpCommand();                                                               //<1>
        assertEquals("help", test.getName());
    }

    @Test
    void executeHelp() {
        Robot robot = new Robot("CrashTestDummy");
        Command help = Command.create("help");
        assertTrue(help.execute(robot));
        assertEquals("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move back by specified number of steps, e.g. 'BACK 5'\n" +
                "LEFT - turns the robot to the left\n" +
                "RIGHT - turns the robot to the right\n" +
                "SPRINT - moves the robot forward quickly by specified number of steps, e.g. 'SPRINT 10'\n" +
                "REPLAY - repeats all previous movement commands", robot.getStatus());
    }

    @Test
    void getLeftCommandName() {
        RightCommand test = new RightCommand();
        assertEquals("right", test.getName());
        assertEquals("", test.getArgument());
    }

    @Test
    void executeLeft() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nleft\noff\n".getBytes());
        System.setIn(mockedInput);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Play.main(new String[]{"text", "emptymaze"});
        String actualOutput = outputStreamCaptor.toString();
        String expectedOutput = "What do you want to name your robot?\n" +
                "Hello Kiddo!\n" +
                "Loaded EmptyMaze.\n" +
                "[0,0] HAL> Ready\n" +
                "HAL> What must I do next?\n" +
                "[0,0] HAL> Turned left.\n" +
                "HAL> What must I do next?\n" +
                "[0,0] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getRightCommandName() {
        RightCommand test = new RightCommand();
        assertEquals("right", test.getName());
        assertEquals("", test.getArgument());
    }

    @Test
    void executeRight() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nright\noff\n".getBytes());
        System.setIn(mockedInput);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Play.main(new String[]{"text", "emptymaze"});
        String actualOutput = outputStreamCaptor.toString();
        String expectedOutput = "What do you want to name your robot?\n" +
                "Hello Kiddo!\n" +
                "Loaded EmptyMaze.\n" +
                "[0,0] HAL> Ready\n" +
                "HAL> What must I do next?\n" +
                "[0,0] HAL> Turned right.\n" +
                "HAL> What must I do next?\n" +
                "[0,0] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void createCommand() {
        Command forward = Command.create("forward 10");                                                 //<1>
        assertEquals("forward", forward.getName());
        assertEquals("10", forward.getArgument());

        Command shutdown = Command.create("shutdown");                                                  //<2>
        assertEquals("off", shutdown.getName());

        Command help = Command.create("help");                                                          //<3>
        assertEquals("help", help.getName());
    }

    @Test
    void createInvalidCommand() {
        try {
            Command forward = Command.create("say hello");                                              //<4>
            fail("Should have thrown an exception");                                                    //<5>
        } catch (IllegalArgumentException e) {
            assertEquals("Unsupported command: say hello", e.getMessage());                             //<6>
        }
    }
}
