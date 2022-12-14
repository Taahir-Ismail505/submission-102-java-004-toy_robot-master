package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Play;
import za.co.wethinkcode.toyrobot.SprintCommand;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SprintCommandTest {

    @Test
    void getSprintName() {
        SprintCommand test = new SprintCommand("5");
        assertEquals("sprint", test.getName());
        assertEquals("5", test.getArgument());
    }

    @Test
    void executeSprint() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nsprint 5\noff\n".getBytes());
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
                "[0,9] HAL> Moved forward by 4 steps.\n" +
                "[0,12] HAL> Moved forward by 3 steps.\n" +
                "[0,14] HAL> Moved forward by 2 steps.\n" +
                "[0,15] HAL> Moved forward by 1 steps.\n" +
                "HAL> What must I do next?\n" +
                "[0,15] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sprintTooFarOutput() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nsprint 50\noff\n".getBytes());
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
                "[0,50] HAL> Moved forward by 50 steps.\n" +
                "[0,99] HAL> Moved forward by 49 steps.\n" +
                "[0,147] HAL> Moved forward by 48 steps.\n" +
                "[0,194] HAL> Moved forward by 47 steps.\n" +
                "[0,194] HAL> Sorry, I cannot go outside my safe zone.\n" +
                "HAL> What must I do next?\n" +
                "[0,194] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }
}