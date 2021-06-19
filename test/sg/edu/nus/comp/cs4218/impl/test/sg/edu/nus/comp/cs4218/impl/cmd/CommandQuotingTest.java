package sg.edu.nus.comp.cs4218.impl.cmd;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

/**
 * Single Quote disables interpretation of all special symbols
 * Double quote disables interpretation of all special symbols except for backquotes
 * Back quote allows for command substitution
 * <p>
 * Special Symbols: \t * ' " ` | < > ; WHITESPACE
 *
 * <quoted> ::= <single-quoted> | <double-quoted> |
 * <backquoted>
 * <single-quoted> ::= “‘” <non-newline and non-single-quote> “‘”
 * <backquoted> ::= “`” <non-newline and non-backquote> “`”
 * <double-quoted> ::= ““”(<backquoted> | <double-quote-content>)*”””
 * where <double-quote-content> can contain any character except for newlines,
 * double quotes and backquotes.
 * <p>
 * Test Unclosed quotes
 * - Single
 * - Double
 * - Back
 * - Back in Double
 * <p>
 * Test Closed quotes
 * <p>
 * Test single quotes with special symbols
 * Test double quotes with special symbols
 * <p>
 * Test filename with spaces
 * <p>
 * Test whitespace in quotes
 * <p>
 * Test double quotes in single quotes
 * Test back quotes in single quotes
 * <p>
 * Test single quotes in double quotes
 * Test back quotes in double quotes command substitution
 * <p>
 * Test single quotes in back quotes
 * Test double quotes in back quotes
 */
class CommandQuotingTest {

    Shell shellApp;
    private OutputStream outputStream;
    private static final String QUOTING_TEST_DIR = "testAssets" + CHAR_FILE_SEP + "quoting";
    private static final String CWD = Environment.currentDirectory;

    @BeforeAll
    static void setUpOnce() {
        Environment.currentDirectory += CHAR_FILE_SEP + QUOTING_TEST_DIR;
    }

    @AfterAll
    static void tearDownOnce() {
        Environment.currentDirectory = CWD;
    }

    @BeforeEach
    void setUp() {
        shellApp = new ShellImpl();
        outputStream = new ByteArrayOutputStream();
    }

    @AfterEach
    void tearDown() {
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void parseAndEvaluate_UncloseBackQuotesInDoubleQuotes_ReturnShellException() {
        String commandString = "echo \"Not` closed\" ";
        ShellException thrownException = assertThrows(ShellException.class, () -> parseAndEvaluateWrapper(commandString));
        ShellException expectedResult = new ShellException(ERR_SYNTAX);
        assertEquals(expectedResult.toString(), thrownException.toString());
    }


    private String parseAndEvaluateWrapper(String commandString) throws AbstractApplicationException, ShellException, FileNotFoundException {
        shellApp.parseAndEvaluate(commandString, outputStream);
        return outputStream.toString();
    }

}