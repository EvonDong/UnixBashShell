package sg.edu.nus.comp.cs4218.impl.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.*;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.APP_GREP;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.junit.jupiter.api.*;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;

public class GrepIntegrationTest {
    public static final String TEMP = "temp";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    private static final String STRING_A = "I really like" + STRING_NEWLINE + "CS4218. LikE, sEriously" + STRING_NEWLINE;
    private static final String STRING_B = "Testing is so fun" + STRING_NEWLINE;
    public static final byte[] BYTES_A = ("I really like" + STRING_NEWLINE + "CS4218. LikE, sEriously" + STRING_NEWLINE).getBytes();
    public static final byte[] BYTES_B = ("Testing is so fun" + STRING_NEWLINE).getBytes();
    public static final String GREP_LITERAL = "grep ";
    public static Deque<Path> files = new ArrayDeque<>();
    private static final String FILE_ONE = "testFile";
    private static final String FILE_TWO = "testFile2";
    private static final String OUTPUT_FILE = "output";
    private static final String NOFLAGSOUTPUT = "I really like";

    @BeforeAll
    static void createTemp() throws IOException {
        Files.createDirectory(TEMP_PATH);
    }

    @AfterAll
    static void deleteTemp() throws IOException {
        for (Path file : files) {
            Files.deleteIfExists(file);
        }
        Files.delete(TEMP_PATH);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(getRelativePath(FILE_ONE));
        Files.deleteIfExists(getRelativePath(FILE_TWO));
        Files.deleteIfExists(getRelativePath(OUTPUT_FILE));
    }

    private Path createFile(String name) throws IOException {
        Path path = TEMP_PATH.resolve(name);
        Files.createFile(path);
        files.push(path);
        return path;
    }

    private Path getRelativePath(String name) {
        return Paths.get(TEMP, name);
    }

    // POSITIVE TEST CASES
    @Test
    void testQuoting_DoubleQuotesGrepNoFlagsFileInput_DisplaysGrepResult() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = GREP_LITERAL + "\"e\"" + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((NOFLAGSOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testQuoting_SingleQuotesGrepNoFlagsFileInput_DisplaysGrepResult() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = GREP_LITERAL + "'e'" + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((NOFLAGSOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testRedirInput_GrepNoFlagsRedirInput_DisplaysGrepResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = GREP_LITERAL + "e" + CHAR_SPACE + CHAR_REDIR_INPUT + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((NOFLAGSOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testRedirOutput_GrepNoFlagsFileInputRedirOutput_DisplaysGrepResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        createFile(OUTPUT_FILE);
        String commandString = GREP_LITERAL + "e" + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + CHAR_REDIR_OUTPUT + CHAR_SPACE + getRelativePath(OUTPUT_FILE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals("", output.toString());
        assertEquals(NOFLAGSOUTPUT + STRING_NEWLINE, Files.readString(Paths.get(getRelativePath(OUTPUT_FILE).toString()), StandardCharsets.US_ASCII));
    }

    @Test
    void testRedirInputOutput_GrepNoFlags_DisplaysGrepResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        createFile(OUTPUT_FILE);
        String commandString = GREP_LITERAL + "e" + CHAR_SPACE + CHAR_REDIR_INPUT + getRelativePath(FILE_ONE) + CHAR_SPACE + CHAR_REDIR_OUTPUT + CHAR_SPACE + getRelativePath(OUTPUT_FILE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals("", output.toString());
        assertEquals(NOFLAGSOUTPUT + STRING_NEWLINE, Files.readString(Paths.get(getRelativePath(OUTPUT_FILE).toString()), StandardCharsets.US_ASCII));
    }

    @Test
    void testSequence_GrepEcho_DisplaysGrepEchoResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = GREP_LITERAL + "e" + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SEMICOLON + APP_ECHO + CHAR_SPACE + "sequence test";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((NOFLAGSOUTPUT + STRING_NEWLINE + "sequence test" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testCommandSubstitution_GrepEcho_DisplaysResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = GREP_LITERAL + "e" + CHAR_SPACE + CHAR_BACK_QUOTE + APP_ECHO + CHAR_SPACE + TEMP + CHAR_FILE_SEP + FILE_ONE + CHAR_BACK_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((NOFLAGSOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testGlobbing_GrepNoFlagsFileInput_DisplaysGrepResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = GREP_LITERAL + "e" + CHAR_SPACE + TEMP + "/" + FILE_ONE + "*";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((NOFLAGSOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testPipe_GrepCat_DisplaysGrepCatResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = GREP_LITERAL + "e" + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + CHAR_PIPE + CHAR_SPACE + APP_CAT;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((NOFLAGSOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    // Negative integration
    @Test
    void testQuoting_GrepFilesBunchedInQuotes_ShowsErrorMessage() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = GREP_LITERAL + "e" + CHAR_SPACE + CHAR_DOUBLE_QUOTE + getRelativePath(FILE_ONE) + CHAR_SPACE + getRelativePath(FILE_TWO) + CHAR_DOUBLE_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((getRelativePath(FILE_ONE).toString() + CHAR_SPACE + getRelativePath(FILE_TWO).toString() + ": " + ERR_FILE_NOT_FOUND + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testSequence_SemiColonAfterSingleGrep_ShowsErrorMessage() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = GREP_LITERAL + "e" + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SEMICOLON;
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            assertArrayEquals((new ShellException(ERR_SYNTAX)).getMessage().getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    public void testRedir_GrepWithMultipleFilesForInputRedirection_ThrowsShellException() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = APP_GREP + " e " + CHAR_REDIR_INPUT + CHAR_SPACE + FILE_ONE + CHAR_SPACE + FILE_TWO;
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }

    @Test
    public void testRedir_GrepWithMultipleInputRedirection_ThrowsShellException() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = APP_GREP + " e " + CHAR_REDIR_INPUT + CHAR_SPACE + FILE_ONE + CHAR_SPACE + CHAR_REDIR_INPUT + CHAR_SPACE + FILE_TWO;
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }

        @Test
    void testGlobbingRedir_GrepNoFlagsGlobReturns2FilesInputRedir_DisplaysIORedirErrorMessage() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = GREP_LITERAL + "e" + CHAR_SPACE + CHAR_REDIR_INPUT + CHAR_SPACE + TEMP + "/" + FILE_ONE + "*";
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            assertArrayEquals(new ShellException(ERR_SYNTAX).getMessage().getBytes(), se.getMessage().getBytes());
        }
    }
}