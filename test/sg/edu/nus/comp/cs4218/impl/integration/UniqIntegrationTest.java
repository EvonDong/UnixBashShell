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
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.UniqException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.UniqApplication;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;

public class UniqIntegrationTest {
    public static final String TEMP = "temp";
    public static final Path TEMP_PATH = Paths.get(Environment.currentDirectory, TEMP);
    public static final String LINE1 = "I love CS4218";
    public static final String LINE2 = "Alice";
    public static final String LINE3 = "Bob";
    private static final String STRING_A = LINE1 + STRING_NEWLINE + LINE1 + STRING_NEWLINE + LINE2 + STRING_NEWLINE + LINE2 + STRING_NEWLINE + LINE3 + STRING_NEWLINE + LINE2 + STRING_NEWLINE + LINE3;
//    private static final String STRING_B = "Testing is so fun" + STRING_NEWLINE;
    public static final byte[] BYTES_A = STRING_A.getBytes();
    public static final byte[] BYTES_B = ("Testing is so fun" + STRING_NEWLINE).getBytes();
    public static final String UNIQ_LITERAL = "uniq ";
    public static Deque<Path> files = new ArrayDeque<>();
    private static final String FILE_ONE = "testFile";
    private static final String FILE_TWO = "testFile2";
    private static final String OUTPUT_FILE = "output";
    private static final String UNIQNOFLAGOUTPUT = "I love CS4218" + STRING_NEWLINE +
            "Alice" + STRING_NEWLINE +
            "Bob" + STRING_NEWLINE +
            "Alice" + STRING_NEWLINE +
            "Bob";

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


    private Path createDirectory(String folder) throws IOException {
        Path path = TEMP_PATH.resolve(folder);
        Files.createDirectory(path);
        files.push(path);
        return path;
    }

    private Path getRelativePath(String name) {
        return Paths.get(TEMP, name);
    }

    // POSITIVE TEST CASES
    @Test
    void testQuoting_DoubleQuotesUniqNoFlagsFileInput_DisplaysUniqResult() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = UNIQ_LITERAL + CHAR_SPACE + CHAR_DOUBLE_QUOTE + getRelativePath(FILE_ONE) + CHAR_DOUBLE_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((UNIQNOFLAGOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testQuoting_SingleQuotesUniqNoFlagsFileInput_DisplaysUniqResult() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = UNIQ_LITERAL + CHAR_SPACE + CHAR_SINGLE_QUOTE + getRelativePath(FILE_ONE) + CHAR_SINGLE_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((UNIQNOFLAGOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testRedirInput_UniqNoFlagsRedirInput_DisplaysUniqResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = UNIQ_LITERAL + CHAR_SPACE + CHAR_REDIR_INPUT + CHAR_SPACE + getRelativePath(FILE_ONE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((UNIQNOFLAGOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testRedirOutput_UniqNoFlagsFileInputRedirOutput_DisplaysUniqResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = UNIQ_LITERAL + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + CHAR_REDIR_OUTPUT + CHAR_SPACE + getRelativePath(OUTPUT_FILE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals("", output.toString());
        assertArrayEquals((UNIQNOFLAGOUTPUT + STRING_NEWLINE).getBytes(), Files.readAllBytes(getRelativePath(OUTPUT_FILE)));
    }

    @Test
    void testRedirInputOutput_UniqNoFlags_DisplaysUniqResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = UNIQ_LITERAL + CHAR_SPACE + CHAR_REDIR_INPUT + getRelativePath(FILE_ONE) + CHAR_SPACE + CHAR_REDIR_OUTPUT + CHAR_SPACE + getRelativePath(OUTPUT_FILE);
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertEquals("", output.toString());
        assertArrayEquals((UNIQNOFLAGOUTPUT + STRING_NEWLINE).getBytes(), Files.readAllBytes(getRelativePath(OUTPUT_FILE)));
    }

    @Test
    void testSequence_UniqEcho_DisplaysUniqEchoResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = UNIQ_LITERAL + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SEMICOLON + APP_ECHO + CHAR_SPACE + "sequence test";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((UNIQNOFLAGOUTPUT + STRING_NEWLINE + "sequence test" + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

        @Test
    void testCommandSubstitution_UniqEcho_DisplaysResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = UNIQ_LITERAL + CHAR_SPACE + CHAR_BACK_QUOTE + APP_ECHO + CHAR_SPACE + TEMP + "/" + FILE_ONE + CHAR_BACK_QUOTE;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((UNIQNOFLAGOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testGlobbing_UniqNoFlagsFileInput_DisplaysUniqResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = UNIQ_LITERAL + TEMP + "/" + FILE_ONE + "*";
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((UNIQNOFLAGOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    @Test
    void testPipe_UniqCat_DisplaysUniqCatResults() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = UNIQ_LITERAL + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SPACE + CHAR_PIPE + CHAR_SPACE + APP_CAT;
        new ShellImpl().parseAndEvaluate(commandString, output);
        assertArrayEquals((UNIQNOFLAGOUTPUT + STRING_NEWLINE).getBytes(), output.toByteArray());
    }

    // Negative integration
    @Test
    void testQuoting_FilesBunchedInQuotes_ShowsErrorMessage() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = UNIQ_LITERAL + CHAR_SPACE + CHAR_DOUBLE_QUOTE + getRelativePath(FILE_ONE) + CHAR_SPACE + getRelativePath(OUTPUT_FILE) + CHAR_DOUBLE_QUOTE;
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (UniqException ue) {
            assertArrayEquals(("uniq: " + getRelativePath(FILE_ONE).toString() + CHAR_SPACE + getRelativePath(OUTPUT_FILE).toString() + ": " + ERR_FILE_NOT_FOUND).getBytes(), ue.getMessage().getBytes());
        }
    }

    @Test
    void testSequence_SemiColonAfterSingleUniq_ShowsErrorMessage() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        String commandString = UNIQ_LITERAL + CHAR_SPACE + getRelativePath(FILE_ONE) + CHAR_SEMICOLON;
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            assertArrayEquals((new ShellException(ERR_SYNTAX).getMessage()).getBytes(), se.getMessage().getBytes());
        }
    }

    @Test
    public void testRedir_UniqWithMultipleFilesForInputRedirection_ThrowsShellException() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = UNIQ_LITERAL + CHAR_REDIR_INPUT + CHAR_SPACE + FILE_ONE + CHAR_SPACE + FILE_TWO;
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }

    @Test
    public void testRedir_UniqWithMultipleInputRedirection_ThrowsShellException() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = UNIQ_LITERAL + CHAR_REDIR_INPUT + CHAR_SPACE + FILE_ONE + CHAR_SPACE + CHAR_REDIR_INPUT + CHAR_SPACE + FILE_TWO;
        assertThrows(ShellException.class, () -> new ShellImpl().parseAndEvaluate(commandString, output));
    }

        @Test
    void testGlobbingRedir_UniqNoFlagsGlobReturns2FilesInputRedir_DisplaysIORedirErrorMessage() throws IOException, AbstractApplicationException, ShellException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Files.write(createFile(FILE_ONE), BYTES_A);
        Files.write(createFile(FILE_TWO), BYTES_B);
        String commandString = UNIQ_LITERAL + CHAR_REDIR_INPUT + CHAR_SPACE + (TEMP + "/" + FILE_ONE + "*");
        try {
            new ShellImpl().parseAndEvaluate(commandString, output);
        } catch (ShellException se) {
            assertArrayEquals(new ShellException(ERR_SYNTAX).getMessage().getBytes(), se.getMessage().getBytes());
        }
    }

}