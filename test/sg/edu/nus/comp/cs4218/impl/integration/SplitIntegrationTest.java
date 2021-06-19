package sg.edu.nus.comp.cs4218.impl.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner.*;
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
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.SplitException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class SplitIntegrationTest {
    public static final String TEMP = "temp-split";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    private static final String STRING_A = "I really like" + STRING_NEWLINE + "CS4218. LikE, sEriously" + STRING_NEWLINE;
    private static final String STRING_B = "Testing is so fun" + STRING_NEWLINE;
    public static final byte[] BYTES_A = ("I really like" + STRING_NEWLINE + "CS4218. LikE, sEriously" + STRING_NEWLINE).getBytes();
    public static final byte[] BYTES_B = ("Testing is so fun" + STRING_NEWLINE).getBytes();
    public static final String SPLIT_LITERAL = "split ";
    public static Deque<Path> files = new ArrayDeque<>();
    private static final String FILE_ONE = "testFile";
    private static final String FILE_TWO = "testFile2";
    private static final Integer LINES_FACTOR = 1;
    private static final String OUTPUT_FILE_XAA = "xaa";
    private static final String OUTPUT_FILE_XAB = "xab";
    private static final String OUTPUT_FILE = "split-output";
    private static final String OUTPUT_FILE_XAC = "xac";
    private static final String LINE1 = "I really like";
    private static final String LINE2 = "CS4218. LikE, sEriously";

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
        Files.deleteIfExists(getRelativePath(OUTPUT_FILE_XAA));
        Files.deleteIfExists(getRelativePath(OUTPUT_FILE_XAB));
        Files.deleteIfExists(getRelativePath(OUTPUT_FILE));
    }

    @AfterAll
    static void terminate() throws IOException{
        Files.deleteIfExists(Paths.get(FILE_ONE));
        Files.deleteIfExists(Paths.get(FILE_TWO));
        Files.deleteIfExists(Paths.get(OUTPUT_FILE_XAA));
        Files.deleteIfExists(Paths.get(OUTPUT_FILE_XAB));
        Files.deleteIfExists(Paths.get(OUTPUT_FILE_XAC));
        Files.deleteIfExists(Paths.get(OUTPUT_FILE));
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
    void testQuoting_DoubleQuotesSplitNoFlagsFileInput_SplitsFile() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = SPLIT_LITERAL + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + LINES_FACTOR + CHAR_SPACE + CHAR_DOUBLE_QUOTE + getRelativePath(FILE_ONE) + CHAR_DOUBLE_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(LINE1.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
        assertArrayEquals(LINE2.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAB)));
        assertArrayEquals("".getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAC)));
    }

    @Test
    void testQuoting_SingleQuotesSplitNoFlagsFileInput_SplitsFile() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = SPLIT_LITERAL + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + LINES_FACTOR + CHAR_SPACE + CHAR_SINGLE_QUOTE + getRelativePath(FILE_ONE) + CHAR_SINGLE_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(LINE1.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
        assertArrayEquals(LINE2.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAB)));
        assertArrayEquals("".getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAC)));
    }

    @Test
    void testRedirInput_SplitNoFlagsRedirInput_SplitsFile() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = SPLIT_LITERAL + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + LINES_FACTOR + CHAR_SPACE + CHAR_REDIR_INPUT + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(LINE1.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
        assertArrayEquals(LINE2.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAB)));
        assertArrayEquals("".getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAC)));
    }

    @Test
    void testRedirOutput_SplitNoFlagsFileInputRedirOutput_SplitsFile() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = SPLIT_LITERAL + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + LINES_FACTOR + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + CHAR_REDIR_OUTPUT + CHAR_SPACE + getRelativePath(OUTPUT_FILE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(LINE1.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
        assertArrayEquals(LINE2.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAB)));
        assertArrayEquals("".getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAC)));
    }

    @Test
    void testRedirInputOutput_SplitNoFlags_SplitsFile() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = SPLIT_LITERAL + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + LINES_FACTOR + CHAR_SPACE + CHAR_REDIR_INPUT + getRelativePath(FILE_ONE) + CHAR_SPACE + CHAR_REDIR_OUTPUT + CHAR_SPACE + getRelativePath(OUTPUT_FILE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(LINE1.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
        assertArrayEquals(LINE2.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAB)));
        assertArrayEquals("".getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAC)));
    }

    @Test
    void testSequence_SplitEcho_SplitsFileAndDisplaysEchoResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = SPLIT_LITERAL + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + LINES_FACTOR + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SEMICOLON + APP_ECHO + CHAR_SPACE + "sequence test";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(("sequence test" + STRING_NEWLINE).getBytes(), output.toByteArray());
        assertArrayEquals(LINE1.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
        assertArrayEquals(LINE2.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAB)));
        assertArrayEquals("".getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAC)));
    }

    @Test
    void testCommandSubstitution_GrepEcho_DisplaysResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = SPLIT_LITERAL + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + LINES_FACTOR + CHAR_SPACE + CHAR_BACK_QUOTE + APP_ECHO + CHAR_SPACE + TEMP + CHAR_FILE_SEP + FILE_ONE + CHAR_BACK_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(LINE1.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
        assertArrayEquals(LINE2.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAB)));
        assertArrayEquals("".getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAC)));
    }

    @Test
    void testGlobbing_SplitNoFlagsFileInput_DisplaysSplitResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = SPLIT_LITERAL + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + LINES_FACTOR + CHAR_SPACE + TEMP + "/" + FILE_ONE + "*";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(LINE1.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
        assertArrayEquals(LINE2.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAB)));
        assertArrayEquals("".getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAC)));
    }

    @Test
    void testPipe_CatSplit_SplitFile() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = APP_CAT + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_PIPE + CHAR_SPACE + SPLIT_LITERAL + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + LINES_FACTOR;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals(LINE1.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAA)));
        assertArrayEquals(LINE2.getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAB)));
        assertArrayEquals("".getBytes(), Files.readAllBytes(Paths.get(OUTPUT_FILE_XAC)));
    }

    // Negative integration
    @Test
    void testQuoting_FilesBunchedInQuotes_ShowsErrorMessage() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = SPLIT_LITERAL + CHAR_SPACE + CHAR_FLAG_PREFIX + "l" + CHAR_SPACE + LINES_FACTOR + CHAR_SPACE + CHAR_DOUBLE_QUOTE + getRelativePath(FILE_ONE) + CHAR_SPACE + getRelativePath(FILE_TWO) + CHAR_DOUBLE_QUOTE;
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (SplitException se) {
            assertArrayEquals(("split: " + getRelativePath(FILE_ONE).toString() + CHAR_SPACE + getRelativePath(FILE_TWO).toString() + ": " + ERR_FILE_NOT_FOUND).getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    void testSequence_SemiColonAfterSingleSplit_ShowsErrorMessage() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = SPLIT_LITERAL + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SEMICOLON;
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            assertArrayEquals((new ShellException(ERR_SYNTAX).getMessage()).getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    public void testRedir_SplitWithMultipleFilesForInputRedirection_ThrowsShellException() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = SPLIT_LITERAL + CHAR_REDIR_INPUT + CHAR_SPACE + FILE_ONE + CHAR_SPACE + FILE_TWO;
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }

    @Test
    public void testRedir_SplitWithMultipleFilesForOutputRedirection_ThrowsShellException() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = SPLIT_LITERAL + CHAR_REDIR_OUTPUT + CHAR_SPACE + FILE_ONE + CHAR_SPACE + FILE_TWO;
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }

    @Test
    public void testRedir_SplitWithMultipleInputRedirection_ThrowsShellException() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = SPLIT_LITERAL + CHAR_REDIR_INPUT + CHAR_SPACE + FILE_ONE + CHAR_SPACE + CHAR_REDIR_OUTPUT + CHAR_SPACE + FILE_TWO + CHAR_REDIR_OUTPUT + CHAR_SPACE + OUTPUT_FILE;
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }


    @Test
    void testGlobbingRedir_SplitNoFlagsGlobReturns2FilesInputRedir_DisplaysIORedirErrorMessage() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = SPLIT_LITERAL + CHAR_REDIR_INPUT + CHAR_SPACE + (TEMP + "/" + FILE_ONE + "*");
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            assertArrayEquals(new ShellException(ERR_SYNTAX).getMessage().getBytes(), se.getMessage().getBytes());
        }
    }

}