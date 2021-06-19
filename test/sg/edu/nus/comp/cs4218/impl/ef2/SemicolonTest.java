package sg.edu.nus.comp.cs4218.impl.ef2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;

import java.io.IOException;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

public class SemicolonTest {

    private static final String CURR_DIR = Environment.currentDirectory;
    private static final String NEW_LINE = System.lineSeparator();
    private static final String ECHO_LINE = "Hello, we love testing!";
    private static final String TESTFILE1 = "testFile1";
    private static final String TESTFILE2 = "testFile2;";
    private static final String DOUBLE_QUOTES = "\"";
    private static final String FILE1_CONTENT = "I like test cases and I cannot lie." + NEW_LINE + "ThE othEr brothErs cannot dEny.";
    private static final String FILE2_CONTENT = "this is semicolon file" + NEW_LINE + "I like test cases and I cannot lie.";
    private static final String PATTERN = "e";
    private static final String SHELL_PREFIX = "shell: ";
    private ShellImpl shell;
    private ApplicationRunner appRunner;
    private static final String SEMICOLON = "; ";

    @BeforeAll
    static void setupAll() {
        Environment.currentDirectory = CURR_DIR + CHAR_FILE_SEP + "testFolders" + CHAR_FILE_SEP + "util" + CHAR_FILE_SEP + "semicolonFolder";
    }

    @AfterAll
    static void terminate() {
        Environment.currentDirectory = CURR_DIR;
    }

    @BeforeEach
    void setUp() throws IOException {
        shell = new ShellImpl();
        appRunner = new ApplicationRunner();
    }

    @Test
    public void oneSemicolon_EmptySemicolon_NothingDone() throws Exception{
        String commandString = SEMICOLON;
        // can add stub for CommandBuilder?
        assertThrows(ShellException.class, () -> CommandBuilder.parseCommand(commandString, appRunner));
    }

    @Test
    public void echo_SemiColonInQuotes_OutputsSemicolonAsPartOfEcho() throws Exception {
        String commandString = APP_ECHO + CHAR_SPACE + DOUBLE_QUOTES + ECHO_LINE + SEMICOLON + DOUBLE_QUOTES;
        String actualOutput = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals(ECHO_LINE + SEMICOLON + NEW_LINE, actualOutput);
    }

    @Test
    public void echoAndCat_EchoSemiColonWithoutQuotes_ExecutesExpectedBehaviour() throws Exception {
        String commandString = APP_ECHO + CHAR_SPACE + ECHO_LINE + SEMICOLON + APP_CAT + CHAR_SPACE + TESTFILE1;
        String actualOutput = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals(ECHO_LINE + NEW_LINE + FILE1_CONTENT + NEW_LINE, actualOutput);
    }

    @Test
    public void grep_FileNameWithSemicolonInQuotes_OutputsContent() throws Exception {
        String commandString = APP_GREP + CHAR_SPACE + PATTERN + CHAR_SPACE + DOUBLE_QUOTES + TESTFILE2 + DOUBLE_QUOTES;
        String actualOutput = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals(FILE2_CONTENT + NEW_LINE, actualOutput);
    }

    @Test
    public void echo_AdjacentSemicolon_ReturnsErrorMessage() {
        String commandString = APP_ECHO + CHAR_SPACE + ECHO_LINE + SEMICOLON + SEMICOLON;
        try {
            shell.parseAndEvaluate(commandString, System.out);
        } catch (Exception e) {
            assertEquals("shell: " + ERR_SYNTAX, e.getMessage());
        }
    }

    @Test
    public void echo_MultipleSemicolons_OutputsContent() throws Exception {
        String commandString = APP_ECHO + CHAR_SPACE + "1" + CHAR_SEMICOLON + APP_ECHO + CHAR_SPACE + "2" + CHAR_SEMICOLON + APP_ECHO + CHAR_SPACE + "3" + CHAR_SEMICOLON + APP_ECHO + CHAR_SPACE + "4";
        String actualOutput = tapSystemOut(() -> shell.parseAndEvaluate(commandString, System.out));
        assertEquals("1" + STRING_NEWLINE + "2" + STRING_NEWLINE + "3" + STRING_NEWLINE  + "4" + STRING_NEWLINE , actualOutput);
    }
}
