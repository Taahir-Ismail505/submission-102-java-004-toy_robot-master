package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.MazeRunCommand;
import za.co.wethinkcode.toyrobot.Play;
import za.co.wethinkcode.toyrobot.MazeRunCommand;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleMazeRunnerCommandTest {

    @Test
    void getRunnerName() {
        MazeRunCommand test = new MazeRunCommand("top");
        assertEquals("mazerun", test.getName());
        assertEquals("top", test.getArgument());
    }

    @Test
    void executeRunnerTop() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nmazerun top\noff\n".getBytes());
        System.setIn(mockedInput);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Play.main(new String[]{"text", "simplemaze"});
        String actualOutput = outputStreamCaptor.toString();
        assertTrue(actualOutput.contains("I am at the top edge. (Cost: "));
    }

    @Test
    void executeRunnerRight() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nmazerun right\noff\n".getBytes());
        System.setIn(mockedInput);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Play.main(new String[]{"text", "simplemaze"});
        String actualOutput = outputStreamCaptor.toString();
        assertTrue(actualOutput.contains("I am at the right edge. (Cost: "));
    }

    @Test
    void executeRunnerLeft() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nmazerun left\noff\n".getBytes());
        System.setIn(mockedInput);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Play.main(new String[]{"text", "simplemaze"});
        String actualOutput = outputStreamCaptor.toString();
        assertTrue(actualOutput.contains("I am at the left edge. (Cost: "));
    }

    @Test
    void executeRunnerBottom() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nmazerun bottom\noff\n".getBytes());
        System.setIn(mockedInput);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Play.main(new String[]{"text", "simplemaze"});
        String actualOutput = outputStreamCaptor.toString();
        assertTrue(actualOutput.contains("I am at the bottom edge. (Cost: "));
    }

}