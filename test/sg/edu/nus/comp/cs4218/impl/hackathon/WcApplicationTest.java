package sg.edu.nus.comp.cs4218.impl.hackathon;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.FilesUtils.*;

public class WcApplicationTest {
    private static final String INITIAL_DIRECTORY = Paths.get(Environment.currentDirectory).toString();

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DIRECTORY = "dir1";
    private static final String NUMBER_FORMAT = " %7d";
    private static final String DIRECTORY_MESSAGE = "wc: This is a directory";

    public static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public static final PrintStream ORIGINAL_OUT = System.out;

    @BeforeAll
    static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
    }

    @BeforeEach
    void init() {
        createDirectory(DIRECTORY);
    }

    @AfterEach
    void terminate() throws IOException {
        Environment.currentDirectory = INITIAL_DIRECTORY;
        deleteDirectory(DIRECTORY);
    }

    @AfterEach
    void setUp() {
        Environment.currentDirectory = INITIAL_DIRECTORY;
    }

    @Test
    public void wc_WithDirectory_DisplaysFolderNameAndCount() throws Exception {
        outContent.reset();
        String commandString = "wc " + DIRECTORY;
        new ShellImpl().parseAndEvaluate(commandString, outContent);
        String expected = DIRECTORY_MESSAGE + NEW_LINE + String.format(NUMBER_FORMAT, 0)
                + String.format(NUMBER_FORMAT, 0) + String.format(NUMBER_FORMAT, 0) + " " + DIRECTORY + NEW_LINE;
        assertEquals(expected, outContent.toString());
    }
}
