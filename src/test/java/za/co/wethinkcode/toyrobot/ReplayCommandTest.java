package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Play;
import za.co.wethinkcode.toyrobot.ReplayCommand;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReplayCommandTest {

    @Test
    void getReplayCommandName() {
        ReplayCommand test = new ReplayCommand("5");
        assertEquals("replay", test.getName());
        assertEquals("5", test.getArgument());
    }

    @Test
    void executeReplay() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 5\nforward 10\nreplay\noff\n".getBytes());
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
                "[0,15] HAL> Moved forward by 10 steps.\n" +
                "HAL> What must I do next?\n" +
                "[0,20] HAL> Moved forward by 5 steps.\n" +
                "[0,30] HAL> Moved forward by 10 steps.\n" +
                "[0,30] HAL> replayed 2 commands.\n" +
                "HAL> What must I do next?\n" +
                "[0,30] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeReplaySingle() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 5\nforward 10\nreplay 1\noff\n".getBytes());
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
                "[0,15] HAL> Moved forward by 10 steps.\n" +
                "HAL> What must I do next?\n" +
                "[0,25] HAL> Moved forward by 10 steps.\n" +
                "[0,25] HAL> replayed 1 commands.\n" +
                "HAL> What must I do next?\n" +
                "[0,25] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeReplayRange() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 5\nforward 10\nback 3\nreplay 3-1\noff\n".getBytes());
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
                "[0,15] HAL> Moved forward by 10 steps.\n" +
                "HAL> What must I do next?\n" +
                "[0,12] HAL> Moved back by 3 steps.\n" +
                "HAL> What must I do next?\n" +
                "[0,17] HAL> Moved forward by 5 steps.\n" +
                "[0,27] HAL> Moved forward by 10 steps.\n" +
                "[0,27] HAL> replayed 2 commands.\n" +
                "HAL> What must I do next?\n" +
                "[0,27] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeReplayReversed() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 5\nforward 10\nreplay reversed\noff\n".getBytes());
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
                "[0,15] HAL> Moved forward by 10 steps.\n" +
                "HAL> What must I do next?\n" +
                "[0,25] HAL> Moved forward by 10 steps.\n" +
                "[0,30] HAL> Moved forward by 5 steps.\n" +
                "[0,30] HAL> replayed 2 commands.\n" +
                "HAL> What must I do next?\n" +
                "[0,30] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeReplayReversedSingle() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 5\nforward 10\nreplay reversed 1\noff\n".getBytes());
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
                "[0,15] HAL> Moved forward by 10 steps.\n" +
                "HAL> What must I do next?\n" +
                "[0,25] HAL> Moved forward by 10 steps.\n" +
                "[0,25] HAL> replayed 1 commands.\n" +
                "HAL> What must I do next?\n" +
                "[0,25] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void executeReplayReversedRange() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 5\nforward 10\nback 3\nreplay reversed 3-1\noff\n".getBytes());
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
                "[0,15] HAL> Moved forward by 10 steps.\n" +
                "HAL> What must I do next?\n" +
                "[0,12] HAL> Moved back by 3 steps.\n" +
                "HAL> What must I do next?\n" +
                "[0,22] HAL> Moved forward by 10 steps.\n" +
                "[0,27] HAL> Moved forward by 5 steps.\n" +
                "[0,27] HAL> replayed 2 commands.\n" +
                "HAL> What must I do next?\n" +
                "[0,27] HAL> Shutting down...\n";
        assertEquals(expectedOutput, actualOutput);
    }
}